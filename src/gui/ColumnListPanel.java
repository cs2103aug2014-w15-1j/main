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
 * <code>JPanel</code>. Its can function as a template to display the
 * information of a certain task list in two columns.
 * <p>
 * Layout manager used in <code>ColumnListPanel</code> is
 * <code>GridBagLayout</code>. All sub components inside are JLabel with
 * customized colors.
 * </p>
 * 
 * <strong>Note</strong>: The default behavior of components in
 * <code>GridBagLayout</code> will clump together in the center of their
 * container. To place the component from the top left corner, corresponding 
 * empty JLabel will be added.
 * 
 * @author A0119391
 * 
 */
public class ColumnListPanel extends JPanel {

	private static final long serialVersionUID = -5452419359255825458L;
	
	//info of task list 
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	
	//length of rows 
	private int NUM_OF_COL = 10;
	
	private static Color lightCyan120 = new Color(0, 229, 238, 120);
	private static Color lightCyan20 = new Color(0, 229, 238, 20);
	
	/**
	 * create a new ColumnListPanel with 2-column information filled
	 * @param firstCol ArrayList of all task names
	 * @param secondCol ArrayList of all task endDates
	 */
	ColumnListPanel(ArrayList<String> firstCol, ArrayList<String> secondCol) {
		super();
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		
		setUp();
		constructFirstCol(firstCol);
		constructSecondCol(secondCol);

	}

	private void constructSecondCol(ArrayList<String> secondCol) {
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridy = 0;
		c2.gridx = 1;
		c2.weightx = 0.1;
		c2.insets = new Insets(0, 10, 0, 10);
		c2.fill = GridBagConstraints.BOTH;
		this.add(createColoredLabel("endTime", lightCyan120), c2);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c2.gridy = i + 1;
			if (i < secondCol.size()) {
				this.add(
						createColoredLabel(secondCol.get(i),
								lightCyan20), c2);
			} else {
				this.add(createEmptyLabel(), c2);
			}

		}
	}

	private void constructFirstCol(ArrayList<String> firstCol) {
		GridBagConstraints c1 = new GridBagConstraints();

		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 17;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = new Insets(0, 10, 0, 10);
		c1.weightx = 0.9;
		this.add(createColoredLabel("title", lightCyan120), c1);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c1.gridy = i + 1;
			if (i < firstCol.size()) {
				this.add(
						createColoredLabel(i + 1 + ". " + firstCol.get(i),
								lightCyan20), c1);
			} else {
				this.add(createEmptyLabel(), c1);
			}

		}
	}

	private void setUp() {
		setOpaque(false);
		setLayout(new GridBagLayout());
	}

	// create and return a empty label
	private JLabel createEmptyLabel() {
		JLabel label = new JLabel(" ");
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		label.setOpaque(false);
		return label;
	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		return label;
	}

}
