package Algorithms;

import DataStructures.LinkedLists.DNode;

/**
 * SortMachine class. provides implementations for different sorting algorithms
 * using linked lists and arrays.
 * @author Annie Talbot
 *
 * @param <T> The class of the objects held in the linked list / array
 */
public class SortMachine<T extends Comparable<T>> {
	
	/**
	 * Operation for running a selection sort on an array.
	 * @param list the array to sort
	 */
	public void selectionSort(T[] list) {
		int last = list.length - 1;
		int posOfMax;
		while (last > 0) {
			posOfMax = findMax(list, last);
			swap(list, last, posOfMax);
			last--;
		}
	}
	
	/**
	 * Operation for running an insertion sort on an array.
	 * @param list the array to sort
	 */
	public void insertionSort(T[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int newIndex = i + 1;
			T newValue = list[newIndex];
			while (newIndex > 0 && list[newIndex - 1].compareTo(newValue) > 0 ) {
				list[newIndex] = list[newIndex - 1];
				newIndex--;
			}
			list[newIndex] = newValue;
		}
	}
	
	/**
	 * Operation for running an insertion sort on a linked list.
	 * @param list the linked list to sort
	 */
	public void insertionSort(LinkedList<T> list) {
		DNode<T> currNode = list.getHead().getPrev();
		while (currNode != list.getTail()) {
			DNode<T> newIndex = currNode.getNext();
			while (newIndex != list.getHead() && newIndex.getValue().compareTo(currNode.getValue()) > 0) {
				newIndex = newIndex.getNext();
			}
			// Fix gap
			currNode.getNext().setPrev(currNode.getPrev());
			currNode.getPrev().setNext(currNode.getNext());
			// Set up node for its new position
			currNode.setPrev(newIndex.getPrev());
			currNode.setNext(newIndex);
			// Fix links around new position
			newIndex.getPrev().setNext(currNode);
			newIndex.setPrev(currNode);
			
			currNode = currNode.getPrev();
		}
	}
	
	/**
	 * Operation for running a quick sort on an array.
	 * @param list the array to sort
	 */
	public void quickSort(T[] list) {
		quickSortRecursion(list, 0, list.length - 1);
	}
	
	/**
	 * Recursion method to split and order the list of value between the 2
	 * indices given using quick sort methods
	 * @param list	The array to sort
	 * @param first	An index to start the sort on
	 * @param last An index to end the sort on
	 */
	private void quickSortRecursion(T[] list, int first, int last) {
		if (first < last) {
			medianOf3(list, first, last);
			int split = partition(list, first, last);
			quickSortRecursion(list, first, split - 1);
			quickSortRecursion(list, split + 1, last);
		}
		
	}

	/**
	 * Operation to move all value after first either before or after depending
	 * on if their value is below or above the value held at the first index.
	 * @param list the array to sort
	 * @param first the index of element pivot the other elements around
	 * @param last the index of the last value to move
	 * @return the new index of the value held at first
	 */
	private int partition(T[] list, int first, int last) {
		int lastSorted = first;
		int split = first;
		while (lastSorted < last) {
			if (list[lastSorted + 1].compareTo(list[split]) < 0) {
				for (int i = lastSorted + 1; i > split; i--) {
					swap(list, i, i - 1);
				}
			}
			lastSorted++;
		}
		return split;
	}

	/**
	 * Moves elements so that the item at the first index is the median of the
	 * elements at the first, last and middle indices.
	 * @param list	The array to alter
	 * @param first	the first index
	 * @param last	the last index
	 */
	private void medianOf3(T[] list, int first, int last) {
		int middle = (first + last) / 2;
		// Puts lowest element in first position
		if (list[middle].compareTo(list[first]) < 0) {
			swap(list, middle, first);
		}
		if (list[last].compareTo(list[first]) < 0) {
			swap(list, last, first);
		}
		// Puts highest element in last position
		if (list[middle].compareTo(list[last]) > 0) {
			swap(list, middle, last);
		}
		// First, middle and last are now in sorted order
		// Moving middle to first position for quick sort
		swap(list, middle, first);
	}
	
	/**
	 * Operation for running a quick sort on a linked list.
	 * @param list the linked list to sort
	 */
	public void quickSort(LinkedList<T> list) {
		list.insertEnd(quickSortRecursion(list));
	}
	
	/**
	 * Recursion method to split and order the list of values using quick sort 
	 * methods.
	 * @param list	The linked list to sort
	 * @return the sorted linked list
	 */
	private LinkedList<T> quickSortRecursion(LinkedList<T> list) {
		if (list.getSize() > 1) {
			T split = list.removeBeginning();
			
			LinkedList<T> sortedList = new LinkedList<T>(split);
			LinkedList<T> leftList = new LinkedList<T>();
			LinkedList<T> rightList = new LinkedList<T>();
			
			while (!list.isEmpty()) {
				T iterator = list.removeBeginning();
				if (iterator.compareTo(split) < 0)
				{
					leftList.insertEnd(iterator);
				}
				else {
					rightList.insertEnd(iterator);
				}
			}
			sortedList.insertBeginning(quickSortRecursion(leftList));
			sortedList.insertEnd(quickSortRecursion(rightList));
			
			return sortedList;
		} else {
			return list;
		}
	}
	
	/**
	 * Operation for running a merge sort on an array.
	 * @param list the array to sort
	 */
	public void mergeSort(Integer[] list) {
		mergeSortRecursion(list, 0, list.length - 1);		
	}

	/**
	 * Recursion method to split and order the list of values between the 2
	 * indices given using merge sort methods
	 * @param list	The array to sort
	 * @param first	An index to start the sort on
	 * @param last An index to end the sort on
	 */
	private void mergeSortRecursion(Integer[] list, int first, int last) {
		if (first < last) {
			int centre = (first + last) / 2;
			mergeSortRecursion(list, first, centre);
			mergeSortRecursion(list, centre + 1, last);
			
			// Set up temporary list
			Integer[] temp = new Integer[list.length];
			for (int i = first; i < centre + 1; i++) {
				temp[i] = list[i];
			}
			for (int i = centre; i < last; i++) {
				temp[last + (centre-i)] = list[i + 1];
			}
			// Combine Lists
			int rightIndex = last;
			int leftIndex = first;
			for (int i = first; i <= last;i++) {
				if (temp[rightIndex].compareTo(temp[leftIndex]) < 0) {
					list[i] = temp[rightIndex];
					rightIndex--;
				}
				else {
					list[i] = temp[leftIndex];
					leftIndex++;
				}
			}
		}
		
	}

	/**
	 * Operation for running a merge sort on a linked list.
	 * @param list the linked list to sort
	 */
	public void mergeSort(LinkedList<T> list) {
		list.replaceList(mergeRecur(list.getHead().getPrev()));
	}
	
	/**
	 * Recursion method to split and order the list of values using merge sort 
	 * methods.
	 * @param head	The head of the raw list
	 * @return the head of the sorted list
	 */
	private DNode<T> mergeRecur(DNode<T> head) {
		// if next is tail 
		if (head.getPrev().getValue() != null) {
			DNode<T> slow = findMiddleNode(head);
			DNode<T> centre = slow.getPrev();
			slow.setPrev(new DNode<T>());
			centre.setNext(new DNode<T>());
			
			DNode<T> left = mergeRecur(head);
			DNode<T> right = mergeRecur(centre);
			
			return merge(left, right);
		}
		return head;
	}

	/**
	 * Combines the elements in the lists starting with left and right nodes, 
	 * maintaining sorted order.
	 * @param left	The head of one sorted list
	 * @param right	The head of the other sorted list
	 * @return the head of the sorted list
	 */
	private DNode<T> merge(DNode<T> left, DNode<T> right) {
		if (left.getValue() == null) {
			// Left is a tail
			return right;
		}
		if (right.getValue() == null) {
			// Right is a tail
			return left;
		}
		if (left.getValue().compareTo(right.getValue()) <= 0) {
			// Left is less than right
			left.setPrev(merge(left.getPrev(), right));
			left.getPrev().setNext(left);
			return left;
		} else {
			// Right is less than left
			right.setPrev(merge(left, right.getPrev()));
			right.getPrev().setNext(right);
			return right; 
		}
	}

	/**
	 * Operation to find the node in the middle of the linked list
	 * @param head the head of the linked list
	 * @return the node at the middle of the list
	 */
	private DNode<T> findMiddleNode(DNode<T> head) {
		DNode<T> slow = head;
		DNode<T> fast = head.getPrev();
		
		while (fast.getValue() != null && fast.getPrev().getValue() != null) {
			slow = slow.getPrev();
			fast = fast.getPrev().getPrev();
		}
		return slow;
	}
	
	public Integer[] countingSort(Integer[] list) {
		Integer[] output = new Integer[list.length];
		int[] amounts = new int[list[findMax((T[]) list, list.length - 1)] + 1];
		for (int i = 0; i < list.length; i++) {
			amounts[list[i]]++;
		}
		for (int i=2; i < amounts.length; i++) {
            amounts[i] += amounts[i-1]; 
		}
		for (int i = list.length-1; i>=0; i--) 
        { 
            output[amounts[list[i]]-1] = list[i]; 
            amounts[list[i]]--; 
        }
		return output;
	}
	
	/**
	 * Operation to swap the values in an array stored in the indices given.
	 * @param list the array containing the values
	 * @param index1 
	 * @param index2
	 */
	private void swap(T[] list, int index1, int index2) {
		T temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
	}
	
	/**
	 * Operation to find the largest value in an array before the index given.
	 * @param list the array to search
	 * @param lastIndex index of the last element to compare
	 * @return the index of the highest value
	 */
	private int findMax(T[] list, int lastIndex) {
		int highestIndex = 0;
		for (int i = 0; i <= lastIndex; i++) {
			if(list[i].compareTo(list[highestIndex]) > 0) {
				highestIndex = i;
			}
		}
		return highestIndex;
	}
}
