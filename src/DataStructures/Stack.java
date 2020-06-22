package DataStructures;

import DataStructures.LinkedLists.SLinkedList;

/**
 * Stack data structure that implements a singly linked list.
 * 
 * @author Annie Talbot
 *
 */
public class Stack<T> extends SLinkedList<T>{
	
	/**
	 * Constructor for empty stack
	 */
	public Stack() {
		super();
	}
	
	/**
	 * Constructor for a stack with an initial element
	 * @param value the first value in the stack
	 */
	public Stack(T value) {
		super(value);
	}
	
	/**
	 * Operation to remove and return the top element in the stack
	 * @return value of the top element in the stack
	 */
	public T pop() {
		return this.removeBeginning();
	}
	
	/**
	 * Operation to peak at the top element of the stack
	 * @return The value of the element at the top of the stack
	 */
	public T top() {
		return this.getBeginningValue();
	}
	
	/**
	 * Operation to add an element to the top of the stack
	 * @param value the value to be added to the stack
	 */
	public void push(T value) {
		this.insertBeginning(value);
	}
}
