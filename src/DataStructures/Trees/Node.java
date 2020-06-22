package DataStructures.Trees;

/**
 * Node class. To be used for the tree data structures found in this package.
 * @author Annie Talbot
 *
 */
public class Node<T> {
	/**
	 * Pointer to this node's parent node
	 */
	private Node<T> parent;
	/**
	 * @return the parent node of this node
	 */
	public Node<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the node to set as this node's parent
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	/**
	 * Pointer to this node's left child node
	 */
	private Node<T> leftChild;
	
	/**
	 * @return the left child node of this node
	 */
	public Node<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param parent the node to set as this node's left child
	 */
	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
		if (leftChild != null) {
			leftChild.setParent(this);
		}
		// Update height
		updateHeight();
	}
	
	/**
	 * Pointer to this node's right child node
	 */
	private Node<T> rightChild;
	
	/**
	 * @return the right child node of this node
	 */
	public Node<T> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild the node to set as this node's right child
	 */
	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
		if (rightChild != null) {
			rightChild.setParent(this);
		}
		// Update height
		updateHeight();
	}
	
	/**
	 * The value this vertex holds
	 */
	private T value;
	
	/**
	 * @return the value this vertex holds
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set this vertex to hold
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * The level of the tree this node is on
	 */
	private int height = 0;
	
	/**
	 * Getter for the height of this node.
	 * @return the height of this node
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Checks whether this node is the root of a tree (no parent node).
	 * @return True = this node is a root, False = this node is not a root
	 */
	public boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether this node has a left child node.
	 * @return True = a left child exists, False = no left child
	 */
	public boolean hasLeftChild() {
		if (leftChild != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether this node has a right child node.
	 * @return True = a right child exists, False = no right child
	 */
	public boolean hasRightChild() {
		if (rightChild != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether this node is a left child node.
	 * @return True = is a left child or a root, False = is a right child
	 */
	public boolean isLeftChild() {
		if(isRoot()) {
			return true;
		}
		return getParent().getLeftChild() == this;
	}
	/**
	 * Checks whether this node is a right child node.
	 * @return True = is a right child, False = is a left child or a root
	 */
	public boolean isRightChild() {
		return !isLeftChild();
	}
	
	/**
	 * Checks whether this node is a leaf (no child nodes).
	 * @return True = is a leaf, False = has 1 or more child nodes
	 */
	public boolean isLeaf(){
		if (getLeftChild() == null && getRightChild() == null) {
			return true;
		}
		return false;
	}

	/**
	 * Constructor for a Node object.
	 * @param value	The value for this node to hold
	 * @param parent	The parent node of this (null if root)
	 */
	public Node(T value, Node<T> parent) {
		setValue(value);
		setParent(parent);
	}

	@Override
	public String toString() {
		String node = "Value: " + value + " Height: " + height;
		if (hasLeftChild()) {
			node += " Left: " + leftChild.getValue();
		}
		if (hasRightChild()) {
			node += " Right: " + rightChild.getValue();
		}
		node += " Weight: " + weight;
		node += " \n";
		return node;
	}

	/**
	 * An extra integer value that can be used in more complex trees (e.g. weight
	 * = balance factor in an AVL tree, or weight = priority in a priority queue)
	 */
	private int weight;
	
	/**
	 * Getter for the weight of this node.
	 * @return the weight of this node
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Setter for the weight of this node.
	 * @param weight the weight to set this node to
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Calculates (and replaces the value of) this nodes height in the tree and
	 * updates the height of all nodes above.
	 */
	private void updateHeight() {
		int leftHeight = hasLeftChild() ? getLeftChild().getHeight() + 1: 0;
		int rightHeight = hasRightChild() ? getRightChild().getHeight() + 1: 0;
		height = leftHeight > rightHeight ? leftHeight : rightHeight;
		if (!isRoot()) {
			getParent().updateHeight();
		}
	}
}
