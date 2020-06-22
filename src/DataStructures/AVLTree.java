package DataStructures;

import DataStructures.Trees.DuplicateValueException;
import DataStructures.Trees.Node;
import DataStructures.Trees.ValueNotFoundException;
/**
 * AVLTree object. An extension to to the {@link BinarySearchTree} that holds methods
 * to conduct rotations, ensuring that the a maximum difference in height between a
 * left and right subtree is 1 whenever a node is added or removed.
 * @author Annie Talbot
 *
 * @param <T> Any object that implement comparable so that their position 
 * in the tree can be determined
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	
	/**
	 * Constructor for an empty AVL tree.
	 */
	public AVLTree() {
		super();
	}
	
	/**
	 * Constructor for an AVL tree with the initial value of the root given.
	 * @param value A first value to add to the tree, this will be set as the root
	 */
	public AVLTree(T value) {
		super(value);
		getTree().getRoot().setWeight(0);
	}
	

	/**
	 * Operation for adding a value to the tree. The values are added left if 
	 * they are less than the root and right if they are greater than (or equal 
	 * to). The balance factors of each node are then updated and any necessary
	 * rotation occurs.
	 * @param value the value to be added to the tree
	 * @throws DuplicateValueException The value being added already exists 
	 * within the tree
	 */
	public Node<T> add(T value) throws DuplicateValueException {
		Node<T> addedNode = super.addInternal(value, getTree().getRoot());
		
		updateBalanceFactors(addedNode, addedNode.isLeftChild(), addedNode.isLeftChild()? 
				addedNode.getParent().hasRightChild() ? false: true : 
					addedNode.getParent().hasLeftChild() ? false: true);
		
		return addedNode;
	}

	/**
	 * Operation for removing a value from the tree. The balance factors of each
	 * node are then updated and any necessary rotation occurs.
	 * @param value the value to be removed from the tree
	 * @throws  ValueNotFoundException The value does not exist within the tree
	 */
	public Node<T> delete(T value) throws ValueNotFoundException {
		Node<T> node = super.deleteInternal(value, getTree().getRoot()).getParent();
		updateBalanceFactors(node, node.isLeftChild(), node.isLeftChild() ?
				node.getParent().hasRightChild() ? false: true : 
					node.getParent().hasLeftChild() ? false: true);
		return node;
	}
	
	/**
	 * Operation for updating the balance factors of a node and then all the nodes
	 * above until no change is occurring.
	 * @param node the node whose balance factor needs updating
	 * @param isLeftChild if this node is a left child of its parent (false = it is 
	 * a right child)
	 * @param heightChanged whether the change in the tree that has called this function
	 * affected the height of the parent of node (this controls how far up the tree the 
	 * balance factors are updated)
	 */
	private void updateBalanceFactors(Node<T> node, boolean isLeftChild, boolean heightChanged) {
		if (!node.isRoot()) {
			Node<T> parent = node.getParent();
			parent.setWeight(parent.getWeight() + (isLeftChild? 1 : -1));
			if (parent.getWeight() < -1) {
				System.out.println(toString());
				if (parent.getRightChild().getWeight() > 0) {
					doubleLeftRotation(parent);
				} else {
					singleLeftRotation(parent);
				}
			}
			else if (parent.getWeight() > 1){
				if (parent.getLeftChild().getWeight() > 0) {
					singleRightRotation(parent);
				} else {
					doubleRightRotation(parent);
				}
				parent.setWeight(0);
			}
			if (heightChanged) {
				updateBalanceFactors(parent, parent.isLeftChild(), true);	
			}
		}
	}
	
	/**
	 * Operation to conduct a single right rotation operation on the tree at the 
	 * position of the node given (to re-balance the tree).
	 * @param node the node to representing the position of the unbalanced node 
	 * to conduct this rotation on
	 */
	private void singleRightRotation(Node<T> node) {
		Node<T> b = node.getLeftChild();
		Node<T> c = b.getRightChild();
		
		// Move parent pointer
		if (!node.isRoot()) {
			if (node.isLeftChild()) {
				node.getParent().setLeftChild(b);
			} else {
				node.getParent().setRightChild(b);
			}
		} else {
			b.setParent(null);
			getTree().setRoot(b);
		}
		
		// Move left pointer
		node.setLeftChild(c);
		
		// Move right pointer
		b.setRightChild(node);
		
	}
	
	/**
	 * Operation to conduct a single left rotation operation on the tree at the 
	 * position of the node given (to re-balance the tree).
	 * @param node the node to representing the position of the unbalanced node 
	 * to conduct this rotation on
	 */
	private void singleLeftRotation(Node<T> node) {
		Node<T> b = node.getRightChild();
		Node<T> c = b.getLeftChild();
		
		// Move parent pointer
		if (!node.isRoot()) {
			if (node.isLeftChild()) {
				node.getParent().setLeftChild(b);
			} else {
				node.getParent().setRightChild(b);
			}
		} else {
			b.setParent(null);
			getTree().setRoot(b);
		}
		// Move left pointer
		node.setRightChild(c);
		
		// Move right pointer
		b.setLeftChild(node);
		
	}

	/**
	 * Operation to conduct a double right rotation operation on the tree at the 
	 * position of the node given (to re-balance the tree).
	 * @param node the node to representing the position of the unbalanced node 
	 * to conduct this rotation on
	 */
	private void doubleRightRotation(Node<T> node) {
		singleLeftRotation(node.getLeftChild());
		singleRightRotation(node);
	}
	
	/**
	 * Operation to conduct a double left rotation operation on the tree at the 
	 * position of the node given (to re-balance the tree).
	 * @param node the node to representing the position of the unbalanced node 
	 * to conduct this rotation on
	 */
	private void doubleLeftRotation(Node<T> node) {
		singleRightRotation(node.getRightChild());
		singleLeftRotation(node);
	}
}
