package DataStructures;

import DataStructures.Trees.BinaryTree;
import DataStructures.Trees.DuplicateValueException;
import DataStructures.Trees.Node;
import DataStructures.Trees.TreeIsEmptyException;
import DataStructures.Trees.ValueNotFoundException;
/**
 * BinarySearchTree class. Implementation of a Binary Search Tree data structure with
 * operations to add, search and remove elements.
 * @author Annie Talbot
 *
 * @param <T> The class of the objects this tree will hold.
 */
public class BinarySearchTree<T extends Comparable<T>> {
	/**
	 * The binary tree holding all the values in the BST. 
	 */
	private BinaryTree<T> tree;
	/**
	 * Getter for the tree.
	 * @return	the tree
	 */
	public BinaryTree<T> getTree() {
		return tree;
	}
	
	/**
	 * Constructor for an empty BinarySearchTree object.
	 */
	public BinarySearchTree() {
		tree = new BinaryTree<T>();
	}
	
	/**
	 * Constructor for a BinarySearchTree object with a first value to be added.
	 * @param value the value to be added
	 */
	public BinarySearchTree(T value) {
		tree = new BinaryTree<T>(value);
	}
	
	/**
	 * Operation for adding a value to the tree. The values are added left if 
	 * they are less than the root and right if they are greater than (or equal 
	 * to).
	 * @param value the value to be added
	 * @throws DuplicateValueException The value being added already exists 
	 * within the tree
	 */
	public Node<T> add(T value) throws DuplicateValueException {
		return addInternal(value, tree.getRoot());
	}
	
	/**
	 * The internal recursive add operation that places an element in its correct position
	 * within the tree.
	 * @param value	the value to be added
	 * @param root	The subtree to add this value to
	 * @return	the node holding the added value
	 * @throws DuplicateValueException	thrown if this value is already in the tree
	 */
	protected Node<T> addInternal(T value, Node<T> root) throws DuplicateValueException {
		if (root != null) {
			if (root.getValue().compareTo(value) > 0) {
				if (root.hasLeftChild()) {
					// Move left down the tree
					return addInternal(value, root.getLeftChild());
				} else {
					// Add value left of root
					root.setLeftChild(new Node<T>(value, root));
					return root.getLeftChild();
				}
			} else if (root.getValue().compareTo(value) < 0) {
				if (root.hasRightChild()) {
					// Move right down the tree
					return addInternal(value, root.getRightChild());
				} else {
					// Add value right of root
					root.setRightChild(new Node<T>(value, root));
					return root.getRightChild();
				}
			} else {
				throw new DuplicateValueException("This value is already in the tree");
			}	

		} else {
			tree.setRoot(new Node<T>(value, null));
			return tree.getRoot();
		}
	}
	
	/**
	 * Operation for removing the value given from the tree
	 * @param value the value to search for and remove
	 * @throws ValueNotFoundException if this value cannot be found in the tree
	 */
	public Node<T> delete(T value) throws ValueNotFoundException {
		return deleteInternal(value, tree.getRoot());
	}
	
	/**
	 * The internal recursive add operation that places an element in its correct position
	 * within the tree.
	 * @param value	the value to be removed
	 * @param root	The subtree to remove this value from
	 * @return	The removed Node
	 * @throws ValueNotFoundException thrown if this value is not in the tree
	 */
	protected Node<T> deleteInternal(T value, Node<T> root) throws ValueNotFoundException {
		if (root != null) {
			if (root.getValue().compareTo(value) > 0) {
				return deleteInternal(value, root.getLeftChild());
			} else if (root.getValue().compareTo(value) < 0) {
				return deleteInternal(value, root.getRightChild());
			} else {
				// value found at root
				if (root.isLeaf()) {
					return tree.deleteLeaf(root);
				}
				else {
					try {
						if (root.hasLeftChild()) {
							Node<T> replacementNode = tree.findMax(root.getLeftChild());
							root.setValue(replacementNode.getValue());
							return tree.deleteLeaf(replacementNode);
						} else {
							Node<T> replacementNode = tree.findMin(root.getRightChild());
							root.setValue(replacementNode.getValue());
							return tree.deleteLeaf(replacementNode);
						}
					} catch (TreeIsEmptyException e) {
						e.printStackTrace();
					}
				}
			}
		}
		throw new ValueNotFoundException("The value could not be found in the tree.");
	}
	
	
	/**
	 * Operation to search for and return the node with the value given
	 * @param value the value to search for
	 * @param root the root of the tree to search
	 * @return the node with the value required (if value is null, the value could 
	 * not be found)
	 * @throws ValueNotFoundException the value searched for is not in the tree
	 */
	protected Node<T> search(T value, Node<T> root) throws ValueNotFoundException {
		if (root == null) {
			throw new ValueNotFoundException("The value could not be found in the tree.");
		}
		if (root.getValue() == value) {
			return root;
		}  else if (root.getValue().compareTo(value) > 0) {
			return search(value, root.getRightChild());
		} else {
			return search(value, root.getRightChild());
		}
	}
	
	/**
	 * Operation to create a window hat display the current state of the tree.
	 * @param title	The title of this window
	 */
	public void display(String title) {
		tree.display(title);
	}
	
	/**
	 * Operation to create a string describing all the values in the tree, 
	 * traversed in pre-order.
	 * @return	The string describing the traversal
	 */
	public String preorder() {
		return tree.preorder(tree.getRoot()).toString();
	}
	
	/**
	 * Operation to create a string describing all the values in the tree, 
	 * traversed in in-order.
	 * @return	The string describing the traversal
	 */
	public String inorder() {
		return tree.inorder(tree.getRoot()).toString();
	}
	
	/**
	 * Operation to create a string describing all the values in the tree, 
	 * traversed in post-order.
	 * @return	The string describing the traversal
	 */
	public String postorder() {
		return tree.postorder(tree.getRoot()).toString();
	}
	
}
