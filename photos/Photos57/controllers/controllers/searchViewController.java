package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import models.Album;
import models.Photo;
import javafx.scene.control.Alert.AlertType;
/**
 * 
 * @author DanHarding
 *
 */
public class searchViewController {

	@FXML
	private Spinner typeSpinner;
	@FXML
	private DatePicker fromDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private TextField firstTagField;
	@FXML
	private TextField secondTagField;
	@FXML
	private Spinner andOrSpinner;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCreateAlbum;
	@FXML
	private TextField albumTextField;
	

		ObservableList<String> types = FXCollections.observableArrayList("Date", "Tag");
		SpinnerValueFactory<String> typeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(types);
		
		//LocalDate firstDate = fromDate.getValue();
		//LocalDate secondDate = endDate.getValue();
		
		/*
		 * if (spinner value is date) {
		 * 		fromDate.setEditable(true);
		 * 		endDate.setEditable(true);
		 * } else if (spinner value is tag) {
		 * 		firstTagField.setEditable(true);
		 * 		secondTagField.setEditable(true);
		 */
		
		ObservableList<String> list = FXCollections.observableArrayList("Date", "Tag");
		SpinnerValueFactory<String> andOrValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(list);
		
		//String firstTag = firstTagField.getText();
		//String secondTag = secondTagField.getText();
		
		ArrayList<Photo> photos;
		/**
		 * 
		 * @param event: Create New Album button is pressed
		 * Creates a new album using the edited search results, adds this album to user's albums
		 */
		@FXML
		void createAlbum(ActionEvent event) {
			String albumName = albumTextField.getText();
			if (albumName.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("No Album Name Entered!");
				alert.setContentText("Need an album name to create a new album!");
				alert.showAndWait();
			} else {
				Album album = new Album(albumName, photos);
				//add album to list of user's albums
			}
		}
		
		/**
		 * 
		 * @param event: Search button is pressed
		 * Shows all user's photos that match search results
		 */
		@FXML
		void search(ActionEvent event) {
			//if spinner value is date
			if (true) {
				//for all pictures in album, set an array list equal to all photos that are in date range inclusive
				//show this list in photo details view
			} else if (true) {
				//this is if spinner value is tag
				
				//if second spinner value is "and"
				if (true) {
					//for all pics in album, set arraylist equal to all photos that have same tags as firstTag AND secondTag
					//show list in photo details view
				} else {
					//if second spinner value is "or"
					//set list equal to all photos that have either firstTag or secondTg
					//show list in photo details view
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Invalid search type!");
				alert.setContentText("Search type doesn't exist!");
				alert.showAndWait();
			}
		}

	
}
