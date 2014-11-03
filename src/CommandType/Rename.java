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

	// added by Zhang Ji
	private long taskPointer;

	// Added by Chen Di
	private String beforeChangeTitle;
	
	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}

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
		// Added by Chen Di
		beforeChangeTitle = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getName();
		// =======
		
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).rename(
				newName);
		update();

		DataStore.writeTask(taskList);
		
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.NAME);
		return dis;
	}

	@Override
	public DisplayInfo undo() {
		// Added by Chen Di
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).rename(
				beforeChangeTitle);
		update();

		DataStore.writeTask(taskList);
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, String.format(ConvertCommand.DETAIL_TITLE_FORMAT, beforeChangeTitle));
		DisplayInfo dis = read.execute();
		dis.setHightlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.NAME);
		return dis;
		// =======
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


}
