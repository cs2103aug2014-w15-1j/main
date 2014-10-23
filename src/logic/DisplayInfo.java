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
	
	private ArrayList<Task> taskList;
	private String feedback;
	private String title;

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
		setIsPageInvolved();

		this.taskList = taskList;
		this.feedback = feedback;
		this.title = title;
		
		this.changeTasklist = false;
	}
	
	public DisplayInfo(GUIStatus status,
			String feedback, Boolean change) {
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		setIsPageInvolved();

		this.feedback = feedback;
			
		this.changeTasklist = change;
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
