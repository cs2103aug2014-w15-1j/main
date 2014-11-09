package CommandType;

import java.util.ArrayList;

import logic.*;

public class SearchTaskList implements Command{
	private static String[] keyWord;
	private static String feedback;
	private static String title;
	
	//local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	
	public SearchTaskList(String word, String myFeedback, String myTitle){
		keyWord = word.split(" ");
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		modifyIndexList();
		update();
		return determineDisplay();
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}


	
	//-----------helper functions-----------------
	
	
	private static void initialize(){
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
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


	private DisplayInfo determineDisplay() {
		Command search = new ViewTaskList(0, feedback, title);
		return search.execute();
	}

	private void modifyIndexList() {
		int[] tempListIndex = initializeDisplayList(currentListIndex.length);
		for(int i = 0, j = 0; currentListIndex[i] >= 0; i++){
			for(int k = 0; k < keyWord.length; k++){
				if(taskList.get(currentListIndex[i]).getName().contains(keyWord[k])){
					tempListIndex[j] = currentListIndex[i];
					j++;
					break;
				}
			}

		}
		currentListIndex = tempListIndex;
	}
}
