package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <code>TaskListPanel</code> is a customized swing container that extends
 * <code>JPanel</code>. Its can function as a template to display the
 * information of a task list in four columns. It also implements
 * <code>CustomizedJPanel</code>
 * <p>
 * Layout manager used in <code>TaskListPanel</code> is
 * <code>GridBagLayout</code>. All sub components inside are JLabel with
 * customized colors or images.
 * 
 * <p>
 * <strong>Note</strong>: The default behavior of components in
 * <code>GridBagLayout</code> will clump together in the center of their
 * container. To place the component from the top left corner, corresponding
 * empty JLabel will be added.
 * 
 * @author A0119391A
 * @see CustomizedJPanel
 */
public class TaskListPanel extends JPanel implements CustomizedJPanel {

	private static final long serialVersionUID = -5452419359255825458L;

	// info of task list
	private ArrayList<String> firstCol;
	private ArrayList<String> secondCol;
	private ArrayList<String> thirdCol;
	private ArrayList<Boolean> fourthCol;
	private ArrayList<String> indexCol;

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

	private int INDEX_COL = 0;
	private int TITLE_COL = 1;
	private int START_COL = 2;
	private int END_COL = 3;
	private int STATUS_COL = 4;
	private String folder = "images/";
	private String previousArrowYPath = folder + "previousArrowY.png";
	private String previousArrowNPath = folder + "previousArrowN.png";
	private String nextArrowYPath = folder + "nextArrowY.png";
	private String nextArrowNPath = folder + "nextArrowN.png";
	private String tickPath = folder + "tick.png";
	private String untickPath = folder + "untick.png";

	// Pre-defined color
	private static Color lightCyan120 = new Color(55, 177, 241, 220);
	private static Color lightCyan20 = new Color(255, 255, 255, 150);
	private static Color highlightedColor = new Color(255, 165, 186, 180);

	/********************************************
	 ************** Constructor *****************
	 ********************************************/

	/**
	 * create a new ColumnListPanel with multiple column information filled
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
	 * @see #constructTextCol(int, String, ArrayList, GridBagConstraints)
	 */
	public void construct() {
		constructPageIndicatorRow();
		constructIndexCol();
		constructFirstCol();
		constructSecondCol();
		constuctThirdCol();
		constructFourthCol();

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
		// initialize index column
		for (int i = 1; i <= firstCol.size(); i++) {
			indexCol.add(Integer.toString(i));
		}
	}

	/**
	 * construct the next page and previous page indicator in the first row
	 */
	private void constructPageIndicatorRow() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridx = 0;
		c.insets = new Insets(0, 10, 0, 10);
		String imgPath;
		if (previousPage) {
			imgPath = previousArrowYPath;
		} else {
			imgPath = previousArrowNPath;
		}
		this.add(createImageLabel(imgPath, null, new Dimension(50, 50), false),
				c);
		c.gridx = 4;

		if (nextPage) {
			imgPath = nextArrowYPath;
		} else {
			imgPath = nextArrowNPath;
		}
		this.add(createImageLabel(imgPath, null, new Dimension(50, 50), false),
				c);
	}

	// column for task index
	private void constructIndexCol() {
		int colIndex = INDEX_COL;

		GridBagConstraints c0 = new GridBagConstraints();
		c0.fill = GridBagConstraints.BOTH;
		c0.ipady = 25;
		c0.insets = new Insets(0, 10, 0, 0);
		c0.weightx = 0.03;
		c0.weighty = 0.1;
		constructTextCol(colIndex, indexColTitle, indexCol, c0);
	}

	// column for task title
	private void constructFirstCol() {
		int colIndex;
		colIndex = TITLE_COL;
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;

		c1.insets = new Insets(0, 10, 0, 0);
		c1.weightx = 0.7;
		constructTextCol(colIndex, firstColTitle, firstCol, c1);
	}

	// column for task start date
	private void constructSecondCol() {
		int colIndex;
		colIndex = START_COL;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.weightx = 0.03;
		c2.insets = new Insets(0, 10, 0, 0);
		c2.fill = GridBagConstraints.BOTH;
		constructTextCol(colIndex, secondColTitle, secondCol, c2);
	}

	// column for task end date
	private void constuctThirdCol() {
		int colIndex;
		colIndex = END_COL;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.weightx = 0.03;
		c3.insets = new Insets(0, 10, 0, 0);
		c3.fill = GridBagConstraints.BOTH;
		constructTextCol(colIndex, thirdColTitle, thirdCol, c3);
	}

	// column for task status
	private void constructFourthCol() {
		int colIndex;
		colIndex = STATUS_COL;
		GridBagConstraints c4 = new GridBagConstraints();
		c4.weightx = 0.03;
		c4.insets = new Insets(0, 10, 0, 10);
		c4.fill = GridBagConstraints.BOTH;
		constructStatusCol(colIndex, fourthColTitle, fourthCol, c4);
	}

	/**
	 * construct a column of text JLabels
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
	private void constructTextCol(int colIndex, String title,
			ArrayList<String> lst, GridBagConstraints c) {
		c.gridx = colIndex;
		c.gridy = 1;

		this.add(createColoredLabel(title, lightCyan120), c);
		for (int i = 0; i < NUM_OF_COL; i++) {
			int offset = 2; // page indicator and title row
			c.gridy = i + offset;
			if (i < lst.size()) {
				Color bg;
				// check for line highlight
				if (checkHightedLines(i)) {
					bg = highlightedColor;
				} else {
					bg = lightCyan20;
				}
				// check for date highlight
				if (colIndex == START_COL || colIndex == END_COL) {
					if (checkHighlightedDate(lst.get(i))) {
						bg = highlightedColor;
					}

				}
				this.add(createColoredLabel(lst.get(i), bg), c);
			} else {
				this.add(createEmptyLabel(), c);
			}

		}
	}

	/**
	 * construct a columns of image labels(special for done/undone status)
	 * 
	 * @param index
	 *            of the columns (start from 0)
	 * @param title
	 *            title of the column
	 * @param lst
	 *            data in this column
	 * @param c
	 *            GridBagConstraint
	 */
	private void constructStatusCol(int colIndex, String title,
			ArrayList<Boolean> lst, GridBagConstraints c) {
		c.gridx = colIndex;
		int offset = 1; // page indicator row
		c.gridy = offset;
		this.add(createColoredLabel(title, lightCyan120), c);
		for (int i = 0; i < NUM_OF_COL; i++) {
			offset = 2; // page indicator and title row
			c.gridy = i + offset;

			if (i < lst.size()) {
				Dimension labelSize = new Dimension(10, 10);
				String imagePath = untickPath;
				Color backgroundColor = lightCyan20;
				if (lst.get(i)) {
					imagePath = tickPath;
				}
				if (checkHightedLines(i)) {
					backgroundColor = highlightedColor;
				}

				this.add(
						createImageLabel(imagePath, backgroundColor, labelSize,
								true), c);

			} else {
				this.add(createEmptyLabel(), c);
			}

		}
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

	/**
	 * create and return a image <code>JLabel</code>
	 * 
	 * @param path
	 *            file path of image
	 * @param color
	 *            background color
	 * @param d
	 *            dimension
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 *            
	 * @param opaque
	 *            is opaque
	 * @return JLabel
	 */
	private JLabel createImageLabel(String path, Color color, Dimension d,
			boolean opaque) {
		try {
			ImageIcon icon;

			InputStream pic = ResourceLoader.load(path);
			icon = new ImageIcon(ImageIO.read(pic));

			JLabel label = new JLabel(icon);
			label.setOpaque(opaque);
			label.setBackground(color);
			label.setMaximumSize(d);
			label.setPreferredSize(d);
			return label;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
	 * check if the given line should be highlighted by given line number
	 * <strong>note</strong>: the line number count from the first datum in
	 * certain column, excluding the title. Note first line number is 0.
	 * 
	 * @param lineNum
	 * @return true if given line should be highlighted.
	 */
	private boolean checkHightedLines(int lineNum) {
		if (isHighlightedMultipleLine) {
			return highlightedLine <= lineNum;
		} else {
			return highlightedLine == lineNum;
		}
	}

	/**
	 * check if the certain position should be highlighted given highlighted
	 * date <strong>Note</strong>: given highlighted date and data in column are
	 * all stored in strings
	 * 
	 * @param date
	 * @return true if date matches
	 */
	private boolean checkHighlightedDate(String date) {
		if (date == null) {
			return false;
		} else {
			return date.equalsIgnoreCase(highlightedDate);
		}
	}

}
