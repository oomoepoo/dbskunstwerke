package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import application.SQLiteConection;
import backend.Adresse;
import backend.ArtistComment;
import backend.Artwork;
import backend.Atellier;
import backend.Collection;
import backend.OpeningTime;
import backend.User;
import backend.Weekdays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KuenstlerProfilModel {
	private String nutzer;
	private Connection connection;
	private DataModel daten;

	public KuenstlerProfilModel(String kuenstlername){
		connection = SQLiteConection.Connector();
		if (connection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
		this.nutzer = kuenstlername;
		this.daten = generateDatafromSQL();
	}

	private DataModel generateDatafromSQL(){
		String sql = "Select * from Benutzer inner join Adresse on Benutzer.Adresse = Adresse.AdressenID where Benutzername = ?";
		String sql2 = "Select * from Adresse inner join Atelier_gehoert_Kuenstler on Adresse.AdressenID "
				+ "= Atelier_gehoert_Kuenstler.Atelier inner join Gebaeude on Atelier_gehoert_Kuenstler.Atelier = "
				+ "Gebaeude.GebaeudeID where Kuenstler = ?";
		String sql3 = "Select * from Oeffnungszeiten where GebaeudeID = ?";
		Integer aid = null;
		User user = null;
		Atellier atelier = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nutzer);
			results = preparedStatement.executeQuery();
			while(results.next()){
				String vorname = results.getString("Vorname");
				String nachname = results.getString("Nachname");
				String passwort = null;
				aid = results.getInt("AdressenID");
				String email = results.getString("E-Mail");
				String country = results.getString("Land");
				String street = results.getString("Strasse");
				String hnumber = results.getString("Hausnummer");
				String city = results.getString("Stadt");
				user = new User(nutzer, vorname, nachname, passwort, aid, email);
				Adresse adresse = new Adresse(aid, country, city, street, hnumber);
				user.setAdress(adresse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1,nutzer);
			results = preparedStatement.executeQuery();

			if(!results.next()) throw new Exception();
			while (results.next()){
				String country = results.getString("Land");
				String street = results.getString("Strasse");
				String hnumber = results.getString("Hausnummer");
				String name = results.getString("Name");
				String city = results.getString("Stadt");

				atelier = new Atellier(country, street, city, hnumber, name, aid, nutzer);

			}


		} catch(SQLException e){
			System.out.println("Fehler beim holen der Atellier Infos!");
			e.printStackTrace();
		} catch (Exception ee){
			System.out.println("Atellier existiert nicht!");
		}

		try {
			preparedStatement = connection.prepareStatement(sql3);
			preparedStatement.setInt(1,aid);
			results = preparedStatement.executeQuery();

			while(results.next()){
				Weekdays Wochentag = Weekdays.from_String(results.getString("Wochentag"));
				String von = results.getString("von");
				String bis = results.getString("bis");
				OpeningTime open = new OpeningTime(Wochentag, von, bis);
				System.out.println(open.getWeekday());
				atelier.setOpeningtimes(open);
			}
		} catch(SQLException e){
			System.out.println("Fehler beim Hinzufügen von Öffnungszeiten...");
			e.printStackTrace();
		}
		return new DataModel(user, atelier);
	}

	public User getUser(){
		return daten.getUser();
	}
	public Atellier getAtellier(){
		return daten.getAtelier();
	}

	public ObservableList<ObservableList<String>> getArtStylesfromSQL() {
		ObservableList<ObservableList<String>> items = FXCollections.observableArrayList();
		String sql = "Select Kunststil from Kuenstler_bevorzugt_Kunststil where  Kuenstler = ?";


		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nutzer);


			ResultSet result = preparedStatement.executeQuery();
			while (result.next()){
				items.add(FXCollections.observableArrayList(result.getString("Kunststil")));
			}
		} catch (SQLException e){
			System.out.println("Fehler bei Kunststilliste!");
			e.printStackTrace();
		}

		return items;
	}


	public ObservableList<Artwork> getArtworksfromSQL() {
		ObservableList<Artwork> ArtworkList = FXCollections.observableArrayList();
		String sql = "Select Kunstwerk.Entstehungsjahr, Kunstwerk.Name, Kunstwerk.Kunststilname "
				+ "from Kunstwerk inner join Kuenstler_erstellt_Kunstwerk on Kunstwerk.KunstwerkID = "
				+ "Kuenstler_erstellt_Kunstwerk.Kunstwerk where Kuenstler_erstellt_Kunstwerk.Kuenstler = ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nutzer);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()){
				String name = result.getString("Name");
				Integer creationyear = result.getInt("Entstehungsjahr");
				String artstyle = result.getString("Kunststilname");

				ArtworkList.add(new Artwork(name, creationyear, artstyle));
			}
		}
		catch (SQLException e){
			System.out.println("Fehler bei Kunstwerketabelle!");
			e.printStackTrace();
		}
		return ArtworkList;
	}
	public ObservableList<OpeningTime> getOpeningTimesfromSQL() {
		ObservableList<OpeningTime> OpeningList = FXCollections.observableArrayList();
		while(this.daten.getAtelier().getOpeningtimes().hasNext()){
			OpeningList.add(this.daten.getAtelier().getOpeningtimes().next());
		}
		return OpeningList;
	}
	public ObservableList<Collection> getCollectionfromSQL() {
		String col = "Sammlung_ausgestellt_in_Gebaeude";
		String query = "Select Sammlung.Name, Sammlung.SammlungID from Sammlung"
				+ "INNER JOIN "+col+" on Sammlung.SammlungID = "+col+".SammlungID"
				+ "where "+col+".GebaeudeID = ?";
		ObservableList<Collection> col_data = FXCollections.observableArrayList();
		try {
			PreparedStatement col_query = connection.prepareStatement(query);
			ResultSet results = null;
			col_query.setInt(1, daten.getAtelier().getID());
			results = col_query.executeQuery();
			while(results.next()){
				int sammlungID = results.getInt("SammlungID");
				String name = results.getString("Name");
				col_data.add(new Collection(sammlungID, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return col_data;
	}
	public ObservableList<ArtistComment> getArtistCommentsfromSQL() {
		String query = "Select Kommentator, Kommentartext, Zeitpunkt from KuenstlerKommentar where Kuenstler = ?";
		ObservableList<ArtistComment> acomments = FXCollections.observableArrayList();
		try {
			PreparedStatement acomment_query = connection.prepareStatement(query);
			acomment_query.setString(1, nutzer);
			ResultSet results = acomment_query.executeQuery();
			while(results.next()){
				String acommentor = results.getString("Kommentator");
				String acomment = results.getString("Kommentartext");
				String zeitpunkt = results.getString("Zeitpunkt");
				acomments.add(new ArtistComment(acomment, acommentor, nutzer, zeitpunkt));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		return acomments;
	}

	public void add_artist_comment(String comment, String kommentator) {
		ArtistComment Kommentar = new ArtistComment(comment, kommentator, nutzer, (new Timestamp(System.currentTimeMillis())).toString());
		String query = Kommentar.getQuery();
		try {
			PreparedStatement add_comment_query = connection.prepareStatement(query);
			add_comment_query.setString(1, kommentator);
			add_comment_query.setString(2, nutzer);
			add_comment_query.setString(3, comment);
			add_comment_query.setString(4, Kommentar.getZeitpunkt());
			add_comment_query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static class DataModel {
		private User user;
		private Atellier atelier;

		public DataModel(User user, Atellier atelier){
			this.user = user;
			this.atelier = atelier;
		}
		public User getUser(){
			return user;
		}
		public Atellier getAtelier(){
			return atelier;
		}

	}

}
