package application;

import java.util.ArrayList;
import java.util.Iterator;

public class Museum extends Building {
	private final String mname;
	private final int ID;
	private ArrayList<OpeningTime> openingtimes;

	public Museum(String country, String street, String city, String hnumber, int id, String name) {
		super(country, street, city, hnumber);
		this.ID = id;
		this.mname = name;
	}

	public Iterator<OpeningTime> getOpeningtimes() {
		return openingtimes.iterator();
	}

	public void addOpeningtimes(OpeningTime openingtimes) {
		this.openingtimes.add(openingtimes);
	}

	public String getMname() {
		return mname;
	}

	public int getID() {
		return ID;
	}

}
