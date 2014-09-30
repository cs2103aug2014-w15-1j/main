package gui;

import java.util.ArrayList;

import logic.GUIStatus;
import logic.Task;

/**
 * class DisplayConfiguration
 * 
 * @author JJ This class is an package of data passed from LOGIC TO GUI which
 *         contains all needed information for display
 */
public class DisplayConfiguration {
	/*
	 * ====================================================================
	 * ===================== Start OF PRIVATE FIELD =======================
	 * ====================================================================
	 */
	private VIEW_MODE mode;
	private boolean isPageInvolved;
	private boolean hasNextPage;
	private boolean hasPreviousPage;

	private ArrayList<Task> TaskList;
	private String feedback;
	private String title;

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */
	public DisplayConfiguration(GUIStatus status, ArrayList<Task> taskList,
			String feedback, String title) {
		this.mode = status.getMode();
		this.hasNextPage = status.hasNext();
		this.hasPreviousPage = status.hasPrevious();
		setIsPageInvolved();

		this.TaskList = taskList;
		this.feedback = feedback;
		this.title = title;
	}

	public String getContentString() {
		ArrayList<Task> taskList = getTaskList();
		switch (mode) {
		case TASK_DETAIL:
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			Task task = taskList.get(0);
			return processTaskDetailText(task);
		case MONTH:
			throw new Error("not supported yet");
		default:
			return processTaskListText(taskList);
		}
	}

	/**
	 * method setIsPageInvolved: check if the nextPage and previousPage should
	 * be involved in this view mode or not
	 */
	private void setIsPageInvolved() {
		switch (mode) {
		case DATE:
			isPageInvolved = true;
			break;
		case MONTH:
			isPageInvolved = true;
			break;
		case UNDONE:
			isPageInvolved = true;
			break;
		case BIN:
			isPageInvolved = true;
			break;
		case TASK_DETAIL:
			isPageInvolved = false;
			break;
		default:
			throw new Error("Invalid View Mode" + mode);
		}
	}

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
			String taskOpen = "<li font color=" + fontColor + ">";
			String taskClose = "</li>";
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
	 * string with all necessary attributes displayed.
	 * 
	 * @param task
	 * @return corresponding string in text\html format
	 */
	private String processTaskDetailText(Task task) {
		String attrFontColor = "black";
		String infoFontColor = "green";
		String attrOpen = "<i font color=" + attrFontColor + ">";
		String attrClose = "</i>";
		String infoOpen = "<big font color = " + infoFontColor + ">";
		String infoClose = "</big>";
		
		
		String nameInfo = infoOpen + task.getName() + infoClose;
		String descriptionInfo = infoOpen + task.getDescription() + infoClose;
		String startDateInfo = infoOpen + task.getStartDate() + infoClose;
		String endDateInfo = infoOpen + task.getEndDate() + infoClose;
		
		String nameAttr = attrOpen + "Name: " + attrClose;
		String descriptionAttr = attrOpen + "description: " + attrClose;
		String StartDateAttr = attrOpen + "startTime: " + attrClose;
		String endDateAttr = attrOpen + "endTime: " + attrClose;

		String output = "<html>" + nameAttr + nameInfo + "<br>"
				+ descriptionAttr + descriptionInfo + "<br>"
				+ StartDateAttr + startDateInfo + "<br>" + endDateAttr
				+ endDateInfo + "<br>" + "</html>";
		return output;

	}

	public String getTaskListString() {
		return processTaskListText(getTaskList());
	}

	protected ArrayList<Task> getTaskList() {
		return this.TaskList;
	}

	String getFeedback() {
		return this.feedback;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean hasNextPage() {
		return this.hasNextPage;
	}

	public boolean isPageInvolved() {
		return isPageInvolved;
	}

	public boolean hasPreviousPage() {
		return this.hasPreviousPage;
	}

	public boolean isTaskView() {
		return mode == VIEW_MODE.TASK_DETAIL;
	}

}
