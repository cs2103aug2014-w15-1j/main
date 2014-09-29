package gui;

import java.util.ArrayList;

import logic.Task;

public class DisplayConfiguration {
	private VIEW_MODE 
	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;
	private int currentTask;
	
	public DisplayConfiguration(){
		this.GUI = new MainWindowConfiguration();
		this.TaskList = null;
		this.feedback = null;
		this.title = null;
		this.currentTask = -1;
	}
	
	public DisplayConfiguration(MainWindowConfiguration GUI, ArrayList<Task> taskList, String feedback, String title, int currentTask){
		this.GUI = GUI;
		this.TaskList = taskList;
		this.feedback = feedback;
		this.title = title;
		this.currentTask = currentTask;
	}
	
	public MainWindowConfiguration getGUIstatus(){
		return this.GUI;
	}
	
	public ArrayList<Task> getList(){
		return this.TaskList;
	}
	
	public String getFeedback(){
		return this.feedback;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public int getCurrentTask(){
		return this.currentTask;
	}
}
