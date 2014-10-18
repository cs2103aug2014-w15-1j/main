package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import logic.*;

public class ViewTaskList implements Command{
	int firstTaskIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	
	//values for GUI and I/O
	private static DisplayInfo passToGui;
	
	public ViewTaskList(){
		firstTaskIndex = 0;
		initialize();
	}
	
	public ViewTaskList(int index){
		firstTaskIndex = index;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeDisplayList(currentDisplay);
		if(taskList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.TASK_LIST, false, false, -1, GUI.getDate());
		} else {
			boolean hasNext = false;
			for(int i = 1, j = firstTaskIndex;  j < taskList.size(); j++){
				if(i <= Default.MAX_DISPLAY_LINE){
					display.add(taskList.get(j));
					currentDisplay[i] = j;
					i++;
				} else {
					hasNext = true;
					break;
				}
			}
			boolean hasPrevious = (currentDisplay[1] > 0);
			GUI = new GUIStatus(VIEW_MODE.TASK_LIST, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
		}
		constructBridges(display, Default.VIEW_FEEDBACK, Default.TITLE);
		update();
		return passToGui;
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
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}
	
	private static int[] initializeDisplayList(int[] currentDisplay) {
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}
	
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}

}
