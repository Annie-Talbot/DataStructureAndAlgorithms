package DataStructures;

import DataStructures.Trees.BinaryTree;
import DataStructures.Trees.CompleteNode;
import DataStructures.Trees.Node;
import DataStructures.Trees.TreeIsEmptyException;
/**
 * PriorityQueue Class. Implementation of the priority queue data structure where
 * elements are added and removed from the list in the order of their priority.
 * @author Annie Talbot
 *
 * @param <T>	The class of the objects this structure will hold
 */
public class PriorityQueue<T>{
	
	/**
	 * Constructor for an empty Priority Queue.
	 */
	public PriorityQueue() {
		tree = new BinaryTree<T>();
	}
	
	/**
	 * Constructor for a Priority Queue specifying, adding the first element.
	 * @param value	The value of be added
	 * @param priority	The priority of this value in the queue
	 */
	public PriorityQueue(T value, int priority) {
		tree = new BinaryTree<T>(value);
		leftMostLeaf = tree.getRoot();
		getTree().getRoot().setWeight(priority);
	}
	
	/**
	 * The number of nodes in this priority queue.
	 */
	private int size = 0;
	/**
	 * Getter for the number of node in the queue.
	 * @return	The number of nodes in this queue
	 */
	public int getSize( ) {
		return size;
	}
	
	/**
	 * Operation for adding an element to the queue.
	 * @param value	The value of be added
	 * @param priority The value's priority
	 * @return The node that containing the value added
	 */
	public Node<T> push(T value, int priority) {
		CompleteNode<T> leaf = new CompleteNode<T>(value, priority, null);
		if (tree.getRoot() == null) {
			tree.setRoot(leaf);
			leftMostLeaf = tree.getRoot();
			size++;
		} else {
			addLeaf(tree.getRoot(), leaf);
			swim(leaf);
		}
		return leaf;
	}

	/**
	 * The left mode node of the highest node on the tree.
	 */
	private Node<T> leftMostLeaf;
	
	/**
	 * The binary tree holding all the values in the queue.
	 */
	private BinaryTree<T> tree;
	
	/**
	 * Getter for the binary tree holding all the values in the priority queue.
	 * @return
	 */
	protected BinaryTree<T> getTree() {
		return tree;
	}

	/**
	 * Operation to add a value to the priority queue.
	 * @param value	 the value to be added
	 * @return	the node with the value added
	 */
	public Node<T> push(T value) {
		return push(value, 0);
	}
	
	/**
	 * Operation to add node to the bottom of the tree, maintaining a complete structure.
	 * @param root	The root of the subtree to add the node to
	 * @param leaf	The node to be added
	 */
	protected void addLeaf(Node<T> root, Node<T> leaf) {
		if (root != null) {
			if (!root.hasLeftChild()) {
				root.setLeftChild(leaf);
				leftMostLeaf = leaf;
				size++;
				return;
			} else if (((CompleteNode<T>) root.getLeftChild()).isComplete()) {
				if (!root.hasRightChild()) {
					root.setRightChild(leaf);
					leftMostLeaf = leaf;
					size++;
				} else if (!((CompleteNode<T>) root.getRightChild()).isComplete() &&
						root.getLeftChild().getHeight() > root.getRightChild().getHeight()) {
					addLeaf(root.getRightChild(), leaf);
				}else {
					addLeaf(root.getLeftChild(), leaf);
				}
				
			} else {
				addLeaf(root.getLeftChild(), leaf);
			}
		}
	}
	/**
	 * Operation to move a node UP to its correct position within the tree.
	 * @param node the node to be moved
	 */
	protected void swim(Node<T> node) {
		if (!node.isRoot()) {
			if (node.getParent().getWeight() > node.getWeight()) {
				swapValues(node.getParent(), node);
				swapPriorities(node.getParent(), node);
				swim(node.getParent());
			}
		}
	}
	
	/**
	 * Operation to swap the values in the nodes given.
	 * @param a	 node 1
	 * @param b	 node 2
	 */
	protected void swapValues(Node<T> a, Node<T> b) {
		T temp = a.getValue();
		a.setValue(b.getValue());
		b.setValue(temp);
	}
	/**
	 * Operation to swap the priorities in the nodes given.
	 * @param a	 node 1
	 * @param b	 node 2
	 */
	protected void swapPriorities(Node<T> a, Node<T> b) {
		int temp = a.getWeight();
		a.setWeight(b.getWeight());
		b.setWeight(temp);
	}
	/**
	 * Operation to remove the value at the top of the queue.
	 * @return the value at the top of the queue
	 * @throws TreeIsEmptyException if the there is no nodes in the queue to be removed
	 */
	public T pop() {
		if (!isEmpty()) {
			size--;
			T value = tree.getRoot().getValue();
			if (tree.getRoot().isLeaf()) {
				// Only 1 node in the tree
				tree.setRoot(null);
			} else {
				swapValues(leftMostLeaf, tree.getRoot());
				swapPriorities(leftMostLeaf, tree.getRoot());
				tree.deleteLeaf(leftMostLeaf);
				sink(tree.getRoot());
				// re-assign left most leaf here!!
				leftMostLeaf = findLeftMostLeaf(tree.getRoot());
			}
			return value;
		}
		new TreeIsEmptyException("Tree cannot be popped becuase it is empty.").printStackTrace();
		return null;
	}
	
	/**
	 * Operation to move a node DOWN to its correct position within the tree.
	 * @param node the node to be moved
	 */
	protected void sink(Node<T> node) {
		if (!node.isLeaf()) {
			if (node.hasLeftChild() && node.getLeftChild().getWeight() < node.getWeight()) {
				swapValues(node.getLeftChild(), node);
				swapPriorities(node.getLeftChild(), node);
				sink(node.getLeftChild());
			} else if (node.hasRightChild() && node.getRightChild().getWeight() < node.getWeight()) {
				swapValues(node.getRightChild(), node);
				swapPriorities(node.getRightChild(), node);
				sink(node.getRightChild());
				
			}
		}
	}
	
	/**
	 * Operation to check whether this queue is empty.
	 * @return True = is empty, False = is NOT empty
	 */
	public boolean isEmpty() {
		return tree.getRoot() == null;
	}
	
	/**
	 * Operation to find the highest left most leaf in the queue.
	 * @return The left most node
	 */
	private Node<T> findLeftMostLeaf(Node<T> root) {
		if (root != null) {
			if (!root.hasLeftChild()) {
				return root;
			} else if (((CompleteNode<T>) root.getLeftChild()).isComplete()) {
				if (!root.hasRightChild()) {
					return root.getLeftChild();
				} else if (((CompleteNode<T>) root.getRightChild()).isComplete() &&
						root.getLeftChild().getHeight() > root.getRightChild().getHeight()) {
					return findLeftMostLeaf(root.getLeftChild());
				}else {
					return findLeftMostLeaf(root.getRightChild());
				}
				
			} else {
				return findLeftMostLeaf(root.getLeftChild());
			}
		}
		return null;
	}
	
	/**
	 * Operation to display the priority queue in a separate window.
	 * @param title the title of the window
	 */
	public void display(String title) {
		tree.display(title);
	}
	
	@Override
	public String toString() {
		return tree.toString();
	}
}
