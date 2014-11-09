package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
public class Add implements Command{
	// feedback format
	private static String UNDO_INVALID = "Task not found!";
	
	private static Task task;
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	private int taskPointer;
	
	//values for GUI
	private static DisplayInfo passToGui;
	



	
	public Add(Task newTask, String myFeedback, String myTitle){
		task = newTask;
		setTaskPointer(task.getPointer());
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}

	@Override
	public DisplayInfo execute() {
		modifyTaskList();
		modifyIndexList();
		modifyGUI();
		modifyDisplayIndex();
		update();
		constructBridges(constructDisplay(), feedback, title);
		DataStore.writeTask(taskList);
		return passToGui;
	}


	@Override
	public DisplayInfo undo() {
		initialize();
		if(RunLogic.removeTaskByPointer(taskList, getTaskPointer())){
			modifyIndexList();	
			DataStore.writeTask(taskList);
			update();
			int highlightLine = determinHighlightLine();
			int firstLine = determinfistLine();
			return construstDisplay(firstLine, highlightLine);
		} else {
			return constructInvalid();
		}
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
	}

	@Override
	public boolean supportUndo() {
		return true;
	}

	public void setTaskPointer(int pointer) {
		this.taskPointer = pointer;
	}

	public int getTaskPointer() {
		return taskPointer;
	}
	

	private ArrayList<Task> constructDisplay() {
		ArrayList<Task> display = new ArrayList<Task>();	
		display.add(task);
		return display;
	}

	private void modifyTaskList() {
		taskList.add(task);
	}

	private void modifyIndexList() {
		currentListIndex = updateListIndex(currentListIndex);
	}

	private void modifyGUI() {
		GUI.changeCurretnTask((taskList.size() - 1));
		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
	}

	private void modifyDisplayIndex() {
		currentDisplay = initializeDisplayList(currentDisplay.length);
		currentDisplay[1] = GUI.getTaskIndex();
	}
	
	private DisplayInfo constructInvalid() {
		Command invalid = new Invalid(UNDO_INVALID);
		return invalid.execute();
	}

	private DisplayInfo construstDisplay(int firstLine, int highlightLine) {
		ViewTaskList viewTaskList = new ViewTaskList(firstLine, feedback, title);
		DisplayInfo dis = viewTaskList.execute();
		dis.setHighlight(Default.HIGHLIGHT_LINE);
		dis.setHighlightLine(highlightLine);
		return dis;
	}

	private int determinfistLine() {
		int index = RunLogic.getIndexInList(taskList, getTaskPointer());
		index -= index % Default.MAX_DISPLAY_LINE;
		if(currentListIndex[index] != -1){
			return index;
		} else if (((index -= Default.MAX_DISPLAY_LINE) >= 0 ) && currentListIndex[index -= Default.MAX_DISPLAY_LINE] != -1){
			return currentDisplay[1] - Default.MAX_DISPLAY_LINE;
		} else {
			return 0;
		}
	}

	private int determinHighlightLine() {
		return RunLogic.getIndexInList(taskList, getTaskPointer()) % Default.MAX_DISPLAY_LINE;
	}

}
