package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

		AddUserModel.AddUsersData(getUserData(), cboxArtist.isSelected());
		AddUserModel.addAddressData(txtUsername.getText().trim(), getAddressData());
		/*
		System.out.println("UserData:");
		for (int i=0;i < getUserData().size();i++){
			System.out.println(i);
			System.out.println(getUserData().get(i));
		}
		System.out.println("AddressData:");
		for (int i=0;i < getAddressData().size();i++){
			System.out.println(i);
			System.out.println(getAddressData().get(i));
		}*/
		StatusLabel.setText("Nutzereintragung erfolgreich!");
		AddBtn.setDisable(true);



	}

	@FXML
	private void handleCheckButtonAction(ActionEvent event){
		try {
			if(AddUserModel.isUnique(txtUsername.getText().trim())){
				AddBtn.setDisable(false);
				StatusLabel.setText("Alles Okay! Bitte Hinzufügen klicken!");
			} else {
				StatusLabel.setText("Nutzername existiert bereits!");
			}
		} catch (SQLException e1) {
			StatusLabel.setText("SQL-Fehler!");
			e1.printStackTrace();
		}

	}

	/**
	 * Gets data from the TextFields and compiles an ArrayList out of it.
	 * Order is <b>always</b> username, password, email, firstname, lastname
	 *
	 * @return an ArrayList of Strings with the userdata.
	 */
	private ArrayList<String> getUserData(){
		String username = txtUsername.getText().trim();
		String password = txtPassword.getText().trim();
		String email = txtEmail.getText().trim();
		String vorname = txtFirstname.getText().trim();
		String nachname = txtSurname.getText().trim();
		ArrayList<String> userData = new ArrayList<String>();
		userData.add(username);
		userData.add(password);
		userData.add(email);
		userData.add(vorname);
		userData.add(nachname);
		return userData;




	}

	/**
	 * Gets Data from the Textfields and builds an ArrayList out of it
	 * Order is <b>always</b>:
	 * country, city, street, number
	 *
	 * @return an ArrayList of Strings with the address data.
	 */

	private ArrayList<String> getAddressData(){
		String land = txtCountry.getText().trim();
		String stadt = txtCity.getText().trim();
		String strasse = txtStreet.getText().trim();
		String hausnummer = txtNumber.getText().trim();
		ArrayList<String> addressData = new ArrayList<String>();
		addressData.add(land);
		addressData.add(stadt);
		addressData.add(strasse);
		addressData.add(hausnummer);
		return addressData;




	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AddBtn.setDisable(true);
		// TODO Auto-generated method stub

	}

}
