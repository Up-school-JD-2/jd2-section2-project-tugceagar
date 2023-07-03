import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PhoneManagement pm = new PhoneManagement();
		FileManagement fileManagement = new FileManagement();
		Phone phone = null;
		boolean quit = false;
		boolean phoneStatus = false;

		do {
			boolean quit2 = false;
			System.out.println("1- Add Phone:");
			System.out.println("2- Application Management");
			System.out.println("3- Contact Management:");
			System.out.println("4- Storage Space Control:");
			System.out.println("5- Data Backup and Restore:");
			System.out.println("6- Exit");

			int choice = sc.nextInt();
			switch (choice) {
			case 1 -> {
				sc.nextLine();
				pm.appMap.clear();
				pm.personMap.clear();
				System.out.println("Please enter the brand information of the phone:");
				String brand = sc.nextLine();
				System.out.println("Please enter the model information of the phone:");
				String model = sc.nextLine();
				System.out.println("Please enter the serial number information of the phone:");
				String serialNumber = sc.nextLine();
				System.out.println("Please enter the storage area information of the phone:");
				Double storageArea = sc.nextDouble();
				System.out.println("Please enter the operating system information of the phone:");
				sc.nextLine();
				String os = sc.nextLine();

				phone = new Phone(brand, model, serialNumber, storageArea, os);
				phone.printInfo();
				phoneStatus = true;
				
			}

			case 2 -> {
				do {
					if (phoneStatus == true) {
						System.out.println("1- Add Application:");
						System.out.println("2- Delete Application");
						System.out.println("3- Update Application:");
						System.out.println("4- View Applications:");
						System.out.println("5- Go to Main Menu:");
						System.out.println("6- Exit");
						int choice2 = sc.nextInt();
						switch (choice2) {
						case 1 -> {
							sc.nextLine();
							System.out.println("Application name:");
							String appName = sc.nextLine();
							System.out.println("Application version");
							Double appVersion = sc.nextDouble();
							System.out.println("Application size");
							Double appSize = sc.nextDouble();
							Application app = new Application(appName, appVersion, appSize);
							pm.addApplication(appName, app);
						}
						case 2 -> {
							sc.nextLine();
							System.out.println("Please enter the name of the application you want to delete");
							String appName = sc.nextLine();
							try {
								pm.removeApplication(appName);

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						case 3 -> {
							sc.nextLine();
							System.out.println("Please enter the name of the application you want to update");
							String appName = sc.nextLine();
							System.out.println("What is the updated version of the application you want to update?");
							Double appVersion = sc.nextDouble();
							System.out.println("What is the updated size of the application you want to update?");
							Double appSize = sc.nextDouble();
							pm.updateApplication(appName, appVersion, appSize);

						}
						case 4 -> {
							pm.appMap.entrySet().forEach(System.out::println);
						}
						case 5 -> {
							quit2 = true;
						}
						case 6 -> {
							quit = true;
						}
						default -> {
							throw new IllegalArgumentException("Unexpected value: " + choice2);
						}
						}
					} else {
						System.out.println("First you need the add phone.");
						break;
					}
				} while (quit2 != true && quit != true);
			}
			case 3 -> {
				do {

					System.out.println("1- Add Person:");
					System.out.println("2- Delete Person:");
					System.out.println("3- Edit Person:");
					System.out.println("4- Search Person:");
					System.out.println("5- View People:");
					System.out.println("6- Go to Main Menu:");
					System.out.println("7- Exit");
					int choice3 = sc.nextInt();
					switch (choice3) {
					case 1 -> {
						sc.nextLine();
						System.out.println("Please enter the name of the person you want to add:");
						String personName = sc.nextLine();
						System.out.println("Please enter the surname of the person you want to add:");
						String personLastName = sc.nextLine();
						System.out.println("Please enter the phone number of the person you want to add:");
						String phoneNumber = sc.nextLine();
						System.out.println("Please enter the email of the person you want to add:");
						String email = sc.nextLine();
						Person person = new Person(personName, personLastName, phoneNumber, email);
						pm.addPerson(personName, person);
						System.out.println(person);
					}
					case 2 -> {
						sc.nextLine();
						System.out.println("Please enter the name of the person you want to delete:");
						String personName = sc.nextLine();
						pm.removePerson(personName);
					}

					case 3 -> {
						sc.nextLine();
						System.out.println("Please enter the name of the person you want to update:");
						String personName = sc.nextLine();

						System.out.println("Which field do you want to update? (Name/Surname/Phone Number/Email)");
						String updateField = sc.nextLine();

						System.out.println("Please enter the new value:");
						String updatedValue = sc.nextLine();

						try {
							pm.updatePerson(personName, (person, value) -> {
								switch (updateField.toLowerCase()) {
								case "name" -> person.setName(updatedValue);
								case "surname" -> person.setSurname(updatedValue);
								case "phone number" -> person.setPhoneNumber(updatedValue);
								case "email" -> person.setEmail(updatedValue);
								default -> throw new IllegalArgumentException("Invalid update field.");
								}
							});
						} catch (PhoneManagement.PersonNotFoundException | IllegalArgumentException e) {
							System.out.println(e.getMessage());
						}
					}
					case 4 -> {
						sc.nextLine();
						System.out.println("Please enter the name of the person you want to search for:");
						String personName = sc.nextLine();
						try {
							var personInfo = pm.findPerson(personName);
							System.out.println(personInfo);

						} catch (PhoneManagement.PersonNotFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					case 5 -> {
						pm.personMap.entrySet().forEach(System.out::println);
					}
					case 6 -> {
						quit2 = true;
					}
					case 7 -> {
						quit = true;
					}
					}
				} while (quit2 != true && quit != true);
			}
			case 4 -> {
				try {
					if (phone == null) {
						throw new PhoneCantFoundException("The phone was not added.");
					}
					System.out.println("The total storage space:" + phone.getStorageArea());
					System.out.println("The remaining storage space: " + pm.storageAreaControl(phone));
				} catch (PhoneCantFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			case 5 -> {
				do {
					sc.nextLine();
					System.out.println("1- Backup Data");
					System.out.println("2- Restore Data");
					System.out.println("3- Go to Main Menu");
					System.out.println("4- Exit");
					int choice4 = sc.nextInt();

					switch (choice4) {
					case 1 -> {
						fileManagement.backup(pm.getAppMap(), pm.getPersonMap());
						System.out.println("The data has been backed up.");
					}
					case 2 -> {
						pm = fileManagement.restore();
						System.out.println("The data has been restored.");
					}
					case 3 -> {
						quit2 = true;
					}
					case 4 -> {
						quit = true;
					}
					}
				} while (quit2 != true && quit != true);
			}
			case 6 -> {
				quit = true;
			}

			default -> {
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			}
		} while (quit != true);
	}

}

class PhoneCantFoundException extends Exception {
	public PhoneCantFoundException(String message) {
		super(message);
	}
}
