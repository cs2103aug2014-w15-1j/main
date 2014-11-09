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
	private static String UNDO_FEEDBACK = "Restored task deleted!";
	
	private static String feedback;
	private static String title;

	int restoreIndex;

	// local memory
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;	
	private long taskPointer;
	private int originListIndex;

	// values for I/O
	private static LogicToStore passToStore;

	
	public Restore(int line, String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		this.restoreIndex = currentDisplay[line];
	}

	@Override
	public DisplayInfo execute() {
		recordOldInfo();
		modifyTaskList();
		modifyIndexList();
		update();
		constructBridges();
		DataStore.writeAllData(passToStore);
		return constructDisplay();	
	}

	
	@Override
	public DisplayInfo undo() {
		initialize();
		modifyTrashBin();
		modifyIndexList();
		update();
		constructBridges();
		DataStore.writeAllData(passToStore);
		return constructUndoDisplay();
		
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
	
	public void setTaskPointer(long pointer) {
		this.taskPointer = pointer;
	}

	public long getTaskPointer() {
		return taskPointer;
	}
	
	private DisplayInfo constructDisplay() {
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

	private void modifyIndexList() {
		currentListIndex = updateListIndex(currentListIndex);
	}

	private void modifyTaskList() {
		taskList.add(trashbinList.remove(currentListIndex[restoreIndex]));
	}

	private void recordOldInfo() {
		originListIndex = currentListIndex[restoreIndex];
	}
	
	private DisplayInfo constructUndoDisplay() {
		Command viewTrashBin = new ViewTrashBin(trashbinList.size() - 1 - (trashbinList.size() % Default.MAX_DISPLAY_LINE), UNDO_FEEDBACK, title);
		if (currentListIndex[originListIndex] != -1) {
			viewTrashBin = new ViewTrashBin(originListIndex, feedback, title);
		} else if (GUI.hasPrevious()) {
			viewTrashBin = new ViewTrashBin(originListIndex
					- Default.MAX_DISPLAY_LINE, feedback, title);
		} else {
			viewTrashBin = new ViewTrashBin(feedback, title);
		}
		return viewTrashBin.execute();
	}

	private void modifyTrashBin() {
		trashbinList.add(taskList.remove(originListIndex));
	}

}
