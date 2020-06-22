package DataStructures.LinkedLists;
/**
 * ElementNotFoundException inherits the Exception class. This class should be used
 * whilst searching for/ deleting an element from a data structure. If this element
 * if not found, this object is thrown.
 * @author Annie Talbot
 *
 */
public class ElementNotFoundException extends Exception {

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455602L;
	/**
	 * Constructor for the Exception object that will output the message given when
	 * this object is thrown.
	 * @param message	the String object to print when this exception occurs
	 */
	public ElementNotFoundException(String message) {
		super(message);
	}
}
