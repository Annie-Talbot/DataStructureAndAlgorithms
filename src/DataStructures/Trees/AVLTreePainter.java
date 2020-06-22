package DataStructures.Trees;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import DataStructures.AVLTree;
/**
 * AVLTreePainter class inherits TreeDisplay but with implementation to handle
 * the changing root of an AVL Tree.
 * @author Annie Talbot
 *
 * @param <T> The class of the object held in each Node of the tree.
 */
public class AVLTreePainter<T extends Comparable<T>> extends TreeDisplay<T>{
	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455601L;
	/**
	 * The tree to be drawn.
	 */
	private AVLTree<T> tree;
	/**
	 * Constructor for an AVLTreePainter object.
	 * @param tree the tree to be drawn
	 * @param width	the width of the frame
	 * @param height the height of the frame
	 */
	public AVLTreePainter(AVLTree<T> tree, int width, int height) {
		super(tree.getTree().getRoot(), width, height);
		setSize(width, height);
		this.tree = tree;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		
		treeHeight = tree.getTree().getRoot().getHeight();
		radius = 20;
		difference = (height - 50) / (treeHeight + 1);
		drawNode(tree.getTree().getRoot(), g, width - radius, 25 - difference);
	}
}
