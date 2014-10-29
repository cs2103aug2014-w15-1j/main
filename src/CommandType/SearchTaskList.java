package CommandType;

import java.util.ArrayList;

import logic.*;

public class SearchTaskList implements Command{
	private static String keyWord;
	private static String feedback;
	private static String title;
	
	//local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	
	public SearchTaskList(String word, String myFeedback, String myTitle){
		keyWord = word;
		feedback = myFeedback;
		title = myTitle;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		currentDisplay = initializeDisplayList(currentDisplay);
		int[] tempListIndex = initializeDisplayList(currentListIndex);
		for(int i = 0, j = 0; i < taskList.size(); i++){
			if(taskList.get(currentListIndex[i]).getName().contentEquals(keyWord)){
				tempListIndex[j] = currentListIndex[i];
				j++;
			}
		}
		currentListIndex = tempListIndex;
		update();
		ViewTaskList search = new ViewTaskList(0, feedback, title);
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
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
		RunLogic.updateCurrentListIndex(currentListIndex);
	}
	
	private static int[] initializeDisplayList(int[] currentDisplay) {
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}

}
