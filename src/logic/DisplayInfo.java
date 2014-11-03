package logic;

import gui.VIEW_MODE;

import java.util.ArrayList;

/**
 * class DisplayConfiguration
 * 
 * @author JJ This class is an package of data passed from LOGIC TO GUI which
 *         contains all needed information for display
 */
public class DisplayInfo {
	/*
	 * ====================================================================
	 * ===================== Start OF PRIVATE FIELD =======================
	 * ====================================================================
	 */
	private VIEW_MODE mode;
	private boolean isPageInvolved;
	private boolean hasNextPage;
	private boolean hasPreviousPage;

	private boolean changeTasklist;
	private boolean changeTitle = true;
	
	private ArrayList<Task> taskList;
	private String feedback;
	private String title;
	private JDate date;
	
	// info for highlight
	private int highlight = Default.NO_HIGHLIGHT;
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
	public DisplayInfo(GUIStatus status, ArrayList<Task> taskList,
			String feedback, String title) {
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		this.date = status.getDate();
		setIsPageInvolved();

		this.taskList = taskList;
		this.feedback = feedback;
		this.title = title;
		
		this.changeTasklist = true;
	}
	
	public DisplayInfo(GUIStatus status,
			String feedback, Boolean changeTaskList, String title) {
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		this.date = status.getDate();
		setIsPageInvolved();

		this.feedback = feedback;
		this.title = title;
			
		this.changeTasklist = changeTaskList;
	}
	public DisplayInfo(GUIStatus status,
			String feedback, Boolean changeTaskList, Boolean changeTitle) {
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		this.date = status.getDate();
		setIsPageInvolved();

		this.feedback = feedback;
		
		this.changeTitle = changeTitle;
		this.changeTasklist = changeTaskList;
	}
	
	public VIEW_MODE getViewMode(){
		return this.mode;
	}
	
	public ArrayList<Task> getTaskList() {
		return this.taskList;
	}

	public boolean hasNextPage() {
		return this.hasNextPage;
	}

	public boolean isPageInvolved() {
		return isPageInvolved;
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
	
	public JDate getDate(){
		return this.date;
	}
	
	
	// public method for highlight
	public void setHightlight(int newHighlight){
		this.highlight = newHighlight;
	}
	
	public void setHighlightLine(int line){
		this.highlightLine = line;
	}
	
	public void setHighlightItem(int item){
		this.highlightItem = item;
	}
	
	public int getHightlight(){
		return this.highlight;
	}
	
	public int getHighlightLine(){
		return this.highlightLine;
	}
	
	public int getHighlightItem(){
		return this.highlightItem;
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */

	/**
	 * method setIsPageInvolved: check if the nextPage and previousPage should
	 * be involved in this view mode or not
	 */
	private void setIsPageInvolved() {
		switch (mode) {
		case DATE:
			isPageInvolved = true;
			break;
		case MONTH:
			isPageInvolved = true;
			break;
		case UNDONE:
			isPageInvolved = true;
			break;
		case BIN:
			isPageInvolved = true;
			break;
		case TASK_DETAIL:
			isPageInvolved = false;
			break;
		case BIN_DETAIL:
			isPageInvolved = false;
			break;
		case TASK_LIST:
			isPageInvolved = true;
			break;
		default:
			throw new Error("Invalid View Mode:" + mode);
		}
	}



}
