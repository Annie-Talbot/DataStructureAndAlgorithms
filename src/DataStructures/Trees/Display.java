package DataStructures.Trees;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Display<T> extends JComponent {

	private int width;
	private int height;
	private int radius;
	private int difference;
	private int treeHeight;
	
	Node<T> root;
	public Display(Node<T> root, int width, int height) {
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
	
	private void drawNode(Node<T> node, Graphics g, int prevX, int prevY) {
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
		else {
			System.out.println("This node is null aparently");
		}
	}
}
