package application;



public class MuesumsComment extends Comment {
	public final static String ZCOL = "Museum";


	public MuesumsComment(String Text, Integer Kommentator, Integer Ziel) {
		super(Text, Kommentator, Ziel);
	}

	@Override
	public String getQuery(){
		String query="Insert into "+MCOMMENTS+"("+KCOL+", "+ZCOL+", "+KTEXT+") values (?,?,?)";
		return query;
	}

}
