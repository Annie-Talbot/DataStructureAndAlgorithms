package DataStructures.Graphs;

import DataStructures.LinkedLists.DNode;
import DataStructures.LinkedLists.ElementNotFoundException;
import DataStructures.LinkedLists.SentinelDLinkedList;

public class LinkedList<T> extends SentinelDLinkedList<T> {

	public void add(T value) {
		this.insertEnd(value);
	}
	
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
