/**
 *
 */
package application;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse für Kunstwerke:
 * Wie auch für Atelliers, Adressen und alles andere:
 * Hauptsächlich dazu da, einfach TableViews zu füttern.
 *
 * @author jan
 *
 */
public class Artwork {

	private StringProperty name;
	private ArrayList<StringProperty> creators;
	private IntegerProperty creationyear;
	private StringProperty artstyle;

	public Artwork(String name){
		this.name = new SimpleStringProperty(name);
	}

	public Artwork(String name,int creationyear, String artstyle, String...artists ){
		this.name = new SimpleStringProperty(name);
		this.creationyear = new SimpleIntegerProperty(creationyear);
		this.artstyle = new SimpleStringProperty(artstyle);
		for (String artist : artists) {
			this.creators.add(new SimpleStringProperty(artist));
		}
	}

	public void setName(String name){
		this.name.set(name);
	}

	public String getName(){
		return this.name.get();
	}

	public StringProperty getnameProperty(){
		return name;
	}

	public void setCreationyear(Integer creationyear) {
		this.creationyear.set(creationyear);
	}

	public int getCreationyear(){
		return this.creationyear.get();
	}

	public IntegerProperty getcreationyearProperty() {
		return creationyear;
	}

	public void setArtstyle(String artstyle) {
		this.artstyle.set(artstyle);
	}

	public String getArtstyle(){
		return this.artstyle.get();
	}

	public StringProperty getartstyleProperty() {
		return artstyle;
	}

	public ArrayList<StringProperty> getCreators(){
		return this.creators;
	}

	public void addCreators(String...creators){
		for (String c : creators){
			this.creators.add(new SimpleStringProperty(c));
		}
	}


}
