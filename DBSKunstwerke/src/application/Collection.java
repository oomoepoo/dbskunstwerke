package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collection {

	private final IntegerProperty sammlungID;
	private final StringProperty name;
	private ArrayList<StringProperty> Kunstwerke = null;
	private StringProperty place;



	public Collection(Integer sammlungID, String name, String...kunstwerke) {
		super();
		this.sammlungID = new SimpleIntegerProperty(sammlungID);
		this.name = new SimpleStringProperty(name);
		for (String k : kunstwerke){
			this.Kunstwerke.add(new SimpleStringProperty(k));
		}
	}
/*
	public Collection(Integer sammlungID, String name){
		super();
		this.sammlungID = new SimpleIntegerProperty(sammlungID);
		this.name = new SimpleStringProperty(name);
		this.Kunstwerke = null;
	}
*/
	public ArrayList<String> getKunstwerke() {
		ArrayList<String> s_kunstwerke = new ArrayList<String>();
		for(StringProperty k : this.Kunstwerke){
			s_kunstwerke.add(k.get());
		}
		return s_kunstwerke;
	}

	public void setKunstwerke(String...kunstwerke) {
		for (String k : kunstwerke){
			this.Kunstwerke.add(new SimpleStringProperty(k));
		}
	}

	public ArrayList<StringProperty> getkunstwerkeProperty(){
		return Kunstwerke;
	}

	public Integer getSammlungID() {
		return sammlungID.get();
	}
	public IntegerProperty getsammlungIDProperty(){
		return sammlungID;
	}

	public String getName() {
		return name.get();
	}
	public StringProperty getnameProperty(){
		return name;
	}

	public String getPlace(){
		return place.get();
	}

	public void setPlace(String place){
		this.place.set(place);
	}

	public StringProperty getplaceProperty(){
		return place;
	}
}
