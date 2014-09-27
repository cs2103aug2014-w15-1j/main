package logic;

import java.util.ArrayList;

import TextBuddy.COMMAND_TYPE;

public class RunLogic {
	private static GUIStatus GUI;
	private static LogicToGui passToGui;
	private static LogicToStore passToStore;
	private static CliToLogic userCommand;
	private static ArrayList<String> taskList;
	private static ArrayList<String> trashbinList;
	private static ArrayList<String> undonebinList;
	private static ArrayList<String> undoList;
	
	public static int LIST_VIEW_MODE = 0;
	public static int TASK_VIEW_MODE = 1;
	public static int CALENDAR_VIEW_MODE = 2;
	public static int TRASHBIN_VIEW_MODE = 3;
	public static int UNDONEBIN_VIEW_MODE = 4;
	
	enum COMMAND_TYPE {
		ADD_TASK, DELETE_TASK, READ_TASK, 
		RENAME, DESCRIBE, RESCHEDULE, REPEAT, 
		RESTORE, VIEW_MODE, UNDO, SEARCH, EXIT, INVALID
	};
	
	public static void Logic(CliToLogic CLI){
		userCommand = CLI;
		if(checkValid()){
			executeCommand();
		} else {
			wrongCommand();
		};
		StoreintoFile(passToStore);
		ShowInGui(passToGui);
	}
	
	private static boolean checkValid(){
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
	
	private static void executeCommand(){
		COMMAND_TYPE commandType = determineCommandType(userCommand.getCommand());
		
		switch (commandType) {
			case ADD_TASK:
				addTask();
				break;
			case DELETE_TASK:
				deleteTask();
				break;
			case READ_TASK:
				readTask();
				break;
			case RENAME:
				rename();
				break;
			case DESCRIBE:
				describe();
				break;
			case REPEAT:
				repeat();
				break;
			case RESCHEDULE:
				reschedule();
				break;
			case VIEW_MODE:
				view();
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
	
	private static void addTask() {
		String newLine = userCommand.getArg1();
		
	}

	private static void wrongCommand(){
		
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
