package CommandType;

import java.util.ArrayList;

import logic.*;

public class SearchTrashBin implements Command{
	private static String keyWord;
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	
	public SearchTrashBin(String word, String myFeedback, String myTitle){
		keyWord = word;
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		currentDisplay = initializeDisplayList(currentDisplay.length);
		int[] tempListIndex = initializeDisplayList(currentListIndex.length);
		for(int i = 0, j = 0; currentListIndex[i] >= 0; i++){
			if(trashbinList.get(currentListIndex[i]).getName().contains(keyWord)){
				tempListIndex[j] = currentListIndex[i];
				j++;
			}
		}
		currentListIndex = tempListIndex;
		update();
		
		Command search = new ViewTrashBin(0, feedback, title);
		return search.execute();
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
	
	private static int[] initializeDisplayList(int length) {
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


}
