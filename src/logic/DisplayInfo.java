package logic;

import java.util.ArrayList;

/**
 * This class is an package of data passed from <strong>LOGIC</strong> TO <strong>GUI</strong> which contains all
 * needed information for display
 * 
 * @author A0119391A
 * 
 */
public class DisplayInfo {
	/*
	 * ====================================================================
	 * ===================== Start OF PRIVATE FIELD =======================
	 * ====================================================================
	 */
	private VIEW_MODE mode;
	
	// information for page indicator
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	
	// information for title
	private boolean changeTitle;
	private String title;
	
	// information for task 
	private boolean changeTasklist;
	private ArrayList<Task> taskList;

	// information for feedback
	private String feedback;
	private String helpInfo;
	private JDate date;

	// info for highlight
	private int highlight; 
	private int highlightLine;
	private int highlightItem;

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */

	/*
	 * ====================================================================
	 * ===================== START OF PUBLIC METHOD =======================
	 * ====================================================================
	 */
	public DisplayInfo(GUIStatus status){
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		this.date = status.getDate();
		
		this.changeTasklist = true;
		this.changeTitle = true;
		this.highlight = Default.NO_HIGHLIGHT;
	}
	
	public DisplayInfo(VIEW_MODE newMode, String help, String feedback, String title) {
		this.mode = newMode;
		this.helpInfo = help;
		this.feedback = feedback;
		this.title = title;

	}
	
	public DisplayInfo(GUIStatus status, ArrayList<Task> taskList,
			String feedback, String title) {
		this(status);
		
		this.taskList = taskList;
		this.feedback = feedback;
		this.title = title;

	}

	public DisplayInfo(GUIStatus status, String feedback,
			Boolean changeTaskList, String title) {
		this(status);

		this.feedback = feedback;
		this.title = title;
		this.changeTasklist = changeTaskList;
	}

	public DisplayInfo(GUIStatus status, String feedback,
			Boolean changeTaskList, Boolean changeTitle) {
		this(status);

		this.feedback = feedback;
		this.changeTitle = changeTitle;
		this.changeTasklist = changeTaskList;
	}
	
	
	
	public VIEW_MODE getViewMode() {
		return this.mode;
	}

	public ArrayList<Task> getTaskList() {
		return this.taskList;
	}

	public boolean hasNextPage() {
		return this.hasNextPage;
	}

	
	public boolean hasPreviousPage() {
		return this.hasPreviousPage;
	}

	public String getFeedbackString() {
		return this.feedback;
	}

	public String getTitleString() {
		return this.title;
	}

	public boolean changeTasklist() {
		return changeTasklist;
	}

	public boolean changeTitle() {
		return changeTitle;
	}

	public JDate getDate() {
		return this.date;
	}
	

	public int getHighlight() {
		return this.highlight;
	}

	public int getHighlightLine() {
		return this.highlightLine;
	}

	public int getHighlightItem() {
		return this.highlightItem;
	}
	public String getHelpInfo() {
		return this.helpInfo;
	}
	public void setHelpInfo(String info) {
		this.helpInfo = info;
	}
	
	
	public void setHighlight(int newHighlight) {
		this.highlight = newHighlight;
	}

	public void setHighlightLine(int line) {
		this.highlightLine = line;
	}

	public void setHighlightItem(int item) {
		this.highlightItem = item;
	}
	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */


}
