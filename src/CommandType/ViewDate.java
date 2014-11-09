package CommandType;

import java.util.ArrayList;

import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
public class ViewDate implements Command{
	private static JDate date;
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	
	public ViewDate(JDate myDate, String myFeedback, String myTitle) {
		date = myDate;
		feedback = myFeedback;
		title = myTitle;
		initialize();
		
	}

	@Override
	public DisplayInfo execute() {
		ArrayList<Task> targetList = determineTargetList();
		modifyIndexList(targetList);
		modifyGUI();
		update();
		

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
		taskList = RunLogic.getTaskList();
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
	
	@Override
	public boolean supportUndo() {
		return false;
	}

	private int[] updateListIndex(int length,  ArrayList<Task> targetList) {
		int[] currentList = new int[length];
		for(int i = 0; i < targetList.size(); i++){
			currentList[i] = i;
		}
		for(int i = targetList.size(); i < currentList.length; i++){
			currentList[i] = -1;
		}
		return currentList;
	}
	

	private DisplayInfo constructDisplay() {
		Command viewDate;
		if(GUI.getMode().equals(VIEW_MODE.BIN) || GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			viewDate = new ViewTrashBin(0, feedback, title);
		} else {
			viewDate = new ViewTaskList(0, feedback, title);
		}
		
		DisplayInfo dis = viewDate.execute();
		dis.setHighlight(Default.HIGHLIGHT_DATE);
		return dis;
	}

	private void modifyGUI() {
		GUI.changeDate(date);
	}

	private void modifyIndexList(ArrayList<Task> targetList) {
		currentListIndex = updateListIndex(currentListIndex.length, targetList);
		int[] tempListIndex = initializeList(currentListIndex.length);
		for(int i = 0, j = 0; currentListIndex[i] >= 0; i++){
			if(date.equals(targetList.get(currentListIndex[i]).getStartDate()) || 
					date.equals(targetList.get(currentListIndex[i]).getEndDate())){
				tempListIndex[j] = currentListIndex[i];
				j++;
			}
		}
		currentListIndex = tempListIndex;
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

}
