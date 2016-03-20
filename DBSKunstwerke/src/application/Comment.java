package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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

	private SimpleStringProperty Kommentar;
	private SimpleStringProperty Kommentator;
	private SimpleIntegerProperty Ziel;

	protected Comment (String Text, String Kommentator, Integer Ziel){
		this.setComment(Text);
		this.setKommentator(Kommentator);
		this.setZiel(Ziel);

	}

	public String getQuery () {
		return "";

	}

	public String getComment() {
		return Kommentar.get();
	}

	public void setComment(String kommentar) {
		Kommentar = new SimpleStringProperty(kommentar);
	}

	public String getKommentator() {
		return Kommentator.get();
	}

	public void setKommentator(String kommentator) {
		Kommentator = new SimpleStringProperty(kommentator);
	}

	public int getZiel() {
		return Ziel.get();
	}

	public void setZiel(int ziel) {
		Ziel = new SimpleIntegerProperty(ziel);
	}


}
