package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * Controller Class for the User Window.
 * Handles everything that should happen in the User Window
 */
public class UserController implements Initializable{
	@FXML
	private Label userlabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void getUsername(String user) {
		userlabel.setText(user);

	}

	public void SignOut (ActionEvent event){
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
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
