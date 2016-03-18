package application;

import java.util.ArrayList;
import java.util.Iterator;

public class Atellier extends Building {

	private ArrayList<String> owned_by;
	private String name;
	private int ID;
	private ArrayList<OpeningTime> openingtimes;

	public Atellier(String country, String street, String city, String hnumber, String name, int ID, String[] owned_by, OpeningTime openings) {
		super(country, street, city, hnumber);
		this.setName(name);
		this.setID(ID);
		initialize_ownery(owned_by);

		// TODO Auto-generated constructor stub
	}



	public Iterator<OpeningTime> getOpeningtimes() {
		return openingtimes.iterator();
	}



	public void setOpeningtimes(OpeningTime openingtimes) {
		this.openingtimes.add(openingtimes);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Iterator<String> getOwners(){
		return owned_by.iterator();
	}

	public void setOwners(String owner){
		owned_by.add(owner);
	}

	private void initialize_ownery(String[] owners) {
		for (String owner : owners){
			owned_by.add(owner);

		}

	}

}
