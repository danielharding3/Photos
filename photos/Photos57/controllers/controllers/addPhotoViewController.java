package controllers;

import java.time.LocalDate;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * 
 * @author DanHarding
 *
 */
public class addPhotoViewController {
	
	@FXML
	private TextField photoText;
	@FXML
	private TextField captionText;
	@FXML
	private DatePicker date;
	@FXML
	private TextField tagsText;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;
	
	/**
	 * 
	 * @param event: Add button is pressed
	 * This will add photo to user's album, and return to photos view in app
	 */
	@FXML
	void addPhoto(ActionEvent event) throws Exception {
		String photoPath = photoText.getText();
		String caption = captionText.getText();
		LocalDate localDate = date.getValue();
		String tags = tagsText.getText();
		Image image = new Image(photoPath);
		//Photo photo = new Photo(image, caption, tags);
		//add photo to list of photos
		
		
		//go back to photos view in app.fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/app.fxml"));
		GridPane root = (GridPane)loader.load();
		AppController appControl = loader.getController();
		Scene scene = new Scene(root);
	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Photo Gallery");
		window.show();
	}
	
	/**
	 * 
	 * @param event: Cancel button is pressed
	 * This will redirect user to photos view in app.fxml
	 */
	@FXML
	void cancel(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/app.fxml"));
		GridPane root = (GridPane)loader.load();
		AppController appControl = loader.getController();
		Scene scene = new Scene(root);
	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Photo Gallery");
		window.show();
	}
}
