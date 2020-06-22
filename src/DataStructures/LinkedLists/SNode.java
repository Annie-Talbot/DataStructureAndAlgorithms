package DataStructures.LinkedLists;
/**
 * SNode object for use in a singly linked list containing a value and a single pointer 
 * holding the next value in the list
 * 
 * @author Annie Talbot
 *
 * @param <T> the class of the value the node holds
 */
public class SNode<T>{
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
	 * The node next in the list (if null, this is the tail node of the list).
	 */
	private SNode<T> prev = null;
	
	/**
	 * @return the next node in the list (if null, this is the tail node of the list)
	 */
	public SNode<T> getPrev() {
		return prev;
	}

	/**
	 * Assigns the next node in the list to the parameter given
	 * @param next the node being appended to the list
	 */
	public void setPrev(SNode<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * Constructor for a sentinel or empty node (no value).
	 * 
	 */
	public SNode() {
	}
	
	/**
	 * Constructor for a node that assigns the value to the parameter given
	 * 
	 * @param value the instance of an object that this node should hole
	 */
	public SNode(T value) {
		this.setValue(value);
	}
	
	/**
	 * Constructor for a node that assigns the value to the parameter given and assigns the value
	 * of the previous pointer. 
	 * 
	 * @param value the instance of an object that this node should hole
	 * @param prev the previous node in the list
	 */
	public SNode(T value, SNode<T> prev) {
		this.setValue(value);
		this.setPrev(prev);
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
