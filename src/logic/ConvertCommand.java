package logic;

import gui.VIEW_MODE;
import parser.RawCommand;
import CommandType.*;

public class ConvertCommand {
	private static String TASKLIST_TITLE = "Task List";
	private static String BIN_TITLE = "Trash bin";
	private static String DETAIL_TITLE_FORMAT = "Detail of %s";
	
	
	//feedback formats
	private static String CANNOT_FORMAT = "Cannot command %s1 in %2s view mode!";
	private static String INVALID_ARGUMENT_FORMAT = "Invaid argument for %1s: %2s invalid!";
	private static String UNKNOWN = "Invalid Command! Get command information at tutorial!";
	
	private static String SUCCESSFUL_ADD = "New task added successfully!";
	private static String CANNOT_ADD = String.format(CANNOT_FORMAT, "Add", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_ADD_NAME = String.format(INVALID_ARGUMENT_FORMAT, "Add", "task title");
	private static String INVALID_ADD_STARTDATE = String.format(INVALID_ARGUMENT_FORMAT, "Add", "start date");
	private static String INVALID_ADD_ENDDATE = String.format(INVALID_ARGUMENT_FORMAT, "Add", "end date");

	private static String SUCCESSFUL_DELETE = "Task deleted successfully!";
	private static String CANNOT_DELETE = String.format(CANNOT_FORMAT, "Delete", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_DELETE_ITEM = String.format(INVALID_ARGUMENT_FORMAT, "Delete", "delete line");
	
	private static String SUCCESSFUL_READ = "Task details!";
	private static String CANNOT_READ = String.format(CANNOT_FORMAT, "Read", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_READ_ITEM = String.format(INVALID_ARGUMENT_FORMAT, "Read", "Read line");
	
	private static String SUCCESSFUL_RENAME = "Task rename successfully!";
	private static String CANNOT_RENAME = String.format(CANNOT_FORMAT, "Rename", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_RENAME_NAME = String.format(INVALID_ARGUMENT_FORMAT, "Rename", "New name");

	private static String SUCCESSFUL_RESCHDULE = "Task reschedule successfully!";
	private static String CANNOT_RESCHEDULE = String.format(CANNOT_FORMAT, "Reschedule", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_RESCHEDULE_STARTDATE = String.format(INVALID_ARGUMENT_FORMAT, "Reschedule", "Start date");
	private static String INVALID_RESCHEDULE_ENDDATE = String.format(INVALID_ARGUMENT_FORMAT, "Reschedule", "End date");

	private static String SUCCESSFUL_DESCRIBE = "Task describe successfully!";
	private static String CANNOT_DESCRIBE = String.format(CANNOT_FORMAT, "Describe", RunLogic.getGuiStatus().getMode().toString());

	private static String SUCCESSFUL_RESTORE = "Task restore successfully!";
	private static String CANNOT_RESTORE = String.format(CANNOT_FORMAT, "Restore", RunLogic.getGuiStatus().getMode().toString());
	private static String INVALID_RESTORE_ITEM = String.format(INVALID_ARGUMENT_FORMAT, "Restore", "Restore line");

	private static String SUCCESSFUL_VIEW = "View mode changed!";
	private static String INVALID_VIEW_MODE = String.format(INVALID_ARGUMENT_FORMAT, "View", "View mode");

	private static String SUCCESSFUL_NEXT = "Next page!";
	private static String NO_NEXT = "No next page!";
	private static String CANNOT_NEXT = String.format(CANNOT_FORMAT, "Next", RunLogic.getGuiStatus().getMode().toString());
	
	private static String SUCCESSFUL_PREVIOUS = "Next page!";
	private static String NO_PREVIOUS = "No previous page!";
	private static String CANNOT_PREVIOUS = String.format(CANNOT_FORMAT, "Previous", RunLogic.getGuiStatus().getMode().toString());
	
	private static String SUCCESSFUL_BACK = "Back to List!";
	private static String CANNOT_BACK = String.format(CANNOT_FORMAT, "Back", RunLogic.getGuiStatus().getMode().toString());

	public static Command convert(RawCommand command){
		if(command == null){
			return new Invalid(UNKNOWN, null);
		}
		
		if (command.getCommand().equalsIgnoreCase("add")) {
			return convertAdd(command);
		} else if (command.getCommand().equalsIgnoreCase("delete")) {
			return convertDelete(command);
		} else if (command.getCommand().equalsIgnoreCase("read")) {
			return convertRead(command);
		} else if (command.getCommand().equalsIgnoreCase("rename")) {
			return convertRename(command);
		} else if (command.getCommand().equalsIgnoreCase("repeat")) {
			return convertRepeart(command);
		} else if (command.getCommand().equalsIgnoreCase("reschedule")) {
			return convertReschedule(command);
		} else if (command.getCommand().equalsIgnoreCase("describe")) {
			return ConvertDescribe(command);
		} else if (command.getCommand().equalsIgnoreCase("restore")) {
			return convertRestore(command);
		} else if (command.getCommand().equalsIgnoreCase("view")) {
			return convertView(command);
		} else if (command.getCommand().equalsIgnoreCase("undo")) {
			return convertUndo(command);
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
		} else {
			return new Invalid(UNKNOWN, null);
		}
	}

	private static Command convertAdd(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST) || 
				RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)	){
			if(command.getTitle() == null){
				return new Invalid(INVALID_ADD_NAME, null);
			}
			JDate startDate = null;
			JDate endDate = null;

			if(command.getStartDay() != null){
				if(!isInt(command.getStartDay()) || command.getStartDay().length() != Default.LENGTH_OF_DATE_FORMAT){
					return new Invalid(INVALID_ADD_STARTDATE, null);
				}
				startDate = convertDate(command.getStartDay());
			}
			
			if(command.getEndDay() != null){
				if(!isInt(command.getEndDay()) || command.getEndDay().length() != Default.LENGTH_OF_DATE_FORMAT){
					return new Invalid(INVALID_ADD_ENDDATE, null);
				}
				endDate = convertDate(command.getEndDay());
			}
			
			Task task = new Task(command.getTitle(), command.getDescription(), command.getRPdate(), startDate, endDate);
			return new Add(task, SUCCESSFUL_ADD, String.format(DETAIL_TITLE_FORMAT, task.getName()));
		}
		return new Invalid(CANNOT_ADD, null);
	}

	private static Command convertDelete(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
			if(!isInt(command.getCMDDescription())){
				if(command.getCMDDescription().equalsIgnoreCase("all")){
					return new DeleteTaskList(true, SUCCESSFUL_DELETE, TASKLIST_TITLE);
				} else {
					return new Invalid(INVALID_DELETE_ITEM, null);
				}
			}
			
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if(deleteLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[deleteLine] == -1){
				return new Invalid(INVALID_DELETE_ITEM, null);
			}
			
			return new DeleteTaskList(deleteLine, SUCCESSFUL_DELETE, TASKLIST_TITLE);
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				if(command.getCMDDescription().equalsIgnoreCase("all")){
					return new DeleteTrashbin(true, SUCCESSFUL_DELETE, BIN_TITLE);
				} else {
					return new Invalid(INVALID_DELETE_ITEM, null);
				}
			}
			
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if(deleteLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[deleteLine] == -1){
				return new Invalid(INVALID_DELETE_ITEM, null);
			}
			
			return new DeleteTrashbin(deleteLine, SUCCESSFUL_DELETE, BIN_TITLE);
		}
		
		return new Invalid(CANNOT_DELETE, null);
	}

	private static Command convertRead(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(INVALID_READ_ITEM, null);
			}
			
			int readLine = Integer.parseInt(command.getCMDDescription());
			if(readLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[readLine] == -1){
				return new Invalid(INVALID_READ_ITEM, null);
			}
			
			Task task = RunLogic.getTaskList().get(RunLogic.getCurrentDisplay()[readLine]);
			return new ReadTaskList(readLine, SUCCESSFUL_READ, String.format(DETAIL_TITLE_FORMAT, task.getName()));
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(INVALID_READ_ITEM, null);
			}
			
			int readLine = Integer.parseInt(command.getCMDDescription());
			if(readLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[readLine] == -1){
				return new Invalid(INVALID_READ_ITEM, null);
			}
			
			Task task = RunLogic.getTrashbinList().get(RunLogic.getCurrentDisplay()[readLine]);
			return new ReadBin(readLine, SUCCESSFUL_READ, String.format(DETAIL_TITLE_FORMAT, task.getName()));
		}
		
		return new Invalid(CANNOT_READ, null);
	}

	private static Command convertRename(RawCommand command) {
		if(!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)){
			return new Invalid(CANNOT_RENAME, null);
		}
		
		String newName = command.getTitle();
		if(newName == null){
			return new Invalid(INVALID_RENAME_NAME, null);
		}
		
		return new Rename(newName, SUCCESSFUL_RENAME, String.format(DETAIL_TITLE_FORMAT, newName));
	}

	private static Command convertRepeart(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertReschedule(RawCommand command) {
		if(!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)){
			return new Invalid(CANNOT_RESCHEDULE, null);
		}
		
		String newStartDate = command.getStartDay();
		String newEndDate = command.getEndDay();
		
		JDate startDate = null;
		JDate endDate = null;

		if(command.getStartDay() != null){
			if(!isInt(command.getStartDay()) || command.getStartDay().length() != Default.LENGTH_OF_DATE_FORMAT){
				return new Invalid(INVALID_RESCHEDULE_STARTDATE, null);
			}
			startDate = convertDate(newStartDate);
		}
		
		if(command.getEndDay() != null){
			if(!isInt(command.getEndDay()) || command.getEndDay().length() != Default.LENGTH_OF_DATE_FORMAT){
				return new Invalid(INVALID_RESCHEDULE_ENDDATE, null);
			}
			endDate = convertDate(newEndDate);
		}
		
		Task task = RunLogic.getTaskList().get(RunLogic.getGuiStatus().getTaskIndex());
		return new Reschedule(startDate, endDate, SUCCESSFUL_RESCHDULE, String.format(DETAIL_TITLE_FORMAT, task.getName()));
	}

	private static Command ConvertDescribe(RawCommand command) {
		if(!RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)){
			return new Invalid(CANNOT_DESCRIBE, null);
		}
		
		String newDescription = command.getDescription();

		Task task = RunLogic.getTaskList().get(RunLogic.getGuiStatus().getTaskIndex());
		return new Describe(newDescription, SUCCESSFUL_DESCRIBE, String.format(DETAIL_TITLE_FORMAT, task.getName()));
	}

	private static Command convertRestore(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN_DETAIL)){
			Task task = RunLogic.getTrashbinList().get(RunLogic.getGuiStatus().getTaskIndex());
			return new Restore(1, SUCCESSFUL_RESTORE, String.format(DETAIL_TITLE_FORMAT, task.getName()));
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(INVALID_RESTORE_ITEM, null);
			}
			
			int restoreLine = Integer.parseInt(command.getCMDDescription());
			if(restoreLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[restoreLine] == -1){
				return new Invalid(INVALID_RESTORE_ITEM, null);
			}
			
			return new Restore(restoreLine, SUCCESSFUL_RESTORE, BIN_TITLE);
		}
		
		return new Invalid(CANNOT_RESTORE, null);
	}

	private static Command convertView(RawCommand command) {
		String newMode = command.getCMDDescription();
		if(newMode == null){
			return new ViewTaskList(0, SUCCESSFUL_VIEW, TASKLIST_TITLE);
		} else if(newMode.equalsIgnoreCase("tasklist")){
			return new ViewTaskList(0, SUCCESSFUL_VIEW, TASKLIST_TITLE);
		} else if(newMode.equalsIgnoreCase("bin")){
			return new ViewTrashBin(0, SUCCESSFUL_VIEW, BIN_TITLE);
		} else {
			return new Invalid(INVALID_VIEW_MODE, null);
		}
	}

	private static Command convertUndo(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertNext(RawCommand command) {
		if(RunLogic.getGuiStatus().hasNext()){
			if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
				return new Next(SUCCESSFUL_NEXT, TASKLIST_TITLE);
			} else if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){

				return new Next(SUCCESSFUL_NEXT, BIN_TITLE);
			}
			return new Invalid(CANNOT_NEXT, null);
		}
		return new Invalid(NO_NEXT, null);
	}

	private static Command convertPrevious(RawCommand command) {
		if(RunLogic.getGuiStatus().hasPrevious()){
			if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
				return new Previous(SUCCESSFUL_PREVIOUS, TASKLIST_TITLE);
			} else if (RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
				return new Previous(SUCCESSFUL_PREVIOUS, BIN_TITLE);
			}
			return new Invalid(CANNOT_PREVIOUS, null);
		}
		return new Invalid(NO_PREVIOUS, null);
	}

	private static Command convertSearch(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertBack(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)){
			return new Back(SUCCESSFUL_BACK, TASKLIST_TITLE);
		} else if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN_DETAIL)){
			return new Back(SUCCESSFUL_BACK, BIN_TITLE);
		}
		return new Invalid(CANNOT_BACK, null);
	}

	private static Command convertExit(RawCommand command) {
		return new Exit();
	}
	
	
	
	//--------------------Helper Function-------------------------
	private static boolean isInt(String str){
		if(str == null){
			return false;
		}
		
		int len = str.length();
		for(int i = 0; i < len; i++){
			if(str.charAt(i) < 48 || str.charAt(i) > 57){
				return false;
			}
		}
		
		return true;
	}
	
	private static JDate convertDate(String date){
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		return new JDate(year, month, day);
	}
}
