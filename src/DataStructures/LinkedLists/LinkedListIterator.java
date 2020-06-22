package DataStructures.LinkedLists;

import java.util.Iterator;


public class LinkedListIterator<T> implements Iterator<T> {
	DNode<T> current;
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
