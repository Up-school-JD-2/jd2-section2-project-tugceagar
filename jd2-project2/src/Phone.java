import java.util.List;

public class Phone {

	private String brand;
	private String model;
	private String serialNumber;
	private Double storageArea;
	private String os;
	private int id;

	public Phone(String brand, String model, String serialNumber, Double storageArea, String os) {
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.storageArea = storageArea;
		this.os = os;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Double getStorageArea() {
		return storageArea;
	}

	public void setStorageArea(Double storageArea) {
		this.storageArea = storageArea;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Override
	public String toString() {
		return "Phone [make=" + brand + ", model=" + model + ", serialNumber=" + serialNumber + ", storageArea="
				+ storageArea + ", os=" + os + os + "]";
	}

	public void printInfo() {
		System.out.println("Phone [brand=" + brand + ", model=" + model + ", serialNumber=" + serialNumber
				+ ", storageArea=" + storageArea + ", os=" + os + "]");
	}
}
