package application;

public abstract class Building {

	private String country;
	private String street;
	private String city;
	private String hnumber;

	public Building(String country, String street, String city, String hnumber) {
		this.country=country;
		this.street=street;
		this.city=city;
		this.hnumber=hnumber;


	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHnumber() {
		return hnumber;
	}

	public void setHnumber(String hnumber) {
		this.hnumber = hnumber;
	}



}
