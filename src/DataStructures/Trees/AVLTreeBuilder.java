package DataStructures.Trees;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import DataStructures.AVLTree;

public class AVLTreeBuilder<T extends Comparable<T>> extends JFrame implements ActionListener{

	int width = 960;
	int height = 740;
	AVLTreePainter<T> canvas;
	AVLTree<T> tree;
	private SpinnerModel value = new SpinnerNumberModel(6, 2, 50, 1);
	
	public AVLTreeBuilder(String title, AVLTree<T> tree) {
		super(title);
		setMinimumSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creating GUI in the panel
		Container pane = getContentPane();
		pane.setLayout(null);
		
		// Add components
		this.canvas = new AVLTreePainter<T>(tree, width, height - 50);
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
			tree.add((T) value.getValue());
			canvas.repaint();
		} catch (DuplicateValueException e) {
			e.printStackTrace();
		}
		
	}
}
