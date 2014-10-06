package logic;

import main.StartUp;
import cli.CliToLog;
import cli.CliProcess;

import java.util.ArrayList;

public class RunLogic {

	// keep track on GUI and File status
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay = new int[StartUp.MAX_DISPLAY_LINE + 1];
	
	public static void initialize(GUIStatus initialGUI, ArrayList<Task> initialTaskList, ArrayList<Task> initialTrashbinList, int[] initialDisplay) {
		GUI = initialGUI;
		taskList = initialTaskList;
		trashbinList = initialTrashbinList;
		currentDisplay = initialDisplay;
	}
	
	public static void Logic(String inputCommand){
		// pass user command to CLI for auto-correction
		CliToLog userCommand = CliProcess.interpretCommand(inputCommand);
		
		// check whether the command is valid under current view mode
		if(CheckCommandValid.checkValid(userCommand)){
			Execute.executeCommand(userCommand);
		} else {
			Execute.wrongCommand(userCommand);
		};
	}
	
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
