package views;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Album;
import models.MultiValueTag;
import models.Photo;
import models.SingleValueTag;
import models.Tag;
import models.User;
/**
 * 
 * @author Connor Magee
 *
 */
public class ModelAdder {
	private static ModelAdder instance = null;
	
	private Stage modalStage = new Stage();
	private VBox modalVBox = new VBox(20);
	private Button okButton = new Button("Ok");
	private Button cancelButton = new Button("Cancel");
	
	public static ModelAdder getInstance() {
		if(instance == null) {
			instance = new ModelAdder();
		}
		return instance;
	}
	
	/**
	 * Initializes the rudimentary modal components shared by the rest of the singleton's methods.
	 */
	private void resetModal() {
		modalStage.setTitle("");
		modalVBox = new VBox(20);
		modalVBox.setAlignment(Pos.BASELINE_CENTER);
		
		modalVBox.getChildren().clear();
		
		okButton.setOnAction(null);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				modalStage.close();				
			}
			
		});
	}
	
	/**
	 * Creates a modal that has basic inputs for adding an album to a user's gallery
	 * @param u: The user that is adding an album to their account
	 */
	public void addAlbum(User u) {
		resetModal();
		modalStage.setTitle("Add Album");
		
		TextField albumNameTextField = new TextField();
		Text message = new Text("Enter an Album Name Below");
		
		modalVBox.getChildren().add(message);
		modalVBox.getChildren().add(albumNameTextField);
		modalVBox.getChildren().add(okButton);
		modalVBox.getChildren().add(cancelButton);
		
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String resultName = albumNameTextField.getText();
				if(!resultName.equalsIgnoreCase("") && !u.albums.stream().filter(o -> o.name.equals(resultName)).findFirst().isPresent()) {
					Album result = new Album(resultName, new ArrayList<Photo>());
					u.albums.add(result);
					modalStage.close();
				}
				message.setText("Please Enter an Acceptable Album Name");
			}
		});
		
		Scene modalScene = new Scene(modalVBox, 300, 200);
		modalStage.setScene(modalScene);
		modalStage.showAndWait();
	}
	
	/**
	 * Creates a modal with the basic inputs necessary for a User to add a photo to an album
	 * @param a: The album the photo is being added to.
	 * @param u: The user that the photo belongs to
	 */
	public void addPhoto(User u, Album a) {
		resetModal();
		modalStage.setTitle("Add Photo");
		
		Photo createdPhoto = new Photo();
		
		Text photoMessage = new Text("Select an Image File to Upload to " + a.name);
		Button openFileDialogBtn = new Button("Choose Image");
		FileChooser fileChooser = new FileChooser();
		Text captionMessage = new Text("Set a Caption for this Photo");
		TextField captionTextField = new TextField();
		
		modalVBox.getChildren().add(photoMessage);
		modalVBox.getChildren().add(openFileDialogBtn);
		modalVBox.getChildren().add(captionMessage);
		modalVBox.getChildren().add(captionTextField);
		modalVBox.getChildren().add(cancelButton);
		modalVBox.getChildren().add(okButton);
		
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("Image Files", new ArrayList<String>(Arrays.asList("*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png")))
		);
		
		openFileDialogBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				File selectedImage = fileChooser.showOpenDialog(modalStage);
				//createdPhoto.filePath = selectedImage.getAbsolutePath();
				createdPhoto.filePath = selectedImage.toURI().toString();
				createdPhoto.dateModified = Instant.ofEpochMilli(selectedImage.lastModified()).atZone(ZoneId.systemDefault()).toLocalDate();
				photoMessage.setText(selectedImage.getName());
			}
		});
		
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String captionResult = captionTextField.getText();
				
				if(!captionResult.equalsIgnoreCase("") && createdPhoto.filePath != null) {
					createdPhoto.caption = captionResult;
					a.photos.add(createdPhoto);
					u.photos.add(createdPhoto);
					modalStage.close();
				}
				else if(captionResult.equalsIgnoreCase("")) {
					captionMessage.setText("Please Enter a Valid Caption");
				}
				else if(createdPhoto.filePath == null) {
					photoMessage.setText("Please Provide an Image File.");
				}
			}
		});
		
		Scene modalScene = new Scene(modalVBox, 300, 300);
		modalStage.setScene(modalScene);
		modalStage.showAndWait();
		
	}
	
	/**
	 * Creates a modal that has the necessary inputs and control to add an existing tag for a user to a photo or add a new tag to both a photo and a user's collection
	 * @param u: The User the tag is being pulled from or added to 
	 * @param p: The photo the tag is being applied to
	 */
	
	//TODO: test this with the available UI
	public void addTag(User u, Photo p) {
		resetModal();
		modalStage.setTitle("Add Tag");
		
		Text tagNameMessage = new Text("Select an Existing Tag If Applicable.");
		ComboBox<Tag> comboBox = new ComboBox<Tag>(FXCollections.observableArrayList(u.tags.stream().filter(t -> !p.tags.contains(t)).collect(Collectors.toList())));
		Text txtTagName = new Text("Enter a New Tag Name (if applicable)");
		TextField nameTextField = new TextField();
		Text txtMultiValue = new Text("Supports Multiple Values");
		CheckBox chkMultiValue = new CheckBox();
		HBox hbox2 = new HBox(txtMultiValue, chkMultiValue);
		Text valuesMessage = new Text("Write Some Value(s) for This Tag.");
		TextField valuesTextField = new TextField();
		
		hbox2.setAlignment(Pos.BASELINE_CENTER);
		
		modalVBox.getChildren().add(tagNameMessage);
		modalVBox.getChildren().add(comboBox);
		modalVBox.getChildren().add(txtTagName);
		modalVBox.getChildren().add(nameTextField);
		modalVBox.getChildren().add(hbox2);
		modalVBox.getChildren().add(valuesMessage);
		modalVBox.getChildren().add(valuesTextField);
		modalVBox.getChildren().add(cancelButton);
		modalVBox.getChildren().add(okButton);
		
		comboBox.valueProperty().addListener(new ChangeListener<Tag>() {
			@Override
			public void changed(ObservableValue<? extends Tag> value, Tag oldTag, Tag newTag) {
				
				nameTextField.setText(newTag.name);
				nameTextField.setDisable(true);
				
				if(newTag instanceof MultiValueTag) {
					chkMultiValue.setSelected(true);
					chkMultiValue.setDisable(true);
				}
				else if(newTag instanceof SingleValueTag) {
					chkMultiValue.setSelected(false);
					chkMultiValue.setDisable(true);
				}
				else {
					chkMultiValue.setSelected(false);
					chkMultiValue.setDisable(false);
				}
			}
		});
		
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String nameVal;
				if(comboBox.getSelectionModel().isEmpty()) {
					nameVal = nameTextField.getText();
				}
				else {
					nameVal = comboBox.getSelectionModel().getSelectedItem().name;
				}
				
				
				if(!valuesTextField.getText().equalsIgnoreCase("") && !nameVal.equalsIgnoreCase("")) {
					
					MultiValueTag multiValResult = new MultiValueTag(nameVal, new ArrayList<String>(Arrays.asList(valuesTextField.getText().split(","))));
					SingleValueTag singleValResult = new SingleValueTag(nameVal, valuesTextField.getText());
					
					if(!comboBox.getSelectionModel().isEmpty()) {
						if(chkMultiValue.isSelected()) {
							p.tags.add(multiValResult);
						}
						else {
							p.tags.add(singleValResult);
						}
					}
					else {
						if(chkMultiValue.isSelected()) {
							u.tags.add(multiValResult);
							p.tags.add(multiValResult);
						}
						else {
							u.tags.add(singleValResult);
							p.tags.add(singleValResult);
						}
					}
					modalStage.close();
				}
				else {
					if(nameVal.equalsIgnoreCase("")) {
						tagNameMessage.setText("Please Choose a Valid Tag Name.");
					}
					
					if(valuesTextField.getText().equalsIgnoreCase("")) {
						valuesMessage.setText("Please Enter A Valid Tag Value(s)");
					}
				}
			}
		});
		
		
		Scene modalScene = new Scene(modalVBox, 300, 400);
		modalStage.setScene(modalScene);
		modalStage.showAndWait();
		
	}
}
