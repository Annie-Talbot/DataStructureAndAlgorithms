package DataStructures.LinkedLists;

import java.util.Iterator;
/**
 * LinkedListIterator class implements the interface Iterator so that the elements
 * within a SentinelDLinkedList can be iterated through.
 * @author Annie Talbot
 *
 * @param <T> The class of the objects stored in the linked list.
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	/**
	 * The node in the Linked List the iterator is currently on.
	 */
	private DNode<T> current;
	/**
	 * Constructor for the Iterator assigning this instance it's list to iterate
	 * through.
	 * @param list	The list to be iterated through
	 */
	public LinkedListIterator(SentinelDLinkedList<T> list) {
		current = list.getHead().getPrev();
	}
	
	@Override
	public boolean hasNext() {
		return current.getValue() != null;
	}
	@Override
	public T next() {
		T next = current.getValue();
		current = current.getPrev();
		return next;
	}
	@Override
	public void remove() 
    { 
        throw new UnsupportedOperationException(); 
    } 
}
