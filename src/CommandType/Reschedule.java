package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.JDate;
import logic.RunLogic;
import logic.Task;

public class Reschedule implements Command{
	JDate newStartDate;
	JDate newEndDate;
	//local memory
	private static ArrayList<Task> taskList;

	//values for GUI and I/O
	private static DisplayInfo passToGui;
		
	
	public Reschedule(JDate startDate, JDate endDate){
		initialize();
		newStartDate = startDate;
		newEndDate = endDate;
	}
	
	
	@Override
	public DisplayInfo execute() {
		taskList.get(RunLogic.getGuiStatus().getTaskIndex()).reschedule(newStartDate, newEndDate);
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(RunLogic.getGuiStatus().getTaskIndex()));
		update();
		
		constructBridges(display, Default.RENAME_FEEDBACK, Default.TITLE);
		DataStore.writeTask(taskList);
		return passToGui;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//---------------helper function--------------
	
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
