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
	boolean deleteAll;
	int deleteIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
		
	private static LogicToStore passToStore;
	
	public DeleteTaskList(Boolean all){
		initialize();
		this.deleteAll = all;
		this.deleteIndex = -1;
	}
	
	public DeleteTaskList(int line){
		initialize();
		this.deleteAll = false;
		this.deleteIndex = currentDisplay[line];
	}
	
	@Override
	public DisplayInfo execute() {
		if(deleteAll){
			trashbinList.addAll(taskList);
			taskList.clear();
			
			update();
			
			constructBridges();
			DataStore.writeAllData(passToStore);
			
			ViewTaskList viewTaskList = new ViewTaskList();
			return viewTaskList.execute();
		} else {
			trashbinList.add(taskList.remove(deleteIndex));
			
			if(currentDisplay[1] >= taskList.size()){
				currentDisplay[1] -= Default.MAX_DISPLAY_LINE;
			}
			update();
			
			constructBridges();
			DataStore.writeAllData(passToStore);
			
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
