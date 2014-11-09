package logic;

import parser.RawCommand;
import CommandType.*;

public class ConvertCommand {
	// title formats
	private static String TASKLIST_TITLE = "Task List";
	private static String BIN_TITLE = "Trash bin";
	private static String UNDONE_TITLE = "Undone Tasks";
	public static String DETAIL_TITLE_FORMAT = "Detail of %s";
	private static String SEARCH_TITLE = "Search result of %s";

	// UNDO messages
	public static String UNDO_MARK = "Undo Mark Successful";
	public static String UNDO_DESCRIBE = "Undo Describe Successful";
	public static String UNDO_RENAME = "Undo Rename Successful";
	public static String UNDO_RESCHEDULE = "Undo Reschedule Successful";
	public static String UNDO_RESTORE = "Undo Restore Successful";

	// feedback formats
	private static String CANNOT_FORMAT = "Cannot command %s in current view mode!";
	private static String INVALID_ARGUMENT_FORMAT = "Invaid argument for %s: %s invalid!";
	private static String UNKNOWN = "Invalid Command!";

	private static String SUCCESSFUL_ADD = "New task added successfully!";
	private static String CANNOT_ADD = String.format(CANNOT_FORMAT, "Add");
	private static String INVALID_ADD_NAME = String.format(
			INVALID_ARGUMENT_FORMAT, "Add", "task title");
	private static String INVALID_ADD_STARTDATE = String.format(
			INVALID_ARGUMENT_FORMAT, "Add", "start date");
	private static String INVALID_ADD_ENDDATE = String.format(
			INVALID_ARGUMENT_FORMAT, "Add", "end date");

	private static String SUCCESSFUL_DELETE = "Task deleted successfully!";
	private static String CANNOT_DELETE = String
			.format(CANNOT_FORMAT, "Delete");
	private static String INVALID_DELETE_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Delete", "delete line");

	private static String SUCCESSFUL_READ = "Task details!";
	private static String CANNOT_READ = String.format(CANNOT_FORMAT, "Read");
	private static String INVALID_READ_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Read", "Read line");

	private static String SUCCESSFUL_RENAME = "Task rename successfully!";
	private static String CANNOT_RENAME = String
			.format(CANNOT_FORMAT, "Rename");
	private static String INVALID_RENAME_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Rename", "Update line");
	private static String INVALID_RENAME_NAME = String.format(
			INVALID_ARGUMENT_FORMAT, "Rename", "New name");

	private static String SUCCESSFUL_RESCHDULE = "Task reschedule successfully!";
	private static String CANNOT_RESCHEDULE = String.format(CANNOT_FORMAT,
			"Reschedule");
	private static String INVALID_RESCHEDULE_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Reschedule", "Update line");
	private static String INVALID_RESCHEDULE_STARTDATE = String.format(
			INVALID_ARGUMENT_FORMAT, "Reschedule", "Start date");
	private static String INVALID_RESCHEDULE_ENDDATE = String.format(
			INVALID_ARGUMENT_FORMAT, "Reschedule", "End date");

	private static String SUCCESSFUL_DESCRIBE = "Task describe successfully!";
	private static String INVALID_DESCRIBE_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Describe", "Update line");
	private static String CANNOT_DESCRIBE = String.format(CANNOT_FORMAT,
			"Describe");

	private static String SUCCESSFUL_MARK = "Task Mark successfully!";
	private static String INVALID_MARK_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Mark", "Update line");
	private static String CANNOT_MARK = String.format(CANNOT_FORMAT, "Mark");

	private static String SUCCESSFUL_RESTORE = "Task restore successfully!";
	private static String CANNOT_RESTORE = String.format(CANNOT_FORMAT,
			"Restore");
	private static String INVALID_RESTORE_ITEM = String.format(
			INVALID_ARGUMENT_FORMAT, "Restore", "Restore line");

	private static String SUCCESSFUL_VIEW = "View mode changed!";
	private static String INVALID_VIEW_MODE = String.format(
			INVALID_ARGUMENT_FORMAT, "View", "View mode");

	private static String SUCCESSFUL_NEXT = "Next page!";
	private static String NO_NEXT = "No next page!";
	private static String CANNOT_NEXT = String.format(CANNOT_FORMAT, "Next");

	private static String SUCCESSFUL_PREVIOUS = "Previous page!";
	private static String NO_PREVIOUS = "No previous page!";
	private static String CANNOT_PREVIOUS = String.format(CANNOT_FORMAT,
			"Previous");

	private static String SUCCESSFUL_BACK = "Back to List!";
	private static String CANNOT_BACK = String.format(CANNOT_FORMAT, "Back");

	private static String SUCCESSFUL_SEARCH = "Search Results!";
	private static String INVALID_KEYWORD = String.format(
			INVALID_ARGUMENT_FORMAT, "Search", "Key word");
	private static String SUCCESSFUL_VIEW_DATE = "Search result!";
	
	private static String HELP_FEEDBACK = "All the valid command format!";
	private static String HELP_TITLE = "Helper";

	// some command format
	private static String DELETE_ALL = "all";

	public static Command convert(RawCommand command) {
		if (command == null) {
			return new Invalid(UNKNOWN);
		}

		if (command.getCommand().equalsIgnoreCase("add")) {
			int nextTaskPointer = RunLogic.getNextTaskPointer();
			RunLogic.incrementnextTaskPointer();
			return convertAdd(command, nextTaskPointer);
		} else if (command.getCommand().equalsIgnoreCase("delete")) {
			return convertDelete(command);
		} else if (command.getCommand().equalsIgnoreCase("read")) {
			return convertRead(command);
		} else if (command.getCommand().equalsIgnoreCase("rename")) {
			return convertRename(command);
		} else if (command.getCommand().equalsIgnoreCase("reschedule")) {
			return convertReschedule(command);
		} else if (command.getCommand().equalsIgnoreCase("describe")) {
			return ConvertDescribe(command);
		} else if (command.getCommand().equalsIgnoreCase("mark")) {
			return ConvertMark(command);
		} else if (command.getCommand().equalsIgnoreCase("restore")) {
			return convertRestore(command);
		} else if (command.getCommand().equalsIgnoreCase("view")) {
			return convertView(command);
		} else if (command.getCommand().equalsIgnoreCase("viewdate")) {
			return convertViewDate(command);
		} else if (command.getCommand().equalsIgnoreCase("undo")) {
			return convertUndo();
		} else if (command.getCommand().equalsIgnoreCase("next")) {
			return convertNext(command);
		} else if (command.getCommand().equalsIgnoreCase("previous")) {
			return convertPrevious(command);
		} else if (command.getCommand().equalsIgnoreCase("search")) {
			return convertSearch(command);
		} else if (command.getCommand().equalsIgnoreCase("back")) {
			return convertBack(command);
		} else if (command.getCommand().equalsIgnoreCase("exit")) {
			return convertExit(command);
		} else if (command.getCommand().equalsIgnoreCase("help")) {
			return convertHelp();
		} else {
			return new Invalid(UNKNOWN);
		}
	}

	private static Command convertAdd(RawCommand command, int taskPointer) {
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)
				|| RunLogic.getGuiStatus().getMode()
						.equals(VIEW_MODE.TASK_DETAIL)) {
			// must have a task name
			if (command.getTitle() == null) {
				return new Invalid(INVALID_ADD_NAME);
			}

			// must have correct date format or have no date
			JDate startDate = null;
			JDate endDate = null;

			if (command.getStartDay() != null) {
				if (!checkDateformat(command.getStartDay())) {
					return new Invalid(INVALID_ADD_STARTDATE);
				}
				startDate = convertDate(command.getStartDay());
				if (startDate == null) {
					return new Invalid(INVALID_ADD_STARTDATE);
				}
			}

			if (command.getEndDay() != null) {
				if (!checkDateformat(command.getEndDay())) {
					return new Invalid(INVALID_ADD_ENDDATE);
				}
				endDate = convertDate(command.getEndDay());
				if (endDate == null) {
					return new Invalid(INVALID_ADD_ENDDATE);
				}
			}

			// construct the Task object
			Task task = new Task(command.getTitle(), command.getDescription(),
					startDate, endDate);
			task.setPointer(taskPointer);
			return new Add(task, SUCCESSFUL_ADD, String.format(
					DETAIL_TITLE_FORMAT, task.getName()));
		}

		// command add in wrong view mode
		return new Invalid(CANNOT_ADD);
	}

	private static Command convertDelete(RawCommand command) {
		// delete in task list
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			// delete all
			if (!isInt(command.getCMDDescription())) {
				if (command.getCMDDescription().equalsIgnoreCase(DELETE_ALL)) {
					return new DeleteTaskList(true, SUCCESSFUL_DELETE,
							TASKLIST_TITLE);
				} else {
					return new Invalid(INVALID_DELETE_ITEM);
				}
			}

			// delete certain line
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if (checkLineExist(deleteLine)) {
				return new Invalid(INVALID_DELETE_ITEM);
			}

			return new DeleteTaskList(deleteLine, SUCCESSFUL_DELETE,
					TASKLIST_TITLE);
		}

		// delete line in trash bin
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
			// delete all
			if (!isInt(command.getCMDDescription())) {
				if (command.getCMDDescription().equalsIgnoreCase(DELETE_ALL)) {
					return new DeleteTrashbin(true, SUCCESSFUL_DELETE,
							BIN_TITLE);
				} else {
					return new Invalid(INVALID_DELETE_ITEM);
				}
			}

			// delete certain line
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if (checkLineExist(deleteLine)) {
				return new Invalid(INVALID_DELETE_ITEM);
			}

			return new DeleteTrashbin(deleteLine, SUCCESSFUL_DELETE, BIN_TITLE);
		}

		// command delete in wrong view mode
		return new Invalid(CANNOT_DELETE);
	}

	private static Command convertRead(RawCommand command) {
		// if reading item dose not exist, return invalid
		if (!isInt(command.getCMDDescription())) {
			return new Invalid(INVALID_READ_ITEM);
		}
		int readLine = Integer.parseInt(command.getCMDDescription());
		if (checkLineExist(readLine)) {
			return new Invalid(INVALID_READ_ITEM);
		}

		// read in task list
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			Task task = RunLogic.getTaskList().get(getTaskWithLine(readLine));
			return new ReadTaskList(readLine, SUCCESSFUL_READ, String.format(
					DETAIL_TITLE_FORMAT, task.getName()));
		}

		// read in trash bin
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
			Task task = RunLogic.getTrashbinList().get(
					getTaskWithLine(readLine));
			return new ReadBin(readLine, SUCCESSFUL_READ, String.format(
					DETAIL_TITLE_FORMAT, task.getName()));
		}

		// command read in wrong view mode
		return new Invalid(CANNOT_READ);
	}

	private static Command convertRename(RawCommand command) {
		// rename in task list mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			// if updating item dose not exist, return invalid
			if (!isInt(command.getCMDDescription())) {
				return new Invalid(INVALID_RENAME_ITEM);
			}
			int readLine = Integer.parseInt(command.getCMDDescription());
			if (checkLineExist(readLine)) {
				return new Invalid(INVALID_RENAME_ITEM);
			}

			// must have a new task name
			String newName = command.getTitle();
			if (newName == null) {
				return new Invalid(INVALID_RENAME_NAME);
			}

			return new Rename(readLine, newName, SUCCESSFUL_RENAME,
					String.format(DETAIL_TITLE_FORMAT, newName));
		}

		// command rename in wrong view mode
		if (!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)) {
			return new Invalid(CANNOT_RENAME);
		}

		// rename in task detail mode
		// must have a new name
		if (command.getCMDDescription() == null && command.getTitle() == null) {
			return new Invalid(INVALID_RENAME_NAME);
		}
		String newName = command.getCMDDescription();
		if (newName == null) {
			newName = command.getTitle();
		} else {
			newName = newName.concat(" ").concat(command.getTitle());
		}

		return new Rename(newName, SUCCESSFUL_RENAME, String.format(
				DETAIL_TITLE_FORMAT, newName));
	}

	private static Command convertReschedule(RawCommand command) {
		String newStartDate = command.getStartDay();
		String newEndDate = command.getEndDay();

		JDate startDate = null;
		JDate endDate = null;

		// must have date in correct format or have no date
		if (command.getStartDay() != null) {
			if (!checkDateformat(command.getStartDay())) {
				return new Invalid(INVALID_RESCHEDULE_STARTDATE);
			}
			startDate = convertDate(newStartDate);
		}

		if (command.getEndDay() != null) {
			if (!checkDateformat(command.getEndDay())) {
				return new Invalid(INVALID_RESCHEDULE_ENDDATE);
			}
			endDate = convertDate(newEndDate);
		}

		// reschedule in task list mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			// if updating item dose not exist, return invalid
			if (!isInt(command.getCMDDescription())) {
				return new Invalid(INVALID_RESCHEDULE_ITEM);
			}
			int readLine = Integer.parseInt(command.getCMDDescription());
			if (checkLineExist(readLine)) {
				return new Invalid(INVALID_RESCHEDULE_ITEM);
			}

			Task task = RunLogic.getTaskList().get(getTaskWithLine(readLine));
			return new Reschedule(readLine, startDate, endDate,
					SUCCESSFUL_RENAME, String.format(DETAIL_TITLE_FORMAT,
							task.getName()));
		}

		// command reschedule in wrong view mode
		if (!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)) {
			return new Invalid(CANNOT_RESCHEDULE);
		}

		// command reschedule in task detail mode
		Task task = RunLogic.getTaskList().get(
				RunLogic.getGuiStatus().getTaskIndex());
		return new Reschedule(startDate, endDate, SUCCESSFUL_RESCHDULE,
				String.format(DETAIL_TITLE_FORMAT, task.getName()));
	}

	private static Command ConvertDescribe(RawCommand command) {
		String newDescription = command.getDescription();

		// describe in task list view mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			// if updating item dose not exist, return invalid
			if (!isInt(command.getCMDDescription())) {
				System.out.println(command.getCMDDescription());
				return new Invalid(INVALID_DESCRIBE_ITEM);
			}
			int readLine = Integer.parseInt(command.getCMDDescription());
			if (!checkLineExist(readLine)) {
				return new Invalid(INVALID_DESCRIBE_ITEM);
			}
			Task task = RunLogic.getTaskList().get(getTaskWithLine(readLine));

			return new Describe(readLine, newDescription, SUCCESSFUL_DESCRIBE,
					String.format(DETAIL_TITLE_FORMAT, task.getName()));
		}

		// command describe in wrong view mode
		if (!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)) {
			return new Invalid(CANNOT_DESCRIBE);
		}

		// describe in task detail mode
		if (newDescription == null) {
			newDescription = command.getCMDDescription();
		} else if (command.getDescription() != null) {
			newDescription = command.getCMDDescription() + " " + command.getDescription();
		}
		Task task = RunLogic.getTaskList().get(
				RunLogic.getGuiStatus().getTaskIndex());
		return new Describe(newDescription, SUCCESSFUL_DESCRIBE, String.format(
				DETAIL_TITLE_FORMAT, task.getName()));
	}

	private static Command ConvertMark(RawCommand command) {
		// mark in task list mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
			// if updating item dose not exist, return invalid
			if (!isInt(command.getCMDDescription())) {
				return new Invalid(INVALID_MARK_ITEM);
			}
			int readLine = Integer.parseInt(command.getCMDDescription());
			if (checkLineExist(readLine)) {
				return new Invalid(INVALID_MARK_ITEM);
			}
			Task task = RunLogic.getTaskList().get(getTaskWithLine(readLine));

			boolean status = convertStatus(command.getDescription(), task);

			return new Mark(readLine, status, SUCCESSFUL_MARK, String.format(
					DETAIL_TITLE_FORMAT, task.getName()));
		}

		// command mark in wrong view mode
		if (!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)) {
			return new Invalid(CANNOT_MARK);
		}

		// mark in task detail mode
		Task task = RunLogic.getTaskList().get(
				RunLogic.getGuiStatus().getTaskIndex());
		boolean status = convertStatus(command.getDescription(), task);
		return new Mark(status, SUCCESSFUL_MARK, String.format(
				DETAIL_TITLE_FORMAT, task.getName()));
	}

	private static Command convertRestore(RawCommand command) {
		// restore in trash bin task detail mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN_DETAIL)) {
			Task task = RunLogic.getTrashbinList().get(
					RunLogic.getGuiStatus().getTaskIndex());
			return new Restore(1, SUCCESSFUL_RESTORE, String.format(
					DETAIL_TITLE_FORMAT, task.getName()));
		}

		// restore in trash bin mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
			// if restore line dose not exist, return invalid
			if (!isInt(command.getCMDDescription())) {
				return new Invalid(INVALID_RESTORE_ITEM);
			}
			int restoreLine = Integer.parseInt(command.getCMDDescription());
			if (!checkLineExist(restoreLine)) {
				return new Invalid(INVALID_RESTORE_ITEM);
			}

			return new Restore(restoreLine, SUCCESSFUL_RESTORE, BIN_TITLE);
		}

		// command restore in wrong view mode
		return new Invalid(CANNOT_RESTORE);
	}

	private static Command convertView(RawCommand command) {
		String newMode = command.getCMDDescription();

		if (newMode == null) {
			RunLogic.updateCurrentListIndex(updateListIndexOfTaskList(RunLogic
					.getCurrentListIndex().length));
			return new ViewTaskList(0, SUCCESSFUL_VIEW, TASKLIST_TITLE);
		} else if (newMode.equalsIgnoreCase("tasklist")) {
			RunLogic.updateCurrentListIndex(updateListIndexOfTaskList(RunLogic
					.getCurrentListIndex().length));
			return new ViewTaskList(0, SUCCESSFUL_VIEW, TASKLIST_TITLE);
		} else if (newMode.equalsIgnoreCase("bin")) {
			RunLogic.updateCurrentListIndex(updateListIndexOfTrashBin(RunLogic
					.getCurrentListIndex().length));
			return new ViewTrashBin(0, SUCCESSFUL_VIEW, BIN_TITLE);
		} else if (newMode.equalsIgnoreCase("undone")) {
			return new ViewUndone(SUCCESSFUL_VIEW, UNDONE_TITLE);
		} else {
			return new Invalid(INVALID_VIEW_MODE);
		}
	}

	private static Command convertViewDate(RawCommand command) {
		String rawDate = command.getCMDDescription();
		// if date is in wrong format or has no date, return invalid
		if (rawDate == null) {
			return new Invalid(INVALID_VIEW_MODE);
		} else if (!isInt(rawDate)) {
			return new Invalid(INVALID_VIEW_MODE);
		} else {
			return new ViewDate(convertDate(rawDate), SUCCESSFUL_VIEW_DATE,
					String.format(SEARCH_TITLE, rawDate));
		}
	}

	private static Command convertNext(RawCommand command) {
		if (RunLogic.getGuiStatus().hasNext()) {
			if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
				return new Next(SUCCESSFUL_NEXT, TASKLIST_TITLE);
			} else if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
				return new Next(SUCCESSFUL_NEXT, BIN_TITLE);
			}
			// command next in wrong view mode
			return new Invalid(CANNOT_NEXT);
		}
		// has no next page
		return new Invalid(NO_NEXT);
	}

	private static Command convertPrevious(RawCommand command) {
		if (RunLogic.getGuiStatus().hasPrevious()) {
			if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)) {
				return new Previous(SUCCESSFUL_PREVIOUS, TASKLIST_TITLE);
			} else if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
				return new Previous(SUCCESSFUL_PREVIOUS, BIN_TITLE);
			}
			// command next in wrong view mode
			return new Invalid(CANNOT_PREVIOUS);
		}
		// has no next page
		return new Invalid(NO_PREVIOUS);
	}

	private static Command convertSearch(RawCommand command) {
		String keyWord = command.getCMDDescription();

		// must have a valid keyword
		if (keyWord == null) {
			return new Invalid(INVALID_KEYWORD);
		}

		// if view mode is trash bin or trash bin task detail, search in trash bin; 
		// otherwise search in task list
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN_DETAIL)) {
			return new SearchTrashBin(keyWord, SUCCESSFUL_SEARCH,
					String.format(SEARCH_TITLE, keyWord));
		} else if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)) {
			return new SearchTrashBin(keyWord, SUCCESSFUL_SEARCH,
					String.format(SEARCH_TITLE, keyWord));
		} else {
			return new SearchTaskList(keyWord, SUCCESSFUL_SEARCH,
					String.format(SEARCH_TITLE, keyWord));
		}
	}

	private static Command convertBack(RawCommand command) {
		// task detail mode will back to task list mode
		// trash bin task detail mode will back trash bin mode
		if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)) {
			return new Back(SUCCESSFUL_BACK, TASKLIST_TITLE);
		} else if (RunLogic.getGuiStatus().getMode()
				.equals(VIEW_MODE.BIN_DETAIL)) {
			return new Back(SUCCESSFUL_BACK, BIN_TITLE);
		}
		// command back in wrong view mode
		return new Invalid(CANNOT_BACK);
	}

	private static Command convertExit(RawCommand command) {
		return new Exit();
	}

	private static Command convertUndo() {
		return new Undo();
	}
	
	private static Command convertHelp() {
		return new Helper(HELP_FEEDBACK, HELP_TITLE);
	}

	// --------------------Helper Function-------------------------
	private static boolean isInt(String str) {
		if (str == null || str == "") {
			return false;
		}

		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (str.charAt(i) < 48 || str.charAt(i) > 57) {
				return false;
			}
		}

		return true;
	}

	private static int[] updateListIndexOfTaskList(int length) {
		int[] temp = new int[length];
		for (int i = 0; i < RunLogic.getTaskList().size(); i++) {
			temp[i] = i;
		}
		for (int i = RunLogic.getTaskList().size(); i < length; i++) {
			temp[i] = -1;
		}
		return temp;
	}

	private static int[] updateListIndexOfTrashBin(int length) {
		int[] temp = new int[length];
		for (int i = 0; i < RunLogic.getTrashbinList().size(); i++) {
			temp[i] = i;
		}
		for (int i = RunLogic.getTrashbinList().size(); i < length; i++) {
			temp[i] = -1;
		}
		return temp;
	}

	private static boolean checkDateformat(String date) {
		return isInt(date) && date.length() == Default.LENGTH_OF_DATE_FORMAT;
	}

	private static JDate convertDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));

		// check month valid
		if (month < 1 || month > 12) {
			return null;
		}

		int day = Integer.parseInt(date.substring(6, 8));

		// check day valid
		if ((day < 1) || (day > 31)) {
			return null;
		} else if (day == 29) {
			if ((month == 2) && ((year % 4 != 0) || (year % 400 == 0))) {
				return null;
			}
		} else if (day == 30) {
			if (month == 2) {
				return null;
			}
		} else if (day == 31) {
			if ((month == 2) || (month == 4) || (month == 6) || (month == 9)
					|| (month == 11)) {
				return null;
			}
		}
		return new JDate(year, month - 1, day);
	}

	private static boolean checkLineExist(int line) {
		return line > Default.MAX_DISPLAY_LINE || line <= 0
				|| RunLogic.getCurrentDisplay()[line] == -1;
	}

	private static int getTaskWithLine(int line) {
		return RunLogic.getCurrentListIndex()[RunLogic.getCurrentDisplay()[line]];
	}

	private static boolean convertStatus(String newStatus, Task task) {
		if (newStatus == null) {
			return !task.getDone();
		} else if (newStatus.equalsIgnoreCase("done")) {
			return true;
		} else if (newStatus.equalsIgnoreCase("undone")) {
			return false;
		} else {
			return !task.getDone();
		}
	}
}
