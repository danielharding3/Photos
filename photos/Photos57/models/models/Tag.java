package models;
import java.io.Serializable;
/**
 * 
 * @author Connor Magee
 *
 */
public abstract class Tag implements Serializable {

	public String name;
	
	/**
	 * Constructor that creates a new tag instance
	 * @param name: the name of the created tag
	 */
	public Tag(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the single value or a string of comma separated values from a singlevalue tag or a multivalue tag
	 */
	public abstract String getValues();
	
	public String toString() {
		return this.name;
	}
	
	public boolean equals(Tag other) {
		return this.name.equals(other.name);
	}
}
