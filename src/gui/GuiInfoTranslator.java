package gui;

import java.util.ArrayList;
import logic.DisplayInfo;
import logic.Task;

public class GuiInfoTranslator  {

	/*
	 * ====================================================================
	 * ===================== Start OF PRIVATE FIELD =======================
	 * ====================================================================
	 */
	private DisplayInfo info;
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
	public GuiInfoTranslator(DisplayInfo info){
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
	
	public String getTaskString() {
		ArrayList<Task> taskList = info.getTaskList();
		Task task;
		switch (info.getViewMode()) {
		case TASK_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			return processTaskDetailText(task);
		case BIN_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			task = taskList.get(0);
			return processTaskDetailText(task);
		case MONTH:
			throw new Error("not supported yet");
		default:
			return processTaskListText(taskList);
		}
	}

	
	
	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */

	
	/**
	 * method processTaskListText: convert a list of task into text\html string
	 * with only name displayed in an ordered list
	 * 
	 * @param taskList
	 * @return corresponding string in text\html format
	 */
	private String processTaskListText(ArrayList<Task> taskList) {
		String output = "";
		if (taskList.size() > 0) {
			String fontColor = "blue";
			String taskOpen = "<li><font size=+2 font color=" + fontColor + ">";
			String taskClose = "</font>";
			String body = "";
			for (int i = 0; i < taskList.size(); i++) {
				body += taskOpen + taskList.get(i).getName() + taskClose;
			}
			output = "<html>" + "<ol>" + body + "</ol>" + "</html>";

		} else {
			output = "<html>" + "<center>" + "EMPTY" + "</center>" + "</html>";
		}

		return output;

	}

	/**
	 * method processTaskDetailText: convert a certain task into text\html
	 * 
	 * string with all necessary attributes displayed.
	 * 
	 * @param task
	 * @return corresponding string in text\html format
	 */
	private String processTaskDetailText(Task task) {
		String attrFontColor = "black";
		String infoFontColor = "green";
		String attrOpen = "<i><font size=+1 color=" + attrFontColor + ">";
		String attrClose = "</font>";
		String infoOpen = "<i><font size=+1 color = " + infoFontColor + ">";
		String infoClose = "</i>";
		String newLine = "<br>";

		String nameInfo = infoOpen + task.getName() + infoClose;
		String descriptionInfo = infoOpen + task.getDescription() + infoClose;
		String startDateInfo = infoOpen + task.getStartDate().toLocaleString() + infoClose;
		String endDateInfo = infoOpen + task.getEndDate().toLocaleString() + infoClose;

		String nameAttr = attrOpen + "Name: " + attrClose;
		String descriptionAttr = attrOpen + "description: " + attrClose;
		String StartDateAttr = attrOpen + "startTime: " + attrClose;
		String endDateAttr = attrOpen + "endTime: " + attrClose;

		String output = "<html>" + newLine  + nameAttr + nameInfo + "<br>"
				+ descriptionAttr + descriptionInfo + "<br>" + StartDateAttr
				+ startDateInfo + "<br>" + endDateAttr + endDateInfo + "<br>"
				+ "</html>";
		return output;

	}




}
