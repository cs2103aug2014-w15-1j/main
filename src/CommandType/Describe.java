package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Describe implements Command {
	private static String feedback;
	private static String title;

	private static String newDescription;
	private static int lineIndex;

	// local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	// added by Zhang Ji
	private long taskPointer;
	
	// added by Chen Di
	private String beforeChangeDes;
	
	
	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}

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
		
		// Get old Description
		beforeChangeDes = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getDescription();
		System.out.println("beforeChangeDes:"  + beforeChangeDes);
		
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).describe(
				newDescription);
		update();

		DataStore.writeTask(taskList);
		
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHightlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.DESCRIPTION);
		return dis;
	}

	@Override
	public DisplayInfo undo() {
		// Add by Chen Di
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).describe(
				beforeChangeDes);
		
		update();

		DataStore.writeTask(taskList);
		
		ReadTaskList read = new ReadTaskList(lineIndex, feedback, title);
		DisplayInfo dis = read.execute();
		dis.setHightlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.DESCRIPTION);
		
		return dis;
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
