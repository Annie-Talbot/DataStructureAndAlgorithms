package DataStructures;

import DataStructures.Trees.Node;
/**
 * Heap class inherits PriotiryQueue. An implementation of the Heap data
 * structure where elements are placed in the list according to their value.
 * @author Annie Talbot
 *
 * @param <T> The class of the objects this heap will hold.
 */
public class Heap<T extends Comparable<T>> extends PriorityQueue<T>{

	/**
	 * Constructor for an empty tree	
	 */
	public Heap() {
		super();
	}
	public Heap(T value) {
		super(value, 0);
	}
	
	@Override
	protected void swim(Node<T> node) {
		if (!node.isRoot()) {
			if (node.getParent().getValue().compareTo(node.getValue()) > 0) {
				swapValues(node.getParent(), node);
				swim(node.getParent());
			}
		}
	}
	
	@Override
	protected void sink(Node<T> node) {
		if (!node.isLeaf()) {
			if (node.hasLeftChild() && node.getLeftChild().getValue().compareTo(node.getValue()) < 0) {
				swapValues(node.getLeftChild(), node);
				sink(node.getLeftChild());
			} else if (node.hasRightChild() && node.getRightChild().getValue().compareTo(node.getValue()) < 0) {
				swapValues(node.getRightChild(), node);
				sink(node.getRightChild());
				
			}
		}
	}
	
}
