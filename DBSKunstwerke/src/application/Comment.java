package application;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Abstract class for comments
 * It's not intended to actually create instances of this,
 * we always want a comment to be either for Artists or Museums.
 *
 * Edit: Added Generics stuff to handle Museums- and Artistcomments
 * without problems.
 * (I.e: Having a single super-constructor and getters/setter for the target stuff)
 *
 * @author Jan
 *
 */
public abstract class Comment<V> {
	/*
	 * Names of the SQL-Tables and columns where the comments are stored.
	 *
	 * MCOMMENTS is for Museum comments
	 * ACOMMENTS is for artist comments
	 * KID is for comments id
	 * KTEXT is for comment text
	 *
	 */
	public static final String KCOL = "Benutzer";
	public static final String MCOMMENTS = "MuseumKommentar";
	public static final String ACOMMENTS = "KuenstlerKommentar";
	public static final String KID = "KommentarID";
	public static final String KTEXT = "Kommentartext";

	private StringProperty kommentar;
	private StringProperty kommentator;
	private ObjectProperty<V> ziel;

	protected Comment(String text, String kommentator, V ziel){
		this.kommentar = new SimpleStringProperty(text);
		this.kommentator = new SimpleStringProperty(kommentator);
		this.ziel = new SimpleObjectProperty<V>(ziel);
	}

	public abstract String getQuery ();

	public String getComment() {
		return kommentar.get();
	}

	public void setComment(String kommentar) {
		this.kommentar.set(kommentar);
	}

	public StringProperty getkommentarProperty(){
		return kommentar;
	}

	public String getKommentator() {
		return kommentator.get();
	}

	public void setKommentator(String kommentator) {
		this.kommentator.set(kommentator);
	}

	public StringProperty getkommentatorProperty(){
		return kommentator;
	}

	public V getZiel(){
		return ziel.get();
	}

	public void setZiel(V ziel) {
		this.ziel.set(ziel);
	}

	public ObjectProperty<V> getzielProperty(){
		return ziel;
	}


}