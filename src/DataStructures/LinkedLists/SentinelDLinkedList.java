package DataStructures.LinkedLists;

import java.util.Iterator;

/**
 * Doubly Linked List object is a homogeneous data structure used to contain a list of elements.
 * Each element has a pointer to the previous and next element element in the list.
 * Predefined operations include adding, removing and peaking at elements at the beginning 
 * and end of the list.
 * 
 * This implementation contains sentinels.
 * 
 * @author Annie Talbot
 *
 * @param <T> the class of the value assigned to each node
 */

public class SentinelDLinkedList<T> implements Iterable<T>{
	
	/**
	 * The number of elements in the list
	 */
	private int size = 0;
	
	/**
	 * Getter for the number of elements in the list
	 * @return the number of elements in the list
	 */
	public int getSize() {
		return size;
	}
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
	private DNode<T> head;

	/**
	 * @return the head node of the list
	 */
	protected DNode<T> getHead() {
		return head;
	}

	/**
	 * Assigns a node to the head of the list
	 * @param head the node to set as the head of the list
	 */
	protected void setHead(DNode<T> head) {
		this.head = head;
	}
	
	/**
	 * The last node in the list.
	 */
	protected DNode<T> tail;

	/**
	 * @return the head node of the list
	 */
	protected DNode<T> getTail() {
		return tail;
	}

	/**
	 * Assigns a node to the head of the list
	 * @param head the node to set as the head of the list
	 */
	protected void setTail(DNode<T> tail) {
		this.tail = tail;
	}
	
	/**
	 * Constructor for an empty list
	 */
	public SentinelDLinkedList() {
		this.setHead(new DNode<T>());
		this.setTail(new DNode<T>());
		head.setPrev(tail);
		tail.setNext(head);
		size = 0;
	}

	/**
	 * Constructor of the list, assigning the first element of hold the value given.
	 * @param value the first value in the list
	 */
	public SentinelDLinkedList(T value) {
		this.setHead(new DNode<T>());
		this.setTail(new DNode<T>());
		head.setPrev(new DNode<T>(value));
		tail.setNext(head.getPrev());
		
		head.getPrev().setNext(head);
		head.getPrev().setPrev(tail);
		size = 1;
	}

	/**
	 * Inserts an element with the value given as a parameter to the end of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertEnd(T value) {
		tail.getNext().setPrev(new DNode<T>(value, tail, tail.getNext()));
		tail.setNext(tail.getNext().getPrev());
		size++;
	}
	
	/**
	 * Extends this list with another list, inserted at the start of the list.
	 * @param value The value to be added to the list
	 */
	protected void insertBeginning(T value) {
		head.getPrev().setNext(new DNode<T>(value, head.getPrev(), head));
		head.setPrev(head.getPrev().getNext());
		this.size++;
	}

	/**
	 * Removes the node at the end of the list and returns the value held there.
	 * @return T object held in the end node
	 */
	protected T removeEnd() {
		if (!this.isEmpty()) {
			DNode<T> endNode = tail.getNext();
			endNode.getNext().setPrev(tail);
			tail.setNext(endNode.getNext());
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
			DNode<T> startNode = head.getPrev();
			startNode.getPrev().setNext(head);
			head.setPrev(startNode.getPrev());
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
			return tail.getNext().getValue();
		}
		return null;
	}
	
	/**
	 * @return Object held in the start node (null if list is empty)
	 */
	protected T getBeginningValue() {
		if (!this.isEmpty()) {
			return head.getPrev().getValue();
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
		DNode<T> iterator = head.getPrev();
		while (iterator != tail) {
			list += iterator.toString() + " \n";
			iterator = iterator.getPrev();
		}
		return list;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this);
	}
	

}
