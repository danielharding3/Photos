package models;
/**
 * 
 * @author Connor Magee
 *
 */
public class SingleValueTag extends Tag {

	public String value;
	
	/**
	 * Initializes a new tag that can hold a single value
	 * @param name: name of the tag instance
	 * @param value: the single value that the tag can hold
	 */
	public SingleValueTag(String name, String value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public String getValues() {
		return this.value;
	}

}
