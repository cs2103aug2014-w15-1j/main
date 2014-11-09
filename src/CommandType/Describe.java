package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

/**
 * 
 * @author a0119456Y
 * @author A0119493X
 *
 */
public class Describe implements Command {
	private static String feedback;
	private static String title;

	private static String newDescription;
	private static int lineIndex;

	// local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	private long taskPointer;
	private String beforeChangeDes;
	
	
	public Describe(String description, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newDescription = description;
		
		lineIndex = 1;
	}

	public Describe(int line, String description, String myFeedback,
			String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newDescription = description;
		lineIndex = line;
	}

	@Override
	public DisplayInfo execute() {
		recordOldInfo();
		modifyTaskList(newDescription);
		update();
		DataStore.writeTask(taskList);
		return constructDisplay();
	}

	
	@Override
	public DisplayInfo undo() {
		initialize();
		modifyTaskList(beforeChangeDes);
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
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.DESCRIPTION);
		return dis;
	}

	private void modifyTaskList(String newDescription2) {		
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).describe(
			newDescription);
	}

	private void recordOldInfo() {
		beforeChangeDes = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getDescription();
	}

	private DisplayInfo constructUndoDisplay() {
		ReadTaskList read = new ReadTaskList(lineIndex, ConvertCommand.UNDO_DESCRIBE, title);
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.DESCRIPTION);
		return dis;
	}
}
