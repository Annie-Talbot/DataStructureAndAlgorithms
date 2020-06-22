package Algorithms;

import DataStructures.LinkedLists.DNode;
import DataStructures.LinkedLists.SentinelDLinkedList;

public class SortMachine<T extends Comparable<T>> {
	
	public void selectionSort(T[] list) {
		int last = list.length - 1;
		int posOfMax;
		while (last > 0) {
			posOfMax = findMax(list, last);
			swap(list, last, posOfMax);
			last--;
		}
	}
	
	public void selectionSort(SentinelDLinkedList<T> list) {
	}
	
	// INSERTION SORT
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
	
	// QUICK SORT
	public void quickSort(T[] list) {
		quickSortRecursion(list, 0, list.length - 1);
	}
	
	private void quickSortRecursion(T[] list, int first, int last) {
		if (first < last) {
			medianOf3(list, first, last);
			int split = partition(list, first, last);
			quickSortRecursion(list, first, split - 1);
			quickSortRecursion(list, split + 1, last);
		}
		
	}

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
	
	public void quickSort(LinkedList<T> list) {
		list.insertEnd(quickSortRecursion(list));
	}
	
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
	
	// MERGE SORT
	public void mergeSort(Integer[] list) {
		mergeSortRecursion(list, 0, list.length - 1);		
	}

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

	public void mergeSort(LinkedList<T> list) {
		list.replaceList(mergeRecur(list.getHead().getPrev()));
	}
	
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
	
	
	private void swap(T[] list, int index1, int index2) {
		T temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
	}
	
	private int findMax(T[] list, int lastIndex) {
		int highestIndex = 0;
		for (int i = 0; i <= lastIndex; i++) {
			if(list[i].compareTo(list[highestIndex]) > 0) {
				highestIndex = i;
			}
		}
		return highestIndex;
	}
	private static void printArray(Integer[] amounts) {
		for (int i = 0; i < amounts.length; i++) {
			System.out.println(i + ": " + amounts[i]);
		}
	}
}
