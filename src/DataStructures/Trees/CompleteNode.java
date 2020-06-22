package DataStructures.Trees;
/**
 * Complete Node class inherits Node but with the operation to
 * check whether a node is complete or not.
 * @author Annie Talbot
 *
 * @param <T> The class of the object this node will hold
 */
public class CompleteNode<T> extends Node<T> {
	
	/**
	 * Constructor for a CompleteNode object.
	 * @param value	the value for this node to hold
	 * @param parent	The parent node of this node
	 */
	public CompleteNode(T value, Node<T> parent) {
		super(value, parent);
	}
	
	/**
	 * Constructor for a CompleteNode object with an extra parameter to assign a value
	 * to the weight of the node.
	 * @param value	the value for this node to hold
	 * @param weight the weight to assign to this node
	 * @param parent	The parent node of this node
	 */
	public CompleteNode(T value, int weight, Node<T> parent) {
		super(value, parent);
		setWeight(weight);
	}
	
	/**
	 * Operation to check if the subtree starting from this node is complete.
	 * @return True = this subtree is complete, False = this subtree is NOT complete
	 */
	public boolean isComplete() {		
		if (hasLeftChild()) {
			if (((CompleteNode<T>) getLeftChild()).isComplete()) {
				if (!hasRightChild()){
					// has left but no right child = not complete
					return false;
				} else if (((CompleteNode<T>) getRightChild()).isComplete()){
					// left and right child are complete = complete
					return true;
				}
			}
		}else if (!hasRightChild()){
			// has neither left or right child = complete
			return true;
		}
		// right child is not complete = not complete
		// left child is not complete = not complete
		// has left child but no right child = not complete
		return false;
			
	}
}
