package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddUserController implements Initializable{
	public AddUserModel AddUserModel = new AddUserModel();
	@FXML
	private Label StatusLabel;
	@FXML
	private Button AddBtn;
	@FXML
	private Button CheckBtn;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtFirstname;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtCountry;
	@FXML
	private TextField txtNumber;
	@FXML
	private CheckBox cboxArtist;
	@FXML
	private void handleAddButtonAction (ActionEvent e){
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String email = txtEmail.getText();
		String vorname = txtFirstname.getText();
		String nachname = txtSurname.getText();
		Boolean kuenstler = cboxArtist.isSelected();

		AddUserModel.AddUsersData(username, password, email, vorname, nachname, kuenstler);


	}

	@FXML
	private void handleCheckButtonAction(ActionEvent event){
		try {
			if(AddUserModel.isUnique(txtUsername.getText())){
				AddBtn.setDisable(false);
			} else {
				StatusLabel.setText("Nutzername existiert bereits!");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StatusLabel.setText("SQL-Fehler!");

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AddBtn.setDisable(true);
		// TODO Auto-generated method stub

	}

}
