package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.LogicToStore;
import logic.RunLogic;
import logic.Task;

public class DeleteTaskList implements Command{
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
			for(int i = 1; i<= Default.MAX_DISPLAY_LINE; i++){
				if(currentDisplay[i] != -1){
					trashbinList.add(taskList.remove(currentListIndex[currentDisplay[1]]));
				} else {
					break;
				}
			}
			currentListIndex = updateListIndex(currentListIndex);
			
			update();
			
			constructBridges();
			DataStore.writeAllData(passToStore);
			
			ViewTaskList viewTaskList;
			if(GUI.hasNext()){
				viewTaskList = new ViewTaskList(currentDisplay[1], feedback, title);
			} else if (GUI.hasPrevious()){
				viewTaskList = new ViewTaskList(currentDisplay[1] - Default.MAX_DISPLAY_LINE, feedback, title);
			} else {
				viewTaskList = new ViewTaskList(feedback, title);
			}
			return viewTaskList.execute();
		} else {
			trashbinList.add(taskList.remove(currentListIndex[deleteIndex]));
			
			currentListIndex = updateListIndex(currentListIndex);
			
			update();
			
			constructBridges();
			DataStore.writeAllData(passToStore);
			
			ViewTaskList viewTaskList;
			if(currentListIndex[currentDisplay[1]] != -1){
				viewTaskList = new ViewTaskList(currentDisplay[1], feedback, title);
			} else if (GUI.hasPrevious()){
				viewTaskList = new ViewTaskList(currentDisplay[1] - Default.MAX_DISPLAY_LINE, feedback, title);
			} else {
				viewTaskList = new ViewTaskList(feedback, title);
			}
			return viewTaskList.execute();
		}
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
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
	
	private int[] updateListIndex(int[] currentList) {
		for(int i = 0; i < taskList.size(); i++){
			currentList[i] = i;
		}
		for(int i = taskList.size(); i < currentList.length; i++){
			currentList[i] = -1;
		}
		return currentList;
	}
	@Override
	public boolean supportUndo() {
		return true;
	}

	private static void constructBridges(){
		passToStore = new LogicToStore(taskList,trashbinList);
	}
}
