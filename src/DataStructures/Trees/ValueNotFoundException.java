package DataStructures.Trees;
/**
 * ValueNotFoundException inherits the Exception class. This class should be used
 * whilst operating on a tree data structure class. If an search or delete operation
 * is attempted on a tree and the value requested cannot be found, this exception is 
 * thrown.
 * @author Annie Talbot
 */
public class ValueNotFoundException extends Exception {

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455609L;
	/**
	 * Constructor for the Exception object that will output the message given when
	 * this object is thrown.
	 * @param message	the String object to print when this exception occurs
	 */
	public ValueNotFoundException(String message) {
		super(message);
	}
}
