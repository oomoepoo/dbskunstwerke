package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse {
	private final IntegerProperty aID;
	private StringProperty country;
	private StringProperty city;
	private StringProperty street;
	private StringProperty hnumber;

	public Adresse(int aID, String country, String city, String street,
			String hnumber) {
		super();
		this.aID = new SimpleIntegerProperty(aID);
		this.country = new SimpleStringProperty(country);
		this.city = new SimpleStringProperty(city);
		this.street = new SimpleStringProperty(street);
		this.hnumber = new SimpleStringProperty(hnumber);
	}

	public Adresse() {
		this.aID = new SimpleIntegerProperty(0);
		this.country = new SimpleStringProperty(null);
		this.city = new SimpleStringProperty(null);
		this.street = new SimpleStringProperty(null);
		this.hnumber = new SimpleStringProperty(null);
	}

	public Integer getaID() {
		return aID.get();
	}

	public IntegerProperty aIDProperty(){
		return aID;
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

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty(){
		return city;
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

	public String getHnumber() {
		return hnumber.get();
	}

	public void setHnumber(String hnumber) {
		this.hnumber.set(hnumber);
	}

	public StringProperty hnumberProperty(){
		return hnumber;
	}

	public ArrayList<String> getEverything(){
		ArrayList<String> everything = new ArrayList<String>();
		String[] bla = {country.get(), city.get(), street.get(), hnumber.get()};
		for(String e : bla){
			everything.add(e);
		}
		return everything;

	}

}