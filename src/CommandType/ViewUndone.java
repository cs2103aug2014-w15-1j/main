package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import logic.Default;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.RunLogic;
import logic.Task;

public class ViewUndone implements Command{
	private static String feedback;
	private static String title;
	
	int firstTaskIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	
	//values for GUI and I/O
	private static DisplayInfo passToGui;
	
	public ViewUndone(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		firstTaskIndex = 0;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeCurrentDisplay(currentDisplay.length);
		
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
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
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
	
	private int[] initializeCurrentDisplay(int length) {
		int j = 0;
		int[] result = new int[length];
		for(int i = 0; i < length; i++){
			if(!taskList.get(i).getDone()){
				result[j] = i;
				j++;
			}
		}
		return result;
	}
	
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}
	
	@Override
	public boolean supportUndo() {
		return false;
	}


}
