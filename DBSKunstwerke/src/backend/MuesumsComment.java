package backend;

public class MuesumsComment extends Comment<Integer> {
	public final static String ZCOL = "Museum";
	public final static String KCOL = "Kommentator";


	public MuesumsComment(String Text, String Kommentator, Integer Ziel) {
		super(Text, Kommentator, Ziel);
	}

	@Override
	public String getQuery(){
		String query="Insert into "+MCOMMENTS+"("+MuesumsComment.KCOL+", "+ZCOL+", "+KTEXT+") values (?,?,?)";
		return query;
	}

}
