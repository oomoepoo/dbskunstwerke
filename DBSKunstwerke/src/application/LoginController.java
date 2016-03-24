package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for the Log-In window.
 * Handles everything that should happen in the Window
 * (I.e Labels, buttons... whatever.)
 */

public class LoginController implements Initializable {
	public LogInModel loginModel = new LogInModel();
	Context currentContext = new Context();


	@FXML
	private Button loginBtn;
	@FXML
	private Label isConnected;
	@FXML
	private TextField userField;
	@FXML
	private TextField passField;
	@FXML
	private void handleRegisterButtonAction (ActionEvent event){
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root;
			root = loader.load(getClass().getResource("/application/AddUser.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DBS-Kunstwerke: Nutzer hinzufügen");
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}


	@FXML
	private void handleSubmitButtonAction (ActionEvent event){
		//get user and password from Textfields...
		String user = userField.getText();
		String pass = passField.getText();
		if (user.isEmpty() || pass.isEmpty()){
			isConnected.setText("Passwort oder Nutzername leer!");
			return;
		}
		try {
			if (loginModel.isLogIn(user, pass)){
				isConnected.setText("Log-In korrekt!");
				User u_object = loginModel.getUserfromsql(user, pass);
				currentContext.setNutzer(u_object);
				URL url = getClass().getResource("/application/User.fxml");
				//hide the old window (FX-Magic, for all I know :v)
				((Node)event.getSource()).getScene().getWindow().hide();

				/*
				 * Set up a new User window.
				 * I should probably export this to another class and or method
				 */
				//TODO: Think about a solution, because we will need a few more windows...

				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				UserController userController = new UserController(currentContext);
				loader.setController(userController);
				loader.setLocation(url);
				Pane root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DBS-Kunstwerke: "+user);
				primaryStage.show();


			} else {
				isConnected.setText("Log-In nicht korrekt!");
			}
		} catch (SQLException e) {
			isConnected.setText("Log-In nicht korrekt!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isConnected()){
			isConnected.setText("Verbunden");
		}
		//FX-Magic: Disable Login-Button until Fields have been filled with text

		BooleanBinding booleanBind = userField.textProperty().isEmpty().or(passField.textProperty().isEmpty());
		loginBtn.disableProperty().bind(booleanBind);

	}


}
