package models;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;
/**
 * 
 * @author Connor Magee
 *
 */
public class Photo implements Serializable {

	//public Image imageData;
	public String filePath;
	public String caption;
	public LocalDate  dateModified;
	public ArrayList<Tag> tags;
	
	/**
	 * Initializes a photo object
	 * @param filePath: local path for the image file
	 * @param caption: caption for the image
	 * @param dateModified: the last date this image was modified from the OS.
	 * @param tags: a list of tags a user specifies for this image
	 */
	public Photo(String filePath, String caption, LocalDate dateModified, ArrayList<Tag> tags) {
		this.filePath = filePath;
		this.caption = caption;
		this.dateModified = dateModified;
		this.tags = tags;
	}
	
	/**
	 * Alternate constructor for Image that sets all fields except tags to null and gives an empty list for tags
	 */
	public Photo() {
		this.filePath = null;
		this.caption = null;
		this.dateModified = null;
		this.tags = new ArrayList<Tag>();
	};
}
