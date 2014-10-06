package gui;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.RunLogic;
import logic.Task;
import read_file.ReadFile;

public class StartUp {
	// title information
	public static String TITLE = "title";
	
	// some feedback information format
	public static String ADD_FEEDBACK = "New task added successfully!";
	public static String DELETE_FEEDBACK = "Task deleted successfully!";
	public static String READ_FEEDBACK = "Read details!";
	public static String RENAME_FEEDBACK = "Task informaion updated!";
	public static String RESCHEDULE_FEEDBACK = "Task informaion updated!";
	public static String REPEAT_FEEDBACK = "Task informaion updated!";
	public static String DESCRIBE_FEEDBACK = "Task informaion updated!";
	public static String RESTORE_FEEDBACK = "Task restored to task list!";
	public static String VIEW_FEEDBACK = "View mode changed!";
	public static String UNDO_FEEDBACK = "Command undo!";
	public static String SEARCH_FEEDBACK = "Search rsults!"; 
	public static String INVALID_FEEDBACK = "Invalid Command!";
	public static String START_FEEDBACK = "Start!";
	public static String NEXT_FEEDBACK = "Next page!";
	public static String PREVIOUS_FEEDBACK = "Previous page!";
	
	// the line number to display in GUI
	public static int MAX_DISPLAY_LINE = 5;
	
	public static void main(String[] args) {
		ReadFile rf = new ReadFile();
		ArrayList<Task> initialTasks = rf.getEventTask();
		ArrayList<Task> initialTrashbin = rf.getTrashFile();
		ArrayList<Task> initialDisplay = new ArrayList<Task>();
		int[] initialDisplayIndex = new int[MAX_DISPLAY_LINE + 1];
		for(int i = 0; i < initialDisplayIndex.length; i++){
			initialDisplayIndex[i] = -1;
		}
		boolean hasNext = initialTasks.size() > MAX_DISPLAY_LINE;
		if(hasNext){
			for(int i = 0; i < MAX_DISPLAY_LINE; i++){
				initialDisplay.add(initialTasks.get(i));
				initialDisplayIndex[i + 1] = i;
			}
		} else {
			initialDisplay = initialTasks;
			for(int i = 1; i <= initialDisplay.size(); i++){
				initialDisplayIndex[i] = i - 1;
			}
		}
		GUIStatus initialGui = new GUIStatus(VIEW_MODE.TASK_LIST, hasNext, false, initialTasks.size() - 1, "20140930");
		RunLogic.initialize(initialGui, initialTasks, initialTrashbin, initialDisplayIndex);
		
		DisplayConfiguration initialDisConfig = new DisplayConfiguration(initialGui, initialDisplay, START_FEEDBACK, TITLE);
		Display.display(initialDisConfig);
	}

}
