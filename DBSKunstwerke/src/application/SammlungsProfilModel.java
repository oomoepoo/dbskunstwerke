package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SammlungsProfilModel {
	private Connection conection;

	public SammlungsProfilModel(){
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
	}

	public ObservableList<Artwork> createArtworkList(Collection colection){
		ObservableList<Artwork> artworklist = FXCollections.observableArrayList();
		for(String k : colection.getKunstwerke()){
			artworklist.add(new Artwork(k));
		}
		return artworklist;

	}
	public Collection getCollectionfromSQL(int colid) {
		String query1 = "Select Kunstwerk.Name from Sammlung inner join Kunstwerk_bestandteil_von_Sammlung as kbs on Sammlung.SammlungID = kbs.SammlungID inner join Kunstwerk on Kunstwerk.KunstwerkID = kbs.KunstwerkID where Sammlung.SammlungID = ? ";
		String query2 = "Select Gebaeude.Name from Sammlung_ausgestellt_in_Gebaeude inner join Gebaeude on Sammlung_ausgestellt_in_Gebaeude.GebaeudeID = Gebaeude.GebaeudeID where Sammlung_ausgestellt_in_Gebaeude.SammlungID = ?";
		String query3 = "Select Sammlung.Name from Sammlung where Sammlung.ID = ?";
		ResultSet result1 = null;
		ResultSet result2 = null;
		ResultSet result3 = null;
		String name = null;
		String place = null;
		ArrayList<String>  kunstwerke = new ArrayList<String>();
		try {
			PreparedStatement query = conection.prepareStatement(query1);
			query.setInt(1, colid);

			result1 = query.executeQuery();
			while(result1.next()){
				kunstwerke.add(result1.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement query = conection.prepareStatement(query2);
			query.setInt(1,colid);

			result2 = query.executeQuery();
			while(result2.next()){
				place = result2.getString("Name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement query;
		try {
			query = conection.prepareStatement(query3);
			query.setInt(1, colid);

			result3 = query.executeQuery();
			while(result3.next()){
				name=result3.getString("Name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Collection c = new Collection(colid, name);
		c.setPlace(place);
		for (String k : kunstwerke){
			c.setKunstwerke(k);
		}
		return c;


	}




}
