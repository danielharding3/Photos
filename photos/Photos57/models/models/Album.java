package models;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author Connor Magee
 *
 */
public class Album implements Serializable {
	public String name;
	public ArrayList<Photo> photos;
	
	/**
	 * Initializes an album instance
	 * @param name: name of the album
	 * @param photos: A list of photos contained within this album.
	 */
	public Album(String name, ArrayList<Photo> photos) {
		this.name = name;
		this.photos = photos;
	}
	
	public String toString() {
		return this.name == null ? "" : this.name;
	}
}
