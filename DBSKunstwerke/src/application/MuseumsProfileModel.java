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

public ObservableList<Collection> create_col_from_sql() {

	return null;
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
