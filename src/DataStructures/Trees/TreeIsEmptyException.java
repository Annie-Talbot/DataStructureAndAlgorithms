package DataStructures.Trees;
/**
 * TreeIsEmptyException inherits the Exception class. This class should be used
 * whilst operating on a tree data structure class. If an operation is attempted
 * on a tree that has no nodes, this exception is thrown.
 * @author Annie Talbot
 */
public class TreeIsEmptyException extends Exception {

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455608L;
	/**
	 * Constructor for the Exception object that will output the message given when
	 * this object is thrown.
	 * @param message	the String object to print when this exception occurs
	 */
	public TreeIsEmptyException(String message) {
		super(message);
	}
}
