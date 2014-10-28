package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <code>ColumnListPanel</code> is a customized swing container that extends
 * <code>JPanel</code>. 
 * Its can function as a template to display the information of a certain task list in two columns.
 * <p>Layout manager used in <code>ColumnListPanel</code> is <code>GridBagLayout</code>. 
 * every parts inside  
 * @author A0119391
 * 
 */
public class ColumnListPanel extends JPanel {

	private static final long serialVersionUID = -5452419359255825458L;
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	private int NUM_OF_COL = 10;
	private int alphaValue = 120;
	private Color[] layerColors = { new Color(251, 172, 27, alphaValue),
			new Color(244, 167, 224, alphaValue),
			new Color(246, 40, 52, alphaValue),
			new Color(248, 113, 0, alphaValue),
			new Color(141, 196, 0, alphaValue) };

	private Color lightGrey = new Color(120, 220, 220, 213);
	private Color dodegblueB = new Color(0, 229, 238, 120);
	private Color dodgerblue = new Color(0, 229, 238, 20);
	private Color dodgerblue2 = new Color(255, 62, 150, 50);

	ColumnListPanel(ArrayList<String> firstCol, ArrayList<String> secondCol) {
		super();
		// this.setBackground(new Color(255,0,0,10));
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		this.setOpaque(false);
		setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();

		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 17;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = new Insets(0, 10, 0, 10);
		c1.weightx = 0.9;
		// c1.weighty = 0.91;
		this.add(createColoredLabel("title", dodegblueB), c1);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c1.gridy = i + 1;
			if (i < firstCol.size()) {
				this.add(
						createColoredLabel(i + 1 + ". " + firstCol.get(i),
								dodgerblue), c1);
			} else {
				this.add(createEmptyLabel(), c1);
			}

		}

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridy = 0;
		c2.gridx = 1;
		c2.weightx = 0.1;
		c2.insets = new Insets(0, 10, 0, 10);
		c2.fill = GridBagConstraints.BOTH;
		this.add(createColoredLabel("endTime", dodegblueB), c2);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c2.gridy = i + 1;
			if (i < secondCol.size()) {
				this.add(
						createColoredLabel(i + 1 + ". " + secondCol.get(i),
								dodgerblue), c2);
			} else {
				this.add(createEmptyLabel(), c2);
			}

		}

	}

	private JLabel createEmptyLabel() {
		JLabel label = new JLabel(" ");
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		// label.setVerticalAlignment(JLabel.TOP);
		label.setOpaque(false);
		// label.setBackground(Color.red);
		// label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,20)));
		return label;
	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		// label.setVerticalAlignment(JLabel.TOP);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		// label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,20)));
		return label;
	}

}
