package controllers;

import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.User;
import views.ModelAdder;
import models.Album;
/**
 * 
 * @author Connor Magee
 *
 */
public class AlbumsController {
	
	@FXML
	protected ListView<Album> albumListView;
	
	@FXML
	private Button addBtn;
	
	private ObservableList<Album> albums;
	
	/**
	 * Initializes the necessary functionality needed for the front end to work properly
	 * @param u: The Logged in User
	 */
	public void start(User u) {
		
		ModelAdder ma = ModelAdder.getInstance();
		
		albums = FXCollections.observableArrayList(u.albums);
		
		albumListView.setItems(albums);
		//albumListView.setEditable(true);
		setListViewCellFactory(u);
		
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ma.addAlbum(u);
				albums = FXCollections.observableArrayList(u.albums);
				albumListView.setItems(albums);
				albumListView.refresh();
			}
		});
	}
	
	/**
	 * Creates a cell factory for the list view that adds a context menu with rename and delete functionality and modals to assist this.
	 * @param u: The Logged in User
	 */
	private void setListViewCellFactory(User u) {
		albumListView.setCellFactory(lv ->{
			ListCell<Album> cell = new ListCell<>();
			
			ContextMenu cm = new ContextMenu();
			
			MenuItem renameItem = new MenuItem();
			renameItem.textProperty().bind(Bindings.format("Rename Album \"%s\"", cell.itemProperty()));
			renameItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Stage modalStage = new Stage();
					modalStage.initModality(Modality.APPLICATION_MODAL);
					modalStage.setTitle("Rename Album");
					VBox modalVBox = new VBox(20);
					modalVBox.setAlignment(Pos.BASELINE_CENTER);
					
					TextField newNameTxtBox = new TextField(cell.getItem().name);
					Button okButton = new Button("Ok");
					Button cancelButton = new Button("Cancel");
					
					modalVBox.getChildren().add(new Text("Edit Album Name"));
					modalVBox.getChildren().add(newNameTxtBox);
					modalVBox.getChildren().add(okButton);
					modalVBox.getChildren().add(cancelButton);
					
					okButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							cell.getItem().name = newNameTxtBox.getText();
							cell.textProperty().bind(Bindings.createStringBinding(() -> {
								return String.format("%s", cell.getItem());
							}, cell.itemProperty()));
							modalStage.close();
						}
					});
					
					cancelButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							modalStage.close();
						}
					});
					
					Scene modalScene = new Scene(modalVBox, 300, 200);
					modalStage.setScene(modalScene);
					modalStage.show();
				}
			});
			
			MenuItem deleteItem = new MenuItem();
			deleteItem.textProperty().bind(Bindings.format("Delete Album \"%s\"", cell.itemProperty()));
			deleteItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Delete Song");
					alert.setHeaderText("Are you sure you want to delete this album from your gallery?");
					alert.setContentText(cell.getItem().name);
					Optional<ButtonType> result = alert.showAndWait();
					
					if (result.get() == ButtonType.OK) {
						u.albums.remove(cell.getItem());
						albums.remove(cell.getItem());
					}
				}
			});
			
			cell.textProperty().bind(Bindings.createStringBinding(() -> {
				if(cell.isEmpty()) {
					return "";
				} else {
					return String.format("%s", cell.getItem());
				}
			}, cell.emptyProperty(), cell.itemProperty()));
			
			cm.getItems().addAll(renameItem, deleteItem);
			
			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if(isNowEmpty) {
					cell.setContextMenu(null);
				} else {
					cell.setContextMenu(cm);
				}
			});
			
			return cell;
		});
	}
}
