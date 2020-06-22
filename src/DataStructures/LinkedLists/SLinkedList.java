package DataStructures.LinkedLists;
/**
 * Singly Linked List object is a homogeneous data structure used to contain a list of elements.
 * Each element points to the previous element in the list and so by accessing the head of
 * the list (held by this class), you can access all other elements.
 * Predefined operations include adding, removing and peaking at elements at the beginning 
 * and end of the list.
 * 
 * This implementation contains no sentinels.
 * 
 * @author Annie Talbot
 *
 * @param <T> the class of the value assigned to each node
 */

public class SLinkedList<T> {
	
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
	 * The first node in the list.
	 */
	private SNode<T> head;

	/**
	 * @return the head node of the list
	 */
	protected SNode<T> getHead() {
		return head;
	}

	/**
	 * Assigns a node to the head of the list
	 * @param head the node to set as the head of the list
	 */
	protected void setHead(SNode<T> head) {
		this.head = head;
	}
	
	/**
	 * Constructor for an empty list
	 */
	public SLinkedList() {
		this.setHead(null);
	}

	/**
	 * Constructor of the list, assigning the first element of hold the value given.
	 * @param value the first value in the list
	 */
	public SLinkedList(T value) {
		this.setHead(new SNode<T>(value));
		this.size++;
	}

	/**
	 * Inserts an element with the value given as a parameter to the end of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertEnd(T value) {
		if (head != null) {
			SNode<T> iterator = head;
			while (iterator.getPrev() != null) {
				iterator = iterator.getPrev();
			}
			iterator.setPrev(new SNode<T>(value));
		} else {
			this.setHead(new SNode<T>(value));
		}
		this.size++;
	}
	
	/**
	 * Inserts an element with the value given as a parameter to the start of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertBeginning(T value) {
		this.setHead(new SNode<T>(value, head));
		this.size++;
	}

	/**
	 * Removes the node at the end of the list and returns the value held there.
	 * @return T object held in the end node
	 */
	protected T removeEnd() {
		if (!this.isEmpty()) {
			SNode<T> iterator = this.getHead();
			if (iterator.getPrev() != null) {
				// There are 2 or more elements in the list
				while (iterator.getPrev().getPrev() != null) {
					iterator = iterator.getPrev();
				}
				SNode<T> endNode = iterator.getPrev();
				iterator.setPrev(null);
				size--;
				return endNode.getValue();
			}
			// There is only 1 element in the list
			this.setHead(null);
			size--;
			return iterator.getValue();
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
			SNode<T> startNode = this.getHead();
			this.setHead(startNode.getPrev());
			size--;
			return startNode.getValue();
		}
		return null;
	}
	
	/**
	 * @return Object held in the end node (null if list is empty)
	 */
	protected T getEndValue() {
		if (!this.isEmpty()) {
			SNode<T> iterator = this.getHead();
			while (iterator.getPrev() != null) {
				iterator = iterator.getPrev();
			}
			return iterator.getValue();
		}
		return null;
	}
	
	/**
	 * @return Object held in the start node (null if list is empty)
	 */
	protected T getBeginningValue() {
		if (!this.isEmpty()) {
			return this.getHead().getValue();
		}
		return null;
	}
	
	/**
	 * Returns all values in the list (in the order they are stored in) as a string.
	 * 
	 * @return string A string displaying the contents of the list
	 */
	@Override
	public String toString() {
		String list = "List Contents: \n";
		SNode<T> iterator = head;
		while (iterator != null) {
			list += iterator.toString() + " \n";
			iterator = iterator.getPrev();
		}
		return list;
	}
	

}
