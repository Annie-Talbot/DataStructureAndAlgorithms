package DataStructures.Graphs;
/**
 * EdgeAlreadyExistsException inherits the Exception class. This class should be used
 * whilst appending edges to a graph. If the edge to be added is already on the graph,
 * this exception should be thrown.
 * @author Annie Talbot
 *
 */
public class EdgeAlreadyExistsException extends Exception {

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455604L;
	
	/**
	 * Constructor for the Exception object that will output the message given when
	 * this object is thrown.
	 * @param message	the String object to print when this exception occurs
	 */
	public EdgeAlreadyExistsException(String message) {
		super(message);
	}
}
