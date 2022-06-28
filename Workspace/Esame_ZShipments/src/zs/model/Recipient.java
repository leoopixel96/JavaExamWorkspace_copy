package zs.model;

public class Recipient {
	private String name;
	private String streetAndNumber;
	private int zipCode;
	private String city;
	
	public Recipient(String name, String streetAndNumber, int zipCode, String city) {
		super();
		this.name = name;
		this.streetAndNumber = streetAndNumber;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}
	
	@Override
	public String toString() {
		return name + "; " + streetAndNumber + "; " + zipCode + "; " + city;
	}
}
