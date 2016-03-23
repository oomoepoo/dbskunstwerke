package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

public class MuseumsProfileController implements Initializable{
	public int MuseumsID;
	public String username;
	MuseumsProfileModel mprofilemodel = new MuseumsProfileModel(MuseumsID);

	@FXML
	private TableView<OpeningTime> openingTable;
	@FXML
	private TableView<collection_view> tableCollection;
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
	@FXML
	private TableColumn<OpeningTime,String> op_weekdaycolumn;
	@FXML
	private TableColumn<OpeningTime, String> op_fromcolumn;
	@FXML
	private TableColumn<OpeningTime, String> op_tocolumn;
	@FXML
	private TableColumn <collection_view, String> collectionNameColumn;
	@FXML
	private TableColumn <collection_view, String> col_seitColumn;
	@FXML
	private TableColumn <collection_view, String> col_bisColumn;
	@FXML
	private TableColumn <MuesumsComment, String> userColumn;
	@FXML
	private TableColumn <MuesumsComment, String> commentColumn;

	public ObservableList<OpeningTime> op_list = mprofilemodel.create_ot_from_sql();
	public ObservableList<collection_view> col_list = mprofilemodel.create_col_from_sql();
	public ObservableList<MuesumsComment> com_list = mprofilemodel.create_com_from_sql();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init_tables();
	}

	@FXML
	private void handleCommentBtnAction(ActionEvent event){
		String comment = txtEnterComment.getText();
		mprofilemodel.add_museum_comment(comment,MuseumsID, username);
		com_list.add(new MuesumsComment(comment, username, MuseumsID));
	}

	private void init_tables() {
		mprofilemodel.create_col_from_sql();
		mprofilemodel.create_com_from_sql();
		mprofilemodel.create_ot_from_sql();
		openingTable.setItems(op_list);
		tableCollection.setItems(col_list);
		tableMComments.setItems(com_list);
		init_columns();

	}
	private void init_columns() {
		op_weekdaycolumn.setCellValueFactory(cellData -> cellData.getValue().weekdayProperty());
		op_fromcolumn.setCellValueFactory(cellData -> cellData.getValue().vonProperty());
		op_tocolumn.setCellValueFactory(cellData -> cellData.getValue().bisProperty());

		collectionNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		col_bisColumn.setCellValueFactory(cellData -> cellData.getValue().bisProperty());
		col_seitColumn.setCellValueFactory(cellData -> cellData.getValue().vonProperty());

		commentColumn.setCellValueFactory(cellData -> cellData.getValue().kommentarProperty());
		userColumn.setCellValueFactory(cellData -> cellData.getValue().kommentatorProperty());

	}
	public void set_data(String username, int mid){
		this.username = username;
		this.MuseumsID = mid;
	}

}
