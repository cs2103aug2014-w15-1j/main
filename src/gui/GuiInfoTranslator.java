package gui;

import java.util.ArrayList;

import logic.Default;
import logic.DisplayInfo;
import logic.JDate;
import logic.Task;
import logic.VIEW_MODE;

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
	private ArrayList<String> thirdCol = new ArrayList<String>();
	private ArrayList<Boolean> fourthCol = new ArrayList<Boolean>();

	private int highlightedLine = -1;
	private JDate highlightedDate;
	private int highlightedProperty = -1;
	private boolean hightlightMultipleLines = false;

	// constants
	private final static String EMPTY_STRING = "";
	private final static String STR_DONE = "Done";
	private final static String STR_UNDDONE = "Undone";

	// attributes in task detail view mode
	private String[] taskDetailAttr = { "Name", "StartDate", "EndDate",
			"Status", "Description" };

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

	public boolean hasPreviousPage() {
		return info.hasPreviousPage();
	}

	public boolean changeTitle() {
		return info.changeTitle();
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

	public ArrayList<String> getThirdCol() {
		return thirdCol;
	}

	public ArrayList<Boolean> getFourthCol() {
		return fourthCol;
	}

	public String getFeedbackString() {
		return info.getFeedbackString();
	}

	public VIEW_MODE getViewMode() {
		return info.getViewMode();
	}

	public int getHighlightedLine() {
		return this.highlightedLine;
	}

	public String getHighlightedDate() {
		if (this.highlightedDate != null) {
			return highlightedDate.toString();
		} else {
			return null;
		}
	}

	public int getHighlightedProperty() {
		return this.highlightedProperty;
	}

	public boolean getHighlightMultipleLines() {
		return this.hightlightMultipleLines;
	}

	public String getHelpInfo() {
		return info.getHelpInfo();
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
		int expectedListSize;
		int firstIndex = 0;
		switch (info.getViewMode()) {
		case HELP:
			break;
		case TASK_DETAIL:
			expectedListSize = 1;
			assert taskList.size() ==  expectedListSize : 
				"Length of tasklist passed from Logic must be 1 in Taks detail view mode";

			task = taskList.get(firstIndex);
			processTaskDetail(task);
			return;
		case BIN_DETAIL:
			expectedListSize = 1;
			assert taskList.size() ==  expectedListSize : 
				"Length of tasklist passed from Logic must be 1 in bin detail view mode";
			task = taskList.get(firstIndex);
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
		assert lst != null : "a null tasklit cannot be processed";
		assert lst.size() > 0 : "a empty tasklist cannot be processed";

		if (info.getHighlight() == Default.HIGHLIGHT_LINE) {
			this.highlightedLine = info.getHighlightLine();
		} else if (info.getHighlight() == Default.HIGHLIGHT_DATE) {
			this.highlightedDate = info.getDate();
		} else if (info.getHighlight() == Default.HIGHLIGHT_LINES) {
			this.highlightedLine = info.getHighlightLine();
			this.hightlightMultipleLines = true;
		}

		for (int i = 0; i < lst.size(); i++) {
			firstCol.add(lst.get(i).getName());
			if (lst.get(i).getStartDate() != null) {
				secondCol.add(lst.get(i).getStartDate().toString());
			} else {
				secondCol.add(EMPTY_STRING);
			}
			if (lst.get(i).getEndDate() != null) {
				thirdCol.add(lst.get(i).getEndDate().toString());
			} else {
				thirdCol.add(EMPTY_STRING);
			}
			fourthCol.add(lst.get(i).getDone());

		}

	}

	/**
	 * process detail information of a certain task into several
	 * <code>ArrayList</code>. The first <code>ArrayList</code> is stored in
	 * <em>firstCol</em> The second <code>ArrayList</code> is stored in
	 * <em>secondCol</em>.
	 * 
	 * @param task
	 */
	private void processTaskDetail(Task task) {
		
		assert task != null : "a null task cannot be processed";
		
		String name = task.getName();
		JDate startDate = task.getStartDate();
		JDate endDate = task.getEndDate();
		String descrition = task.getDescription();
		boolean status = task.getDone();

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

		if (status) {
			secondCol.add(STR_DONE);
		} else {
			secondCol.add(STR_UNDDONE);
		}
		secondCol.add(descrition);

		if (info.getHighlight() == Default.HIGHLIGHT_PROPERTY) {
			this.highlightedProperty = info.getHighlightItem();
		}

	}

}
