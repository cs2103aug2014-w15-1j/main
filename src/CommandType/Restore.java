package CommandType;

import java.util.ArrayList;

import data_store.DataStore;
import logic.Default;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.LogicToStore;
import logic.RunLogic;
import logic.Task;

public class Restore implements Command {
	private static String feedback;
	private static String title;

	int restoreIndex;

	// local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;

	private static LogicToStore passToStore;

	// added by Zhang Ji
	private long taskPointer;

	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}

	public Restore(int line, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		this.restoreIndex = currentDisplay[line];
	}

	@Override
	public DisplayInfo execute() {
		taskList.add(trashbinList.remove(currentListIndex[restoreIndex]));

		currentListIndex = updateListIndex(currentListIndex);

		update();

		constructBridges();
		DataStore.writeAllData(passToStore);

		ViewTrashBin viewTrashBin;
		if (currentListIndex[restoreIndex] != -1) {
			viewTrashBin = new ViewTrashBin(restoreIndex, feedback, title);
		} else if (GUI.hasPrevious()) {
			viewTrashBin = new ViewTrashBin(restoreIndex
					- Default.MAX_DISPLAY_LINE, feedback, title);
		} else {
			viewTrashBin = new ViewTrashBin(feedback, title);
		}
		return viewTrashBin.execute();
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
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
		currentListIndex = RunLogic.getCurrentListIndex();
	}

	private static void update() {
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
		RunLogic.updateCurrentListIndex(currentListIndex);
	}

	private int[] updateListIndex(int[] currentList) {
		for (int i = 0; i < trashbinList.size(); i++) {
			currentList[i] = i;
		}
		for (int i = trashbinList.size(); i < currentList.length; i++) {
			currentList[i] = -1;
		}
		return currentList;
	}

	private static void constructBridges() {
		passToStore = new LogicToStore(taskList, trashbinList);
	}
	@Override
	public boolean supportUndo() {
		return true;
	}
}
