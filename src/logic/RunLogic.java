package logic;

import gui.VIEW_MODE;

import java.util.ArrayList;
import parser.RawCommand;
import parser.ParserProcess;
import read_file.ReadFile;

public class RunLogic {

	// keep track on GUI and File status
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay = new int[Constant.MAX_DISPLAY_LINE + 1];
	
	public static DisplayInfo initialize() {
		ReadFile rf = new ReadFile();
		taskList = rf.getEventTask();
		trashbinList = rf.getTrashFile();
		currentDisplay = new int[Constant.MAX_DISPLAY_LINE + 1];
		
		ArrayList<Task> initialDisplay = new ArrayList<Task>();
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		boolean hasNext = taskList.size() > Constant.MAX_DISPLAY_LINE;
		if(hasNext){
			for(int i = 0; i < Constant.MAX_DISPLAY_LINE; i++){
				initialDisplay.add(taskList.get(i));
				currentDisplay[i + 1] = i;
			}
		} else {
			initialDisplay = taskList;
			for(int i = 1; i <= initialDisplay.size(); i++){
				currentDisplay[i] = i - 1;
			}
		}
		GUI = new GUIStatus(VIEW_MODE.TASK_LIST, hasNext, false, currentDisplay[1], "20140930");

		return new DisplayInfo(GUI, initialDisplay, FeedbackFormat.START_FEEDBACK, TitleFormat.TITLE);
	}
	
	public static DisplayInfo logic(String inputCommand){
		// pass user command to CLI for auto-correction
		RawCommand userCommand = ParserProcess.interpretCommand(inputCommand);
		
		// check whether the command is valid under current view mode
		if(CheckCommandValid.checkValid(userCommand)){
			 return Execute.executeCommand(userCommand);
		} else {
			 return Execute.wrongCommand(userCommand);
		}
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
