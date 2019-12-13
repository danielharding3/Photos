package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.MultiValueTag;
import models.Photo;
import models.SingleValueTag;
import models.Tag;
import models.User;
import views.ModelAdder;

/**
 * 
 * @author Connor Magee
 *
 */
public class PhotoDetailsController {

	@FXML
	protected ImageView imageviewDisplay;
	
	@FXML
	protected TextField txtfieldCaption;
	
	@FXML
	private Label lblDateTaken;
	
	@FXML
	private TableView<Tag> tableTags;
	
	@FXML
	private TableColumn<Tag,String> colName;
	
	@FXML
	private TableColumn<Tag,String> colVals;
	
	@FXML
	private Button btnAddTag;
	
	@FXML
	private Button btnDeleteTag;
	
	@FXML
	private Button btnSave;
	
	private ObservableList<Tag> tagList;
	
	/**
	 * Initializes the photo details view with the appropriate data from the currently selected image
	 * @param u: the user the photo belongs to
	 * @param p: the photo that is being examined
	 */
	public void start(User u, Photo p) {
		initTable(p);
		
		//TODO: figure out why this isn't displaying
		//imageviewDisplay = new ImageView(p.filePath);
		imageviewDisplay = new ImageView();
		imageviewDisplay.setFitHeight(50);
		imageviewDisplay.setFitWidth(50);
		imageviewDisplay.setPreserveRatio(true);
		Image currImage;
		System.out.print(p.filePath);
		currImage = new Image(p.filePath);
		imageviewDisplay.setImage(currImage);
		
		txtfieldCaption.setText(p.caption);
		lblDateTaken.setText(p.dateModified.toString());
		
		btnAddTag.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				ModelAdder me = ModelAdder.getInstance();
				me.addTag(u, p);
				initTable(p);
			}
			
		});
		
		btnDeleteTag.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Tag selectedTag = tableTags.getSelectionModel().getSelectedItem();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete Tag from Photo");
				alert.setContentText("Are you sure you want to delete " + selectedTag.name + " from this photo's tags?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK) {
					p.tags.remove(selectedTag);
					initTable(p);
				}
			}
			
		});
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				String newCaption = txtfieldCaption.getText();
				
				if(newCaption.equalsIgnoreCase("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Invalid Caption");
					alert.setContentText("Please enter a valid Caption value.");
					alert.showAndWait();
				}else {
					p.caption = newCaption;
				}
				//TODO: make a toast or some shit to notify that their changes were saved successfully.
			}
			
		});
	}
	
	/**
	 * Initializes the table view that holds tag information
	 * @param p: the photo being examined
	 */
	public void initTable(Photo p) {
		
		tagList = FXCollections.observableArrayList(p.tags);
		tableTags.setItems(tagList);
		tableTags.setEditable(true);
		
		colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name));
		colVals.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValues()));
		
		colVals.setCellFactory(TextFieldTableCell.forTableColumn());
		
		colVals.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tag,String>>() {

			@Override
			public void handle(TableColumn.CellEditEvent<Tag, String> e) {
				Tag t = e.getRowValue();
				if(t instanceof MultiValueTag) {
					MultiValueTag m = (MultiValueTag) t;
					m.values = new ArrayList<String>(Arrays.asList(e.getNewValue().split(",")));
				}
				else {
					SingleValueTag s = (SingleValueTag) t;
					s.value = e.getNewValue();
				}
			}
			
		});
	}
}
