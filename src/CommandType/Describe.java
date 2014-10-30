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

	// values for GUI and I/O
	private static DisplayInfo passToGui;

	// added by Zhang Ji
	private long taskPointer;

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
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).describe(
				newDescription);
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

	// -----------helper functions-----------------

	private static void initialize() {
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}

	private static void update() {
		RunLogic.updateTaskList(taskList);
	}

	private static void constructBridges(ArrayList<Task> display,
			String feedback, String title) {
		passToGui = new DisplayInfo(RunLogic.getGuiStatus(), display, feedback,
				title);
	}
}
