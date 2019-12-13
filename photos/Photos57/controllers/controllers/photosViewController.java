package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import models.Photo;
/**
 * 
 * @author DanHarding
 *
 */
public class photosViewController {

	@FXML
	private ListView listview;
	@FXML
	private Button btnPlus;
	
	//photos arraylist as input
	ObservableList<Photo> photos = FXCollections.observableArrayList();
	
	private ImageView imageView;
	
	//this is to set obsList to user's list of photos
	//photos.setData(listOfPhotos);
	
	/*
	photos.setCellFactory(param -> new ListCell<Photo>() {

		public void updateItem(Photo photo, boolean empty) {
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				for (Photo p : listOfPhotos) {
					setText(p.caption + "\n" + p.tags);
					setGraphic(p.imageData);
				}
			}
		}
	});
	*/
	/**
	 * 
	 * @param event: Plus button is pressed
	 * This will redirect user to addPhotoView where they can add their specified photo to the album
	 */
	@FXML
	void goToCreatePhotoView(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/addPhotoView.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		addPhotoViewController addPhotoControl = loader.getController();
		Scene scene = new Scene(root);
	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Photo Gallery");
		window.show();
	}
	
	
	
}
