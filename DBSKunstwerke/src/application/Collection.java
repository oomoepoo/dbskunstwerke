package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Collection {

	private final SimpleIntegerProperty sammlungID;
	private final SimpleStringProperty name;
	private ArrayList<SimpleIntegerProperty> Kunstwerke = null;



	public Collection(Integer sammlungID, String name, Integer[] kunstwerke) {
		super();
		this.sammlungID = new SimpleIntegerProperty(sammlungID);
		this.name = new SimpleStringProperty(name);
		setKunstwerke(kunstwerke);
	}

	public Collection(Integer sammlungID, String name){
		super();
		this.sammlungID = new SimpleIntegerProperty(sammlungID);
		this.name = new SimpleStringProperty(name);
		Kunstwerke = null;
	}

	public Iterator<SimpleIntegerProperty> getKunstwerke() {
		return Kunstwerke.iterator();
	}
	public void setKunstwerke(Integer[] kunstwerke) {
		for (Integer k : kunstwerke){
			this.Kunstwerke.add(new SimpleIntegerProperty(k));
		}
	}
	public Integer getSammlungID() {
		return sammlungID.get();
	}
	public String getName() {
		return name.get();
	}

}
