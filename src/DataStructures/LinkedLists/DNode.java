package DataStructures.LinkedLists;
/**
 * DNode object for use in a doubly linked list containing a value, a pointer holding 
 * the next node in the list and a pointer holding the previous value in the list
 * 
 * @author Annie Talbot
 *
 * @param <T> the class of the value the node holds
 */
public class DNode<T>{
	/**
	 * The value the node holds
	 */
	private T value;
	
	/**
	 * @return the value this node holds
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Assigns the value of this node to the given parameter
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * The node next in the list (if null, this is the head node of the list).
	 */
	private DNode<T> next = null;
	
	/**
	 * @return the next node in the list (if null, this is the head node of the list).
	 */
	public DNode<T> getNext() {
		return next;
	}

	/**
	 * Assigns the next node in the list to the parameter given
	 * @param next the node being appended to the list
	 */
	public void setNext(DNode<T> next) {
		this.next = next;
	}
	
	/**
	 * The previous node in the list (if null, this is the tail node of the list).
	 */
	private DNode<T> prev = null;
	
	/**
	 * @return the previous node in the list (if null, this is the tail node of the list).
	 */
	public DNode<T> getPrev() {
		return prev;
	}

	/**
	 * Assigns the previous node in the list to the parameter given
	 * @param prev the node being inserted in front of this node
	 */
	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * Constructor for an empty/sentinel node.
	 */
	public DNode() {
		this.setValue(null);
	}
	
	/**
	 * Constructor for a node that assigns the value to the parameter given
	 * 
	 * @param value the instance of an object that this node should hole
	 */
	public DNode(T value) {
		this.setValue(value);
	}
	
	public DNode(T value, DNode<T> prev, DNode<T> next) {
		this.setValue(value);
		this.setPrev(prev);
		this.setNext(next);
	}

	/**
	 * Returns the string form of the value held by this node.
	 * 
	 * @return string A string describing the value of this node
	 */
	@Override
	public String toString() {
		return this.getValue().toString();		
	}
}
