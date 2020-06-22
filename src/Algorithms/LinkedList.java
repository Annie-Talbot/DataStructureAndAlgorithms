package Algorithms;

import DataStructures.LinkedLists.DNode;
import DataStructures.LinkedLists.SentinelDLinkedList;

public class LinkedList<T extends Comparable<T>> extends SentinelDLinkedList<T> {
	/**
	 * @return the head node of the list
	 */
	public DNode<T> getHead() {
		return super.getHead();
	}
	/**
	 * @return the head node of the list
	 */
	public DNode<T> getTail() {
		return super.getTail();
	}
	
	/**
	 * Constructor for an empty list
	 */
	public LinkedList() {
		super();
	}

	/**
	 * Constructor of the list, assigning the first element of hold the value given.
	 * @param value the first value in the list
	 */
	public LinkedList(T value) {
		super(value);
	}

	/**
	 * Inserts an element with the value given as a parameter to the end of the list.
	 * @param value The value to be added to the list
	 */
	public void insertEnd(T value) {
		super.insertEnd(value);
	}
	
	/**
	 * Extends this list with another list, inserted at the end of the list.
	 * @param value The value to be added to the list
	 */
	public void insertEnd(LinkedList<T> list) {
		while (!list.isEmpty()) {
			insertEnd(list.removeBeginning());
		}
	}
	
	/**
	 * Extends this list with another list, inserted at the start of the list.
	 * @param value The value to be added to the list
	 */
	public void insertBeginning(T value) {
		super.insertBeginning(value);
	}
	
	/**
	 * Inserts an element with the value given as a parameter to the start of the list.
	 * @param value The value to be added to the list
	 */
	public void insertBeginning(LinkedList<T> list) {
		while (!list.isEmpty()) {
			insertBeginning(list.removeEnd());
		}
		
	}

	/**
	 * Removes the node at the end of the list and returns the value held there.
	 * @return T object held in the end node
	 */
	public T removeEnd() {
		return super.removeEnd();
	}
	
	/**
	 * Removes the node at the start of the list and returns the value held there.
	 * @return T object held in the start node
	 */
	public T removeBeginning() {
		return super.removeBeginning();
	}
	
	public void replaceList(DNode<T> head) {
		getHead().setPrev(head);
		head.setNext(getHead());
		DNode<T> iterator = head.getPrev();
		while (iterator.getValue() != null) {
			iterator = iterator.getPrev();
		}
		// iterator is now tail
		setTail(iterator);
	}
}
