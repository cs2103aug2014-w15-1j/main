package logic;

import java.util.ArrayList;

public class RunLogic {
	private static GUIStatus GUI;
	private static LogicToGui passToGui;
	private static LogicToStore passToStore;
	private static ArrayList<String> taskList;
	private static ArrayList<String> trashbinList;
	
	enum COMMAND_TYPE {
		ADD_TASK, DELETE_TASK, READ_TASK, 
		RENAME, DESCRIBE, RESCHEDULE, REPEAT, 
		RESTORE, VIEW_MODE, UNDO, SEARCH, EXIT, INVALID
	};
	
	public static void Logic(CliToLogic CLI){
		CliToLogic userCommand = CLI;
		if(checkValid(userCommand)){
			executeCommand(userCommand);
		} else {
			wrongCommand(userCommand);
		};
	}
	
	private static boolean checkValid(CliToLogic userCommand){
		String command = userCommand.getCommand();
		switch(GUI.getMode()){
		case 0:
			return (command.equalsIgnoreCase("add") 
					|| command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit"));
		case 1:
			return (command.equalsIgnoreCase("rename") 
					|| command.equalsIgnoreCase("describe")
					|| command.equalsIgnoreCase("repeat") 
					|| command.equalsIgnoreCase("reschedule")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit"));
		case 2:
			return  (command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("exit"));
		case 3:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("restore")
					|| command.equalsIgnoreCase("exit"));
		case 4:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("exit"));
		default:
			return false;
		}
	}
	
	private static void executeCommand(CliToLogic userCommand){
		COMMAND_TYPE commandType = determineCommandType(userCommand.getCommand());
		
		switch (commandType) {
			case ADD_TASK:
				addTask(userCommand);
				break;
			case DELETE_TASK:
				deleteTask(userCommand);
				break;
			case READ_TASK:
				readTask(userCommand);
				break;
			case RENAME:
				rename(userCommand);
				break;
			case DESCRIBE:
				describe(userCommand);
				break;
			case REPEAT:
				repeat(userCommand);
				break;
			case RESCHEDULE:
				reschedule(userCommand);
				break;
			case VIEW_MODE:
				view(userCommand);
				break;
			case UNDO:
				undo();
				break;
			case RESTORE:
				restore(userCommand);
				break;
			case SEARCH:
				search(userCommand);
				break;
			case EXIT:
				System.exit(0);
			default:
				//throw an error if the command is not recognized
				throw new Error("Unrecognized command type");
		}
	}
	

	private static void addTask(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void deleteTask(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void readTask(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void rename(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void describe(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void repeat(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void reschedule(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void view(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void undo() {
		// TODO Auto-generated method stub
		
	}

	private static void restore(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void search(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void wrongCommand(CliToLogic userCommand){
		
	}
	
	// This method determine which command the user want to use
	private static COMMAND_TYPE determineCommandType(String commandTypeString) {
		if (commandTypeString.equalsIgnoreCase("add")) {
			return COMMAND_TYPE.ADD_TASK;
		} else if (commandTypeString.equalsIgnoreCase("delete")) {
			return COMMAND_TYPE.DELETE_TASK;
		} else if (commandTypeString.equalsIgnoreCase("read")) {
			return COMMAND_TYPE.READ_TASK;
		} else if (commandTypeString.equalsIgnoreCase("rename")) {
			return COMMAND_TYPE.RENAME;
		} else if (commandTypeString.equalsIgnoreCase("repeat")) {
			return COMMAND_TYPE.REPEAT;
		} else if (commandTypeString.equalsIgnoreCase("reschedule")) {
			return COMMAND_TYPE.RESCHEDULE;
		} else if (commandTypeString.equalsIgnoreCase("describe")) {
			return COMMAND_TYPE.DESCRIBE;
		} else if (commandTypeString.equalsIgnoreCase("restore")) {
			return COMMAND_TYPE.RESTORE;
		} else if (commandTypeString.equalsIgnoreCase("view")) {
			return COMMAND_TYPE.VIEW_MODE;
		} else if (commandTypeString.equalsIgnoreCase("undo")) {
			return COMMAND_TYPE.UNDO;
		} else if (commandTypeString.equalsIgnoreCase("search")) {
			return COMMAND_TYPE.SEARCH;
		} else if (commandTypeString.equalsIgnoreCase("exit")) {
			return COMMAND_TYPE.EXIT;
		} else {
			return COMMAND_TYPE.INVALID;
		}
	}
}
