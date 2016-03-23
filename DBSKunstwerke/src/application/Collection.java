package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collection {

	private final IntegerProperty sammlungID;
	private final StringProperty name;
	private ArrayList<IntegerProperty> Kunstwerke = null;



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

	public Iterator<IntegerProperty> getKunstwerke() {
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
	public IntegerProperty sammlungIDProperty(){
		return sammlungID;
	}

	public String getName() {
		return name.get();
	}
	public StringProperty nameProperty(){
		return name;
	}

}
