package DataStructures;
import DataStructures.LinkedLists.SentinelDLinkedList;

/**
 * Queue data structure that implements a doubly linked list with sentinels.
 * 
 * @author Annie Talbot
 *
 */
public class Queue<T> extends SentinelDLinkedList<T> {
	/**
	 * Constructor for an empty queue
	 */
	public Queue() {
		super();
	}
	
	/**
	 * Constructor for a queue with the first element already defined.
	 * @param value The value for the first element in the queue
	 */
	public Queue(T value) {
		super(value);
	}
	
	/**
	 * Operation to append an element to the queue
	 * @param value The element to be added of the list
	 */
	public void enqueue(T value) {
		this.insertEnd(value);
	}
	
	/**
	 * Operation to append a sub-queue to this queue
	 * @param queue the sub-queue to be added
	 */
	public void enqueue(Queue<T> queue) {
		while (!queue.isEmpty()) {
			enqueue(queue.dequeue());
		}
	}
	
	/**
	 * Operation to remove the next element in the queue.
	 * @return The value of the element removed
	 */
	public T dequeue() {
		return this.removeBeginning();
	}
	
	/**
	 * Operation to peak at the element at the front of the queue.
	 * @return the value of the element at the front of the queue
	 */
	public T front() {
		return this.getBeginningValue();
	}
}