package controll;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import backend.Adresse;
import backend.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AddUserModel;


public class AddUserController implements Initializable{
	public AddUserModel AddUserModel = new AddUserModel();
	/*
	 * Initialize the various Elements of the window with their FX-ID
	 */
	@FXML
	private Label StatusLabel;
	@FXML
	private Button BackBtn;
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

	/**
	 * Handle the Add-Button press.
	 * Get the User-Input and deliver it to the Model.
	 *
	 * @param e
	 */
	@FXML
	private void handleAddButtonAction (ActionEvent e){

		User userdata = getUserData();
		Adresse adressdata = getAddressData();
		AddUserModel.AddUsersData(userdata, cboxArtist.isSelected());
		AddUserModel.addAddressData(userdata.getUsername(), adressdata);
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
	/**
	 * Handle the Back-Button presses.
	 * closes this window and opens the Login window again.
	 *
	 * @param event
	 */
	@FXML
	private void handleBackButtonAction(ActionEvent event){

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
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * Handle Check Button presses.
	 * checks if the Username is unique.
	 *
	 * @param event
	 */
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
	 * Order is <b>always</b>:
	 * username, password, email, firstname, lastname
	 *
	 * @return an ArrayList of Strings with the userdata.
	 */
	private User getUserData(){
		String username = txtUsername.getText().trim();
		String password = txtPassword.getText().trim();
		String email = txtEmail.getText().trim();
		String vorname = txtFirstname.getText().trim();
		String nachname = txtSurname.getText().trim();
		/*ArrayList<String> userData = new ArrayList<String>();
		userData.add(username);
		userData.add(password);
		userData.add(email);
		userData.add(vorname);
		userData.add(nachname);*/
		return new User(username, vorname, nachname, password, null, email);




	}

	/**
	 * Gets Data from the Textfields and builds an Adress object out of it
	 * Notice: adressID is 0 for this one, cause it isn't generated until
	 * it's actually in the database (auto-increment.)
	 * Still in the constructor for completeness' sake.
	 *
	 * @return an Adress object.
	 *
	 */

	private Adresse getAddressData(){
		String land = txtCountry.getText().trim();
		String stadt = txtCity.getText().trim();
		String strasse = txtStreet.getText().trim();
		String hausnummer = txtNumber.getText().trim();
		/*ArrayList<String> addressData = new ArrayList<String>();
		addressData.add(land);
		addressData.add(stadt);
		addressData.add(strasse);
		addressData.add(hausnummer);*/
		return new Adresse(0, land, stadt, strasse, hausnummer);




	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AddBtn.setDisable(true);

	}

}
