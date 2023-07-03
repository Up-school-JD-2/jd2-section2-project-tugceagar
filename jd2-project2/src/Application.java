
public class Application {

	private String name;
	private double version;
	private double size;

	public Application(String name, double version, double size) {
		this.name = name;
		this.version = version;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Application name=" + name + ", version=" + version + ", size=" + size;
	}

	public static Application fromString(String appInfo) {
		String[] parts = appInfo.split(", ");

		String name = parts[0].substring(parts[0].indexOf("=") + 1);
		double version = Double.parseDouble(parts[1].substring(parts[1].indexOf("=") + 1));
		double size = Double.parseDouble(parts[2].substring(parts[2].indexOf("=") + 1));

		return new Application(name, version, size);
	}
}
