package DataStructures.LinkedLists;
/**
 * SentinelSLinkedList object is a homogeneous data structure used to contain a list of elements.
 * Each element points to the previous element in the list and so by accessing the head of
 * the list (held by this class), you can access all other elements.
 * Predefined operations include adding, removing and peaking at elements at the beginning 
 * and end of the list.
 * 
 * This implementation contains head and tail sentinels.
 * 
 * @author Annie Talbot
 *
 * @param <T> the class of the value assigned to each node
 */

public class SentinelSLinkedList<T> {
	
	/**
	 * The number of elements in the list
	 */
	private int size = 0;
	
	/**
	 * Checks if this instance of a list has 0 elements
	 * @return true = empty, false = not empty
	 */
	public boolean isEmpty() {
		if (size > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * The sentinel node for the start of the list.
	 */
	private SNode<T> head;

	/**
	 * @return the sentinel head node of the list
	 */
	protected SNode<T> getHead() {
		return head;
	}
	
	/**
	 * The sentinel node for the end of the list.
	 */
	private SNode<T> tail;
	
	/**
	 * Constructor for an empty list
	 */
	public SentinelSLinkedList() {
		this.head = new SNode<T>();
		this.tail = new SNode<T>();
	}

	/**
	 * Constructor of the list, assigning the first element of hold the value given.
	 * @param value the first value in the list
	 */
	public SentinelSLinkedList(T value) {
		this.head = new SNode<T>();
		this.tail = new SNode<T>();
		this.getHead().setPrev(new SNode<T>(value, this.tail));;
		this.size++;
	}

	/**
	 * Inserts an element with the value given as a parameter to the end of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertEnd(T value) {
		SNode<T> iterator = head;
		while (iterator.getPrev() != this.tail) {
			iterator = iterator.getPrev();
		}
		iterator.setPrev(new SNode<T>(value, this.tail));
		size++;
	}
	
	/**
	 * Inserts an element with the value given as a parameter to the start of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertBeginning(T value) {
		this.getHead().setPrev(new SNode<T>(value, this.getHead().getPrev()));
		this.size++;
	}

	/**
	 * Removes the node at the end of the list and returns the value held there.
	 * @return T object held in the end node
	 */
	protected T removeEnd() {
		if (!this.isEmpty()) {
			SNode<T> iterator = this.getHead();
			while (iterator.getPrev().getPrev() != tail) {
				iterator = iterator.getPrev();
			}
			SNode<T> endNode = iterator.getPrev();
			iterator.setPrev(tail);
			size--;
			return endNode.getValue();
		}
		// The list is empty
		return null;
	}
	
	/**
	 * Removes the node at the start of the list and returns the value held there.
	 * @return T object held in the start node
	 */
	protected T removeBeginning() {
		if (!this.isEmpty()) {
			SNode<T> startNode = this.getHead().getPrev();
			this.getHead().setPrev(startNode.getPrev());
			size--;
			return startNode.getValue();
		}
		return null;
	}
	
	/**
	 * @return Object held in the end node (null if list is empty)
	 */
	protected T getEndValue() {
		SNode<T> iterator = this.getHead();
		while (iterator.getPrev() != tail) {
			iterator = iterator.getPrev();
		}
		return iterator.getValue();
	}
	
	/**
	 * @return Object held in the start node (null if list is empty)
	 */
	protected T getBeginningValue() {
		return this.getHead().getPrev().getValue();
	}
	
	/**
	 * Returns all values in the list (in the order they are stored in) as a string.
	 * 
	 * @return A string displaying the contents of the list
	 */
	@Override
	public String toString() {
		String list = "List Contents: \n";
		SNode<T> iterator = this.getHead().getPrev();
		while (iterator != tail) {
			list += iterator.toString() + " \n";
			iterator = iterator.getPrev();
		}
		return list;
	}
	

}
