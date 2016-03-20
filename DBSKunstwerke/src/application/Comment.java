package application;

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

	private String Kommentar;
	private String Kommentator;
	private int Ziel;

	protected Comment (String Text, String Kommentator, Integer Ziel){
		this.setComment(Text);
		this.setKommentator(Kommentator);
		this.setZiel(Ziel);

	}

	public String getQuery () {
		return "";

	}

	public String getComment() {
		return Kommentar;
	}

	public void setComment(String kommentar) {
		Kommentar = kommentar;
	}

	public String getKommentator() {
		return Kommentator;
	}

	public void setKommentator(String kommentator) {
		Kommentator = kommentator;
	}

	public int getZiel() {
		return Ziel;
	}

	public void setZiel(int ziel) {
		Ziel = ziel;
	}


}
