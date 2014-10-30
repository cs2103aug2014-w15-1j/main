package logic;

import gui.VIEW_MODE;

import java.util.ArrayList;

import parser.RawCommand;
import parser.ParserProcesser;
import read_file.ReadFile;
import CommandType.*;

public class RunLogic {
	private static String WELCOME = "Welcome to MagiCal!";
	private static String TITLE = "Task List";
	
	// keep track on GUI and File status
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay = new int[Default.MAX_DISPLAY_LINE + 1];
	private static int[] currentListIndex = new int[Default.MAX_TASKS];
	
	//added by Zhang Ji
	private static long nextTaskPointer;
	private static void initializeTaskPointer() {
		nextTaskPointer = 0;
		for(Task t: taskList) {
			t.setPointer(nextTaskPointer);
			incrementnextTaskPointer();
		}
		for(Task t: trashbinList) {
			t.setPointer(nextTaskPointer);
			incrementnextTaskPointer();
		}
	}
	public static long getNextTaskPointer() {
		return nextTaskPointer;
	}
	public static void incrementnextTaskPointer() {
		nextTaskPointer++;
	}
	
	public static DisplayInfo initialize() {
		ReadFile rf = new ReadFile();
		taskList = rf.getEventTask();
		trashbinList = rf.getTrashFile();
		initializeTaskPointer();
		currentDisplay = new int[Default.MAX_DISPLAY_LINE + 1];
		GUI = new GUIStatus(VIEW_MODE.TASK_LIST, false, false, -1, "20141022");

		currentListIndex = updateListIndexOfTaskList(currentListIndex);
		Command start = new ViewTaskList(WELCOME, TITLE);
		return start.execute();
	}
	
	public static DisplayInfo logic(String inputCommand){
		// pass user command to CLI for auto-correction
		RawCommand stringCommand = ParserProcesser.interpretCommand(inputCommand);
		Command userCommand = ConvertCommand.convert(stringCommand);
		
		return userCommand.execute();
	}
	
	
	
	//-------------------helper--------------------------
	
	public static GUIStatus getGuiStatus(){
		return GUI;
	}
	
	public static ArrayList<Task> getTaskList(){
		return taskList;
	}
	
	public static ArrayList<Task> getTrashbinList(){
		return trashbinList;
	}
	
	public static int[] getCurrentDisplay(){
		return currentDisplay;
	}
	
	public static int[] getCurrentListIndex(){
		return currentListIndex;
	}
	public static void updateGuiStatus(GUIStatus newGUI){
		GUI = newGUI;
	}
	
	public static void updateTaskList(ArrayList<Task> newTaskList){
		taskList = newTaskList;
	}
	
	public static void updateTrashbinList(ArrayList<Task> newTrashbinList){
		trashbinList = newTrashbinList;
	}
	
	public static void updateCurrentdiaplay(int[] newDisplay){
		currentDisplay = newDisplay;
	}
	
	public static void updateCurrentListIndex(int[] newListIndex){
		currentListIndex = newListIndex;
	}
	
	private static int[] updateListIndexOfTaskList(int[] currentList) {
		for(int i = 0; i < taskList.size(); i++){
			currentList[i] = i;
		}
		for(int i = taskList.size(); i < currentList.length; i++){
			currentList[i] = -1;
		}
		return currentList;
	}
}
