package DataStructures;

import DataStructures.Trees.Node;

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
