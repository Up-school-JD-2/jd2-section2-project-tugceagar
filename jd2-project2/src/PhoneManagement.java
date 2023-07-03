import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class PhoneManagement {
	class PersonNotFoundException extends Exception {
		public PersonNotFoundException(String message) {
			super(message);
		}
	}

	class AppNotFoundException extends Exception {
		public AppNotFoundException(String message) {
			super(message);
		}
	}
	
	Map<String, Application> appMap = new HashMap<>();
	Map<String, Person> personMap = new HashMap<>();

	public Map<String, Application> getAppMap() {
		return appMap;
	}

	public void setAppMap(Map<String, Application> appMap) {
		this.appMap = appMap;
	}

	public Map<String, Person> getPersonMap() {
		return personMap;
	}

	public void setPersonMap(Map<String, Person> personMap) {
		this.personMap = personMap;
	}

	public double storageAreaControl(Phone phone) {
		double storageAreaControl = phone.getStorageArea()
				- appMap.entrySet().stream().mapToDouble(entrySet -> entrySet.getValue().getSize()).sum();
		return storageAreaControl;
	}

	public void addPerson(String personName, Person person) {
		personMap.put(personName, person);
	}

	public void removePerson(String personName) {
		try {
			if (!personMap.containsKey(personName)) {
				throw new PersonNotFoundException("Person not found.");
			}
			personMap.remove(personName);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Entry<String, Person>> findPerson(String personName) throws PhoneManagement.PersonNotFoundException {
		if (!personMap.containsKey(personName)) {
			throw new PersonNotFoundException("Person not found.");
		}

		return personMap.entrySet().stream().filter(x -> x.getKey().equals(personName)).toList();
	}

	public void updatePerson(String personName, BiConsumer<Person, String> updateFunction)
			throws PersonNotFoundException {
		if (!personMap.containsKey(personName)) {
			throw new PersonNotFoundException("Person not found.");
		}

		Person person = personMap.get(personName);
		updateFunction.accept(person, personName);
		System.out.println("Person successfully updated.");
	}

	public void addApplication(String appName, Application app) {
		appMap.put(appName, app);
	}

	public void removeApplication(String appName) {

		try {
			if (!appMap.containsKey(appName)) {
				throw new AppNotFoundException("Application not found.");
			}
			appMap.remove(appName);
			System.out.println("Application deleted.");
		} catch (AppNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateApplication(String appName, Double version, Double size) {

		try {
			if (!appMap.containsKey(appName)) {
				throw new AppNotFoundException("Application not found.");
			}
			appMap.get(appName).setVersion(version);
			appMap.get(appName).setSize(size);
		} catch (AppNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
