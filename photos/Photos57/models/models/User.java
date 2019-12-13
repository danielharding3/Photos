package models;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Connor Magee
 *
 */
public class User implements Serializable {
	public String username;
	public String password;
	public ArrayList<Album> albums;
	public ArrayList<Photo> photos;
	public ArrayList<Tag> tags;
	
	public static final String dir = "data";
	
	/**
	 * Initializes a user object
	 * @param username: the user's username alias
	 * @param password: the user's password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.albums = new ArrayList<Album>();
		this.photos = new ArrayList<Photo>();
		this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Saves the user instance to a .dat file locally using the serializable interface
	 * @throws IOException
	 */
	public void Save() throws IOException {
		String filename = this.username + ".dat";
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dir + File.separator + filename));
		outputStream.writeObject(this);
		outputStream.close();
	}
}
