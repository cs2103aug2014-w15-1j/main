package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import logic.*;

public class ReadTaskList implements Command {
	private static String feedback;
	private static String title;

	private static int readIndex;

	// local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;

	// added by Zhang Ji
	private long taskPointer;

	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}

	// values for GUI and I/O
	private static DisplayInfo passToGui;

	public ReadTaskList(int line, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		readIndex = currentDisplay[line];
	}

	@Override
	public DisplayInfo execute() {
		ArrayList<Task> display = new ArrayList<Task>();

		currentDisplay = initializeDisplayList(currentDisplay);
		currentDisplay[1] = readIndex;

		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
		GUI.changeCurretnTask(readIndex);

		display.add(taskList.get(RunLogic.getCurrentListIndex()[readIndex]));

		constructBridges(display, feedback, title);
		update();
		return passToGui;
	}

	@Override
	public DisplayInfo undo() {
		// TODO Auto-generated method stub
		return null;
	}

	// -----------helper functions-----------------

	private static void initialize() {
		GUI = RunLogic.getGuiStatus();
		taskList = RunLogic.getTaskList();
		currentDisplay = RunLogic.getCurrentDisplay();
	}

	private static void update() {
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}

	private static int[] initializeDisplayList(int[] currentDisplay) {
		for (int i = 0; i < currentDisplay.length; i++) {
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}

	private static void constructBridges(ArrayList<Task> display,
			String feedback, String title) {
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}
}
