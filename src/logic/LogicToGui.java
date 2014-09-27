package logic;

import java.util.ArrayList;

public class LogicToGui {
	private GUIStatus GUI;
	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;
	
	LogicToGui(){
		this.GUI = new GUIStatus();
		this.TaskList = null;
		feedback = null;
		title = null;
	}
	
	LogicToGui(GUIStatus GUI, ArrayList<Task> taskList, String feedback, String title){
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
