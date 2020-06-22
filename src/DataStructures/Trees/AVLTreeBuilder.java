package DataStructures.Trees;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import DataStructures.AVLTree;
/**
 * AVLTreeBuilder inherits JFrame and implement the interface ActionListener. The class is a
 * small application used to display a visual representation of how an AVL adds elements to
 * itself.
 * @author Annie Talbot
 *
 */
public class AVLTreeBuilder extends JFrame implements ActionListener{

	/**
	 * Serialisation ID for this class
	 */
	private static final long serialVersionUID = -103989763965455602L;
	/*
	 * Width of the frame.
	 */
	int width = 960;
	/**
	 * Height of the frame.
	 */
	int height = 740;
	/**
	 * The component that draws the tree to the frame.
	 */
	AVLTreePainter<Integer> canvas;
	/**
	 * The AVL Tree to be drawn.
	 */
	AVLTree<Integer> tree;
	/**
	 * The number spinner storing the value to be added to the tree upon user action.
	 */
	private SpinnerModel value = new SpinnerNumberModel(1, 1, 50, 1);
	
	/**
	 * Constructor for an AVLTreeBuilder object.
	 * @param title	The title of this frame.
	 * @param tree	The tree to be drawn initially.
	 */
	public AVLTreeBuilder(String title, AVLTree<Integer> tree) {
		super(title);
		setMinimumSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creating GUI in the panel
		Container pane = getContentPane();
		pane.setLayout(null);
		
		// Add components
		this.canvas = new AVLTreePainter<Integer>(tree, width, height - 50);
        pane.add(canvas);
        
        JSpinner spinBox = new JSpinner(value);
        pane.add(spinBox);
        
        JButton addBtn = new JButton("ADD");
        addBtn.addActionListener(this);
        pane.add(addBtn);
        pack();

        // Arrange Components
        Insets insets = getInsets();
        canvas.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        spinBox.setBounds(0, canvas.getHeight() - (insets.top + insets.bottom), canvas.getWidth() / 2 - insets.left, 50);
        addBtn.setBounds(canvas.getWidth()/2 - insets.left, canvas.getHeight() - (insets.top + insets.bottom), canvas.getWidth() / 2 - insets.left, 50);
        pack();
        setVisible(true);
        
        this.tree = tree;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			System.out.println("Button pressed. Value of spin is: " + value.getValue().toString());
			tree.add((Integer) value.getValue());
			canvas.repaint();
		} catch (DuplicateValueException e) {
			e.printStackTrace();
		}
		
	}
}
