package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Building {

	private StringProperty country;
	private StringProperty street;
	private StringProperty city;
	private StringProperty hnumber;

	public Building(String country, String street, String city, String hnumber) {
		this.country=new SimpleStringProperty(country);
		this.street=new SimpleStringProperty(street);
		this.city=new SimpleStringProperty(city);
		this.hnumber=new SimpleStringProperty(hnumber);


	}


	public String getCountry() {
		return country.get();
	}

	public void setCountry(String country) {
		this.country.set(country);
	}

	public StringProperty countryProperty(){
		return country;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty(){
		return street;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty(){
		return city;
	}

	public String getHnumber() {
		return hnumber.get();
	}

	public void setHnumber(String hnumber) {
		this.hnumber.set(hnumber);
	}
	public StringProperty hnumberProperty(){
		return hnumber;
	}



}
