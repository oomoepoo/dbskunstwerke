package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Museum extends Building {
	private final StringProperty mname;
	private final IntegerProperty ID;
	private ArrayList<OpeningTime> openingtimes;

	public Museum(String country, String street, String city, String hnumber, int id, String name) {
		super(country, street, city, hnumber);
		this.ID = new SimpleIntegerProperty(id);
		this.mname = new SimpleStringProperty(name);
	}

	public Iterator<OpeningTime> getOpeningtimes() {
		return openingtimes.iterator();
	}

	public void addOpeningtimes(OpeningTime openingtimes) {
		this.openingtimes.add(openingtimes);
	}

	public String getMname() {
		return mname.get();
	}

	public StringProperty getmnameProperty(){
		return mname;
	}

	public int getID() {
		return ID.get();
	}

}
