package gui;

import java.util.ArrayList;

import logic.DisplayInfo;
import logic.JDate;
import logic.Task;

public class GuiInfoTranslator {

	/*
	 * ====================================================================
	 * ===================== Start OF PRIVATE FIELD =======================
	 * ====================================================================
	 */
	private DisplayInfo info;
	private final static String MESSAGE_EMPTY_LIST = "No relevent information here";
	private final static String EMPTY_STRING = "";
	private String[] taskDetailAttr = {"Name", "StartDate", "EndDate", "Description", "Repition"};
	private ArrayList<String> firstCol = new ArrayList<String>();
	private ArrayList<String> secondCol = new ArrayList<String>();

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */

	/*
	 * ====================================================================
	 * ===================== START OF PUBLIC METHOD =======================
	 * ====================================================================
	 */
	public GuiInfoTranslator(DisplayInfo info) {
		this.info = info;
	}

	public boolean hasNextPage() {
		return info.hasNextPage();
	}

	public boolean isPageInvolved() {
		return info.isPageInvolved();
	}

	public boolean hasPreviousPage() {
		return this.hasPreviousPage();
	}

	public String getFeedbackString() {
		return info.getFeedbackString();
	}

	public String getTitleString() {
		return info.getTitleString();
	}

	public boolean changeTaskList() {
		return info.changeTasklist();
	}

	public VIEW_MODE getViewMode() {
		return info.getViewMode();
	}

	public ArrayList<String> getFirstCol() {
		return firstCol;
	}

	public ArrayList<String> getSecondCol() {
		return secondCol;
	}

	public boolean processTaskInfo() {
		ArrayList<Task> taskList = info.getTaskList();
		Task task;
		if (taskList == null) {
			return false;
		} else if(taskList.size() <= 0) {
			return false;
		}

		switch (info.getViewMode()) {
		case TASK_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			processTaskDetail(task);
			return true;
		case BIN_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			processTaskDetail(task);
			return true;
		case MONTH:
			throw new UnsupportedOperationException(
					"view in Month is not supported yet");
		default:
			processTaskList(taskList);
			return true;
		}
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */

	private void processTaskList(ArrayList<Task> lst) {
		if(lst == null) {
			throw new Error("tasklist cannot be null at this point");
		}
		if(lst.size() <= 0) {
			throw new Error("tasklist cannot be null at this point");
		}
		
		for(int i=0; i<lst.size(); i++) {
			firstCol.add(lst.get(i).getName());
			if(lst.get(i).getEndDate() != null){
				secondCol.add(lst.get(i).getEndDate().toString());
			} else {
				secondCol.add(EMPTY_STRING);
			}
		}

	}
	private void processTaskDetail(Task task) {
		if(task == null) {
			throw new Error("task cannot be null at this point");
		}
		String name = task.getName();
		JDate startDate = task.getStartDate();
		JDate endDate = task.getEndDate();
		String descrition = task.getDescription();
		String repetition = task.getRepeatDays();
		
		for(String attr: taskDetailAttr) {
			firstCol.add(attr);
		}
		secondCol.add(name);
		if(startDate != null) {
			secondCol.add(startDate.toString());
		} else {
			secondCol.add(EMPTY_STRING);
		}
		if(endDate != null) {
			secondCol.add(endDate.toString());
		} else {
			secondCol.add(EMPTY_STRING);
		}
		secondCol.add(descrition);
		secondCol.add(repetition);
		
	}

	private static boolean checkNullInfo(Object o) {
		return o == null;
	}

}
