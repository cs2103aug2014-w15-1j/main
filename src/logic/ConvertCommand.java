package logic;

import gui.VIEW_MODE;
import parser.RawCommand;
import CommandType.*;

public class ConvertCommand {
	public static Command convert(RawCommand command){
		if(command == null){
			return new Invalid(Default.UNKNOWN_COMMAND);
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
			return new Invalid(Default.UNKNOWN_COMMAND);
		}
	}

	private static Command convertAdd(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST) || 
				RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_DETAIL)	){
			if(command.getTitle().equals(null)){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Add", "task title"));
			}
			JDate startDate = null;
			JDate endDate = null;

			if(!command.getStartDay().equals(null)){
				if(!isInt(command.getStartDay()) || command.getStartDay().length() != Default.LENGTH_OF_DATE_FORMAT){
					return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Add", "start date"));
				}
				startDate = convertDate(command.getStartDay());
			}
			
			if(!command.getEndDay().equals(null)){
				if(!isInt(command.getEndDay()) || command.getEndDay().length() != Default.LENGTH_OF_DATE_FORMAT){
					return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Add", "end date"));
				}
				endDate = convertDate(command.getEndDay());
			}
			
			Task task = new Task(command.getTitle(), command.getDescription(), command.getRPdate(), startDate, endDate);
			return new Add(task);
		}
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Add", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertDelete(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
			if(!isInt(command.getCMDDescription())){
				if(command.getCMDDescription().equalsIgnoreCase("all")){
					return new DeleteTaskList(true);
				} else {
					return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Delete", "delete line"));
				}
			}
			
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if(deleteLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[deleteLine] == -1){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Delete", "delete line"));
			}
			
			return new DeleteTaskList(deleteLine);
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				if(command.getCMDDescription().equalsIgnoreCase("all")){
					return new DeleteTrashbin(true);
				} else {
					return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Delete", "delete line"));
				}
			}
			
			int deleteLine = Integer.parseInt(command.getCMDDescription());
			if(deleteLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[deleteLine] == -1){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Delete", "delete line"));
			}
			
			return new DeleteTrashbin(deleteLine);
		}
		
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Delete", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertRead(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.TASK_LIST)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Read", "read line"));
			}
			
			int readLine = Integer.parseInt(command.getCMDDescription());
			if(readLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[readLine] == -1){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Read", "read line"));
			}
			
			return new ReadTaskList(readLine);
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Read", "read line"));
			}
			
			int readLine = Integer.parseInt(command.getCMDDescription());
			if(readLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[readLine] == -1){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Read", "read line"));
			}
			
			return new ReadBin(readLine);
		}
		
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Read", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertRename(RawCommand command) {
		String newName = command.getTitle();
		if(newName.equals(null)){
			return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Rename", "Name"));
		}
		return new Rename(newName);
	}

	private static Command convertRepeart(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertReschedule(RawCommand command) {
		String newStartDate = command.getStartDay();
		String newEndDate = command.getEndDay();
		
		JDate startDate = null;
		JDate endDate = null;

		if(!command.getStartDay().equals(null)){
			if(!isInt(command.getStartDay()) || command.getStartDay().length() != Default.LENGTH_OF_DATE_FORMAT){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Reschedule", "start date"));
			}
			startDate = convertDate(newStartDate);
		}
		
		if(!command.getEndDay().equals(null)){
			if(!isInt(command.getEndDay()) || command.getEndDay().length() != Default.LENGTH_OF_DATE_FORMAT){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Reschedule", "end date"));
			}
			endDate = convertDate(newEndDate);
		}
		
		return new Reschedule(startDate, endDate);
	}

	private static Command ConvertDescribe(RawCommand command) {
		String newDescription = command.getDescription();
		if(newDescription.equals(null)){
			return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Describe", "Description"));
		}
		return new Describe(newDescription);
	}

	private static Command convertRestore(RawCommand command) {
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN_DETAIL)){
			return new Restore(1);
		}
		
		if(RunLogic.getGuiStatus().getMode().equals(VIEW_MODE.BIN)){
			if(!isInt(command.getCMDDescription())){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Restore", "restore line"));
			}
			
			int restoreLine = Integer.parseInt(command.getCMDDescription());
			if(restoreLine > Default.MAX_DISPLAY_LINE || RunLogic.getCurrentDisplay()[restoreLine] == -1){
				return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "Restore", "restore line"));
			}
			
			return new Restore(restoreLine);
		}
		
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Restore", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertView(RawCommand command) {
		String newMode = command.getCMDDescription();
		if(newMode.equals(null)){
			return new ViewTaskList(0);
		} else if(newMode.equalsIgnoreCase("tasklist")){
			return new ViewTaskList(0);
		} else if(newMode.equalsIgnoreCase("bin")){
			return new ViewTrashBin(0);
		} else {
			return new Invalid(String.format(Default.INVALID_ARGUMENT_FORMAT, "view", "view mode"));
		}
	}

	private static Command convertUndo(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertNext(RawCommand command) {
		if(RunLogic.getGuiStatus().hasNext()){
			return new Next();
		}
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Next", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertPrevious(RawCommand command) {
		if(RunLogic.getGuiStatus().hasPrevious()){
			return new Previous();
		}
		return new Invalid(String.format(Default.CANNOT_FORMAT, "Previous", RunLogic.getGuiStatus().getMode().toString()));
	}

	private static Command convertSearch(RawCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command convertBack(RawCommand command) {
		return new Back();
	}

	private static Command convertExit(RawCommand command) {
		return new Exit();
	}
	
	
	
	//--------------------Helper Function-------------------------
	private static boolean isInt(String str){
		if(str.equals(null)){
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
