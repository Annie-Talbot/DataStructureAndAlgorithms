package DataStructures.Trees;
/**
 * DuplicateValueException inherits the Exception class. This class should be used
 * whilst appending an element to a binary search tree data structure. If this element
 * already in the tree, it cannot be added and this exception is thrown.
 * @author Annie Talbot
 */
public class DuplicateValueException extends Exception {

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455607L;
	/**
	 * Constructor for the Exception object that will output the message given when
	 * this object is thrown.
	 * @param message	the String object to print when this exception occurs
	 */
	public DuplicateValueException(String message) {
		super(message);
	}
}
