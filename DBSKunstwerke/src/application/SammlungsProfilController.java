package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class SammlungsProfilController implements Initializable{
	private SammlungsProfilModel spm;
	public Collection collection;

	@FXML
	private TableView<Artwork> tview_artworks;
	@FXML
	private TableColumn<Artwork, String> aw_nameColumn;
	@FXML
	private Label labelColName;
	@FXML
	private Label labelColPlace;

	public ObservableList<Artwork> artworks = spm.createArtworkList(collection);

	public SammlungsProfilController(int colid) {
		spm = new SammlungsProfilModel();
		this.collection = spm.getCollectionfromSQL(colid);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelColName.setText(collection.getName());
		labelColPlace.setText(collection.getPlace());
		aw_nameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());
		tview_artworks.getItems();

	}
// TODO : Komisches Klickding adden.

}
