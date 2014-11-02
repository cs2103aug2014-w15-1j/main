package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Reschedule implements Command {
	private static String feedback;
	private static String title;

	private static JDate newStartDate;
	private static JDate newEndDate;
	private static int lineIndex;

	// local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	// added by Zhang Ji
	private long taskPointer;

	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}

	public Reschedule(JDate startDate, JDate endDate, String myFeedback,
			String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newStartDate = startDate;
		newEndDate = endDate;
		lineIndex = 1;
	}

	public Reschedule(int line, JDate startDate, JDate endDate,
		String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newStartDate = startDate;
		newEndDate = endDate;
		lineIndex = line;
	}

	@Override
	public DisplayInfo execute() {
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).reschedule(
				newStartDate, newEndDate);
		update();

		update();

		DataStore.writeTask(taskList);
		
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHightlight(Default.HIGHLIGHT_PROPERTY);
		if(newStartDate != null){
			dis.setHighlightItem(Default.STARTDATE);
		}
		if(newEndDate != null){
			dis.setHighlightItem(Default.ENDDATE);
		}
		return dis;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------helper function--------------

	private static void initialize() {
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}

	private static void update() {
		RunLogic.updateTaskList(taskList);
	}
	@Override
	public boolean supportUndo() {
		return true;
	}

}
