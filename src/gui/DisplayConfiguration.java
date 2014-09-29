package gui;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.Task;

public class DisplayConfiguration {
	private GUIStatus GUI;
	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;
	
	public DisplayConfiguration(){
		this.GUI = new GUIStatus();
		this.TaskList = null;
		this.feedback = null;
		this.title = null;
	}
	
	public DisplayConfiguration(GUIStatus GUI, ArrayList<Task> taskList, String feedback, String title, int currentTask){
		this.GUI = GUI;
		this.TaskList = taskList;
		this.feedback = feedback;
		this.title = title;
	}
	
	public GUIStatus getGUIstatus(){
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
}
