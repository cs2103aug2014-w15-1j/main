package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AttributePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	private static Color lightCyan220 = new Color(55, 177, 241, 120);

	AttributePanel(ArrayList<String> firstCol, ArrayList<String> secondCol) {
		super();
		this.setOpaque(false);
		
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		
		int currentIndex = 0;
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 3;
		c1.ipady = 20;
		c1.insets = new Insets(50, 10, 0, 10);
		c1.weightx = 0.8;
		this.add(new InfoPanel(new Color(255,255,255,100), firstCol.get(currentIndex), secondCol.get(currentIndex)), c1);
		
		currentIndex++;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		c2.gridx = 0;
		c2.gridy = 1;
		c2.weightx = 0.3;
		c2.ipady = 20;
		c2.insets = new Insets(10, 10, 0, 0);
		this.add(new InfoPanel(new Color(255,255,255,100), firstCol.get(currentIndex), secondCol.get(currentIndex)), c2);
		
		c2.gridx++;
		currentIndex++;
		this.add(new InfoPanel(new Color(255,255,255,100), firstCol.get(currentIndex), secondCol.get(currentIndex)), c2);
		
		c2.gridx++;
		c2.insets = new Insets(10, 10, 0, 10);
		currentIndex++;
		this.add(new InfoPanel(new Color(255,255,255,100), firstCol.get(currentIndex), secondCol.get(currentIndex)), c2);
		
		currentIndex++;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.BOTH;
		c3.gridx = 0;
		c3.gridy = 2;
		c3.weightx = 0.9;
		c3.ipady = 20;
		c3.insets = new Insets(10, 10, 70, 10);
		c3.gridwidth = 3;
		c3.gridheight = 3;
		c3.weighty = 0.3;
		this.add(new InfoPanel(new Color(255,255,255,100), firstCol.get(currentIndex), secondCol.get(currentIndex)), c3);
	}
	
}
