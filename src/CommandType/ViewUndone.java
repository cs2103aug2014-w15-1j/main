package CommandType;

import java.util.ArrayList;

import logic.*;

public class ViewUndone implements Command{
	private static String feedback;
	private static String title;
	
	int firstTaskIndex;
	
	//local memory
	private static ArrayList<Task> taskList;
	private static int[] currentListIndex;
	
	public ViewUndone(String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		firstTaskIndex = 0;
		initialize();
	}
	
	@Override
	public DisplayInfo execute() {
		currentListIndex = initializeCurrentDisplay(currentListIndex.length);
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
		taskList = RunLogic.getTaskList();
		currentListIndex = RunLogic.getCurrentListIndex();
	}
	
	private static void update(){
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
		int[] result = initializeDisplayList(length);
		for(int i = 0, j = 0; currentListIndex[i] >= 0; i++){
			if(!taskList.get(currentListIndex[i]).getDone()){
				result[j] = currentListIndex[i];
				j++;
			}
		}
		return result;
	}

	@Override
	public boolean supportUndo() {
		return false;
	}


}
