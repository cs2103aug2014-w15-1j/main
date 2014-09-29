package gui;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.Task;

public class DisplayConfiguration {
	private VIEW_MODE mode;
	private boolean isPageInvolved;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private int currentTaskIndex;
	
	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;
	
	public DisplayConfiguration(GUIStatus status, ArrayList<Task> taskList, String feedback, String title){
		this.mode = status.getMode();
		this.hasNextPage = status.hasNextPage();
		this.hasPreviousPage = status.hasPreviousPage();
		
		this.TaskList = taskList;
		this.feedback = feedback;
		this.title = title;
	}
	
	
	private void setIsPageInvolved() {
		switch(mode) {
			case DATE:
				isPageInvolved = true;
			case MONTH:
				isPageInvolved = true;
			case UNDONE:
				isPageInvolved = true;
			case BIN:
				isPageInvolved = true;
			case TASK:
				isPageInvolved = false;
			default:
				throw new Error("invalid view Mode");
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
}
