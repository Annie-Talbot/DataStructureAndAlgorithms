package DataStructures.Trees;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
/**
 * Display class inherits from JComponent and is used to create a visual
 * representation of a Tree object. 
 * @author Annie Talbot
 *
 * @param <T> The class of the object held in each Node of the tree.
 */
public class TreeDisplay<T> extends JComponent {
	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455607L;
	/**
	 * Width of the frame.
	 */
	protected int width;
	/**
	 * Height of the frame.
	 */
	protected int height;
	/**
	 * Radius of the nodes.
	 */
	protected int radius;
	/**
	 * The number of pixels in between each level of the tree.
	 */
	protected int difference;
	/**
	 * The number of levels in the tree.
	 */
	protected int treeHeight;
	
	/**
	 * The root node of the tree.
	 */
	private Node<T> root;

	/**
	 * Constructor for a TreeDisplay object.
	 * @param root the root of the tree to draw
	 * @param width the width of the frame
	 * @param height the height of the frame
	 */
	public TreeDisplay(Node<T> root, int width, int height) {
		this.root = root;
		this.width = width;
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		if (root != null) {
			treeHeight = root.getHeight();
			radius = 20;
			difference = (height - 50) / (treeHeight + 1);
			drawNode(root, g, width - radius, 25 - difference);
		}
	}
	
	/**
	 * Function used to draw the node given onto the frame and any child nodes.
	 * @param node	The node to be drawn
	 * @param g	The graphics component to be used to draw the nodes
	 * @param prevX	The x coordinate on the frame of the previous node drawn
	 * @param prevY The y coordinate on the frame of the previous node drawn
	 */
	protected void drawNode(Node<T> node, Graphics g, int prevX, int prevY) {
		if (node != null) {
			int relativeHeight = treeHeight - node.getHeight();
			int ratio = (int) Math.pow(2, relativeHeight + 1);
			int x;
			if (node.isLeftChild()) {
				x = prevX - (width/ratio);
			} else {
				x = prevX + (width/ratio);
			}
			int y = prevY + difference;
			System.out.println("Node: " + node.getValue() + " x: " + x + " y: " + y + " \n");
			g.fillOval(x, y, radius * 2, radius * 2);
			g.setColor(Color.WHITE);
			FontMetrics fm = g.getFontMetrics();
			g.drawString(node.getValue().toString(), x + radius - 
					(fm.stringWidth(node.getValue().toString()) / 2), y + radius + (fm.getHeight()/4));
			g.setColor(Color.BLACK);
			drawNode(node.getLeftChild(), g, x, y);
			drawNode(node.getRightChild(), g, x, y);
			if (!node.isRoot()) {
				g.drawLine(prevX + radius, prevY + radius * 3/2, x + radius, y + radius * 1/2);
			}
		}
	}
}
