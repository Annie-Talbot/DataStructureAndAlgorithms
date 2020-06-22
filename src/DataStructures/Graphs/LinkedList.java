package DataStructures.Graphs;

import DataStructures.LinkedLists.DNode;
import DataStructures.LinkedLists.ElementNotFoundException;
import DataStructures.LinkedLists.SentinelDLinkedList;
/**
 * Linked List class inherits the SentinelDLinkedList class. This implementation is 
 * for use with this classes in this package and provides additional methods to 
 * supports these other classes.
 * @author Annie Talbot
 *
 * @param <T> The class of the object this LinkedList holds
 */
public class LinkedList<T> extends SentinelDLinkedList<T> {

	/**
	 * Operation to append a value to this list.
	 * @param value The value to be added
	 */
	public void add(T value) {
		this.insertEnd(value);
	}
	
	/**
	 * Operation to remove a value from this list.
	 * @param value The value to be removed
	 * @return The value that has been removed
	 * @throws ElementNotFoundException thrown if the value specified cannot be found
	 * in this list
	 */
	public T removeElementWithValue(T value) throws ElementNotFoundException {
		DNode<T> iterator = this.getHead().getPrev();
		while (iterator != tail) {
			if (iterator.getValue() == value) {
				// Remove element
				iterator.getPrev().setNext(iterator.getNext());
				iterator.getNext().setPrev(iterator.getPrev());
				// Return value
				return iterator.getValue();
			}
		}
		// Element not found
		throw new ElementNotFoundException("The list does not contain this value");
	}
}
