package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.LogicToStore;
import logic.RunLogic;
import logic.Task;

public class Restore implements Command{
	private static String feedback;
	private static String title;
	
	int restoreIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
		
	private static LogicToStore passToStore;
	
	public Restore(int line, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		initialize();
		this.restoreIndex = currentDisplay[line];
	}
		
		
	@Override
	public DisplayInfo execute() {
		taskList.add(trashbinList.remove(restoreIndex));
		
		if(currentDisplay[1] > trashbinList.size()){
			currentDisplay[1] -= Default.MAX_DISPLAY_LINE;
		}
		update();
		
		constructBridges();
		DataStore.writeAllData(passToStore);
		
		ViewTrashBin viewTrashbin = new ViewTrashBin(currentDisplay[1], feedback, title);
		return viewTrashbin.execute();
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
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}
	
	private static void constructBridges(){
		passToStore = new LogicToStore(taskList,trashbinList);
	}

}
