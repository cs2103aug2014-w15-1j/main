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
	
	public static DisplayInfo initialize() {
		ReadFile rf = new ReadFile();
		taskList = rf.getEventTask();
		trashbinList = rf.getTrashFile();
		currentDisplay = new int[Default.MAX_DISPLAY_LINE + 1];
		GUI = new GUIStatus(VIEW_MODE.TASK_LIST, false, false, -1, "20141022");
	
		Command view = new ViewTaskList(WELCOME, TITLE);
		return view.execute();
	}
	
	public static DisplayInfo logic(String inputCommand){
		// pass user command to CLI for auto-correction
		RawCommand stringCommand = ParserProcesser.interpretCommand(inputCommand);
		Command userCommand = ConvertCommand.convert(stringCommand);
		
		return userCommand.execute();
	}
	
	
	
	//-------------------helper API--------------------------
	
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
}
