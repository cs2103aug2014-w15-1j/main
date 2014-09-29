package logic;

import gui.Display;
import gui.DisplayConfiguration;
import gui.VIEW_MODE;
import cli.CliToLog;
import cli.CliProcess;

import java.util.ArrayList;
import java.util.Date;

import data_store.DataStore;

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
	
	enum COMMAND_TYPE {
		ADD_TASK, DELETE_TASK, READ_TASK, 
		RENAME, DESCRIBE, RESCHEDULE, REPEAT, 
		RESTORE, VIEW_MODE, UNDO, SEARCH, EXIT, INVALID
	};
	
	public static void Logic(String inputCommand){
		CliToLog userCommand = CliProcess.interpretCommand(inputCommand);
		if(checkValid(userCommand)){
			executeCommand(userCommand);
		} else {
			wrongCommand(userCommand);
		};
	}
	
	private static boolean checkValid(CliToLog userCommand){
		String command = userCommand.getCommand();
		VIEW_MODE mode = GUI.getMode();
		switch(mode){
		case DATE:
			return (command.equalsIgnoreCase("add") 
					|| command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit"));
		case TASK:
			return (command.equalsIgnoreCase("rename") 
					|| command.equalsIgnoreCase("describe")
					|| command.equalsIgnoreCase("repeat") 
					|| command.equalsIgnoreCase("reschedule")
					|| command.equalsIgnoreCase("undo")
					|| command.equalsIgnoreCase("search")
					|| command.equalsIgnoreCase("exit"));
		case MONTH:
			return  (command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("exit"));
		case BIN:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("restore")
					|| command.equalsIgnoreCase("exit"));
		case UNDONE:
			return (command.equalsIgnoreCase("delete")
					|| command.equalsIgnoreCase("read") 
					|| command.equalsIgnoreCase("view")
					|| command.equalsIgnoreCase("exit"));
		default:
			return false;
		}
	}
	
	private static void executeCommand(CliToLog userCommand){
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
	

	private static void addTask(CliToLog userCommand) {
		Date startDate = null;
		Date endDate = null;
		if(userCommand.getArg5() != null && userCommand.getArg6() != null){
			int startYear = Integer.valueOf(userCommand.getArg5().substring(0,4));
			int startMonth = Integer.valueOf(userCommand.getArg5().substring(4,6));
			int startDay = Integer.valueOf(userCommand.getArg5().substring(6,8));
			int endYear = Integer.valueOf(userCommand.getArg6().substring(0,4));
			int endMonth = Integer.valueOf(userCommand.getArg6().substring(4,6));
			int endDay = Integer.valueOf(userCommand.getArg6().substring(6,8));
			startDate = new Date(startYear, startMonth, startDay);
			endDate = new Date(endYear, endMonth, endDay);
		}
		Task newTask = new Task(userCommand.getArg1(), userCommand.getArg2(), userCommand.getArg3(),
				userCommand.getArg4(), startDate, endDate);
		taskList.add(newTask);
		GUI.changeCurretnTask((taskList.size() - 1) / MAX_DISPLAY_LINE);
		GUI.changeViewMode(VIEW_MODE.TASK);
		
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(newTask);
		
		passToGui = new DisplayConfiguration(GUI, display, ADD_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}


	private static void deleteTask(CliToLog userCommand) {
		String deleteLine = userCommand.getArg1();
		if(deleteLine.equalsIgnoreCase("all")){
			trashbinList.addAll(taskList);
			taskList.clear();
			GUI = new GUIStatus(GUI.getMode(), false, false, -1);
			passToGui = new DisplayConfiguration(GUI, taskList, DELETE_FEEDBACK, TITLE);
			passToStore = new LogicToStore(taskList, trashbinList);
		} else if(Integer.valueOf(deleteLine) > taskList.size()){
			ArrayList<Task> display = new ArrayList<Task>();
			for(int i = 0; i < MAX_DISPLAY_LINE; i++){
				if((GUI.getTaskIndex() + i) >= taskList.size()){
					display.add(taskList.get(GUI.getTaskIndex() + i));
				} else {
					break;
				}
			}
			passToGui = new DisplayConfiguration(GUI, display, INVALID_FEEDBACK, TITLE);
			passToStore = new LogicToStore(taskList, trashbinList);
		} else {
			trashbinList.add(taskList.remove(Integer.valueOf(deleteLine) - 1));
			if(GUI.hasNext() && (taskList.size() % MAX_DISPLAY_LINE == MAX_DISPLAY_LINE - 1)){
				GUI.changeHasNext(false);
				if(GUI.getTaskIndex() >= taskList.size()){
					GUI.changeCurretnTask(GUI.getTaskIndex() - MAX_DISPLAY_LINE);
				}
			}
			ArrayList<Task> display = new ArrayList<Task>();
			for(int i = 0; i < MAX_DISPLAY_LINE; i++){
				if((GUI.getTaskIndex() + i) >= taskList.size()){
					display.add(taskList.get(GUI.getTaskIndex() + i));
				} else {
					break;
				}
			}
			passToGui = new DisplayConfiguration(GUI, display, DELETE_FEEDBACK, TITLE);
			passToStore = new LogicToStore(taskList, trashbinList);
		}
		GuiAndStore(passToGui, passToStore);
	}

	private static void readTask(CliToLog userCommand) {
		GUI.changeCurretnTask(Integer.valueOf(userCommand.getArg1()) - 1);
		GUI.changeViewMode(VIEW_MODE.TASK);
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, READ_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void rename(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).rename(userCommand.getArg1());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, RENAME_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void describe(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).describe(userCommand.getArg1());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, DESCRIBE_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void repeat(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).repeat(userCommand.getArg1(), userCommand.getArg2());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, REPEAT_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
		
	}

	private static void reschedule(CliToLog userCommand) {
		int startYear = Integer.valueOf(userCommand.getArg1().substring(0,4));
		int startMonth = Integer.valueOf(userCommand.getArg1().substring(4,6));
		int startDay = Integer.valueOf(userCommand.getArg1().substring(6,8));
		int endYear = Integer.valueOf(userCommand.getArg2().substring(0,4));
		int endMonth = Integer.valueOf(userCommand.getArg2().substring(4,6));
		int endDay = Integer.valueOf(userCommand.getArg2().substring(6,8));
		Date startDate = new Date(startYear, startMonth, startDay);
		Date endDate = new Date(endYear, endMonth, endDay);
		taskList.get(GUI.getTaskIndex()).reschedule(startDate, endDate);
		
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, RESCHEDULE_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static void view(CliToLog userCommand) {
		VIEW_MODE mode = determineViewMode(userCommand.getArg1());
		ArrayList<Task> display;
		switch (mode) {
		case DATE:
			display = viewDate(userCommand.getArg2());
			break;
		case MONTH:
			display = viewMonth(userCommand.getArg2());
			break;
		case BIN:
			display = viewBin();
			break;
		case UNDONE:
			display = viewUndone();
			break;
		default:
			//throw an error if the mode is not recognized
			throw new Error("Unrecognized view mode");
		}
		
		passToGui = new DisplayConfiguration(GUI, display, VIEW_FEEDBACK, TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static ArrayList<Task> viewDate(String Date) {
		ArrayList<Task> display = new ArrayList<Task>();
		int year = Integer.valueOf(Date.substring(0,4));
		int month = Integer.valueOf(Date.substring(4,6));
		int day = Integer.valueOf(Date.substring(6,8));
		Date requiredDate = new Date(year, month, day);
		for(Task task : taskList){
			if(task.getStartDate().equals(requiredDate)){
				display.add(task);
			}
		}
		boolean hasNext = display.size() > MAX_DISPLAY_LINE;
		GUI = new GUIStatus(VIEW_MODE.DATE, hasNext, false, 0);
		return display;
	}

	private static ArrayList<Task> viewMonth(String Month) {
		// TODO Auto-generated method stub
		int requiredMonth = Integer.valueOf(Month);
		ArrayList<Task> display = new ArrayList<Task>();
		for(Task task : taskList){
			if(task.getEndDate().getMonth() == requiredMonth){
				display.add(task);
			}
		}
		boolean hasNext = display.size() > MAX_DISPLAY_LINE;
		GUI = new GUIStatus(VIEW_MODE.MONTH, hasNext, false, 0);
		return display;
	}

	private static ArrayList<Task> viewBin() {
		// TODO Auto-generated method stub
		if(trashbinList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.BIN, false, false, -1);
		} else {
			boolean hasNext = trashbinList.size() > MAX_DISPLAY_LINE;
			GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, false, 0);
		}
		return trashbinList;
	}

	private static ArrayList<Task> viewUndone() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void undo() {
		// TODO Auto-generated method stub
		
	}

	private static void restore(CliToLog userCommand) {
		// TODO Auto-generated method stub
		
	}

	private static void search(CliToLog userCommand) {
		// TODO Auto-generated method stub
	}

	private static void wrongCommand(CliToLog userCommand){
		// TODO Auto-generated method stub
	}
	
	private static void GuiAndStore(DisplayConfiguration passToGui, LogicToStore passToStore) {
		DataStore.writeAllData(passToStore);
		Display.display(passToGui);
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
	
	private static VIEW_MODE determineViewMode(String viewModeString) {
		if (viewModeString.equalsIgnoreCase("DATE")) {
			return VIEW_MODE.DATE;
		} else if (viewModeString.equalsIgnoreCase("MONTH")) {
			return VIEW_MODE.MONTH;
		} else if (viewModeString.equalsIgnoreCase("BIN")) {
			return VIEW_MODE.BIN;
		} else {
			return VIEW_MODE.UNDONE;
		}
	}
}
