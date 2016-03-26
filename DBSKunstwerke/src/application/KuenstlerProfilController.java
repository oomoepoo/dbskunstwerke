package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class KuenstlerProfilController implements Initializable{
	private User u_object;
	private Atellier a_object;
	KuenstlerProfilModel kpm;
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
	private TableView<String> tviewArtstyles;
	@FXML
	private TableColumn<String, String> ap_nameColumn;
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

	public ObservableList<String> astyletable = kpm.getArtStylesfromSQL();
	public ObservableList<Artwork> aworktable = kpm.getArtworksfromSQL();
	public ObservableList<OpeningTime> otimetable = kpm.getOpeningTimesfromSQL();
	public ObservableList<Collection> collectiontable = kpm.getCollectionfromSQL();
	public ObservableList<ArtistComment> acommenttable = kpm.getArtistCommentsfromSQL();

	public KuenstlerProfilController(String benutzername) {
		this.kpm = new KuenstlerProfilModel(benutzername);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


}
