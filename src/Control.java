import java.util.Random;

import javax.swing.SwingUtilities;

import Algorithms.LinkedList;
import Algorithms.SortMachine;
import DataStructures.AVLTree;
import DataStructures.PriorityQueue;
import DataStructures.Stack;
import DataStructures.Graphs.EdgeAlreadyExistsException;
import DataStructures.Graphs.Graph;
import DataStructures.LinkedLists.DNode;
import DataStructures.LinkedLists.SentinelDLinkedList;
import DataStructures.Trees.AVLTreeBuilder;
import DataStructures.Trees.TreeIsEmptyException;

public class Control {
	public static void main(String[] args) {
		
//		Graph<Integer> g = new Graph<Integer>(6);
//		 
//		try { 
//			g.addEdge(0, 1, 2, false); 
//			g.addEdge(0, 4, 6, false); 
//			g.addEdge(0, 5, 3, false); 
//			g.addEdge(1, 2, 4, false); 
//			g.addEdge(1, 3, 2, false); 
//			g.addEdge(2, 3, 1, false); 
//			g.addEdge(3, 4, 5, false); 
//			g.addEdge(4, 5, 1, false); 
//		} catch (EdgeAlreadyExistsException e) { 
//			System.out.println(e); 
//		} finally {
//		System.out.println(g.toString()); 
//		}
//		 
//		System.out.printf("Breadth First Search Route: \n " + g.BFS(0).toString()); 
//		g.resetStatus();
//		 
//		System.out.printf("Depth First Search Route: \n " + g.DFS(0).toString()); 
//		g.resetStatus();
//		
//		System.out.printf("Prims MST: \n " + g.prims(0).toString());
//		g.resetStatus();
//		 
//		System.out.printf("Dijkstras MST: \n " + g.prims(0).toString());
//		g.resetStatus();

		
//		 BinarySearchTree<Integer> t = new BinarySearchTree<Integer>(); 
//		 try { 
//			 t.add(4); 
//			 t.add(0); 
//			 t.add(2); 
//			 t.add(1); 
//			 t.add(8);
//			 t.add(6);
//			 t.add(7);
//			 t.add(5);
//			 t.add(3);
//			 try {
//				t.delete(4);
//			} catch (ValueNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 t.display(2);
//		 
//		 } catch (DuplicateValueException e) {
//			 e.printStackTrace(); 
//		 }
//		 
		 

		/*
		 * AVLTree<Integer> at = new AVLTree<Integer>(2); try { at.add(1); at.add(5);
		 * at.add(3); at.add(6); at.add(4);
		 * 
		 * } catch (DuplicateValueException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } at.display(3);
		 */
//
//		 SwingUtilities.invokeLater(new Runnable() {
//		 
//			 @Override public void run() { AVLTreeBuilder<Integer> tb = new
//		 AVLTreeBuilder<Integer>("Tree 1", new AVLTree<Integer>(2)); 
//			 } 
//			 });
//		 

//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
//		pq.push(-1, 0);
//		try {
//			pq.pop();
//		} catch (TreeIsEmptyException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		pq.push(1, 2);
//		pq.push(4, 6);
//		pq.push(5, 3);
//		try {
//			pq.pop();
//		} catch (TreeIsEmptyException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		pq.push(2, 4);
//		pq.push(3, 2);
//		System.out.printf(pq.toString());
//		pq.display();
		
		LinkedList<Integer>  list = new LinkedList<Integer>();
		Integer[] array = new Integer[10];
		Random r = new Random();
		int[] numbers = {7, 4, 8, 1, 2, 13, 10, 14, 9, 11};
		
		for (int i = 0; i < 10; i ++) {
			int x = numbers[i];
			list.insertEnd(x);
			array[i] = x;
		}
		printArray(array);
		
		SortMachine<Integer> sortM = new SortMachine<Integer>();
		

		//sortM.mergeSort(list);
		//System.out.println(list.toString());
		array = sortM.countingSort(array);
	}
	
	private static void printArray(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
