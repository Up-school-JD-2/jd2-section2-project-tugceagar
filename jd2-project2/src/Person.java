
public class Person {

	private String name;
	private String surname;
	private String phoneNumber;
	private String email;

	public Person(String name, String surname, String phoneNumber, String email) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", email=" + email;
	}

	public static Person fromString(String personInfo) {
		String[] parts = personInfo.split(", ");

		String name = parts[0].substring(parts[0].indexOf("=") + 1);
		String surname = parts[1].substring(parts[1].indexOf("=") + 1);
		String phoneNumber = parts[2].substring(parts[2].indexOf("=") + 1);
		String email = parts[3].substring(parts[3].indexOf("=") + 1);

		return new Person(name, surname, phoneNumber, email);
	}

}
