package logic;

import gui.DisplayConfiguration;
import gui.GUIStatus;

import java.util.ArrayList;

public class RunLogic {
	private static String ADD_FEEDBACK = "New task added successfully!";
	private static String DELETE_FEEDBACK = "Task deleted successfully!";
	private static String READ_FEEDBACK = "";
	private static String RENAME_FEEDBACK = "Task informaion updated!";
	private static String RESCHEDULE_FEEDBACK = "Task informaion updated!";
	private static String REPEAT_FEEDBACK = "Task informaion updated!";
	private static String DESCRIBE_FEEDBACK = "Task informaion updated!";
	private static String RESTORE_FEEDBACK = "Task restored to task list!";
	private static String VIEW_FEEDBACK = "";
	private static String UNDO_FEEDBACK = "";
	private static String SEARCH_FEEDBACK = "";
	private static String INVALID_FEEDBACK = "Invalid Command! Please check your command again!";
	
	private static String TITLE = "";
	
	private static int MAX_DISPLAY_LINE = 10;
	
	private static GUIStatus GUI;
	private static DisplayConfiguration passToGui;
	private static LogicToStore passToStore;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int currentTask;
	
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
		Task newTask = new Task(userCommand.getArg1(), userCommand.getArg2(), userCommand.getArg3(),
				userCommand.getArg4(), userCommand.getArg5(), userCommand.getArg6());
		taskList.add(newTask);
		if(!GUI.hasNext() && (taskList.size() % MAX_DISPLAY_LINE == 0)){
			GUI.changeHasNext();
		}
		passToGui = new DisplayConfiguration(GUI, taskList, ADD_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}


	private static void deleteTask(CliToLogic userCommand) {
		String deleteLine = userCommand.getArg1();
		if(deleteLine.equalsIgnoreCase("all")){
			trashbinList.addAll(taskList);
			taskList.clear();
			GUI = new GUIStatus(GUI.getMode(), false, false, 0);
			passToGui = new DisplayConfiguration(GUI, taskList, DELETE_FEEDBACK, TITLE, currentTask);
			passToStore = new LogicToStore(taskList, trashbinList);
		} else if(Integer.valueOf(deleteLine) >= taskList.size()){
			passToGui = new DisplayConfiguration(GUI, taskList, INVALID_FEEDBACK, TITLE, currentTask);
			passToStore = new LogicToStore(taskList, trashbinList);
		} else {
			trashbinList.add(taskList.remove(Integer.valueOf(deleteLine) - 1));
			if(GUI.hasNext() && (taskList.size() % MAX_DISPLAY_LINE == MAX_DISPLAY_LINE - 1)){
				GUI.changeHasNext();
				if(currentTask >= taskList.size()){
					currentTask -= MAX_DISPLAY_LINE;
				}
			}
			passToGui = new DisplayConfiguration(GUI, taskList, DELETE_FEEDBACK, TITLE, currentTask);
			passToStore = new LogicToStore(taskList, trashbinList);
		}
		GuiAndStore(passToGui, passToStore);
	}

	private static void readTask(CliToLogic userCommand) {
		currentTask = Integer.valueOf(userCommand.getArg1()) - 1;
		GUI.changeCurretnTask(getModeNumber("TaskView"));
		passToGui = new DisplayConfiguration(GUI, taskList, READ_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void rename(CliToLogic userCommand) {
		taskList.get(currentTask).rename(userCommand.getArg1());
		passToGui = new DisplayConfiguration(GUI, taskList, RENAME_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void describe(CliToLogic userCommand) {
		taskList.get(currentTask).describe(userCommand.getArg1());
		passToGui = new DisplayConfiguration(GUI, taskList, DESCRIBE_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void repeat(CliToLogic userCommand) {
		taskList.get(currentTask).repeat(userCommand.getArg1(), userCommand.getArg2());
		passToGui = new DisplayConfiguration(GUI, taskList, REPEAT_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
		
	}

	private static void reschedule(CliToLogic userCommand) {
		taskList.get(currentTask).reschedule(userCommand.getArg1(), userCommand.getArg2());
		passToGui = new DisplayConfiguration(GUI, taskList, RESCHEDULE_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
		
	}

	private static void view(CliToLogic userCommand) {
		// TODO Auto-generated method stub
		String viewMode = userCommand.getArg1();
		int modeNumber = getModeNumber(viewMode);
		GUI.changeCurretnTask(modeNumber);
		passToGui = new DisplayConfiguration(GUI, taskList, VIEW_FEEDBACK, TITLE, currentTask);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
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
		// TODO Auto-generated method stub
	}
	
	private static int getModeNumber(String viewMode) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static void GuiAndStore(DisplayConfiguration passToGui, LogicToStore passToStore) {
		// TODO Auto-generated method stub
		
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
