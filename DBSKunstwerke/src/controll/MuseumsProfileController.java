package controll;

import java.net.URL;
import java.util.ResourceBundle;

import backend.MuesumsComment;
import backend.Museum;
import backend.OpeningTime;
import backend.collection_view;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.MuseumsProfileModel;
import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

public class MuseumsProfileController implements Initializable{
	public int MuseumsID;
	public Museum museum;
	public String username;
	MuseumsProfileModel mprofilemodel;

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

	ObservableList<OpeningTime> op_list;
	ObservableList<collection_view> col_list;
	ObservableList<MuesumsComment> com_list;


	public MuseumsProfileController(String username, Museum museum) {
		this.username = username;
		this.MuseumsID = museum.getID();
		this.museum = museum;
		this.mprofilemodel = new MuseumsProfileModel(MuseumsID);
		op_list = mprofilemodel.create_ot_from_sql();
		col_list = mprofilemodel.create_col_from_sql();
		com_list = mprofilemodel.create_com_from_sql();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		init_tables();
		txtMStreet.setText(museum.getStreet());

		txtMNumber.setText(museum.getHnumber());

		txtMCountry.setText(museum.getCountry());

		txtMCity.setText(museum.getCity());

		txtMName.setText(museum.getMname());

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
		op_weekdaycolumn.setCellValueFactory(cellData -> cellData.getValue().getweekdayProperty());
		op_fromcolumn.setCellValueFactory(cellData -> cellData.getValue().getvonProperty());
		op_tocolumn.setCellValueFactory(cellData -> cellData.getValue().getbisProperty());

		collectionNameColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());
		col_bisColumn.setCellValueFactory(cellData -> cellData.getValue().bisProperty());
		col_seitColumn.setCellValueFactory(cellData -> cellData.getValue().vonProperty());

		commentColumn.setCellValueFactory(cellData -> cellData.getValue().getkommentarProperty());
		userColumn.setCellValueFactory(cellData -> cellData.getValue().getkommentatorProperty());

	}

}
