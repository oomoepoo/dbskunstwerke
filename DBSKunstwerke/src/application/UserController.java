package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * Controller Class for the User Window.
 * Handles everything that should happen in the User Window
 */
public class UserController implements Initializable{
	Connection conection;
	private Context context;
	@FXML
	private Label userlabel;
	@FXML
	private Label labelFName;
	@FXML
	private Label labelSName;
	@FXML
	private Label labelEMail;
	@FXML
	private Label labelStreet;
	@FXML
	private Label labelCountry;
	@FXML
	private Label labelCity;
	@FXML
	private VBox buttonBox;
	@FXML
	private TextField txtSearch;
	@FXML
	private CheckBox cboxMuseums;
	@FXML
	private CheckBox cboxArtists;
	@FXML
	private CheckBox cboxAtelliers;
	@FXML
	private CheckBox cboxArtworks;
	@FXML
	private Button btnSearch;
	@FXML
	public TableView<Bestellung> tableabdruck = new TableView<Bestellung>();
	@FXML
	public TableColumn<Bestellung, Integer> abd_abdruckid = new TableColumn<Bestellung, Integer>();
	@FXML
	public TableColumn<Bestellung, String> abd_bestellt = new TableColumn<Bestellung, String>();
	@FXML
	public TableColumn<Bestellung, String> abd_geliefert = new TableColumn<Bestellung, String>();
	@FXML
	public TableColumn<Bestellung, Integer> abd_price = new TableColumn<Bestellung, Integer>();

	public ObservableList<Bestellung> bestellungs_table = FXCollections.observableArrayList(new Bestellung(1, "2015-02-02 16:00:00", "2015-02-04 16:00:00", 60));

	public UserController(Context context){
		this.context = context;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		create_bestellung_from_sql();
		init_tables();
		setup_userlabels();

	}


	private void setup_userlabels() {
		userlabel.setText(context.getNutzer().getUsername());
		userlabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		labelSName.setText(context.getNutzer().getNachname());
		labelFName.setText(context.getNutzer().getVorname());
		labelEMail.setText(context.getNutzer().getEmail());
		labelCity.setText(context.getAddress().getCity());
		labelCountry.setText(context.getAddress().getCountry());
		labelStreet.setText(context.getAddress().getStreet() +" "+context.getAddress().getHnumber());
	}

	public void init_tables(){
		tableabdruck.setItems(bestellungs_table);
		abd_abdruckid.setCellValueFactory(cellData -> cellData.getValue().abdruckIDProperty().asObject());
		abd_bestellt.setCellValueFactory(cellData -> cellData.getValue().bestelldatumProperty());
		abd_geliefert.setCellValueFactory(cellData -> cellData.getValue().lieferdatumProperty());
		abd_price.setCellValueFactory(cellData -> cellData.getValue().preisProperty().asObject());


	}


	private void create_bestellung_from_sql() {
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
		PreparedStatement bestellungs_query = null;
		ResultSet results = null;
		String query = "Select Benutzer_bestellt_Abdruck.AbdruckID, Benutzer_bestellt_Abdruck.Bestelldatum, Benutzer_bestellt_Abdruck.Lieferdatum, Abdruck.Preis "
				+ "from Benutzer_bestellt_Abdruck "
				+ "inner join Abdruck on Benutzer_bestellt_Abdruck.AbdruckID = Abdruck.AbdruckID where Benutzer_bestellt_Abdruck.Benutzername = ?";
		try {
			bestellungs_query = conection.prepareStatement(query);
			bestellungs_query.setString(1, context.getNutzer().getUsername());
			results = bestellungs_query.executeQuery();
			while (results.next()){
				int abid = results.getInt("AbdruckID");
				String bestelldatum = results.getString("Bestelldatum");
				String lieferdatum = results.getString("Lieferdatum");
				int preis = results.getInt("Preis");
				bestellungs_table.add(new Bestellung(abid, bestelldatum, lieferdatum, preis));
				System.out.println(bestellungs_table.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void SignOut (ActionEvent event){
		((Node)event.getSource()).getScene().getWindow().hide();

		/*
		 * Set up a new User window.
		 * I should probably export this to another class and or method
		 */
		// TODO: Think about a solution, because we will need a few more windows...

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/Login.fxml").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	@FXML
	private void handleSearchButtonAction (ActionEvent ae){
		String query_artists = "Select Benutzername, Vorname, Nachname, Adresse, E-Mail from Benutzer inner join Kuenstler on Benutzer.Benutzername = Kuenstler.Benutzername where "
				+ "Benutzer.Vorname like ? or Benutzer.Nachname like ?";
		String query_museums = "Select *  from Gebauede inner join Mueseum on Gebaeude.GebauedeID=Museum.GebaeudeID where Gebaeude.Name like ?";
		String query_ateliers = "Select *  from Gebauede inner join Atelier on Gebaeude.GebauedeID=Atelier.GebaeudeID where Gebaeude.Name like ?";
		String query_artworks = "Select * from Kunstwerke where Name like ?";
		String search = txtSearch.getText()+"%";
		ArrayList<String> querys = new ArrayList<String>();
		ArrayList<ResultSet> results = new ArrayList<ResultSet>();
		ArrayList<PreparedStatement> s_querys = new ArrayList<PreparedStatement>();
		if (cboxArtists.isSelected()){
			querys.add(query_artists);
		}
		if (cboxArtworks.isSelected()){
			querys.add(query_artworks);
		}
		if (cboxAtelliers.isSelected()){
			querys.add(query_ateliers);
		}
		if (cboxMuseums.isSelected()){
			querys.add(query_museums);
		}
		for (String q : querys){
			try{
				s_querys.add(conection.prepareStatement(q));
			} catch(SQLException sqle) {
				System.out.println("Fehler bei Statement: "+q);
				sqle.printStackTrace();
			}
		}
		for (PreparedStatement ps : s_querys){
			try {
				ps.setString(1, search);
				if (ps.getParameterMetaData().getParameterCount()>1){
					ps.setString(2, search);
				}

			} catch (SQLException sql){
				System.out.println("Fehler beim Vorbereite von Statement: "+ps.toString());
				sql.printStackTrace();
			}
			try {
				results.add(ps.executeQuery());
			} catch (SQLException e) {
				System.out.println("Fehler beim Ausführen von Statement: "+ps.toString());
				e.printStackTrace();
			}

		}

	}

}
