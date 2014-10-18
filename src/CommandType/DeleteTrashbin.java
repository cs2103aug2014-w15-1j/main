package CommandType;

import java.util.ArrayList;

import logic.*;
import data_store.DataStore;

public class DeleteTrashbin implements Command{
	boolean deleteAll;
	int deleteLine;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	
	public DeleteTrashbin(Boolean all){
		initialize();
		this.deleteAll = all;
		this.deleteLine = -1;
	}
	
	public DeleteTrashbin(int line){
		initialize();
		this.deleteAll = false;
		this.deleteLine = currentDisplay[line];
	}
	
	@Override
	public DisplayInfo execute() {
		if(deleteAll){
			trashbinList.clear();
			
			update();
			ViewTrashBin viewTrashBin = new ViewTrashBin();
			return viewTrashBin.execute();
		} else {
			trashbinList.remove(deleteLine);
			
			if(currentDisplay[1] > trashbinList.size()){
				currentDisplay[1] -= Default.MAX_DISPLAY_LINE;
			}
			update();
			
			DataStore.writeTrash(trashbinList);
			
			ViewTaskList viewTaskList = new ViewTaskList(currentDisplay[1]);
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
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}

}
