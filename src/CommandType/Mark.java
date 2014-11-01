package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.RunLogic;
import logic.Task;

public class Mark implements Command{
	private static String feedback;
	private static String title;

	private static boolean status;
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
	
	public Mark(boolean myStatus, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		status = myStatus;
		lineIndex = 1;
	}

	public Mark(int line, boolean myStatus, String myFeedback,
			String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		status = myStatus;
		lineIndex = line;
	}
	
	@Override
	public DisplayInfo execute() {
		if(status){
			taskList.get(currentListIndex[currentDisplay[lineIndex]]).setDone();
		} else {
			taskList.get(currentListIndex[currentDisplay[lineIndex]]).setUndone();
		}
		update();

		DataStore.writeTask(taskList);
		
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHightlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.NAME);
		return dis;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	// -----------helper functions-----------------

	private static void initialize() {
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}

	private static void update() {
		RunLogic.updateTaskList(taskList);
	}
}
