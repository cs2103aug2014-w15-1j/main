package gui;

import java.util.ArrayList;

import logic.DisplayInfo;
import logic.JDate;
import logic.Task;

/**
 * <code>GuiInfoTranslator</code> functions as the translator between GUI and
 * Logic. It translate information <code>DisplayInfo</code> sent by Logic into
 * valid data type that can be used by GUI.
 * <p>
 * <strong>Note</strong>: Some inner logic is involved here due to the
 * dependency between information displayed and view mode.
 * </p>
 * 
 * 
 * @author A0119391A
 * 
 */
public class GuiInfoTranslator {

	// display information sent by Logic
	private DisplayInfo info;

	private ArrayList<String> firstCol = new ArrayList<String>();
	private ArrayList<String> secondCol = new ArrayList<String>();

	// constants
	private final static String MESSAGE_EMPTY_LIST = "No relevent information here";
	private final static String EMPTY_STRING = "";

	// attributes in task detail view mode
	private String[] taskDetailAttr = { "Name", "StartDate", "EndDate",
			"Description", "Repitition" };

	/********************************************
	 ************** Constructor *****************
	 ********************************************/
	public GuiInfoTranslator(DisplayInfo info) {
		this.info = info;
		processTaskInfo();
	}

	/********************************************
	 ************** Public Method ***************
	 ********************************************/
	public boolean hasNextPage() {
		return info.hasNextPage();
	}

	public boolean isPageInvolved() {
		return info.isPageInvolved();
	}

	public boolean hasPreviousPage() {
		return this.hasPreviousPage();
	}

	public boolean changeTitle() {
		return false;
	}

	public String getTitleString() {
		return info.getTitleString();
	}

	public boolean changeTaskList() {
		return info.changeTasklist();
	}

	public ArrayList<String> getFirstCol() {
		return firstCol;
	}

	public ArrayList<String> getSecondCol() {
		return secondCol;
	}

	public String getFeedbackString() {
		return info.getFeedbackString();
	}

	public VIEW_MODE getViewMode() {
		return info.getViewMode();
	}

	/********************************************
	 ************* Private Method ***************
	 ********************************************/
	/**
	 * determine and process task information according to the view mode
	 */
	private void processTaskInfo() {
		ArrayList<Task> taskList = info.getTaskList();
		Task task;
		if (taskList == null || taskList.size() <= 0) {
			return;
		}
		switch (info.getViewMode()) {
		case TASK_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			processTaskDetail(task);
			return;
		case BIN_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			processTaskDetail(task);
			return;
		case MONTH:
			throw new UnsupportedOperationException(
					"view in Month is not supported yet");
		default:
			processTaskList(taskList);
			return;
		}
	}

	/**
	 * process the title and end date for each task in task list into two
	 * <code>ArrayList</code>. The first <code>ArrayList</code> is stored in
	 * <em>firstCol</em> The second <code>ArrayList</code> is stored in
	 * <em>secondCol</em>.
	 * 
	 * @param lst
	 *            ArrayList of task.
	 */
	private void processTaskList(ArrayList<Task> lst) {
		if (lst == null) {
			throw new Error("tasklist cannot be null at this point");
		}
		if (lst.size() <= 0) {
			throw new Error("tasklist cannot be null at this point");
		}

		for (int i = 0; i < lst.size(); i++) {
			firstCol.add(lst.get(i).getName());
			if (lst.get(i).getEndDate() != null) {
				secondCol.add(lst.get(i).getEndDate().toString());
			} else {
				secondCol.add(EMPTY_STRING);
			}
		}

	}

	/**
	 * process detail information of a certain task into two
	 * <code>ArrayList</code>. The first <code>ArrayList</code> is stored in
	 * <em>firstCol</em> The second <code>ArrayList</code> is stored in
	 * <em>secondCol</em>.
	 * 
	 * @param task
	 */
	private void processTaskDetail(Task task) {
		if (task == null) {
			throw new Error("task cannot be null at this point");
		}
		String name = task.getName();
		JDate startDate = task.getStartDate();
		JDate endDate = task.getEndDate();
		String descrition = task.getDescription();
		String repetition = task.getRepeatDays();

		for (String attr : taskDetailAttr) {
			firstCol.add(attr);
		}
		secondCol.add(name);

		if (startDate != null) {
			secondCol.add(startDate.toString());
		} else {
			secondCol.add(EMPTY_STRING);
		}

		if (endDate != null) {
			secondCol.add(endDate.toString());
		} else {
			secondCol.add(EMPTY_STRING);
		}

		secondCol.add(descrition);

		secondCol.add(repetition);

	}
}
