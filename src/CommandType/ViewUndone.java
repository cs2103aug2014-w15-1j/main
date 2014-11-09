package CommandType;

import java.util.ArrayList;

import logic.*;

public class ViewUndone implements Command{
	private static String feedback;
	private static String title;
	
	int firstTaskIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentListIndex;
	
	public ViewUndone(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		firstTaskIndex = 0;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		ArrayList<Task> targetList = determineTargetList();
		currentListIndex = initializeCurrentDisplay(currentListIndex.length, targetList);
		update();
		
		Command view  = new ViewTaskList(0, feedback, title);
		return view.execute();
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
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
		RunLogic.updateCurrentListIndex(currentListIndex);
	}
	
	private static int[] initializeList(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}
	
	private int[] initializeCurrentDisplay(int length, ArrayList<Task> targetList) {
		int[] result = initializeList(length);
		for(int i = 0, j = 0; i < targetList.size(); i++){
			if(!targetList.get(i).getDone()){
				result[j] = i;
				j++;
			}
		}
		return result;
	}
	
	private ArrayList<Task> determineTargetList() {
		ArrayList<Task> targetList = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN) || GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			targetList = trashbinList;
		} else {
			targetList = taskList;
		}
		return targetList;
	}

	@Override
	public boolean supportUndo() {
		return false;
	}


}
