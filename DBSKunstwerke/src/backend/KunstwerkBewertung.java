package backend;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Handles the Artworks review stuff...
 * extends Comment<Integer> because:
 * a) Artworks have an ID.
 * b) You should be able to write a reasoning for your review.
 *
 * @author jan
 *
 */
public class KunstwerkBewertung extends Comment<Integer>{
	public static final String TNAME = "Benutzer_bewertet_Kunstwerk";

	private IntegerProperty bewertung;

	public KunstwerkBewertung(String text, String kommentator, Integer ziel, Integer bewertung) {
		super(text, kommentator, ziel);
		this.bewertung = new SimpleIntegerProperty(bewertung);
	}

	@Override
	public String getQuery() {
		String query = "Insert into "+TNAME+"(Benutzername, KunstwerkID, Bewertung, Kommentar ) values (?,?,?,?)";
		return query;
	}

	public int getBewertung(){
		return bewertung.get();
	}

	public void setBewertung(int bewertug){
		this.bewertung.set(bewertug);
	}

	public IntegerProperty getberwertungProperty(){
		return bewertung;
	}

}
