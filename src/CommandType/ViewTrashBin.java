package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import logic.*;

public class ViewTrashBin implements Command{
	private static String feedback;
	private static String title;
	
	int firstTaskIndex;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	
	//values for GUI and I/O
	private static DisplayInfo passToGui;
	
	public ViewTrashBin(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		firstTaskIndex = 0;
		initialize();
	}
	
	public ViewTrashBin(int index, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		firstTaskIndex = index;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeDisplayList(currentDisplay.length);
		
		if(trashbinList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.BIN, false, false, -1, GUI.getDate());
		} else {
			boolean hasNext = currentListIndex[firstTaskIndex + Default.MAX_DISPLAY_LINE] > 0;
			boolean hasPrevious = firstTaskIndex > Default.MAX_DISPLAY_LINE;
			for(int i = 1, j = firstTaskIndex;  currentListIndex[j] >= 0; j++){
				if(i <= Default.MAX_DISPLAY_LINE){
					display.add(trashbinList.get(currentListIndex[j]));
					currentDisplay[i] = j;
					i++;
				} else {
					break;
				}
			}
			GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
		}
		constructBridges(display, feedback, title);
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
		trashbinList = RunLogic.getTrashbinList();
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

	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}

}
