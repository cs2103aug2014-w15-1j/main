package logic;

import main.StartUp;
import gui.Display;
import gui.DisplayConfiguration;
import gui.VIEW_MODE;
import cli.CliToLog;
import cli.CliProcess;

import java.util.ArrayList;
import java.util.Date;

import data_store.DataStore;

public class RunLogic {

	

	
	// keep track on GUI and File status
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay = new int[StartUp.MAX_DISPLAY_LINE + 1];
	private static String date;

	// informations needed to pass to GUI and data store
	private static DisplayConfiguration passToGui;
	private static LogicToStore passToStore;
	
	// all valid command type modified from CLI
	enum COMMAND_TYPE {
		ADD_TASK, DELETE_TASK, READ_TASK, 
		RENAME, DESCRIBE, RESCHEDULE, REPEAT, 
		RESTORE, VIEW_MODE, UNDO, SEARCH, EXIT, INVALID
	};
	
	public static void Logic(String inputCommand){
		// pass user command to CLI for autocorrection
		CliToLog userCommand = CliProcess.interpretCommand(inputCommand);
		//initialize();
		
		// check whether the command is valid under current view mode
		if(checkValid(userCommand)){
			executeCommand(userCommand);
		} else {
			wrongCommand(userCommand);
		};
	}
	
	public static void initialize(GUIStatus initialGUI, ArrayList<Task> initialTaskList, ArrayList<Task> initialTrashbinList, int[] initialDisplay, String initialDate) {
		GUI = initialGUI;
		taskList = initialTaskList;
		trashbinList = initialTrashbinList;
		currentDisplay = initialDisplay;
		date = initialDate;
	}

	// check whether the command is valid under current view mode
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
			return (command.equalsIgnoreCase("view")
					||command.equalsIgnoreCase("rename") 
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
	

	// add a task to task list. update new GUI and File information
	private static void addTask(CliToLog userCommand) {
		// modify date information of task
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
		
		// update File
		Task newTask = new Task(userCommand.getArg1(), userCommand.getArg2(), userCommand.getArg3(),
				userCommand.getArg4(), startDate, endDate);
		taskList.add(newTask);
		
		// update GUI view mode
		GUI.changeCurretnTask((taskList.size() - 1));
		GUI.changeViewMode(VIEW_MODE.TASK);
		currentDisplay = initializeDisplayList(currentDisplay);
		currentDisplay[1] = GUI.getTaskIndex();
		
		// update GUI display information
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(newTask);
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.ADD_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	private static int[] initializeDisplayList(int[] currentDisplay) {
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}

	// delete a certain task or delete or tasks
	private static void deleteTask(CliToLog userCommand) {
		String deleteLine = userCommand.getArg1();
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			if(deleteLine.equalsIgnoreCase("all")){
				trashbinList.clear(); 
				passToGui = new DisplayConfiguration(GUI, display, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			} else if(currentDisplay[Integer.valueOf(deleteLine)] == -1) {
				display = viewBin();
				passToGui = new DisplayConfiguration(GUI, display, StartUp.INVALID_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			} else {
				trashbinList.remove(currentDisplay[Integer.valueOf(deleteLine)]);
				display = viewBin();
				passToGui = new DisplayConfiguration(GUI, display, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			}
		} else if(GUI.getMode().equals(VIEW_MODE.DATE)) {
			if(deleteLine.equalsIgnoreCase("all")){
				for(int i = 1; i <= StartUp.MAX_DISPLAY_LINE; i++){
					if(currentDisplay[i] != -1){
						trashbinList.add(taskList.remove(currentDisplay[i]));
					} else {
						break;
					}
				}
				display = viewDate(date);
				passToGui = new DisplayConfiguration(GUI, display, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			} else if(currentDisplay[Integer.valueOf(deleteLine)] == -1) {
				display = viewDate(date);
				passToGui = new DisplayConfiguration(GUI, display, StartUp.INVALID_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			} else {
				trashbinList.add(taskList.remove(currentDisplay[Integer.valueOf(deleteLine)]));
				display = viewDate(date);
				passToGui = new DisplayConfiguration(GUI, display, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
				passToStore = new LogicToStore(taskList, trashbinList);
			}
		} else {
			if(deleteLine.equalsIgnoreCase("all")){
				// delete all	
					// update File
					trashbinList.addAll(taskList);
					taskList.clear();
					
					// update GUI view mode
					GUI = new GUIStatus(GUI.getMode(), false, false, -1);
					
					passToGui = new DisplayConfiguration(GUI, taskList, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
					passToStore = new LogicToStore(taskList, trashbinList);
				} else if(currentDisplay[Integer.valueOf(deleteLine)] == -1){
				// delete certain task while the task does not exist
					
					for(int i = 1; i <= StartUp.MAX_DISPLAY_LINE; i++){
						if(currentDisplay[i] != -1){
							display.add(taskList.get(currentDisplay[i]));
						} else {
							break;
						}
					}
					passToGui = new DisplayConfiguration(GUI, display, StartUp.INVALID_FEEDBACK, StartUp.TITLE);
					passToStore = new LogicToStore(taskList, trashbinList);
				} else {
				// delete certain task
					// update File
					trashbinList.add(taskList.remove(currentDisplay[Integer.valueOf(deleteLine)]));
					
					// update GUI display information
					display = new ArrayList<Task>();
					initializeDisplayList(currentDisplay);
					for(int i = 0; i < StartUp.MAX_DISPLAY_LINE; i++){
						if((GUI.getTaskIndex() + i) < taskList.size()){
							display.add(taskList.get(GUI.getTaskIndex() + i));
							currentDisplay[i + 1] = GUI.getTaskIndex() + i;
						} else {
							break;
						}
					}
					
					// update GUI view mode
					GUI.changeHasPrevious(GUI.getTaskIndex() != 0);
					GUI.changeHasNext(GUI.getTaskIndex() + StartUp.MAX_DISPLAY_LINE < taskList.size());
					
					passToGui = new DisplayConfiguration(GUI, display, StartUp.DELETE_FEEDBACK, StartUp.TITLE);
					passToStore = new LogicToStore(taskList, trashbinList);
				}
		}
		
		GuiAndStore(passToGui, passToStore);
	}

	// read details of a certain task
	private static void readTask(CliToLog userCommand) {
		GUI.changeCurretnTask(Integer.valueOf(userCommand.getArg1()) - 1);
		GUI.changeViewMode(VIEW_MODE.TASK);
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		currentDisplay = initializeDisplayList(currentDisplay);
		currentDisplay[1] = GUI.getTaskIndex();
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.READ_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}
	
	// update the name of a certain task 
	private static void rename(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).rename(userCommand.getArg1());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.RENAME_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	// update the description of a certain task
	private static void describe(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).describe(userCommand.getArg1());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.DESCRIBE_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	// update the repeat times and dates of a certain task
	private static void repeat(CliToLog userCommand) {
		taskList.get(GUI.getTaskIndex()).repeat(userCommand.getArg1(), userCommand.getArg2());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.REPEAT_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
		
	}

	// update the time information of a certain task
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
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.RESCHEDULE_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	// change view mode
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
		
		passToGui = new DisplayConfiguration(GUI, display, StartUp.VIEW_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}

	// change view mode to the task list of a certain day
	private static ArrayList<Task> viewDate(String Date) {
		date = Date;
		ArrayList<Task> display = new ArrayList<Task>();
		int year = Integer.valueOf(Date.substring(0,4));
		int month = Integer.valueOf(Date.substring(4,6));
		int day = Integer.valueOf(Date.substring(6,8));
		Date requiredDate = new Date(year, month, day);
		for(int i = 1, j = 0;  j < taskList.size(); j++){
			if(taskList.get(j).getStartDate().equals(requiredDate)){
				display.add(taskList.get(j));
			}
			if(i <= StartUp.MAX_DISPLAY_LINE){
				currentDisplay[i] = j;
				i++;
			} else {
				break;
			}
		}
		boolean hasNext = display.size() > StartUp.MAX_DISPLAY_LINE;
		GUI = new GUIStatus(VIEW_MODE.DATE, hasNext, false, 0);
		return display;
	}

	// change the view mode to the calendar of a certain month
	private static ArrayList<Task> viewMonth(String Month) {
		date = Month;
		int requiredMonth = Integer.valueOf(Month);
		ArrayList<Task> display = new ArrayList<Task>();
		for(Task task : taskList){
			if(task.getEndDate().getMonth() == requiredMonth){
				display.add(task);
			}
		}
		boolean hasNext = display.size() > StartUp.MAX_DISPLAY_LINE;
		GUI = new GUIStatus(VIEW_MODE.MONTH, hasNext, false, 0);
		return display;
	}

	// change the view mode to trash bin
	private static ArrayList<Task> viewBin() {
		if(trashbinList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.BIN, false, false, -1);
			return trashbinList;
		} else {
			boolean hasNext = trashbinList.size() > StartUp.MAX_DISPLAY_LINE;
			GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, false, 0);
			
			ArrayList<Task> display = new ArrayList<Task>();
			if(hasNext){
				for(int i = 0; i < StartUp.MAX_DISPLAY_LINE; i++){
					
					display.add(trashbinList.get(i));
					currentDisplay[i + 1] = i;
				}
			} else {
				display = trashbinList;
				for(int i = 1; i <= display.size(); i++){
					currentDisplay[i] = i;
				}
			}
		return display;
		}
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

	// This method gives feedback when the user gives unreadable command
	private static void wrongCommand(CliToLog userCommand){
		ArrayList<Task> display = new ArrayList<Task>();
		for(int i = 1; i <= StartUp.MAX_DISPLAY_LINE; i++){
			if(currentDisplay[i] != -1){
				display.add(taskList.get(currentDisplay[i]));
			} else{
				break;
			}
		}
		passToGui = new DisplayConfiguration(GUI, display, StartUp.INVALID_FEEDBACK, StartUp.TITLE);
		passToStore = new LogicToStore(taskList,trashbinList);
		GuiAndStore(passToGui, passToStore);
	}
	
	// pass GUI and File information
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
	// This method determine which view mode the user want to use
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
	
	public void test(){
		
	}
}
