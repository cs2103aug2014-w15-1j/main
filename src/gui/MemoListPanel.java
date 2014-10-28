package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MemoListPanel extends JPanel {

	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;

	private int alphaValue = 0;
	private Color[] layerColors = {
			new Color(251, 172, 27, alphaValue),
			new Color(244, 167, 224, alphaValue),
			new Color(246, 40, 52, alphaValue),
			new Color(248, 113, 0, alphaValue),
			new Color(141, 196, 0, alphaValue) };
	private Color lightGrey = new Color(220, 220, 220, 213);
	
	MemoListPanel(ArrayList<String> firstCol, ArrayList<String> secondCol) {
		super();
		this.setOpaque(false);
		
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.anchor = GridBagConstraints.FIRST_LINE_START;
		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 10;  
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = new Insets(5,10,1,1);
		c1.weightx = 0.7;
		this.add(createColoredLabel("title" , layerColors[1]), c1);
		for(int i=0; i<firstCol.size(); i++) {
			c1.gridy = i+1;
			this.add(createColoredLabel(i+1 + ". " + firstCol.get(i), lightGrey), c1);
		}
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.weightx = 0.1;
		c2.insets = new Insets(5,10,1,1);
		c2.fill = GridBagConstraints.BOTH;
		this.add(createColoredLabel("endTime" , layerColors[0]), c2);
		for(int i=0; i<secondCol.size(); i++) {
			c2.gridy = i+1;
			this.add(createColoredLabel(secondCol.get(i), lightGrey), c2);
		}
		
	}
	
	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		// label.setVerticalAlignment(JLabel.TOP);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		return label;
	}

	
}
