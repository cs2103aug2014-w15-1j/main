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
 * @author A0119391A
 * 
 */
public class ColumnListPanel extends JPanel {

	private static final long serialVersionUID = -5452419359255825458L;

	// info of task list
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	private ArrayList<String> thirdCol;
	private ArrayList<String> indexCol;
	private final static String SPACE = " ";
	private final static String indexColTitle = "Index";
	private final static String firstColTitle = "Title";
	private final static String secondColTitle = "Start Date";
	private final static String thirdColTitle = "End Date";
	// length of rows firstColTitle
	private int NUM_OF_COL = 10;

	// Pre-defined color
	private static Color lightCyan120 = new Color(0, 229, 238, 120);
	private static Color lightCyan20 = new Color(0, 229, 238, 20);
	

	
	
	/********************************************
	 ************** Constructor *****************
	 ********************************************/
	
	/**
	 * create a new ColumnListPanel with 2-column information filled
	 * 
	 * @param firstCol
	 *            ArrayList of all task names
	 * @param secondCol
	 *            ArrayList of all task endDates
	 */
	public ColumnListPanel(ArrayList<String> firstCol,
			ArrayList<String> secondCol, ArrayList<String> thirdCol) {
		super();
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		this.thirdCol = thirdCol;
		this.indexCol = new ArrayList<String>();
		
		setUp();
		constructAllCol();

	}
	/*********************************************
	 ************* Private Method ****************
	 ********************************************/
	/**
	 * set the attributes of the <code>container</code> and
	 * <code>Layout Manager</code>
	 */
	private void setUp() {
		setOpaque(false);
		setLayout(new GridBagLayout());
	}
		
	/**
	 * construct all columns using <em>constructCol<em>
	 * 
	 * @see #constructCol(int, String, ArrayList, GridBagConstraints)
	 */
	private void constructAllCol() {
		int colIndex;
		colIndex = 0;
		for(int i=1; i<=firstCol.size(); i++) {
			indexCol.add(Integer.toString(i));
		}
		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		c0.ipady = 17;
		c0.insets = new Insets(0, 10, 0, 0);
		c0.weightx = 0;
		constructCol(colIndex, indexColTitle, indexCol, c0);
		
		// column 0
		colIndex = 1;
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.ipady = 17;
		c1.insets = new Insets(0, 5, 0, 0);
		c1.weightx = 0.7;
		constructCol(colIndex, firstColTitle, firstCol, c1);

		// column 1
		colIndex = 2;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.weightx = 0.1;
		c2.insets = new Insets(0, 10, 0, 0);
		c2.fill = GridBagConstraints.BOTH;
		constructCol(colIndex, secondColTitle, secondCol, c2);
		
		colIndex = 3;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.weightx = 0.1;
		c3.insets = new Insets(0, 10, 0, 10);
		c3.fill = GridBagConstraints.BOTH;
		constructCol(colIndex, thirdColTitle, thirdCol, c3);
		
		
		int rowIndex = NUM_OF_COL;
		colIndex = 0;
		colIndex = 1;
		
		

	}

	/**
	 * construct a column of JLabels
	 * 
	 * @param colIndex
	 *            index of the columns (start from 0)
	 * @param title
	 *            title of the column
	 * @param lst
	 *            data in this column
	 * @param c
	 *            GridBagConstraints
	 */
	private void constructCol(int colIndex, String title,
			ArrayList<String> lst, GridBagConstraints c) {
		c.gridx = colIndex;
		c.gridy = 0;
		this.add(createColoredLabel(title, lightCyan120), c);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c.gridy = i + 1;
			if (i < lst.size()) {
				this.add(
						createColoredLabel(lst.get(i),
								lightCyan20), c);
			} else {
				this.add(createEmptyLabel(), c);
			}

		}
	}

	/**
	 * create and return a empty <code>JLabel</code>
	 * 
	 * @return JLabel
	 */
	private JLabel createEmptyLabel() {
		JLabel label = new JLabel(SPACE); //note that empty string cannot be used here
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		label.setOpaque(false);
		return label;
	}

	/**
	 * create and return a colored <code>JLabel</code>
	 * 
	 * @param text
	 *            JLabel text
	 * @param color
	 *            background color of JLabel
	 * @return JLabel
	 */
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD | Font.ITALIC));
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		return label;
	}
}
