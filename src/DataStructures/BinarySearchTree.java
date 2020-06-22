package DataStructures;

import DataStructures.Trees.BinaryTree;
import DataStructures.Trees.DuplicateValueException;
import DataStructures.Trees.Node;
import DataStructures.Trees.TreeIsEmptyException;
import DataStructures.Trees.ValueNotFoundException;
/**
 * 
 * @author Annie Talbot
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
	
	private BinaryTree<T> tree;
	
	public BinaryTree<T> getTree() {
		return tree;
	}
	public BinarySearchTree() {
		tree = new BinaryTree<T>();
	}
	
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
	
	public void display(int i) {
		tree.display(i);
	}
	
	public String preorder() {
		return tree.preorder(tree.getRoot()).toString();
	}
	
	public String inorder() {
		return tree.inorder(tree.getRoot()).toString();
	}
	
	public String postorder() {
		return tree.postorder(tree.getRoot()).toString();
	}
	
}
