package models;
import java.util.ArrayList;
/**
 * 
 * @author Connor Magee
 *
 */
public class MultiValueTag extends Tag {

	public ArrayList<String> values;
	
	/**
	 * Constructs a tag that can hold multiple values.
	 * @param name: the name of the tag
	 * @param values: the list of values this tag instance holds.
	 */
	public MultiValueTag(String name, ArrayList<String> values) {
		super(name);
		this.values = values;
	}
	
	/**
	 * Inherited from Tag
	 */
	@Override
	public String getValues() {
		return values.toString().replace("[", "").replace("]", "");
	}

}
