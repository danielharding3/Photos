package controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import models.*;
import views.ModelAdder;

public class AppController {

	@FXML
	protected AlbumsController albumsController;
	@FXML
	protected PhotoDetailsController photodetailsController;
	
	//TODO: Have this take a User u as a parameter. This function will be called on the loaded user from the login screen.
	public void start() {
		
		ModelAdder ma = ModelAdder.getInstance();
		//Test user data.
		User u = new User("test","test");
		u.albums.add(new Album("testAlbum",new ArrayList<Photo>()));
		ma.addPhoto(u, u.albums.get(0));
		u.tags.add(new SingleValueTag("test", "test"));
		u.albums.get(0).photos.get(0).tags.add(u.tags.get(0));
		
		albumsController.start(u);
		photodetailsController.start(u, u.albums.get(0).photos.get(0));
		
		if(!albumsController.albumListView.getItems().isEmpty()) {
			albumsController.albumListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Album>() {
				@Override
				public void changed(ObservableValue<? extends Album> observable, Album oldVal, Album newVal) {
					//TODO: Add a photosController.start(newVal) or some other thing to initialize the view
				}
			});
		}
	}
}
