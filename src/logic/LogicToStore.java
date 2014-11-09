package logic;

import java.util.ArrayList;

/**
 * 
 * @author a0119456Y
 *
 */
public class LogicToStore {
	private ArrayList<Task> TaskList;
	private ArrayList<Task> TrashbinList;
	
	LogicToStore(){
		this.TaskList = null;
		this.TrashbinList = null;
	}
	
	public LogicToStore(ArrayList<Task> taskList, ArrayList<Task> TrashbinList){
		this.TaskList = taskList;
		this.TrashbinList = TrashbinList;
	}
	
	public ArrayList<Task> getTaskList(){
		return this.TaskList;
	}
	
	public  ArrayList<Task> getTrashbinList(){
		return this.TrashbinList;
	}
}
