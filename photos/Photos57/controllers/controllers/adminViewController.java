package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.User;
import javafx.scene.Node;
/**
 * 
 * @author DanHarding
 *
 */
public class adminViewController implements Initializable {

	@FXML
	private Button btnNewUser;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnLogOut;
	@FXML
	private TextField userText;
	@FXML
	private TextField passText;
	@FXML
	private TableView<User> table;
	@FXML
	private TableColumn<User, String> usernameCol;
	@FXML
	private TableColumn<User, String> passwordCol;
	

	ObservableList<User> obsList = FXCollections.observableArrayList();
	
//	if (!table.getItems().isEmpty()) {
//		table.getSelectionModel().select(0);
//		User u = table.getSelectionModel().getSelectedItem();
//		userText.setText(u.username);
//		passText.setText(u.password);
//	}
	
	//User u = table.getSelectionModel().getSelectedItem();
	//table.setItems(obsList);
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
		passwordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
		//table.setItems(obsList);
	}
	
	
//	@FXML
//	void deleteUser(ActionEvent event) {
//		
//	}
	
	/**
	 * 
	 * @param event: Create New User button is pressed
	 * This will create a new user with the username and password texts from the TextFields
	 */
	@FXML
	void createUserView(ActionEvent event) {
		
	}
	
	
	/**
	 * 
	 * @param event: Log Out button is pressed
	 * This will log the user out of the Admin account and switch to the Login Screen view
	 */
	@FXML
	void changeToLoginView(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/loginScreen.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		loginController loginControl = loader.getController();
		Scene scene = new Scene(root);
	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Photo Gallery");
		window.show();
	}


	
	
}
