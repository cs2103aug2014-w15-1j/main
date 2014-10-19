package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Add implements Command{
	private static Task task;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;

	//values for GUI and I/O
	private static DisplayInfo passToGui;
	private static LogicToStore passToStore;
	
	public Add(Task newTask){
		task = newTask;
		initialize();
	}

	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();
		
		taskList.add(task);
		GUI.changeCurretnTask((taskList.size() - 1));
		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
		currentDisplay = initializeDisplayList(currentDisplay);
		currentDisplay[1] = GUI.getTaskIndex();
		update();
		
		display.add(task);
		constructBridges(display, Default.ADD_FEEDBACK, Default.TITLE);
		DataStore.writeTask(taskList);
		return passToGui;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		if(taskList.remove(task)){
			
		};
		return null;
	}
	
	//-----------helper functions-----------------
	
	
	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
		taskList = RunLogic.getTaskList();
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}
	
	private static int[] initializeDisplayList(int[] currentDisplay) {
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}
	
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
		passToStore = new LogicToStore(taskList,trashbinList);
	}
}
