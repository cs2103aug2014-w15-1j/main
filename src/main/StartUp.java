package main;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.Task;
import read_file.ReadFile;
import gui.Display;
import gui.DisplayConfiguration;
import gui.VIEW_MODE;

public class StartUp {
	// title information
	public static String TITLE = "";
	
	// some feedback inormation format
	public static String ADD_FEEDBACK = "New task added successfully!";
	public static String DELETE_FEEDBACK = "Task deleted successfully!";
	public static String READ_FEEDBACK = "";
	public static String RENAME_FEEDBACK = "Task informaion updated!";
	public static String RESCHEDULE_FEEDBACK = "Task informaion updated!";
	public static String REPEAT_FEEDBACK = "Task informaion updated!";
	public static String DESCRIBE_FEEDBACK = "Task informaion updated!";
	public static String RESTORE_FEEDBACK = "Task restored to task list!";
	public static String VIEW_FEEDBACK = "";
	public static String UNDO_FEEDBACK = "";
	public static String SEARCH_FEEDBACK = ""; 
	public static String INVALID_FEEDBACK = "Invalid Command! Please check your command again!";
	public static String START_FEEDBACK = "Start!";
	
	// the line number to display in GUI
	public static int MAX_DISPLAY_LINE = 10;
	
	public static void main(String[] args) {
		ReadFile rf = new ReadFile();
		ArrayList<Task> initialTasks = rf.getEventTask();
		ArrayList<Task> initialTrashbin = rf.getTrashFile();
		ArrayList<Task> initialDisplay = new ArrayList<Task>();
		boolean hasNext = initialTasks.size() > MAX_DISPLAY_LINE;
		if(hasNext){
			for(int i = 0; i < MAX_DISPLAY_LINE; i++){
				initialDisplay.add(initialTasks.get(i));
			}
		} else {
			initialDisplay = initialTasks;
		}
		GUIStatus initialGui = new GUIStatus(VIEW_MODE.TASK, hasNext, false, 0);
		DisplayConfiguration initialDisConfig = new DisplayConfiguration(initialGui, initialDisplay, START_FEEDBACK, TITLE);
		Display.display(initialDisConfig);
	}

}
