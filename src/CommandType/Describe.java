package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Describe implements Command{
	private static String feedback;
	private static String title;
	
	String newDescription;
	
	//local memory
	private static ArrayList<Task> taskList;

	//values for GUI and I/O
	private static DisplayInfo passToGui;
		
	public Describe(String description, String myFeedback, String myTitle){
		feedback = myFeedback;
		title = myTitle;
		
		initialize();
		newDescription = description;
	}
	
	@Override
	public DisplayInfo execute() {
		taskList.get(RunLogic.getGuiStatus().getTaskIndex()).describe(newDescription);
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(RunLogic.getGuiStatus().getTaskIndex()));
		update();
		
		constructBridges(display, feedback, title);
		DataStore.writeTask(taskList);
		return passToGui;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//-----------helper functions-----------------
	
	
	private static void initialize(){
		taskList = RunLogic.getTaskList();
	}
		
	private static void update(){
		RunLogic.updateTaskList(taskList);
	}
		
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(RunLogic.getGuiStatus(), display, feedback, title);
	}
}
