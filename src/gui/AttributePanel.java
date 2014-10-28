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

	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;

	private int alphaValue = 100;
	private Color[] layerColors = { new Color(251, 172, 27, alphaValue),
			new Color(52, 167, 224, alphaValue),
			new Color(246, 40, 52, alphaValue),
			new Color(248, 113, 0, alphaValue),
			new Color(141, 196, 0, alphaValue) };

	AttributePanel(ArrayList<String> firstCol, ArrayList<String> secondCol) {
		super();
		this.setOpaque(false);
		
		
		
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 10;  
		c1.gridx = 0;
		c1.gridwidth = 2;
		c1.insets = new Insets(2,10,1,1);
		c1.weightx = 0.1;
		for(int i=0; i<firstCol.size(); i++) {
			c1.gridy = i;
			this.add(createColoredLabel(firstCol.get(i), layerColors[1]), c1);
		}
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 4;
		c2.weightx = 0.8;
		c2.gridwidth = 7;
		c2.insets = new Insets(2,10,1,10);
		c2.fill = GridBagConstraints.BOTH;
		for(int i=0; i<secondCol.size(); i++) {
			c2.gridy = i;
			this.add(createColoredLabel(secondCol.get(i), layerColors[3]), c2);
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
