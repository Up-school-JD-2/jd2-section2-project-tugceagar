import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileManagement {
	PhoneManagement pm;

	public void backup(Map<String, Application> appMap, Map<String, Person> personMap) {
		String backupFile = "phoneInfo.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
			writer.write("Applications:\n\n");
			appMap.entrySet().stream().forEach(entry -> {
				String appName = entry.getKey();
				Application appInfo = entry.getValue();
				try {
					writer.write(appName + ": " + appInfo.toString());
					writer.newLine();
				} catch (IOException e) {
					System.err.println("Write error: " + e.getMessage());
				}
			});
			writer.write("\nPersons:\n\n");
			personMap.entrySet().stream().forEach(entry -> {
				String personName = entry.getKey();
				Person personInfo = entry.getValue();
				try {
					writer.write(personName + ": " + personInfo.toString());
					writer.newLine();
				} catch (IOException e) {
					System.err.println("Write error: " + e.getMessage());
				}
			});

		} catch (IOException e) {
			System.err.println("File operation error: " + e.getMessage());
		}
	}

	public PhoneManagement restore() {
		String backupFile = "phoneInfo.txt";
		PhoneManagement pm = new PhoneManagement();
		try (BufferedReader reader = new BufferedReader(new FileReader(backupFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals("Applications:")) {
					pm.setAppMap(restoreApplications(reader));
				} else if (line.equals("Persons:")) {
					pm.setPersonMap(restorePersons(reader));
				}
			}
		} catch (IOException e) {
			System.err.println("File operation error: " + e.getMessage());
		}
		return pm;
	}

	private Map<String, Application> restoreApplications(BufferedReader reader) throws IOException {
		String line;
		Map<String, Application> appMap = new HashMap<>();
		line = reader.readLine();
		while ((line = reader.readLine()) != null && !line.isEmpty()) {
			String[] parts = line.split(": ");
			String appName = parts[0];
			String appInfo = parts[1];
			Application application = Application.fromString(appInfo);
			appMap.put(appName, application);
		}
		return appMap;
	}

	private Map<String, Person> restorePersons(BufferedReader reader) throws IOException {
		String line;
		Map<String, Person> personMap = new HashMap<>();
		line = reader.readLine();
		while ((line = reader.readLine()) != null && !line.isEmpty()) {
			String[] parts = line.split(": ");
			String personName = parts[0];
			String personInfo = parts[1];
			Person person = Person.fromString(personInfo);
			personMap.put(personName, person);
		}
		return personMap;
	}

}