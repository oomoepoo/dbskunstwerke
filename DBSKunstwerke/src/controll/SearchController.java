package controll;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import application.SQLiteConection;
import backend.Adresse;
import backend.Artwork;
import backend.Collection;
import backend.Context;
import backend.Museum;
import backend.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class SearchController implements Initializable{
	Connection connection = SQLiteConection.Connector();
	Context currentContext;
	@FXML
	private TextField tfieldMuseumFilter;
	@FXML
	private ComboBox<String> cboxMuseumFilter;
	@FXML
	private TableView<Museum> tviewMuseum;
	@FXML
	private TableColumn<Museum, String> museumnameColumn;
	@FXML
	private TableColumn<Museum, String> museumcityColumn;
	@FXML
	private Button btnMuseumDetails;
	@FXML
	private TextField tfieldArtistFilter;
	@FXML
	private ComboBox<String> cboxArtistFilter;
	@FXML
	private TableView<User> tviewArtists;
	@FXML
	private TableColumn<User, String> artistfnColumn;
	@FXML
	private TableColumn<User, String> artistlnColumn;
	@FXML
	private TableColumn<User, String> artistunameColumn;
	@FXML
	private TextField tfieldArtworkFilter;
	@FXML
	private ComboBox<String> cboxArtworkFilter;
	@FXML
	private TableView<Artwork> tviewArtworks;
	@FXML
	private TableColumn<Artwork, String> artworknameColumn;
	@FXML
	private TableColumn<Artwork, String> artstyleColumn;
	@FXML
	private TableColumn<Artwork, Integer> yearColum;
	@FXML
	private TextField tfieldCollectionFilter;
	@FXML
	private ComboBox<String> cboxCollectionFilter;
	@FXML
	private TableView<Collection> tviewCollections;
	@FXML
	private TableColumn<Collection, String> colnameColumn;
	@FXML
	private TableColumn<Collection, String> colplaceColumn;

	public ObservableList<String> muesumsfilter = FXCollections.observableArrayList("Name", "Stadt");
	public ObservableList<String> artistfilter = FXCollections.observableArrayList("Name","Nutzername");
	public ObservableList<String> artworkfilter = FXCollections.observableArrayList("Name","Enstehungsjahr","Kunststil");
	public ObservableList<String> collectionfilter = FXCollections.observableArrayList("Name");

	public ObservableList<User> artists = getArtistsfromSQL();
	public ObservableList<Museum> museums = getMuseumsfromSQL();
	public ObservableList<Artwork> artworks = getArtworksfromSQL();
	public ObservableList<Collection> collections = getCollectionsfromSQL();

	public FilteredList<User> filteredArtists;
	public FilteredList<Museum> filteredMuseums;
	public FilteredList<Artwork> filteredArtworks;
	public FilteredList<Collection> filteredCollections;

	public SearchController(Context context) {
		this.currentContext = context;
	}
	// Event Listener on Button[#btnMuseumDetails].onAction
	@FXML
	public void showMuseumDetails(ActionEvent event) {
		Museum museum = tviewMuseum.getSelectionModel().getSelectedItem();
		URL url = getClass().getResource("/application/MuseumsProfile.fxml");
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			MuseumsProfileController mcontroller = new MuseumsProfileController(currentContext.getNutzer().getUsername(), museum);
			loader.setController(mcontroller);
			loader.setLocation(url);
			Pane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DBS-Kunstwerke: "+museum.getMname());
			primaryStage.show();
			} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	// Event Listener on Button.onAction
	@FXML
	public void showArtistDetails(ActionEvent event) {
		try {
			URL url = getClass().getResource("/application/KuenstlerProfil.fxml");
			User user = tviewArtists.getSelectionModel().getSelectedItem();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			KuenstlerProfilController kpc = new KuenstlerProfilController(this.currentContext.getNutzer().getUsername(), user.getUsername());
			loader.setController(kpc);
			loader.setLocation(url);
			Pane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DBS-Kunstwerke: "+user.getUsername());
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void showArtworkDetails(ActionEvent event) {
		try {
			Artwork artwork = tviewArtworks.getSelectionModel().getSelectedItem();
			URL url = getClass().getResource("/application/KunstwerkProfil.fxml");
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			KunstwerkProfilController kpc = new KunstwerkProfilController(artwork.getId(), currentContext.getNutzer().getUsername());
			loader.setController(kpc);
			loader.setLocation(url);
			Pane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DBS-Kunstwerke: "+artwork.getName());
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// Event Listener on Button.onAction
	@FXML
	public void showCollectionDetails(ActionEvent event) {
		Collection collection = tviewCollections.getSelectionModel().getSelectedItem();
			try {
				System.out.println(collection.getsammlungIDProperty().get());
				URL url = getClass().getResource("/application/KunstwerkProfil.fxml");
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				SammlungsProfilController kpc = new SammlungsProfilController(collection.getsammlungIDProperty().get());
				loader.setController(kpc);
				loader.setLocation(url);
				Pane root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DBS-Kunstwerke: "+collection.getName());
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboxArtistFilter.setItems(artistfilter);
		cboxArtworkFilter.setItems(artworkfilter);
		cboxCollectionFilter.setItems(collectionfilter);
		cboxMuseumFilter.setItems(muesumsfilter);

		init_Columns();
		filteredArtists = new FilteredList<>(artists);
		filteredArtworks = new FilteredList<>(artworks);
		filteredCollections = new FilteredList<>(collections);
		filteredMuseums = new FilteredList<>(museums);

		// 2. Initially display all data -> predicate's test method returns always true
		Predicate<User> predicate = new Predicate<User>() {
			@Override
			public boolean test(User u) {
				return true;
			}
		};
		filteredArtists.setPredicate(predicate);

		Predicate<Museum> mpredicate = new Predicate<Museum>() {
			@Override
			public boolean test(Museum m) {
				return true;
			}
		};
		filteredMuseums.setPredicate(mpredicate);
		Predicate<Artwork> apredicate = new Predicate<Artwork>() {
			@Override
			public boolean test(Artwork a) {
				return true;
			}
		};
		filteredArtworks.setPredicate(apredicate);

		Predicate<Collection> cpredicate = new Predicate<Collection>() {
			@Override
			public boolean test(Collection c) {
				return true;
			}
		};
		filteredCollections.setPredicate(cpredicate);

		SortedList<User> sortedArtists = new SortedList<>(filteredArtists);
		SortedList<Museum> sortedMuseums = new SortedList<>(filteredMuseums);
		SortedList<Artwork> sortedArtworks = new SortedList<>(filteredArtworks);
		SortedList<Collection> sortedCollections = new SortedList<>(filteredCollections);

		sortedArtists.comparatorProperty().bind(tviewArtists.comparatorProperty());
		sortedMuseums.comparatorProperty().bind(tviewMuseum.comparatorProperty());
		sortedArtworks.comparatorProperty().bind(tviewArtworks.comparatorProperty());
		sortedCollections.comparatorProperty().bind(tviewCollections.comparatorProperty());

		tviewArtists.setItems(sortedArtists);
		tviewArtworks.setItems(sortedArtworks);
		tviewMuseum.setItems(sortedMuseums);
		tviewCollections.setItems(sortedCollections);


	}

	private void init_Columns() {
		museumcityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
		museumnameColumn.setCellValueFactory(cellData -> cellData.getValue().getmnameProperty());

		artistfnColumn.setCellValueFactory(cellData -> cellData.getValue().getvornameProperty());
		artistlnColumn.setCellValueFactory(cellData -> cellData.getValue().getnachnameProperty());
		artistunameColumn.setCellValueFactory(cellData -> cellData.getValue().getusernameProperty());

		artstyleColumn.setCellValueFactory(cellData -> cellData.getValue().getartstyleProperty());
		artworknameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());

		colplaceColumn.setCellValueFactory(cellData -> cellData.getValue().getplaceProperty());
		colnameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());
	}
	@FXML
	private void handleArtistFilter() {
		String newValue = tfieldArtistFilter.getText();
		String filter = cboxArtistFilter.getSelectionModel().getSelectedItem();

		Predicate<User> predicate = new Predicate<User>(){

			@Override
			public boolean test(User user){
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if(filter == "Name"){
					if (user.getNachname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches first name
					} else if (user.getVorname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name
					}
					return false; // Does not match.)
				} else{
					if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1){
						return true; //Filter matches username
					}
					return false;
				}
			}
		};
		filteredArtists.setPredicate(predicate);

	}

	@FXML
	private void handleArtworkFilter() {
		String newValue = tfieldArtworkFilter.getText();
		String filter = cboxArtworkFilter.getSelectionModel().getSelectedItem();

		Predicate<Artwork> apredicate = new Predicate<Artwork>(){

			@Override
			public boolean test(Artwork artwork){
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if(filter == "Entstehungsjahr"){
					if (((Integer)artwork.getCreationyear()).toString().toLowerCase().indexOf(lowerCaseFilter)!= -1) {
						return true; // Filter matches first name
					}
				return false; // Does not match.
			} else if (filter == "Kunststil"){
				if (artwork.getArtstyle().toLowerCase().indexOf(lowerCaseFilter)!= -1){
					return true; //Filter matches Artstyle
				}
				return false;
			} else if (filter == "Name"){
				if(artwork.getName().toLowerCase().indexOf(lowerCaseFilter)!= -1){
					return true;//Filter matches Name.
				}
				return false;
			} return false;

			}
		};filteredArtworks.setPredicate(apredicate);


	}

	@FXML
	private void handleCollectionFilter() {
		String newValue = tfieldArtistFilter.getText();
		String filter = cboxArtistFilter.getSelectionModel().getSelectedItem();

		Predicate<Collection> cpredicate = new Predicate<Collection>() {

			@Override
			public boolean test(Collection c) {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if(filter == "Name"){
					if(c.getName().toLowerCase().indexOf(lowerCaseFilter)!= -1){
						return true; //Filter matches name.
					}
					return false;
				}return false;
			}

		};filteredCollections.setPredicate(cpredicate);

	}

	@FXML
	private void handleMuseumsFilter() {
		String newValue = tfieldArtistFilter.getText();
		String filter = cboxArtistFilter.getSelectionModel().getSelectedItem();
		Predicate<Museum> mpredicate = new Predicate<Museum>()
		{
//FXCollections.observableArrayList("Name", "Stadt");
			@Override
			public boolean test(Museum m) {
				if(newValue.isEmpty() /*|| newValue == null*/){
					return true; //empty filter.
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (filter == "Name"){
					if (m.getMname().toLowerCase().indexOf(lowerCaseFilter)!= -1){
						return true;
					}
					return false;
				} else if (filter == "Stadt"){
					if (m.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1){
						return true; //Filter matches city name.
					}
					return false;
				}
				return false;
			}

		};filteredMuseums.setPredicate(mpredicate);

	}
	private ObservableList<Collection> getCollectionsfromSQL() {
		String query = "select Sammlung.SammlungID, Sammlung.Name as SName, Gebaeude.Name from Sammlung "
				+ "inner join Sammlung_ausgestellt_in_Gebaeude on Sammlung.SammlungID = Sammlung_ausgestellt_in_Gebaeude.SammlungID "
				+ "inner join Gebaeude on Sammlung_ausgestellt_in_Gebaeude.GebaeudeID = Gebaeude.GebaeudeID "
				+ "inner join Adresse on Gebaeude.Adresse = Adresse.AdressenID";

		ObservableList<Collection> col_data = FXCollections.observableArrayList();
		try {
			PreparedStatement col_query = connection.prepareStatement(query);
			ResultSet results = null;
			results = col_query.executeQuery();
			while(results.next()){
				int sammlungID = results.getInt("SammlungID");
				String sname = results.getString("SName");
				String place = results.getString("Name");
				Collection sammlung=new Collection(sammlungID, sname);
				sammlung.setPlace(place);
				col_data.add(sammlung);
			}
		} catch (SQLException e) {
			System.out.println("Fehler bei Collection Query!");
			e.printStackTrace();
		}

		return col_data;
	}

	private ObservableList<User> getArtistsfromSQL() {
		ObservableList<User> artists = FXCollections.observableArrayList();
		String query = "select Benutzer.Benutzername, Benutzer.Vorname, Benutzer.Nachname, Benutzer.`E-Mail`, Adresse.Land, Adresse.Stadt, Adresse.Strasse, Adresse.Hausnummer from Kuenstler inner join Benutzer on Kuenstler.Benutzername = Benutzer.Benutzername inner join Adresse on Benutzer.Adresse = Adresse.AdressenID";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet results = preparedStatement.executeQuery();
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

	private ObservableList<Museum> getMuseumsfromSQL(){
		String query = "Select * from Museum inner join Gebaeude on Museum.GebaeudeID = Gebaeude.GebaeudeID inner join Adresse on Gebaeude.Adresse = Adresse.AdressenID";
		ObservableList<Museum> museums = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			museums = FXCollections.observableArrayList();
			while( rs.next()){
				String country = rs.getString("Land");
				String street = rs.getString("Strasse");
				String city = rs.getString("Stadt");
				String hnumber = rs.getString("Hausnummer");
				int id = rs.getInt(1);
				String name = rs.getString("Name");
				museums.add(new Museum(country, street, city, hnumber, id, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return museums;
	}

	private ObservableList<Artwork> getArtworksfromSQL(){
		ObservableList<Artwork> artworks = FXCollections.observableArrayList();
		String query = "Select * from Kunstwerk";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("KunstwerkID");
				String name = rs.getString("Name");
				int creationyear = rs.getInt("Entstehungsjahr");
				String artstyle = rs.getString("Kunststilname");
				artworks.add(new Artwork(name, creationyear, artstyle, id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artworks;

	}

}
