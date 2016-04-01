package controll;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import backend.Adresse;
import backend.ArtistComment;
import backend.Artwork;
import backend.Atellier;
import backend.Collection;
import backend.OpeningTime;
import backend.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.KuenstlerProfilModel;
/**
 * Controller class for the Artist's profile
 * Handles all kinds of Labels and a bunch of tables.
 *
 * Please note: Needs to be called via the custom constructor, because we
 * need some initialized data for Comments and some labels.
 *
 *
 * @author jan
 *
 */
public class KuenstlerProfilController implements Initializable{
	KuenstlerProfilModel kpm;
	private String nutzer;
	private String kuenstler;
	//Tab1
	@FXML
	private Label labelFName;
	@FXML
	private Label labelLName;
	@FXML
	private Label labelStreet;
	@FXML
	private Label labelCity;
	@FXML
	private Label labelCountry;
	@FXML
	private Label labelUser;
	@FXML
	private Label labelEMail;
	@FXML
	private TableView<ObservableList<String>> tviewArtstyles;
	@FXML
	private TableColumn<ObservableList<String>, String> ap_nameColumn;
	//Tab 2
	@FXML
	private TableView<Artwork> tviewArtworks;
	@FXML
	private TableColumn<Artwork, String> aw_nameColumn;
	@FXML
	private TableColumn<Artwork, String> aw_styleColumn;
	@FXML
	private TableColumn<Artwork, Integer> aw_yearColum;
	//Tab3
	@FXML
	private TableView<OpeningTime> tviewOpeningtimes;
	@FXML
	private TableColumn<OpeningTime, String> aa_opdayColumn;
	@FXML
	private TableColumn<OpeningTime, String> aa_vonColumn;
	@FXML
	private TableColumn<OpeningTime, String> aa_bisColumn;
	@FXML
	private Label labelAteliername;
	@FXML
	private Label labelAStreet;
	@FXML
	private Label labelACity;
	@FXML
	private Label labelACountry;
	@FXML
	private TableView<Collection> tviewCollections;
	@FXML
	private TableColumn<Collection, String> aa_cnameColumn;
	//Tab4
	@FXML
	private TextArea tfieldKommentar;
	@FXML
	private TableView<ArtistComment> tviewArtistComment;
	@FXML
	private TableColumn<ArtistComment, String> ac_userColumn;
	@FXML
	private TableColumn<ArtistComment, String> ac_commentColumn;
	@FXML
	private TableColumn<ArtistComment, String> ac_timestampColumn;
	@FXML
	private Button btnSendComment;

	public ObservableList<ObservableList<String>> astyletable;
	public ObservableList<Artwork> aworktable;
	public ObservableList<OpeningTime> otimetable;
	public ObservableList<Collection> collectiontable;
	public ObservableList<ArtistComment> acommenttable;

	/**
	 * Constructor for the Controller class
	 * Also initializes the Model class with some initial values.
	 *
	 * @param benutzername the user visiting this profile
	 * @param kuenstlername the artist whose profile is visited
	 */
	public KuenstlerProfilController(String benutzername, String kuenstlername) {
		this.kpm = new KuenstlerProfilModel(kuenstlername);
		this.nutzer = benutzername; //Der Nutzer, der auf das Profil zugreift.
		this.kuenstler = kuenstlername; //Der Kuenstler, dessen Profil angesehen wird.
		this.astyletable = kpm.getArtStylesfromSQL();
		this.aworktable = kpm.getArtworksfromSQL();
		this.otimetable = kpm.getOpeningTimesfromSQL();
		this.collectiontable = kpm.getCollectionfromSQL();
		this.acommenttable = kpm.getArtistCommentsfromSQL();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init_userlabels();
		init_atelierlabels();
		init_tablecells();
		init_tables();

	}
	/**
	 * Connects all the tables with their respective {@link ObservableList}.
	 */
	private void init_tables() {
		tviewArtstyles.setItems(astyletable);
		tviewArtistComment.setItems(acommenttable);
		tviewArtworks.setItems(aworktable);
		tviewCollections.setItems(collectiontable);
		tviewOpeningtimes.setItems(otimetable);
	}
	/**
	 * Sets the CellValueFactory for all the various table columns present in this window.
	 */

	private void init_tablecells() {
		ap_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));

		aw_nameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());
		aw_styleColumn.setCellValueFactory(cellData -> cellData.getValue().getartstyleProperty());
		aw_yearColum.setCellValueFactory(cellData -> cellData.getValue().getcreationyearProperty().asObject());

		aa_opdayColumn.setCellValueFactory(cellData -> cellData.getValue().getweekdayProperty());
		aa_vonColumn.setCellValueFactory(cellData -> cellData.getValue().getvonProperty());
		aa_bisColumn.setCellValueFactory(cellData -> cellData.getValue().getbisProperty());
		aa_cnameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());

		 ac_userColumn.setCellValueFactory(cellData -> cellData.getValue().getkommentatorProperty());
		 ac_commentColumn.setCellValueFactory(cellData -> cellData.getValue().getkommentarProperty());
		 ac_timestampColumn.setCellValueFactory(cellData -> cellData.getValue().getzeitpunktProperty());

	}
	/**
	 * Initializes the various labels related to the workshop
	 */
	private void init_atelierlabels() {
		Atellier atelier = kpm.getAtellier();
		String street = atelier.getStreet() + " "+atelier.getHnumber();
		labelAteliername.setText(atelier.getName());
		labelACity.setText(atelier.getCity());
		labelACountry.setText(atelier.getCountry());
		labelAStreet.setText(street);

	}
	/**
	 * initializes labels related to the artist.
	 */
	private void init_userlabels() {
		User user = kpm.getUser();
		Adresse adresse = user.getAdress();
		labelFName.setText(user.getVorname());
		labelLName.setText(user.getNachname());
		labelUser.setText(user.getUsername());
		labelEMail.setText(user.getEmail());
		labelACity.setText(adresse.getCity());
		labelACountry.setText(adresse.getCity());
		labelAStreet.setText(adresse.getStreet() +" "+ adresse.getHnumber() );

	}
	@FXML
	private void handleCommentBtnAction(ActionEvent event){
		String comment = tfieldKommentar.getText();
		kpm.add_artist_comment(comment, nutzer);
		acommenttable.add((new ArtistComment(comment, nutzer, kuenstler, (new Timestamp(System.currentTimeMillis())).toString())));
	}


}
