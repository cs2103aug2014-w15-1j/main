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

import logic.Default;

/**
 * <code>AttributePanel</code> is a customized swing container that extends
 * <code>JPanel</code>. Its can function as a template to display the
 * information of a task detail in five components. It also implements
 * <code>CustomizedJPanel</code>.
 * 
 * <p>
 * <code>AttributePanel</code> is divided into five sections (Title, Start Date,
 * End Date, Status, Description). each of them is InfoPanel.
 * <p>
 * Layout manager used in <code>TaskListPanel</code> is
 * <code>GridBagLayout</code>.
 * 
 * 
 * @author A0119391A
 * @see CustomizedJPanel
 * @see InfoPanel
 * 
 */
public class AttributePanel extends JPanel implements CustomizedJPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;

	private static Color lightCyan220 = new Color(55, 177, 241, 120);

	private static Color highlightedColor = new Color(255, 157, 180, 100);
	private static Color lightWhite = new Color(255, 255, 255, 80);

	// highlight flag for property
	private int highlightedProperty;

	// background color for each attribute panel.
	private Color NAME_BG;
	private Color START_BG;
	private Color END_BG;
	private Color STATUS_BG;
	private Color DESCRIPTION_BG;

	private void determineBG() {
		if (highlightedProperty == Default.NAME) {
			NAME_BG = highlightedColor;
		} else if (highlightedProperty == Default.STARTDATE) {
			START_BG = highlightedColor;
		} else if (highlightedProperty == Default.ENDDATE) {
			END_BG = highlightedColor;
		} else if (highlightedProperty == Default.BOTHDATE) {
			START_BG = highlightedColor;
			END_BG = highlightedColor;
		} else if (highlightedProperty == Default.DESCRIPTION) {
			DESCRIPTION_BG = highlightedColor;
		} else if (highlightedProperty == Default.MARK) {
			STATUS_BG = highlightedColor;
		}
	}

	public void setHighlightedProperty(int propertyNum) {
		this.highlightedProperty = propertyNum;
	}

	/**
	 * 
	 * @param firstCol
	 *            stores titles of each attribute
	 * @param secondCol
	 *            stores data of each attribute
	 */
	public AttributePanel(ArrayList<String> firstCol,
			ArrayList<String> secondCol) {
		super();
		this.firstCol = firstCol;
		this.secondCol = secondCol;

		initialize();
	}

	private void initialize() {
		NAME_BG = lightWhite;
		START_BG = lightWhite;
		END_BG = lightWhite;
		STATUS_BG = lightWhite;
		DESCRIPTION_BG = lightWhite;

		setLayout(new GridBagLayout());
		this.setOpaque(false);
	}

	public void construct() {
		determineBG();
		contructAllColumns(firstCol, secondCol);
	}

	private void contructAllColumns(ArrayList<String> firstCol,
			ArrayList<String> secondCol) {
		int currentIndex = 0;

		// Name attribute
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.ipady = 20;
		c1.insets = new Insets(50, 10, 0, 10);
		c1.weightx = 0.8;
		c1.weighty = 0.2;
		c1.gridwidth = 3;

		this.add(
				new InfoPanel(NAME_BG, firstCol.get(currentIndex), secondCol
						.get(currentIndex)), c1);

		// startDate Attribute
		currentIndex++;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		c2.gridx = 0;
		c2.gridy = 1;
		c2.weightx = 0.3;
		c2.weighty = 0.2;
		c2.ipady = 20;
		c2.insets = new Insets(10, 10, 0, 0);
		this.add(
				new InfoPanel(START_BG, firstCol.get(currentIndex), secondCol
						.get(currentIndex)), c2);

		// endDate Attribute
		c2.gridx++;
		currentIndex++;
		this.add(
				new InfoPanel(END_BG, firstCol.get(currentIndex), secondCol
						.get(currentIndex)), c2);

		// status Attribute
		c2.gridx++;
		c2.insets = new Insets(10, 10, 0, 10);
		currentIndex++;
		this.add(
				new InfoPanel(STATUS_BG, firstCol.get(currentIndex), secondCol
						.get(currentIndex)), c2);

		// description Attribute
		currentIndex++;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 0;
		c3.gridy = 2;
		c3.weightx = 0.9;
		c3.weighty = 0.2;
		c3.ipady = 200;
		c3.insets = new Insets(10, 10, 70, 10);
		c3.gridwidth = 3;
		c3.gridheight = 3;
		this.add(new InfoPanel(DESCRIPTION_BG, firstCol.get(currentIndex),
				secondCol.get(currentIndex)), c3);
	}

}
