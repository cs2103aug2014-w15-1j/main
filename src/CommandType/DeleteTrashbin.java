package CommandType;

import java.util.ArrayList;

import logic.*;
import data_store.DataStore;

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
			for(int i = 1; i<= Default.MAX_DISPLAY_LINE; i++){
				if(currentDisplay[i] != -1){
					trashbinList.remove(currentListIndex[currentDisplay[1]]);
				} else {
					break;
				}
			}
			currentListIndex = updateListIndex(currentListIndex);
			
			update();
			
			DataStore.writeTrash(trashbinList);
			
			ViewTrashBin viewTrashbin;
			if(GUI.hasNext()){
				viewTrashbin = new ViewTrashBin(currentDisplay[1], feedback, title);
			} else if (GUI.hasPrevious()){
				viewTrashbin = new ViewTrashBin(currentDisplay[1] - Default.MAX_DISPLAY_LINE, feedback, title);
			} else {
				viewTrashbin = new ViewTrashBin(feedback, title);
			}
			return viewTrashbin.execute();
		} else {
			trashbinList.remove(currentListIndex[deleteIndex]);
			
			currentListIndex = updateListIndex(currentListIndex);
			
			update();

			DataStore.writeTrash(trashbinList);
			
			ViewTrashBin viewTrashbin;
			if(currentListIndex[deleteIndex] != -1){
				viewTrashbin = new ViewTrashBin(deleteIndex, feedback, title);
			} else if (GUI.hasPrevious()){
				viewTrashbin = new ViewTrashBin(deleteIndex - Default.MAX_DISPLAY_LINE, feedback, title);
			} else {
				viewTrashbin = new ViewTrashBin(feedback, title);
			}
			return viewTrashbin.execute();
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
		return true;
	}

}
