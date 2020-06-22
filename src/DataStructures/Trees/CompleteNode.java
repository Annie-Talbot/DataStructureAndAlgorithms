package DataStructures.Trees;
/**
 * Weight represent whether a node is complete or not
 * @author theb4
 *
 * @param <T>
 */
public class CompleteNode<T> extends Node<T> {

	public CompleteNode(T value, int weight, CompleteNode<T> parent) {
		super(value, parent);
		setWeight(weight);
	}

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
