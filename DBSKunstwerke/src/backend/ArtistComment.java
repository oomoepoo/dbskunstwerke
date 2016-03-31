package backend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArtistComment extends Comment<String> {
	public static final String ZCOL = "Kuenstler";
	public static final String TIME = "Zeitpunkt";
	private StringProperty zeitpunkt;

	public ArtistComment(String Text, String Kommentator, String Ziel, String Zeitpunkt) {
		super(Text, Kommentator, Ziel);
		this.zeitpunkt = new SimpleStringProperty(Zeitpunkt);
	}

	/**
	 * Generates a query String for adding the comment into the database.
	 * "Insert into KuenstlerKommentar (Benutzer, Kuenstler, Kommentartext,Zeitpunkt) VALUES (?,?,?,?)"
	 */
	@Override
	public String getQuery(){
		String query = "Insert into "+ACOMMENTS+"("+KCOL+", "+ZCOL+", "+KTEXT+","+TIME+") VALUES (?,?,?,?)";
		return query;
	}

	public String getZeitpunkt() {
		return zeitpunkt.get();
	}

	public void setZeitpunkt(String zeitpunkt) {
		this.zeitpunkt.set(zeitpunkt);
	}

	public StringProperty getzeitpunktProperty(){
		return this.zeitpunkt;
	}


}
