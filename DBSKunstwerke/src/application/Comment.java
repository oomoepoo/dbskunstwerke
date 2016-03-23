package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Abstract class for comments
 * It's not intended to actually create instances of this,
 * we always want a comment to be either for Artists or Museums.
 *
 * @author Jan
 *
 */
public abstract class Comment {
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
	private IntegerProperty ziel;

	protected Comment (String Text, String Kommentator, Integer Ziel){
		this.kommentar = new SimpleStringProperty(Text);
		this.kommentator = new SimpleStringProperty(Kommentator);
		this.ziel = new SimpleIntegerProperty(Ziel);

	}

	public String getQuery () {
		return "";

	}

	public String getComment() {
		return kommentar.get();
	}

	public void setComment(String kommentar) {
		this.kommentar.set(kommentar);
	}
	public StringProperty kommentarProperty(){
		return kommentar;
	}

	public String getKommentator() {
		return kommentator.get();
	}

	public void setKommentator(String kommentator) {
		this.kommentator.set(kommentator);
	}

	public StringProperty kommentatorProperty(){
		return kommentator;
	}

	public int getZiel() {
		return ziel.get();
	}

	public void setZiel(int ziel) {
		this.ziel.set(ziel);
	}
	public IntegerProperty zielProperty(){
		return ziel;
	}


}
