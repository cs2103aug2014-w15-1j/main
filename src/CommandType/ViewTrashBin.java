package CommandType;

import java.util.ArrayList;

import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
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
		boolean hasNext = determineNext();
		boolean hasPrevious = determinePrevious();
		ArrayList<Task> display = determineDisplayIndex();
		modifyGUI(hasNext, hasPrevious);
		update();
		constructBridges(display, feedback, title);
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
	
	private static int[] initializeList(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}

	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}
	@Override
	public boolean supportUndo() {
		return false;
	}

	private ArrayList<Task> determineDisplayIndex() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeList(currentDisplay.length);
		if(currentListIndex[0] >= 0){
			for(int i = 1, j = firstTaskIndex;  currentListIndex[j] >= 0; j++){
				if(i <= Default.MAX_DISPLAY_LINE){
					display.add(trashbinList.get(currentListIndex[j]));
					currentDisplay[i] = j;
					i++;
				} else {
					break;
				}
			}
		}
		return display;
	}

	private boolean determinePrevious() {
		return firstTaskIndex >= Default.MAX_DISPLAY_LINE;
	}

	private boolean determineNext() {
		return currentListIndex[firstTaskIndex + Default.MAX_DISPLAY_LINE] > 0;
	}

	private void modifyGUI(boolean hasNext, boolean hasPrevious) {
		GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, hasPrevious, currentListIndex[currentDisplay[1]], GUI.getDate());
	}
}
