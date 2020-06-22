package DataStructures.Trees;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import DataStructures.Queue;
/**
 * Binary Tree data structure. This has no organisation but enables adding, 
 * removing and searching for elements.
 * 
 * @author Annie Talbot
 * @param <T> the object type for the tree to hold
 */
public class BinaryTree<T> {
	/**
	 * The root node of the tree
	 */
	private Node<T> root;
	
	/**
	 * @return the root node of the tree
	 */
	public Node<T> getRoot() {
		return root;
	}
	
	/**
	 * 
	 * @param root the node to set as the root of this tree
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	/**
	 * Constructor for an empty tree	
	 */
	public BinaryTree() {
		
	}
	/**
	 * Constructor for a tree with the first value given
	 * @param value the first value to add to the tree
	 */
	public BinaryTree(T value) {
		root = new Node<T>(value, null);
	}
	/**
	 * Constructor for a tree with the root node given
	 * @param root the root node of the tree
	 */
	public BinaryTree(Node<T> root) {
		this.root = root;
	}
	/**
	 * Operation for adding a value to the tree. The values are added evenly so that
	 * the tree stays balanced
	 * @param value the value to be added
	 * @throws DuplicateValueException 
	 */
	public Node<T> add(T value) throws DuplicateValueException {
		return addInternal(value, getRoot());
	}
	
	protected Node<T> addInternal(T value, Node<T> root) throws DuplicateValueException {
		if (root.isLeaf() || root.getLeftChild() == null) {
			root.setLeftChild(new Node<T>(value, root));
			return root.getLeftChild();
		} else if (root.getRightChild() == null) {
			root.setRightChild(new Node<T>(value, root));
			return root.getRightChild();
		} else if (root.getLeftChild().getHeight() > root.getRightChild().getHeight()) {
			return addInternal(value, root.getRightChild());
		} else {
			return addInternal(value, root.getLeftChild());
		}
	}
	
	/**
	 * Operation for removing the first instance of the value given in the tree
	 * @param value the value to search for and remove
	 * @throws ValueNotFoundException if this value cannot be found in the tree
	 */
	public Node<T> delete(T value) throws ValueNotFoundException {
		Node<T> node = search(value, root);
		if (node != null) {
			return delete(node);
		} else {
			throw new ValueNotFoundException("The value could not be found in this tree.");
		}
	}
	
	/**
	 * Operation for removing all instances of the value given in the tree
	 * @param value the value to search for and remove
	 */
	protected void deleteAll(T value) {
		Node<T> node = null;
		try {
			node = search(value, root);
		} catch (ValueNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (node != null) {
			delete(node);
			try {
				node = search(value, root);
			} catch (ValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Operation for replacing the first instance of the value given in the tree
	 * @param oldValue the value to search for and exchange
	 * @param newValue the new value to put in the tree
	 */
	protected void replace(T oldValue, T newValue) throws ValueNotFoundException {
		Node<T> node = search(oldValue, root);
		if (node != null) {
			node.setValue(newValue);
		} else {
			throw new ValueNotFoundException("The value could not be found in this tree.");
		}
	}
	
	/**
	 * Operation for replacing all instances of the value given in the tree
	 * @param oldValue the value to search for and exchange
	 * @param newValue the new value to put in the tree
	 */
	protected void replaceAll(T oldValue, T newValue) {
		Node<T> node = null;
		try {
			node = search(oldValue, root);
		} catch (ValueNotFoundException e) {
			e.printStackTrace();
		}
		while (node != null) {
			node.setValue(newValue);
		}
	}
	
	/**
	 * Operation to search for and return the node with the value given
	 * @param value the value to search for
	 * @param root the root of the tree to search
	 * @return the node with the value required (if value is null, the value could 
	 * not be found)
	 * @throws ValueNotFoundException 
	 */
	protected Node<T> search(T value, Node<T> root) throws ValueNotFoundException {
		Node<T> found;
		if (root == null) {
			return null;
		}
		if (root.getValue() == value) {
			return root;
		} else {
			found = search(value, root.getLeftChild());
			if (found == null) {
				found = search(value, root.getRightChild());
			}
			return found;
		}
	}
	
	/**
	 * Operation to remove the given node from the tree.
	 * @param node the node to remove
	 * @return 
	 */
	protected Node<T> delete(Node<T> node) {
		if (node.isLeaf()) {
			return deleteLeaf(node);
		} else {
			try {
				Node<T> replacementNode = null;
				if (node.hasLeftChild()) {
					replacementNode = findMax(node.getLeftChild());
				}
				if (replacementNode == null) {
					replacementNode = findMin(node.getLeftChild());
				}
				node.setValue(replacementNode.getValue());
				return deleteLeaf(replacementNode);
			} catch (TreeIsEmptyException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * Finds the right most value in the tree starting at the node given.
	 * @param root the node to start from
	 * @return the rightmost node
	 * @throws TreeIsEmptyException If the root node given is empty
	 */
	public Node<T> findMax(Node<T> root) throws TreeIsEmptyException{
		if (root == null) {
			throw new TreeIsEmptyException("The tree is empty.");
		}
		else if (root.hasRightChild()){
			return findMax(root.getRightChild());
		} else {
			return root;
		}
		
	}
	
	/**
	 * Finds the left most value in the tree starting at the node given.
	 * @param root the node to start from
	 * @return the leftmost node
	 * @throws TreeIsEmptyException If the root node given is empty
	 */
	public Node<T> findMin(Node<T> root) throws TreeIsEmptyException{
		if (root == null) {
			throw new TreeIsEmptyException("The tree is empty.");
		}
		else if (root.hasLeftChild()){
			return findMin(root.getLeftChild());
		} else {
			return root;
		}
		
	}
	
	/**
	 * Operation to remove all pointers to the left node given.
	 * @param node the leaf node to remove
	 */
	public Node<T> deleteLeaf(Node<T> node) {
		if (!node.isRoot()) {
			if (node.isLeftChild()) {
				node.getParent().setLeftChild(null);
			} else {
				node.getParent().setRightChild(null);
			}
			
		} else {
			setRoot(null);
		}
		return node;
		
	}
	
	/**
	 * Pre-order traversal of the tree. Returns the vertex ID's in a queue resembling the order
	 * that the graph would be traversed in a pre-order traversal.
	 * @param startVertexId the ID of the vertex to start the sort on
	 */
	public Queue<T> preorder(Node<T> root) {
		Queue<T> route = new Queue<T>();
		if (root != null) {
			route.enqueue(root.getValue());
			route.enqueue(preorder(root.getLeftChild()));
			route.enqueue(preorder(root.getRightChild()));
		}
		return route;
	}
	
	/**
	 * In-order traversal of the tree. Returns the vertex ID's in a queue resembling the order
	 * that the graph would be traversed in a in-order traversal.
	 * @param root the ID of the vertex to start the sort on
	 */
	public Queue<T> inorder(Node<T> root) {
		Queue<T> route = new Queue<T>();
		if (root != null) {
			route.enqueue(preorder(root.getLeftChild()));
			route.enqueue(root.getValue());
			route.enqueue(preorder(root.getRightChild()));
		}
		return route;
	}
	/**
	 * Post-order traversal of the tree. Returns the vertex ID's in a queue resembling the order
	 * that the graph would be traversed in a post-order traversal.
	 * @param startVertexId the ID of the vertex to start the sort on
	 */
	public Queue<T> postorder(Node<T> root) {
		Queue<T> route = new Queue<T>();
		if (root != null) {
			route.enqueue(preorder(root.getLeftChild()));
			route.enqueue(preorder(root.getRightChild()));
			route.enqueue(root.getValue());
		}
		return route;
	}

	/**
	 * Creates a window displaying this tree object.
	 * @param title The title of the display window
	 */
	public void display(String title) {
		int width = 960;
		int height = 640;
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		JComponent canvas = new TreeDisplay<T>(root, width, height);
        frame.add(canvas);
        frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public String toString() {
		String tree = "Tree: \n";
		tree += subtreeToString(root);
		return tree;
	}
	
	/**
	 * Converts the subtree starting from the node given into a string.
	 * @param head	 The node to start from
	 * @return	the String describing the subtree
	 */
	public String subtreeToString(Node<T> head) {
		String subtree = "";
		if (head != null) {
			subtree += head.toString();
			subtree += subtreeToString(head.getLeftChild());
			subtree += subtreeToString(head.getRightChild());
		}
		return subtree;
	}

	
	
}
