package CommandType;

import java.util.ArrayList;

import logic.*;

/**
 * 
 * @author a0119456Y
 *
 */
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
		modifyDisplayList();
		modifyGUI();
		constructBridges(constructDisplay(), feedback, title);
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

	private static int[] initializeList(int length) {
		int[] temp = new int[length];
		for(int i = 0; i < length; i++){
			temp[i] = -1;
		}
		return temp;
	}

	private static void constructBridges(ArrayList<Task> display,
			String feedback, String title) {
		passToGui = new DisplayInfo(GUI, display, feedback, title);
	}
	
	@Override
	public boolean supportUndo() {
		return false;
	}
	
	private void modifyDisplayList() {		
		currentDisplay = initializeList(currentDisplay.length);
		currentDisplay[1] = readIndex;
	}

	private void modifyGUI() {
		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
		GUI.changeCurretnTask(RunLogic.getCurrentListIndex()[readIndex]);
	}

	private ArrayList<Task> constructDisplay() {
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(RunLogic.getCurrentListIndex()[readIndex]));
		return display;
	}


}
