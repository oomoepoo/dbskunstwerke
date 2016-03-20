package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MuseumsProfileModel {
	Connection conection;
	private int MuseumsID;
	public MuseumsProfileModel (int MuseumsID) {
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
		this.MuseumsID = MuseumsID;
	}

public ObservableList<OpeningTime> create_ot_from_sql(){
	String query = "Select Wochentag, von, bis from Oeffnungszeiten where GebauedeID = ?";
	ObservableList<OpeningTime> OpeningList = FXCollections.observableArrayList();
	try {
		PreparedStatement opening_query = conection.prepareStatement(query);
		opening_query.setInt(1, MuseumsID);
		ResultSet results = opening_query.executeQuery();
		while(results.next()){
			Weekdays weekday = Weekdays.from_String(results.getString("Wochentag"));
			String von = ((Integer)results.getInt("von")).toString();
			String bis = ((Integer)results.getInt("bis")).toString();
			OpeningList.add(new OpeningTime(weekday, von, bis));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return OpeningList;


}

public ObservableList<collection_view> create_col_from_sql() {
	String col = "Sammlung_ausgestellt_in_Gebaeude";
	String query = "Select Sammlung.SammlungID, Sammlung.Name,"+col+".von,"+col+".bis from Sammlung"
			+ "INNER JOIN "+col+" on Sammlung.SammlungID = "+col+".SammlungID"
			+ "where "+col+".GebaeudeID = ?";
	ObservableList<collection_view> col_data = FXCollections.observableArrayList();
	try {
		PreparedStatement col_query = conection.prepareStatement(query);
		ResultSet results = null;
		col_query.setInt(1, MuseumsID);
		results = col_query.executeQuery();
		while(results.next()){
			int sammlungID = results.getInt("SammlungID");
			String name = results.getString("Name");
			String von = results.getString("von");
			String bis = results.getString("bis");
			col_data.add(new collection_view(sammlungID, name, von, bis));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return col_data;
}

public ObservableList<MuesumsComment> create_com_from_sql() {
	String query = "Select Kommentator, Kommentartext from MuseumKommentar where Museum = ?";
	ObservableList<MuesumsComment> mcomments = FXCollections.observableArrayList();
	try {
		PreparedStatement mcomment_query = conection.prepareStatement(query);
		mcomment_query.setInt(1, MuseumsID);
		ResultSet results = mcomment_query.executeQuery();
		while(results.next()){
			String mcommentor = results.getString("Kommentator");
			String mcomment = results.getString("Kommentartext");
			mcomments.add(new MuesumsComment(mcomment, mcommentor, MuseumsID));
		}
	} catch (SQLException e){
		e.printStackTrace();
	}

	return mcomments;
}
}
