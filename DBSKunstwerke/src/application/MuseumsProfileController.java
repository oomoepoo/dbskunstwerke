package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

public class MuseumsProfileController {
	@FXML
	private TableView<OpeningTime> openingTable;
	@FXML
	private TableView<Collection> tableCollection;
	@FXML
	private TextField txtMStreet;
	@FXML
	private TextField txtMNumber;
	@FXML
	private TextField txtMCountry;
	@FXML
	private TextField txtMCity;
	@FXML
	private TextField txtMName;
	@FXML
	private TableView<MuesumsComment> tableMComments;
	@FXML
	private TextArea txtEnterComment;
	@FXML
	private Button btnAddComment;

}
