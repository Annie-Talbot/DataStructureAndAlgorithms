package DataStructures.Trees;

/**
 * 
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

	private int height = 0;
	
	public int getHeight() {
		return height;
	}
	
	public boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
	
	public boolean hasLeftChild() {
		if (leftChild != null) {
			return true;
		}
		return false;
	}
	public boolean hasRightChild() {
		if (rightChild != null) {
			return true;
		}
		return false;
	}
	public boolean isLeftChild() {
		if(isRoot()) {
			return true;
		}
		return getParent().getLeftChild() == this;
	}
	public boolean isRightChild() {
		return !isLeftChild();
	}
	
	public boolean isLeaf(){
		if (getLeftChild() == null && getRightChild() == null) {
			return true;
		}
		return false;
	}

	
	public Node(T value, Node<T> parent) {
		setValue(value);
		setParent(parent);
	}

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
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	private void updateHeight() {
		int leftHeight = hasLeftChild() ? getLeftChild().getHeight() + 1: 0;
		int rightHeight = hasRightChild() ? getRightChild().getHeight() + 1: 0;
		height = leftHeight > rightHeight ? leftHeight : rightHeight;
		if (!isRoot()) {
			getParent().updateHeight();
		}
	}
}
