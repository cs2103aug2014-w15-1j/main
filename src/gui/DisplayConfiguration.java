package gui;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.Task;

/**
 * class DisplayConfiguration
 * @author JJ
 * This class is an package of data passed from LOGIC TO GUI
 * which contains all needed information for display
 */
public class DisplayConfiguration {
	private VIEW_MODE mode;
	private boolean isPageInvolved;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	
	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;
	
	public DisplayConfiguration(GUIStatus status, ArrayList<Task> taskList, String feedback, String title){
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		setIsPageInvolved();
		
		this.TaskList = taskList;
		this.feedback = feedback;
		this.title = title;
	}
	
	/**
	 * method setIsPageInvolved: check if the nextPage and previousPage should  
	 * 							 be involved in this view mode or not
	 */
	private void setIsPageInvolved() {
		switch(mode) {
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
			case TASK:
				isPageInvolved = false;
				break;
			default:
				throw new Error("Invalid View Mode" + mode);
		}
	}
	
	
	public boolean isPageInvolved(){
		return isPageInvolved;
	}
	
	public ArrayList<Task> getTaskList(){
		return this.TaskList;
	}
	
	public String getFeedback(){
		return this.feedback;
	}
	
	public String getTitle(){
		return this.title;
	}
	public boolean hasNextPage(){
		return this.hasNextPage;
	}
	public boolean hasPreviousPage(){
		return this.hasPreviousPage;
	}
	public boolean isTaskView(){
		return mode == VIEW_MODE.TASK;
	}
	
}
