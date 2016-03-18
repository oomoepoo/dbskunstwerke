package application;

public class ArtistComment extends Comment {
	public static final String ZCOL = "Kuenstler";
	public static final String TIME = "Zeitpunkt";
	private String Zeitpunkt;

	public ArtistComment(String Text, Integer Kommentator, Integer Ziel, String Zeitpunkt) {
		super(Text, Kommentator, Ziel);
		this.Zeitpunkt = Zeitpunkt;
	}

	@Override
	public String getQuery(){
		String query = "Insert into "+ACOMMENTS+"("+KCOL+", "+ZCOL+", "+KTEXT+","+TIME+") VALUES (?,?,?,?)";
		return query;
	}

	public String getZeitpunkt() {
		return Zeitpunkt;
	}

	public void setZeitpunkt(String zeitpunkt) {
		Zeitpunkt = zeitpunkt;
	}

}
