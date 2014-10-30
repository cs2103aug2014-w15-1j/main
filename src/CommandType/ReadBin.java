package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import logic.*;

public class ReadBin implements Command {
	private static String feedback;
	private static String title;

	int readIndex;

	// local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;

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

	public ReadBin(int line, String myFeedback, String myTitle) {
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

		GUI.changeViewMode(VIEW_MODE.BIN_DETAIL);

		display.add(trashbinList.get(RunLogic.getCurrentListIndex()[readIndex]));

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
		trashbinList = RunLogic.getTrashbinList();
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
