package logic;

import gui.VIEW_MODE;

import java.util.ArrayList;
import java.util.Date;

import cli.Command;
import data_store.DataStore;

public class Execute {
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList;
	private static ArrayList<Task> trashbinList;
	private static int[] currentDisplay;
	
	private static DisplayInfo passToGui;
	private static LogicToStore passToStore;
	
	public static DisplayInfo executeCommand(Command userCommand){
		initialize();
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
			case NEXT:
				next();
				break;
			case PREVIOUS:
				previous();
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
			case BACK:
				back();
				break;
			case EXIT:
				System.exit(0);
			default:
				//throw an error if the command is not recognized
				throw new Error("Unrecognized command type");
		}
		
		update();
		DataStore.writeAllData(passToStore);
		return passToGui;
	}
	
	private static void initialize(){
		GUI = RunLogic.getGuiStatus();
		taskList = RunLogic.getTaskList();
		trashbinList = RunLogic.getTrashbinList();
		currentDisplay = RunLogic.getCurrentDisplay();
	}
	
	private static void update(){
		RunLogic.updateGuiStatus(GUI);
		RunLogic.updateTaskList(taskList);
		RunLogic.updateTrashbinList(trashbinList);
		RunLogic.updateCurrentdiaplay(currentDisplay);
	}

	// add a task to task list. update new GUI and File information
	@SuppressWarnings("deprecation")
	private static void addTask(Command userCommand) {
		// modify date information of task
		Date startDate = null;
		Date endDate = null;
		if(userCommand.getStartDay() != null && userCommand.getEndDay() != null){
			int startYear = Integer.valueOf(userCommand.getStartDay().substring(0,4));
			int startMonth = Integer.valueOf(userCommand.getStartDay().substring(4,6));
			int startDay = Integer.valueOf(userCommand.getStartDay().substring(6,8));
			int endYear = Integer.valueOf(userCommand.getEndDay().substring(0,4));
			int endMonth = Integer.valueOf(userCommand.getEndDay().substring(4,6));
			int endDay = Integer.valueOf(userCommand.getEndDay().substring(6,8));
			startDate = new Date(startYear, startMonth, startDay);
			endDate = new Date(endYear, endMonth, endDay);
		}
		
		// update File
		Task newTask = new Task(userCommand.getTitle(), userCommand.getDescription(), userCommand.getRPdate(), startDate, endDate);
		taskList.add(newTask);
		
		// update GUI view mode
		GUI.changeCurretnTask((taskList.size() - 1));
		GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
		currentDisplay = initializeDisplayList(currentDisplay);
		currentDisplay[1] = GUI.getTaskIndex();
		
		// update GUI display information
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(newTask);
		
		constructBridges(display, FeedbackFormat.ADD_FEEDBACK, TitleFormat.TITLE);
		 
	}


	// delete a certain task or delete or tasks
	private static void deleteTask(Command userCommand) {
		String deleteLine = userCommand.getTaskDescription();
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			if(deleteLine.equalsIgnoreCase("all")){
				trashbinList.clear(); 
				currentDisplay = initializeDisplayList(currentDisplay);
				GUI.changeCurretnTask(-1);
				constructBridges( display, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);	
			} else if(Integer.valueOf(deleteLine) > Constant.MAX_DISPLAY_LINE || currentDisplay[Integer.valueOf(deleteLine)] == -1) {
				display = viewBin();
				constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
			} else {
				trashbinList.remove(currentDisplay[Integer.valueOf(deleteLine)]);
				display = viewBin();
				constructBridges(display, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);
			}
		} else if(GUI.getMode().equals(VIEW_MODE.DATE)) {
			if(deleteLine.equalsIgnoreCase("all")){
				for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
					if(currentDisplay[i] != -1){
						trashbinList.add(taskList.remove(currentDisplay[i]));
					} else {
						break;
					}
				}
				display = viewDate(GUI.getDate());
				constructBridges(display, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);
			} else if(Integer.valueOf(deleteLine) > Constant.MAX_DISPLAY_LINE || currentDisplay[Integer.valueOf(deleteLine)] == -1) {
				display = viewDate(GUI.getDate());
				constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
			} else {
				trashbinList.add(taskList.remove(currentDisplay[Integer.valueOf(deleteLine)]));
				display = viewDate(GUI.getDate());
				constructBridges(display, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);
			}
		} else if (GUI.getMode().equals(VIEW_MODE.TASK_LIST)) {
			if(deleteLine.equalsIgnoreCase("all")){
				// delete all	
					// update File
					trashbinList.addAll(taskList);
					taskList.clear();
					
					// update GUI view mode
					GUI = new GUIStatus(GUI.getMode(), false, false, -1, GUI.getDate());
					
					constructBridges(taskList, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);
				} else if(Integer.valueOf(deleteLine) > Constant.MAX_DISPLAY_LINE || currentDisplay[Integer.valueOf(deleteLine)] == -1){
				// delete certain task while the task does not exist
					for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
						if(currentDisplay[i] != -1){
							display.add(taskList.get(currentDisplay[i]));
						} else {
							break;
						}
					}
					constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
					
				} else {
				// delete certain task
					// update File
					trashbinList.add(taskList.remove(currentDisplay[Integer.valueOf(deleteLine)]));
					
					// update GUI display information
					display = new ArrayList<Task>();
					initializeDisplayList(currentDisplay);
					for(int i = 0; i < Constant.MAX_DISPLAY_LINE; i++){
						if((GUI.getTaskIndex() + i) < taskList.size()){
							display.add(taskList.get(GUI.getTaskIndex() + i));
							currentDisplay[i + 1] = GUI.getTaskIndex() + i;
						} else {
							break;
						}
					}
					
					// update GUI view mode
					GUI.changeHasPrevious(GUI.getTaskIndex() != 0);
					GUI.changeHasNext(GUI.getTaskIndex() + Constant.MAX_DISPLAY_LINE < taskList.size());	
					constructBridges(display, FeedbackFormat.DELETE_FEEDBACK, TitleFormat.TITLE);
				}
		}
		
		 
	}

	// read details of a certain task
	private static void readTask(Command userCommand) {
		int readLine = Integer.valueOf(userCommand.getTaskDescription());
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			if(readLine > Constant.MAX_DISPLAY_LINE || currentDisplay[readLine] == -1){
				for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
					if(currentDisplay[i] != -1){
						display.add(trashbinList.get(currentDisplay[i]));
					} else {
						break;
					}
				}
				constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
				
			} else {
				GUI.changeCurretnTask(currentDisplay[readLine]);
				GUI.changeViewMode(VIEW_MODE.BIN_DETAIL);
				
				display.add(trashbinList.get(GUI.getTaskIndex()));
				currentDisplay = initializeDisplayList(currentDisplay);
				currentDisplay[1] = GUI.getTaskIndex();
				
				constructBridges(display, FeedbackFormat.READ_FEEDBACK, TitleFormat.TITLE);
			}
		} else {
			if(readLine > Constant.MAX_DISPLAY_LINE || currentDisplay[readLine] == -1){
				for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
					if(currentDisplay[i] != -1){
						display.add(taskList.get(currentDisplay[i]));
					} else {
						break;
					}
				}
				constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
				
			} else {
				GUI.changeCurretnTask(currentDisplay[readLine]);
				GUI.changeViewMode(VIEW_MODE.TASK_DETAIL);
				
				display.add(taskList.get(GUI.getTaskIndex()));
				currentDisplay = initializeDisplayList(currentDisplay);
				currentDisplay[1] = GUI.getTaskIndex();
				
				constructBridges(display, FeedbackFormat.READ_FEEDBACK, TitleFormat.TITLE);
				
			}
		}
		 
	}
	
	// update the name of a certain task 
	private static void rename(Command userCommand) {
		taskList.get(GUI.getTaskIndex()).rename(userCommand.getTaskDescription());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		constructBridges(display, FeedbackFormat.RENAME_FEEDBACK, TitleFormat.TITLE);
		 
	}

	// update the description of a certain task
	private static void describe(Command userCommand) {
		taskList.get(GUI.getTaskIndex()).describe(userCommand.getTaskDescription());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		constructBridges(display, FeedbackFormat.DESCRIBE_FEEDBACK, TitleFormat.TITLE);
		 
	}

	// update the repeat times and dates of a certain task
	private static void repeat(Command userCommand) {
		// TODO Auto-generated method stub
		taskList.get(GUI.getTaskIndex()).repeat( userCommand.getRPdate());
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		constructBridges(display, FeedbackFormat.REPEAT_FEEDBACK, TitleFormat.TITLE);
		 
		
	}

	// update the time information of a certain task
	@SuppressWarnings("deprecation")
	private static void reschedule(Command userCommand) {
		// TODO Auto-generated method stub
		int startYear = Integer.valueOf(userCommand.getTitle().substring(0,4));
		int startMonth = Integer.valueOf(userCommand.getTitle().substring(4,6));
		int startDay = Integer.valueOf(userCommand.getTitle().substring(6,8));
		int endYear = Integer.valueOf(userCommand.getTaskDescription().substring(0,4));
		int endMonth = Integer.valueOf(userCommand.getTaskDescription().substring(4,6));
		int endDay = Integer.valueOf(userCommand.getTaskDescription().substring(6,8));
		Date startDate = new Date(startYear, startMonth, startDay);
		Date endDate = new Date(endYear, endMonth, endDay);
		taskList.get(GUI.getTaskIndex()).reschedule(startDate, endDate);
		
		ArrayList<Task> display = new ArrayList<Task>();
		display.add(taskList.get(GUI.getTaskIndex()));
		
		constructBridges(display, FeedbackFormat.RESCHEDULE_FEEDBACK, TitleFormat.TITLE);
		 
	}

	// change view mode
	private static void view(Command userCommand) {
		GUI.changeCurretnTask(0);
		VIEW_MODE mode = determineViewMode(userCommand.getTaskDescription());
		ArrayList<Task> display;
		switch (mode) {
		case DATE:
			display = viewDate(userCommand.getTaskDescription());
			break;
		case MONTH:
			display = viewMonth(userCommand.getTaskDescription());
			break;
		case BIN:
			display = viewBin();
			break;
		case UNDONE:
			display = viewUndone();
			break;
		case TASK_LIST:
			display = viewAllTask();
			break;
		default:
			//throw an error if the mode is not recognized
			throw new Error("Unrecognized view mode");
		}
		
		constructBridges(display, FeedbackFormat.VIEW_FEEDBACK, TitleFormat.TITLE);		 
	}



	// change view mode to the task list of a certain day
	@SuppressWarnings("deprecation")
	private static ArrayList<Task> viewDate(String Date) {
		GUI.changeDate(Date);
		ArrayList<Task> display = new ArrayList<Task>();
		int year = Integer.valueOf(Date.substring(0,4));
		int month = Integer.valueOf(Date.substring(4,6));
		int day = Integer.valueOf(Date.substring(6,8));
		Date requiredDate = new Date(year, month, day);
		boolean hasNext = false;
		for(int i = 1, j = GUI.getTaskIndex();  j < taskList.size(); j++){
			if(taskList.get(j).getEndDate().equals(requiredDate)){
				if(i <= Constant.MAX_DISPLAY_LINE){
					display.add(taskList.get(j));
					currentDisplay[i] = j;
					i++;
				} else {
					hasNext = true;
					break;
				}
			}	
		}
		boolean hasPrevious = GUI.getTaskIndex() > 0;
		GUI = new GUIStatus(VIEW_MODE.DATE, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
		return display;
	}

	// change the view mode to the calendar of a certain month
	// not finished yet
	@SuppressWarnings("deprecation")
	private static ArrayList<Task> viewMonth(String Month) {
		GUI.changeDate(Month);
		int requiredMonth = Integer.valueOf(Month);
		ArrayList<Task> display = new ArrayList<Task>();
		for(Task task : taskList){
			if(task.getEndDate().getMonth() == requiredMonth){
				display.add(task);
			}
		}
		boolean hasNext = display.size() > Constant.MAX_DISPLAY_LINE;
		GUI = new GUIStatus(VIEW_MODE.MONTH, hasNext, false, 0, GUI.getDate());
		return display;
	}

	// change the view mode to trash bin
	private static ArrayList<Task> viewBin() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeDisplayList(currentDisplay);
		if(trashbinList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.BIN, false, false, -1, GUI.getDate());
		} else {
			boolean hasNext = false;
			for(int i = 1, j = GUI.getTaskIndex();  j < trashbinList.size(); j++){
				if(i <= Constant.MAX_DISPLAY_LINE){
					display.add(trashbinList.get(j));
					currentDisplay[i] = j;
					i++;
				} else {
					hasNext = true;
					break;
				}
			}
			boolean hasPrevious = (currentDisplay[1] > 0);
			GUI.changeCurretnTask(currentDisplay[1]);
			GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
		}
		return display;
	}

	// not finish yet
	private static ArrayList<Task> viewUndone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// change view mode to view the whole task list
	private static ArrayList<Task> viewAllTask() {
		ArrayList<Task> display = new ArrayList<Task>();
		currentDisplay = initializeDisplayList(currentDisplay);
		if(taskList.isEmpty()){
			GUI = new GUIStatus(VIEW_MODE.TASK_LIST, false, false, -1, GUI.getDate());
		} else {
			boolean hasNext = false;
			for(int i = 1, j = GUI.getTaskIndex();  j < taskList.size(); j++){
				if(i <= Constant.MAX_DISPLAY_LINE){
					display.add(taskList.get(j));
					currentDisplay[i] = j;
					i++;
				} else {
					hasNext = true;
					break;
				}
			}
			boolean hasPrevious = (currentDisplay[1] > 0);
			GUI.changeCurretnTask(currentDisplay[1]);
			GUI = new GUIStatus(VIEW_MODE.TASK_LIST, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
		}
		return display;
	}
	
	// not finish yet
	private static void previous() {
		// TODO Auto-generated method stub
		GUI.changeCurretnTask(GUI.getTaskIndex() - Constant.MAX_DISPLAY_LINE);
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			display = viewBin();
		} else if(GUI.getMode().equals(VIEW_MODE.DATE)){
			display = viewDate(GUI.getDate());
		} else if(GUI.getMode().equals(VIEW_MODE.UNDONE)){
			display = viewUndone();
		} else if(GUI.getMode().equals(VIEW_MODE.TASK_LIST)){
			display = viewAllTask();
		}
		constructBridges(display, FeedbackFormat.PREVIOUS_FEEDBACK, TitleFormat.TITLE);
		 
	}

	private static void next() {
		GUI.changeCurretnTask(GUI.getTaskIndex() + Constant.MAX_DISPLAY_LINE);
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			display = viewBin();
		} else if(GUI.getMode().equals(VIEW_MODE.DATE)){
			display = viewDate(GUI.getDate());
		} else if(GUI.getMode().equals(VIEW_MODE.UNDONE)){
			display = viewUndone();
		} else if(GUI.getMode().equals(VIEW_MODE.TASK_LIST)){
			display = viewAllTask();
		}
		constructBridges(display, FeedbackFormat.NEXT_FEEDBACK, TitleFormat.TITLE);
		 
	}
	
	private static void back(){
		GUI.changeCurretnTask(GUI.getTaskIndex() - GUI.getTaskIndex() % Constant.MAX_DISPLAY_LINE);
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			display = viewBin();
		} else if (GUI.getMode().equals(VIEW_MODE.TASK_DETAIL)){
			display = viewAllTask();
		}
		constructBridges(display, FeedbackFormat.VIEW_FEEDBACK, TitleFormat.TITLE);
	}
	
	// not finish yet
	private static void undo() {
		// TODO Auto-generated method stub
		
	}

	// not finish yet
	private static void restore(Command userCommand) {
		// TODO Auto-generated method stub
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			int restoreLine = Integer.valueOf(userCommand.getTaskDescription());
			if(restoreLine > Constant.MAX_DISPLAY_LINE || currentDisplay[restoreLine] == -1){
				for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
					if(currentDisplay[i] != -1){
						display.add(trashbinList.get(currentDisplay[i]));
					} else {
						break;
					}
				}
				constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
				
			} else {
				taskList.add(trashbinList.remove(currentDisplay[restoreLine]));
				currentDisplay = initializeDisplayList(currentDisplay);
				
				boolean hasNext = false;
				for(int i = 1, j = GUI.getTaskIndex();  j < trashbinList.size(); j++){
					if(i <= Constant.MAX_DISPLAY_LINE){
						display.add(trashbinList.get(j));
						currentDisplay[i] = j;
						i++;
					} else {
						hasNext = true;
						break;
					}
				}
				boolean hasPrevious = GUI.getTaskIndex() > 0;
				GUI = new GUIStatus(VIEW_MODE.BIN, hasNext, hasPrevious, currentDisplay[1], GUI.getDate());
				
				constructBridges(display, FeedbackFormat.RESTORE_FEEDBACK, TitleFormat.TITLE);
				
			}
		} else if(GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)) {
			taskList.add(trashbinList.remove(currentDisplay[1]));
			currentDisplay[1] = taskList.size() - 1;
			GUI.changeCurretnTask(currentDisplay[1]);
			display.add(taskList.get(GUI.getTaskIndex()));
			constructBridges(display, FeedbackFormat.RESTORE_FEEDBACK, TitleFormat.TITLE);
		}
		 
	}

	// not finish yet
	private static void search(Command userCommand) {
		// TODO Auto-generated method stub
	}
	
	



	// This method gives feedback when the user gives unreadable command
	public static DisplayInfo wrongCommand(Command userCommand){
		initialize();
		ArrayList<Task> display = new ArrayList<Task>();
		if(GUI.getMode().equals(VIEW_MODE.BIN)){
			for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
				if(currentDisplay[i] != -1){
					display.add(trashbinList.get(currentDisplay[i]));
				} else{
					break;
				}
			}
			
			 
		} else if(GUI.getMode().equals(VIEW_MODE.TASK_LIST)){
			for(int i = 1; i <= Constant.MAX_DISPLAY_LINE; i++){
				if(currentDisplay[i] != -1){
					display.add(taskList.get(currentDisplay[i]));
				} else{
					break;
				}
			}
			
			 
		} else if (GUI.getMode().equals(VIEW_MODE.TASK_DETAIL)){
			display.add(taskList.get(currentDisplay[1]));
		} else if (GUI.getMode().equals(VIEW_MODE.BIN_DETAIL)){
			display.add(trashbinList.get(currentDisplay[1]));
		}
		constructBridges(display, FeedbackFormat.INVALID_FEEDBACK, TitleFormat.TITLE);
		update();
		DataStore.writeAllData(passToStore);
		return passToGui;
	}
	

	
	// This method determine which command the user want to use
	public static COMMAND_TYPE determineCommandType(String commandTypeString) {
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
		} else if (commandTypeString.equalsIgnoreCase("next")) {
			return COMMAND_TYPE.NEXT;
		} else if (commandTypeString.equalsIgnoreCase("previous")) {
			return COMMAND_TYPE.PREVIOUS;
		} else if (commandTypeString.equalsIgnoreCase("search")) {
			return COMMAND_TYPE.SEARCH;
		} else if (commandTypeString.equalsIgnoreCase("back")) {
			return COMMAND_TYPE.BACK;
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
		} else if (viewModeString.equalsIgnoreCase("TASKLIST")) {
			return VIEW_MODE.TASK_LIST;
		} else if (viewModeString.equalsIgnoreCase("UNDONE")) {
			return VIEW_MODE.UNDONE;
		} else {
			return VIEW_MODE.TASK_DETAIL;
		}
	}
	
	private static int[] initializeDisplayList(int[] currentDisplay) {
		for(int i = 0; i < currentDisplay.length; i++){
			currentDisplay[i] = -1;
		}
		return currentDisplay;
	}
	
	private static void constructBridges(ArrayList<Task> display, String feedback, String title){
		passToGui = new DisplayInfo(GUI, display, feedback, title);
		passToStore = new LogicToStore(taskList,trashbinList);
	}
}
