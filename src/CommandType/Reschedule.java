package CommandType;

import gui.VIEW_MODE;

import java.util.ArrayList;

import data_store.DataStore;
import logic.*;

public class Reschedule implements Command {
	private static String feedback;
	private static String title;

	private static JDate newStartDate;
	private static JDate newEndDate;
	private static int lineIndex;

	// local memory
	private static ArrayList<Task> taskList;
	private static int[] currentDisplay;
	private static int[] currentListIndex;
	private long taskPointer;
	private JDate startDayBC;
	private JDate endDayBC;
	
	
	public Reschedule(JDate startDate, JDate endDate, String myFeedback,
			String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newStartDate = startDate;
		newEndDate = endDate;
		lineIndex = 1;
	}

	public Reschedule(int line, JDate startDate, JDate endDate,
		String myFeedback, String myTitle) {
		feedback = myFeedback;
		title = myTitle;

		initialize();
		newStartDate = startDate;
		newEndDate = endDate;
		lineIndex = line;
	}

	@Override
	public DisplayInfo execute() {
		recordOldInfo();
		modifyTaskList(newStartDate, newEndDate);
		update();
		DataStore.writeTask(taskList);
		return constructDisplay();	
	}

	

	@Override
	public DisplayInfo undo() {
		modifyTaskList(startDayBC, endDayBC);
		update();
		DataStore.writeTask(taskList);
		return constructUndoDisplay();
	}

	// ---------------helper function--------------

	private DisplayInfo constructUndoDisplay() {
		ReadTaskList read = new ReadTaskList(lineIndex, ConvertCommand.UNDO_RESCHEDULE, title);
		DisplayInfo dis = read.execute();
		dis.setHighlight(Default.HIGHLIGHT_PROPERTY);
		dis.setHighlightItem(Default.BOTHDATE);
		if(newStartDate == null){
			dis.setHighlightItem(Default.ENDDATE);
		} else if(newEndDate == null){
			dis.setHighlightItem(Default.STARTDATE);
		}
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
			dis.setHighlightItem(Default.BOTHDATE);
			if(newStartDate == null){
				dis.setHighlightItem(Default.ENDDATE);
			} else if(newEndDate == null){
				dis.setHighlightItem(Default.STARTDATE);
			}
			return dis;
		} else {
			Command view = new ViewTaskList(RunLogic.getGuiStatus().getTaskIndex(), feedback, title);
			DisplayInfo dis = view.execute();
			dis.setHighlight(Default.HIGHLIGHT_LINE);
			dis.setHighlightLine(lineIndex - 1);
			return dis;
		}
	}

	private void modifyTaskList(JDate startDayBC2, JDate endDayBC2) {
		taskList.get(currentListIndex[currentDisplay[lineIndex]]).reschedule(
			newStartDate, newEndDate);
	}

	private void recordOldInfo() {
		startDayBC = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getStartDate();
		endDayBC = taskList.get(currentListIndex[currentDisplay[lineIndex]]).getEndDate();
	}
}
