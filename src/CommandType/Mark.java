package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import data_store.DataStore;
import logic.ConvertCommand;
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
	private long taskPointer;
	private boolean oldStatus;

	
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
		recordOldInfo();
		modifyTaskList(status);
		update();
		DataStore.writeTask(taskList);
		return constructDisplay();		
	}

	
	@Override
	public DisplayInfo undo() {
		initialize();
		modifyTaskList(oldStatus);
		update();
		DataStore.writeTask(taskList);
		return constructUndoDisplay();
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
	@Override
	public boolean supportUndo() {
		return true;
	}

	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}
	
	private DisplayInfo constructDisplay() {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)){
			Command read = new ReadTaskList(lineIndex, feedback, title);
			DisplayInfo dis = read.execute();
			dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
			dis.setHighlightItem(Default.MARK);
			return dis;
		} else {
			Command view = new ViewTaskList(RunLogic.getCurrentDisplay()[1], feedback, title);
			DisplayInfo dis = view.execute();
			dis.setHighlight(Default.HIGHLIGHT_LINE);
			dis.setHighlightLine(lineIndex - 1);
			return dis;
		}
	}

	private void modifyTaskList(boolean status2) {
		if(status){
			taskList.get(currentListIndex[currentDisplay[lineIndex]]).setDone();
		} else {
			taskList.get(currentListIndex[currentDisplay[lineIndex]]).setUndone();
		}
	}

	private void recordOldInfo() {
		oldStatus = status;
	}

	private DisplayInfo constructUndoDisplay() {
		ReadTaskList read = new ReadTaskList(lineIndex, ConvertCommand.UNDO_MARK, title);
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.MARK);
		return dis;
	}
}
