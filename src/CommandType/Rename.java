package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Rename implements Command {
	private static String feedback;
	private static String title;

	private static String newName;
	private static int lineIndex;

	// local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	private long taskPointer;
	private String beforeChangeTitle;
	
	
	public Rename(int line, String name, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newName = name;
		lineIndex = line;
	}

	public Rename(String name, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newName = name;
		lineIndex = 1;
	}

	@Override
	public DisplayInfo execute() {
		recordOldInfo();
		modifyTaskList(newName);
		update();
		DataStore.writeTask(taskList);
		return constructDisplay();
		
	}

	
	@Override
	public DisplayInfo undo() {
		initialize();
		modifyTaskList(beforeChangeTitle);
		update();
		DataStore.writeTask(taskList);
		return constructUndoDisplay();	
	}

	// -----------helper functions-----------------

	private DisplayInfo constructUndoDisplay() {
		ReadTaskList read = new ReadTaskList(lineIndex, ConvertCommand.UNDO_RENAME, String.format(ConvertCommand.DETAIL_TITLE_FORMAT, beforeChangeTitle));
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.NAME);
		return dis;
	}

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
		dis.setHighlightItem(Default.NAME);
		return dis;
	} else {
		Command view = new ViewTaskList(RunLogic.getGuiStatus().getTaskIndex(), feedback, title);
		DisplayInfo dis = view.execute();
		dis.setHighlight(Default.HIGHLIGHT_LINE);
		dis.setHighlightLine(lineIndex - 1);
		return dis;
	}
	}

	private void modifyTaskList(String name) {
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).rename(
				name);
	}

	private void recordOldInfo() {
		beforeChangeTitle = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getName();
	}

}
