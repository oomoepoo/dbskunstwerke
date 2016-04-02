package controll;

import java.net.URL;
import java.util.ResourceBundle;

import backend.Artwork;
import backend.KunstwerkBewertung;
import backend.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.KunstwerkProfilModel;
import model.KunstwerkProfilModel.Abdruck;

public class KunstwerkProfilController implements Initializable {

	private int kunstwerk;
	private String nutzer;
	private Artwork kwerk;
	@FXML
	private Label labelKWName;
	@FXML
	private Label labelCYear;
	@FXML
	private Label labelArtstyle;
	@FXML
	private TableView<User> tviewArtists;
	@FXML
	private TableColumn<User, String> artistnameColumn;
	@FXML
	private TableView<Abdruck> tview_Abdruck;
	@FXML
	private TableColumn<Abdruck, Integer> breiteColumn;
	@FXML
	private TableColumn <Abdruck, Integer> hoeheColumn;
	@FXML
	private TableColumn <Abdruck, Integer> preisColumn;
	@FXML
	private TableColumn <Abdruck, Integer> idColumn;
	@FXML
	private Button btnBestellen;
	@FXML
	private Label labelStandort;
	@FXML
	private TableView<KunstwerkBewertung> tview_Bewertung;
	@FXML
	private TableColumn<KunstwerkBewertung, Integer> bewertungColumn;
	@FXML
	private TableColumn<KunstwerkBewertung, String> kommentarColumn;
	@FXML
	private TableColumn<KunstwerkBewertung, String> userColumn;
	@FXML
	private ListView<Integer> lviewWertung;
	@FXML
	private TextField tareaWertung;
	@FXML
	private Button btnSendWertung;
	private KunstwerkProfilModel kwpm = new KunstwerkProfilModel();

	public ObservableList<Integer> wertungen;
	public ObservableList<KunstwerkBewertung> bewertungen;
	public ObservableList<User> artists;
	public ObservableList<Abdruck> abdrucke;


	// Constructor: Mal wieder, um Werte zu übergeben.
	public KunstwerkProfilController(Integer kunstwerk, String nutzer) {
		this.kunstwerk = kunstwerk;
		this.nutzer = nutzer;
		bewertungen = kwpm.getBewertungenfromSQL(kunstwerk);
		artists = kwpm.getArtistsfromSQL(kunstwerk);
		abdrucke = kwpm.getAbdruckfromSQL(kunstwerk);
	}

	public KunstwerkProfilController (Artwork kunstwerk, String nutzer){
		this.kunstwerk = kunstwerk.getId();
		this.kwerk = kunstwerk;
		this.nutzer = nutzer;
		bewertungen = kwpm.getBewertungenfromSQL(kunstwerk.getId());
		artists = kwpm.getArtistsfromSQL(kunstwerk.getId());
		abdrucke = kwpm.getAbdruckfromSQL(kunstwerk.getId());
	}

	// Event Listener on Button[#btnBestellen].onAction
	@FXML
	public void handleOrderBtnAction(ActionEvent event) {
		Abdruck abdruck = tview_Abdruck.getSelectionModel().getSelectedItem();
		kwpm.bestelle_abdruck(abdruck, kunstwerk, nutzer);
		//IN THEORY, this ought to work.
		//Delete the selected Element from the list.

		// TODO add some Dialogue thingie for confirmation.

		abdrucke.remove(tview_Abdruck.getSelectionModel().getSelectedIndex());
	}
	// Event Listener on Button[#btnSendWertung].onAction
	@FXML
	public void handleSendBtnAction(ActionEvent event) {
		String text;
		if (tareaWertung.textProperty().isEmpty().get()){
			text = "Kein Kommentar.";
		}else{
			text = tareaWertung.getText().trim();
		}
		Integer bewertung = lviewWertung.getSelectionModel().getSelectedItem();
		//Nutzer hat nichts ausgewählt?
		//Nur, um sicher zu sein: Muss entweder 0 oder null sein...
		if(bewertung == 0 || bewertung == null){
			bewertung = 1;
		}
		KunstwerkBewertung kbewertung = new KunstwerkBewertung(text, nutzer, kunstwerk, bewertung);
		kwpm.sendBewertung(kbewertung);
		bewertungen.add(kbewertung);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init_cells();
		init_tables();
		init_labels();
	}

	public void init_labels() {


	}

	private void init_cells() {
		artistnameColumn.setCellValueFactory(cellData -> cellData.getValue().getusernameProperty());

		breiteColumn.setCellValueFactory(cellData -> cellData.getValue().getbreiteProperty().asObject());
		hoeheColumn.setCellValueFactory(cellData -> cellData.getValue().gethoeheProperty().asObject());
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getidProperty().asObject());
		preisColumn.setCellValueFactory(cellData -> cellData.getValue().getpreisProperty().asObject());

		bewertungColumn.setCellValueFactory(cellData -> cellData.getValue().getberwertungProperty().asObject());
		userColumn.setCellValueFactory(cellData->cellData.getValue().getkommentatorProperty());
		kommentarColumn.setCellValueFactory(cellData -> cellData.getValue().getkommentarProperty());

	}

	private void init_tables() {
		lviewWertung.setOrientation(Orientation.HORIZONTAL);
		lviewWertung.setItems(wertungen);
		tview_Abdruck.setItems(abdrucke);
		tviewArtists.setItems(artists);
		tview_Bewertung.setItems(bewertungen);
	}
}
