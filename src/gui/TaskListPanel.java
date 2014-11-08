package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <code>TaskListPanel</code> is a customized swing container that extends
 * <code>JPanel</code>. Its can function as a template to display the
 * information of a task list in four columns.
 * <p>
 * Layout manager used in <code>TaskListPanel</code> is
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
public class TaskListPanel extends JPanel implements CustomizedJPanel{

	private static final long serialVersionUID = -5452419359255825458L;

	// info of task list
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	private ArrayList<String> thirdCol;
	private ArrayList<Boolean> fourthCol;
	private ArrayList<String> indexCol;

	private int INDEX_COL = 0;
	private int TITLE_COL = 1;
	private int START_COL = 2;
	private int END_COL = 3;
	private int STATUS_COL = 4;

	private boolean previousPage;
	private boolean nextPage;
	private boolean isHighlightedMultipleLine;
	private int highlightedLine;
	private String highlightedDate;

	private final static String SPACE = " ";
	private final static String indexColTitle = "Index";
	private final static String firstColTitle = "Title";
	private final static String secondColTitle = "Start Date";
	private final static String thirdColTitle = "End Date";
	private final static String fourthColTitle = "Status";
	// length of rows firstColTitle
	private int NUM_OF_COL = 10;

	// Pre-defined color
	private static Color lightCyan120 = new Color(55, 177, 241, 220);
	private static Color lightCyan20 = new Color(255, 255, 255, 150);
	private static Color highlightedColor = new Color(255, 165, 186, 180);

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
	public TaskListPanel(ArrayList<String> firstCol,
			ArrayList<String> secondCol, ArrayList<String> thirdCol,
			ArrayList<Boolean> fourthCol) {
		super();
		this.indexCol = new ArrayList<String>();
		this.firstCol = firstCol;
		this.secondCol = secondCol;
		this.thirdCol = thirdCol;
		this.fourthCol = fourthCol;

		this.previousPage = false;
		this.nextPage = false;
		this.isHighlightedMultipleLine = false;

		setUp();

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

	public void setPreviousPage(boolean flag) {
		this.previousPage = flag;
	}

	public void setNextPage(boolean flag) {
		this.nextPage = flag;
	}

	public void setHighlightedLine(int lineNum) {
		this.highlightedLine = lineNum;
	}

	public void setIsHighlightedMultipleLine(boolean flag) {
		this.isHighlightedMultipleLine = flag;
	}

	public void setHighlightedDate(String date) {
		this.highlightedDate = date;
	}

	/**
	 * construct all columns using <em>constructCol<em>
	 * 
	 * @see #constructCol(int, String, ArrayList, GridBagConstraints)
	 */
	public void construct() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridx = 0;
		c.insets = new Insets(0, 10, 0, 10);
		String imgPath;
		if (previousPage) {
			imgPath = "previousArrowY.png";
		} else {
			imgPath = "previousArrowN.png";
		}
		this.add(createImageLabel(imgPath, null, new Dimension(50, 50), false),
				c);
		c.gridx = 4;

		if (nextPage) {
			imgPath = "nextArrowY.png";
		} else {
			imgPath = "nextArrowN.png";
		}
		this.add(createImageLabel(imgPath, null, new Dimension(50, 50), false),
				c);

		int colIndex = INDEX_COL;
		for (int i = 1; i <= firstCol.size(); i++) {
			indexCol.add(Integer.toString(i));
		}
		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		c0.ipady = 25;
		c0.insets = new Insets(0, 10, 0, 0);
		c0.weightx = 0.03;
		c0.weighty = 0.1;
		constructCol(colIndex, indexColTitle, indexCol, c0);

		// column 0
		colIndex = TITLE_COL;
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;

		c1.insets = new Insets(0, 10, 0, 0);
		c1.weightx = 0.7;
		constructCol(colIndex, firstColTitle, firstCol, c1);

		// column 1
		colIndex = START_COL;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.weightx = 0.03;
		c2.insets = new Insets(0, 10, 0, 0);
		c2.fill = GridBagConstraints.BOTH;
		constructCol(colIndex, secondColTitle, secondCol, c2);

		colIndex = END_COL;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.weightx = 0.03;
		c3.insets = new Insets(0, 10, 0, 0);
		c3.fill = GridBagConstraints.BOTH;
		constructCol(colIndex, thirdColTitle, thirdCol, c3);

		colIndex = STATUS_COL;
		GridBagConstraints c4 = new GridBagConstraints();
		c4.weightx = 0.03;
		c4.insets = new Insets(0, 10, 0, 10);
		c4.fill = GridBagConstraints.BOTH;
		constructStatusCol(colIndex, fourthColTitle, fourthCol, c4);

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
		c.gridy = 1;
		
		this.add(createColoredLabel(title, lightCyan120), c);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c.gridy = i + 2;
			if (i < lst.size()) {
				Color bg;
				if ((highlightedLine < i && isHighlightedMultipleLine)
						|| highlightedLine == i) {
					bg = highlightedColor;
				} else {
					bg = lightCyan20;
				}

				if (colIndex == START_COL || colIndex == END_COL) {
					if (lst.get(i).equals(this.highlightedDate)) {
						bg = highlightedColor;
					}

				}
				this.add(createColoredLabel(lst.get(i), bg), c);
			} else {
				this.add(createEmptyLabel(), c);
			}

		}
	}

	private void constructStatusCol(int colIndex, String title,
			ArrayList<Boolean> lst, GridBagConstraints c) {
		c.gridx = colIndex;
		c.gridy = 1;
		this.add(createColoredLabel(title, lightCyan120), c);
		for (int i = 0; i < NUM_OF_COL; i++) {
			c.gridy = i + 2;
			if (i < lst.size()) {
				if (lst.get(i)) {
					if ((highlightedLine < i && isHighlightedMultipleLine)
							|| highlightedLine == i) {
						this.add(
								createImageLabel("tick.png", highlightedColor,
										new Dimension(10, 10), true), c);
					} else {
						this.add(
								createImageLabel("tick.png", lightCyan20,
										new Dimension(10, 10), true), c);
					}

				} else {
					if ((highlightedLine < i && isHighlightedMultipleLine)
							|| highlightedLine == i) {
						this.add(
								createImageLabel("untick.png",
										highlightedColor,
										new Dimension(10, 10), true), c);
					} else {
						this.add(
								createImageLabel("untick.png", lightCyan20,
										new Dimension(10, 10), true), c);
					}
				}

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
		JLabel label = new JLabel(SPACE); // note that empty string cannot be
											// used here
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
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setHorizontalAlignment(label.CENTER);
		return label;
	}

	private JLabel createImageLabel(String path, Color color, Dimension d,
			boolean opaque) {
		ImageIcon icon = new ImageIcon(path);
		JLabel label = new JLabel(icon);
		label.setOpaque(opaque);
		label.setBackground(color);
		label.setMaximumSize(d);
		label.setPreferredSize(d);
		return label;
	}

	
}
