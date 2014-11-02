package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Add implements Command{
	private static Task task;
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	//values for GUI and I/O
	private static DisplayInfo passToGui;
	private static LogicToStore passToStore;
	
	//added by Zhang Ji
	private int taskPointer;
	public void setTaskPointer(int pointer) {
		this.taskPointer = pointer;
	}

	public int getTaskPointer() {
		return taskPointer;
	}
	
	public Add(Task newTask, String myFeedback, String myTitle){
		task = newTask;
		setTaskPointer(task.getPointer());
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}

	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();
		
		taskList.add(task);
		currentListIndex = updateListIndex(currentListIndex);
		GUI.changeCurretnTask((taskList.size() - 1));
		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
		currentDisplay = initializeDisplayList(currentDisplay.length);
		currentDisplay[1] = GUI.getTaskIndex();
		update();
		
		display.add(task);
		constructBridges(display, feedback, title);
		DataStore.writeTask(taskList);
		return passToGui;
	}

	@Override
	public DisplayInfo undo() {
		
		ArrayList<Task> display = new ArrayList<Task>();
		RunLogic.removeTaskByPointer(taskList ,getTaskPointer());
		
		initialize();
		currentListIndex = updateListIndex(currentListIndex);
		GUI.changeViewMode(VIEW_MODE.TASK_LIST);
		currentDisplay = initializeDisplayList(currentDisplay.length);
		currentDisplay[1] = GUI.getTaskIndex();
		update();
		
		
		constructBridges(display, feedback, title);
		DataStore.writeTask(taskList);
		return passToGui;
		
	}
	
	//-----------helper functions-----------------
	
	
	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
		taskList = RunLogic.getTaskList();
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
		RunLogic.updateCurrentListIndex(currentListIndex);
	}
	
	private static int[] initializeDisplayList(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}
	
	private int[] updateListIndex(int[] currentList) {
		for(int i = 0; i < taskList.size(); i++){
			currentList[i] = i;
		}
		for(int i = taskList.size(); i < currentList.length; i++){
			currentList[i] = -1;
		}
		return currentList;
	}
	
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
		passToStore = new LogicToStore(taskList,trashbinList);
	}

	@Override
	public boolean supportUndo() {
		return true;
	}

	
}
