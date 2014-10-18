package logic;

import parser.RawCommand;
import gui.VIEW_MODE;

public class CheckCommandValid {
	// check whether the command is valid under current view mode
	public static boolean checkValid(RawCommand userCommand) {
		String command = userCommand.getCommand();
		VIEW_MODE mode = RunLogic.getGuiStatus().getMode();

		switch (mode) {
		case TASK_LIST:
			return (command.equalsIgnoreCase("add")
					|| command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit")
					|| (command.equalsIgnoreCase("next") && RunLogic.getGuiStatus().hasNext()) 
					|| (command.equalsIgnoreCase("previous") && RunLogic.getGuiStatus().hasPrevious()));
		case DATE:
			return (command.equalsIgnoreCase("add")
					|| command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit")
					|| (command.equalsIgnoreCase("next") && RunLogic.getGuiStatus().hasNext()) 
					|| (command.equalsIgnoreCase("previous") && RunLogic.getGuiStatus().hasPrevious()));
		case TASK_DETAIL:
			return (command.equalsIgnoreCase("add")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("rename")
					|| command.equalsIgnoreCase("describe")
					|| command.equalsIgnoreCase("repeat")
					|| command.equalsIgnoreCase("reschedule")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search") 
					|| command.equalsIgnoreCase("exit")
					|| command.equalsIgnoreCase("back"));
		case MONTH:
			return (command.equalsIgnoreCase("view") 
					|| command.equalsIgnoreCase("exit"));
		case BIN:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("restore")
					|| command.equalsIgnoreCase("exit")
					|| (command.equalsIgnoreCase("next") && RunLogic.getGuiStatus().hasNext()) 
					|| (command.equalsIgnoreCase("previous") && RunLogic.getGuiStatus().hasPrevious()));
		case BIN_DETAIL:
			return (command.equalsIgnoreCase("restore")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("back")
					|| command.equalsIgnoreCase("exit"));
		case UNDONE:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read")
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("exit")
					|| (command.equalsIgnoreCase("next") && RunLogic.getGuiStatus().hasNext()) 
					|| (command.equalsIgnoreCase("previous") && RunLogic.getGuiStatus().hasPrevious()));
		default:
			return false;
		}
	}
	
	public static boolean checkCorrect(RawCommand command){
		
		
		return false;
	}
	

}
