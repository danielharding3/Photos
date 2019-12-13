package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * 
 * @author DanHarding
 *
 */
public class loginController {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button btnLogin;
	
	//String user = username.getText();
	//String pass = password.getText();
	
	/**
	 * 
	 * @param event: The Log In button being pressed
	 * This will log the user into their account and switch to the main app view
	 */
	@FXML
	void changeToAppView(ActionEvent event) throws Exception {
		
		//if username and password are a match
		if (username.getText().equals("user")) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/app.fxml"));
			GridPane root = (GridPane)loader.load();
			AppController appControl = loader.getController();
			Scene scene = new Scene(root);
		
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setTitle("Photo Gallery");
			window.show();
			
			//if admin
		} else if (username.getText().equals("admin") && password.getText().equals("admin")) {
			//System.out.println("test");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/adminView.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			adminViewController adminControl = loader.getController();
			Scene scene = new Scene(root);
		
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setTitle("Photo Gallery");
			window.show();
			
			//if user/password don't exist or are incorrect
		} else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User does not exist!");
			alert.setContentText("Username/password are incorrect!");
			alert.showAndWait();
		}
		
	}
	

}
