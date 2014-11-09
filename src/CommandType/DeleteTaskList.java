package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.LogicToStore;
import logic.RunLogic;
import logic.Task;

/**
 * 
 * @author a0119456Y
 * @author A0119493X
 *
 */
public class DeleteTaskList implements Command{
	// feedback format
	private static String UNDO_FEEDBACK = "Deleted tasks added back!";
	
	private static String feedback;
	private static String title;
	
	private static boolean deleteAll;
	private static int deleteIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
		
	// memory for undo 
	private int[] taskPointers = initializeList(Default.MAX_DISPLAY_LINE);
	
	// value for I/O
	private static LogicToStore passToStore;
	
	public DeleteTaskList(Boolean all, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		initialize();
		deleteAll = all;
		deleteIndex = -1;
	}
	
	public DeleteTaskList(int line, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		initialize();
		deleteAll = false;
		deleteIndex = currentDisplay[line];
	}
	
	@Override
	public DisplayInfo execute() {
		if(deleteAll){
			deleteAll();
		} else {
			deleteLine();
		}
		modifyIndexList();	
		update();
		constructBridges();
		DataStore.writeAllData(passToStore);
		return constructDisplay();
		
	}
	@Override
	public DisplayInfo undo() {
		initialize();
		int[] reAddedTaskIndexList = addTasksBack();	
		int firstHighlightTaskIndex = determinefirstTask(reAddedTaskIndexList);
		modifyIndexList();		
		update();
		return constructUndoDisplay(firstHighlightTaskIndex, reAddedTaskIndexList);

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
	
	private int[] updateListIndex(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < taskList.size(); i++){
			temp[i] = i;
		}
		for(int i = taskList.size(); i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}
	
	@Override
	public boolean supportUndo() {
		return true;
	}

	private static void constructBridges(){
		passToStore = new LogicToStore(taskList,trashbinList);
	}
	
	private static int[] initializeList(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}
	
	private DisplayInfo constructDisplay() {
		ViewTaskList viewTaskList;
		int index = currentDisplay[1];
		if(currentListIndex[index] != -1){
			viewTaskList = new ViewTaskList(index, feedback, title);
		} else if (GUI.hasPrevious()){
			viewTaskList = new ViewTaskList(index - Default.MAX_DISPLAY_LINE, feedback, title);
		} else {
			viewTaskList = new ViewTaskList(feedback, title);
		}
		return viewTaskList.execute();
	}

	private void deleteLine() {
		this.taskPointers[0] = taskList.get(currentListIndex[deleteIndex]).getPointer();
		trashbinList.add(taskList.remove(currentListIndex[deleteIndex]));
	}

	private void deleteAll() {
		for(int i = 1; i<= Default.MAX_DISPLAY_LINE; i++){
			if(currentDisplay[i] != -1){
				this.taskPointers[i - 1] = taskList.get(currentListIndex[currentDisplay[1]]).getPointer();
				trashbinList.add(taskList.remove(currentListIndex[currentDisplay[1]]));
			} else {
				break;
			}
		}
	}
	
	private void modifyIndexList() {
		currentListIndex = updateListIndex(currentListIndex.length);
	}
	
	private DisplayInfo constructUndoDisplay(int firstHighlightTaskIndex, int[] reAddedTaskIndexList) {
		Command view = new ViewTaskList(reAddedTaskIndexList[firstHighlightTaskIndex] - (reAddedTaskIndexList[firstHighlightTaskIndex] % Default.MAX_DISPLAY_LINE), 
				UNDO_FEEDBACK, title);
		DisplayInfo dis = view.execute();
		if(reAddedTaskIndexList[firstHighlightTaskIndex] >= 0){
			dis.setHighlight(Default.HIGHLIGHT_LINES);
			dis.setHighlightLine(reAddedTaskIndexList[firstHighlightTaskIndex] % Default.MAX_DISPLAY_LINE);
		}
		
		return dis;
	}

	private int determinefirstTask(int[] reAddedTaskIndexList) {
		int firstHighlightTaskIndex = 0;
		for(int i = 0; i < Default.MAX_DISPLAY_LINE; i++){
			if(reAddedTaskIndexList[i] >= 0){
				if(reAddedTaskIndexList[i] % Default.MAX_DISPLAY_LINE == 0){
					firstHighlightTaskIndex = i;
					break;
				}
			} else {
				break;
			}
		}
		return firstHighlightTaskIndex;
	}

	private int[] addTasksBack() {
		int[] reAddedTaskIndexList = initializeList(Default.MAX_DISPLAY_LINE); 
		for(int i = 0, j = 0; i < Default.MAX_DISPLAY_LINE; i++){
			if(taskPointers[i] >= 0){
				int index = RunLogic.getIndexInList(trashbinList, taskPointers[i]);
				if(index >= 0){
					taskList.add(trashbinList.remove(index));
					reAddedTaskIndexList[j] = taskList.size() - 1;
					j++;
				}
			} else {
				break;
			}
		}
		return reAddedTaskIndexList;
	}

}
