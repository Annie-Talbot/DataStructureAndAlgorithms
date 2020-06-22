package DataStructures;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import DataStructures.Trees.BinaryTree;
import DataStructures.Trees.CompleteNode;
import DataStructures.Trees.Display;
import DataStructures.Trees.Node;
import DataStructures.Trees.TreeIsEmptyException;

public class PriorityQueue<T>{
	
	public PriorityQueue() {
		tree = new BinaryTree<T>();
	}
	
	public PriorityQueue(T value, int priority) {
		tree = new BinaryTree<T>(value);
		leftMostLeaf = tree.getRoot();
		getTree().getRoot().setWeight(priority);
	}
	
	private int size = 0;
	public int getSize( ) {
		return size;
	}
	
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

	private Node<T> leftMostLeaf;
	
	private BinaryTree<T> tree;
	
	protected BinaryTree<T> getTree() {
		return tree;
	}

	
	public Node<T> push(T value) {
		return push(value, 0);
	}
	
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
	
	protected void swim(Node<T> node) {
		if (!node.isRoot()) {
			if (node.getParent().getWeight() > node.getWeight()) {
				swapValues(node.getParent(), node);
				swapPriorities(node.getParent(), node);
				swim(node.getParent());
			}
		}
	}
	
	protected void swapValues(Node<T> a, Node<T> b) {
		T temp = a.getValue();
		a.setValue(b.getValue());
		b.setValue(temp);
	}
	
	protected void swapPriorities(Node<T> a, Node<T> b) {
		int temp = a.getWeight();
		a.setWeight(b.getWeight());
		b.setWeight(temp);
	}
	
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

	public boolean isEmpty() {
		return tree.getRoot() == null;
	}
	
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
	
	public void display() {
		int width = 960;
		int height = 640;
		JFrame frame = new JFrame("Priority Queue");
		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		JComponent canvas = new Display<T>(tree.getRoot(), width, height);
        frame.add(canvas);
        frame.pack();
		frame.setVisible(true);
	}
	
	public String toString() {
		return tree.toString();
	}
}
