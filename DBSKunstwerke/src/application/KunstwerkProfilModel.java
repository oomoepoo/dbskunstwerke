package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KunstwerkProfilModel {
	Connection conection;
	String query;
	ResultSet results;
	PreparedStatement preparedStatement;

	public KunstwerkProfilModel(){
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
	}


	public ObservableList<KunstwerkBewertung> getBewertungenfromSQL(int kunstwerk) {
		query = "Select * from Benutzer_bewertet_Kunstwerk where KunstwerkID = ?";
		ObservableList<KunstwerkBewertung> bewertungen = FXCollections.observableArrayList();
		try{
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, kunstwerk);
			ResultSet results = preparedStatement.executeQuery();
			while(results.next()){
				String text = results.getString("Kommentar");
				String kommentator = results.getString("Benutzername");
				int ziel = results.getInt("KunstwerkID");
				int bewertung = results.getInt("Bewertung");
				KunstwerkBewertung kbw = new KunstwerkBewertung(text, kommentator, ziel, bewertung);
				bewertungen.add(kbw);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		return bewertungen;
	}

	public void bestelle_abdruck(Abdruck abdruck, int kunstwerk, String nutzer) {
		int id = abdruck.getId();
		query = "insert into Benutzer_bestellt_Abdruck (AbdruckID, Benutzername, Bestelldatum) values(?,?,?)";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nutzer);
			preparedStatement.setString(3, new Timestamp(System.currentTimeMillis()).toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "update Abdruck set Verfuegbar = 0 where AbdruckID = ?";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObservableList<User> getArtistsfromSQL(int kunstwerk) {
		ObservableList<User> artists = FXCollections.observableArrayList();
		query = "select Benutzer.Benutzername, Benutzer.Vorname, Benutzer.Nachname, Benutzer.email"
				+ "Adresse.Land, Adresse.Stadt, Adresse.Strasse, Adresse.Hausnummer "
				+ "from Kuenstler_erstellt_Kunstwerk "
				+ "inner join Benutzer on Kuenstler_erstellt_Kunstwerk.Kuenstler = Benutzer.Benutzername "
				+ "inner join Adresse on Benutzer.Adresse = Adresse.AdressenID "
				+ "where Kuenstler_erstellt_Kunstwerk.Kunstwerk = ?";
		try{
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, kunstwerk);
			results = preparedStatement.executeQuery();
			while (results.next()){
				String username = results.getString(1);
				String vorname = results.getString(2);
				String nachname = results.getString(3);
				String email = results.getString(4);
				User artist = new User(username, vorname, nachname, email);
				String country = results.getString(5);
				String city = results.getString(6);
				String street = results.getString(7);
				String hnumber = results.getString(8);
				artist.setAdress(new Adresse(0, country, city, street, hnumber));
				artists.add(artist);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return artists;
	}

	public ObservableList<Abdruck> getAbdruckfromSQL(int kunstwerk) {
		query = "select * from Abdruck where Verfuegbar = 1 and KunstwerkID = ?";
		ObservableList<Abdruck> abdrucke = FXCollections.observableArrayList();
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, kunstwerk);
			results = preparedStatement.executeQuery();
			while (results.next()){
				int id = results.getInt(1);
				int preis = results.getInt(2);
				int hohe = results.getInt(3);
				int breite = results.getInt(4);
				abdrucke.add(new Abdruck(id, preis, hohe, breite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return abdrucke;
	}

	public void sendBewertung(KunstwerkBewertung bewertung){
		//"Insert into "+TNAME+"(Kommentar, Benutzername, KunstwerkID, Bewertung ) values (?,?,?,?)";
		query = bewertung.getQuery();
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, bewertung.getComment());
			preparedStatement.setString(2,bewertung.getKommentator());
			preparedStatement.setInt(3, bewertung.getZiel());
			preparedStatement.setInt(4, bewertung.getBewertung());
			preparedStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}

	}

	public class  Abdruck{
		private IntegerProperty id;
		private IntegerProperty preis;
		private IntegerProperty breite;
		private IntegerProperty hoehe;

		public Abdruck(int id, int preis, int hohe, int breite){
			this.id = new SimpleIntegerProperty(id);
			this.preis = new SimpleIntegerProperty(preis);
			this.hoehe = new SimpleIntegerProperty(hohe);
			this.breite = new SimpleIntegerProperty(breite);
		}

		public int getId(){
			return this.id.get();
		}

		public void setId(int id){
			this.id.set(id);
		}

		public IntegerProperty getidProperty(){
			return id;
		}

		public int getPreis(){
			return this.preis.get();
		}

		public void setPreis(int preis){
			this.preis.set(preis);
		}

		public IntegerProperty getpreisProperty(){
			return preis;
		}

		public int getBreite() {
			return breite.get();
		}

		public void setBreite(int breite) {
			this.breite.set(breite);
		}

		public IntegerProperty getbreiteProperty() {
			return breite;
		}

		public Integer getHoehe() {
			return hoehe.get();
		}

		public void setHoehe(int hoehe) {
			this.hoehe.set(hoehe);
		}

		public IntegerProperty gethoeheProperty() {
			return hoehe;
		}
	}

}