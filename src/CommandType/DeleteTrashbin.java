package CommandType;

import java.util.ArrayList;

import logic.*;
import data_store.DataStore;

/**
 * 
 * @author a0119456Y
 * @author A0119493X
 *
 */
public class DeleteTrashbin implements Command{
	private static String feedback;
	private static String title;
	
	private static boolean deleteAll;
	private static int deleteIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	
	public DeleteTrashbin(Boolean all, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		initialize();
		deleteAll = all;
		deleteIndex = -1;
	}
	
	public DeleteTrashbin(int line, String myFeedback, String myTitle){
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
		DataStore.writeTrash(trashbinList);
		return constructDisplay();
	}



	

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	//-----------helper functions-----------------
	
	
	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
		RunLogic.updateCurrentListIndex(currentListIndex);
	}

	private int[] updateListIndex(int[] currentList) {
		for(int i = 0; i < trashbinList.size(); i++){
			currentList[i] = i;
		}
		for(int i = trashbinList.size(); i < currentList.length; i++){
			currentList[i] = -1;
		}
		return currentList;
	}
	@Override
	public boolean supportUndo() {
		return false;
	}

	private void deleteLine() {
		trashbinList.remove(currentListIndex[deleteIndex]);
	}

	private void deleteAll() {			
		for(int i = 1; i<= Default.MAX_DISPLAY_LINE; i++){
			if(currentDisplay[i] != -1){
				trashbinList.remove(currentListIndex[currentDisplay[1]]);
			} else {
				break;
			}
		}
	}
	
	private void modifyIndexList() {
		currentListIndex = updateListIndex(currentListIndex);
	}
	
	private DisplayInfo constructDisplay() {
		ViewTrashBin viewTrashbin;
		int index = currentDisplay[1];
		if(currentListIndex[index] != -1){
			viewTrashbin = new ViewTrashBin(index, feedback, title);
		} else if (GUI.hasPrevious()){
			viewTrashbin = new ViewTrashBin(index - Default.MAX_DISPLAY_LINE, feedback, title);
		} else {
			viewTrashbin = new ViewTrashBin(feedback, title);
		}
		return viewTrashbin.execute();
	}
}
