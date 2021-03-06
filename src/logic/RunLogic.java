package logic;

import read_file.ReadFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import parser.RawCommand;
import parser.ParserProcesser;
import CommandType.*;

/**
 * 
 * @author a0119456Y
 *
 */
public class RunLogic {
	private static Logger logger = Logger.getLogger("RunLogic");
	
	private static String WELCOME = "Welcome to MagiCal!";
	private static String TITLE = "Today's tasks";
	
	// keep track on GUI and File status
	private static GUIStatus GUI;
	private static ArrayList<Task> taskList = new ArrayList<Task>();
	private static ArrayList<Task> trashbinList = new ArrayList<Task>();
	private static int[] currentDisplay = new int[Default.MAX_DISPLAY_LINE + 1];
	private static int[] currentListIndex = new int[Default.MAX_TASKS];
	private static Stack<Command> pastCommands ;
	private static int nextTaskPointer;
	
	

	public static DisplayInfo initialize() {
		try{
			initializeTaskLists();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "failed to read file");
		}
		initializeTaskPointer();
		initializeCommandList();
		initializeGUI();
		initializeIndexLists();
		return startupDisplay();
	}
	
	public static DisplayInfo logic(String inputCommand){
		RawCommand stringCommand = ParserProcesser.interpretCommand(inputCommand);
		assert stringCommand != null : "logic cannot get correct command from parser!";
		Command userCommand = ConvertCommand.convert(stringCommand);	
		assert userCommand != null : "logic cannot convert command!";
		addPastCommands(userCommand);
		return userCommand.execute();
	}
	
	
	
	//-------------------helper--------------------------
	
	public static GUIStatus getGuiStatus(){
		return GUI;
	}
	
	public static ArrayList<Task> getTaskList(){
		return taskList;
	}
	
	public static ArrayList<Task> getTrashbinList(){
		return trashbinList;
	}
	
	public static int[] getCurrentDisplay(){
		return currentDisplay;
	}
	
	public static int[] getCurrentListIndex(){
		return currentListIndex;
	}
	public static void updateGuiStatus(GUIStatus newGUI){
		GUI = newGUI;
	}
	
	public static void updateTaskList(ArrayList<Task> newTaskList){
		taskList = newTaskList;
	}
	
	public static void updateTrashbinList(ArrayList<Task> newTrashbinList){
		trashbinList = newTrashbinList;
	}
	
	public static void updateCurrentdiaplay(int[] newDisplay){
		currentDisplay = newDisplay;
	}
	
	public static void updateCurrentListIndex(int[] newListIndex){
		currentListIndex = newListIndex;
	}
	
	private static void initializeTaskLists(){
		ReadFile rf = new ReadFile();
		taskList = rf.getEventTask();
		if(taskList == null) {
			taskList = new ArrayList<Task>();
		}
		trashbinList = rf.getTrashFile();
		if(trashbinList == null) {
			trashbinList = new ArrayList<Task>();
		}
	}
	
	private static void initializeCommandList(){
		pastCommands = new Stack<Command>();
	}
	
	private static void initializeGUI(){
		Calendar c = Calendar.getInstance();
 		
 		GUI = new GUIStatus(VIEW_MODE.TASK_LIST, false, false, -1, 
 				new JDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
 		
	}
	
	private static void initializeIndexLists(){
		int length = currentListIndex.length;
		for(int i = 0; i < taskList.size(); i++){
			currentListIndex[i] = i;
		}
		for(int i = taskList.size(); i < length; i++){
			currentListIndex[i] = -1;
		}
	}
	
	private static DisplayInfo startupDisplay(){
		Command start = new ViewDate(GUI.getDate(), WELCOME, TITLE);
		return start.execute();
	}
	
	public static boolean hasPastCommands() {
		return !pastCommands.isEmpty();
	}
	public static DisplayInfo undo() {
		return pastCommands.pop().undo();
	}
	private static void addPastCommands(Command cmd){
		if(cmd.supportUndo()) {
			pastCommands.push(cmd);
		}
	}
	
	private static void initializeTaskPointer() {
		nextTaskPointer = 0;
		
		for(Task t: taskList) {
			t.setPointer(nextTaskPointer);
			incrementnextTaskPointer();
		}	
		for(Task t: trashbinList) {
			t.setPointer(nextTaskPointer);
				incrementnextTaskPointer();
			}
	}
	
	public static int getNextTaskPointer() {
		return nextTaskPointer;
	}
	
	public static void incrementnextTaskPointer() {
		nextTaskPointer++;
	}
	
	public static int getPointerInList(ArrayList<Task> lst, int index){
		if (lst != null) {
			return lst.get(index).getPointer();
		} else {
			return -1;
		}
	}

	public static int getIndexInList(ArrayList<Task> lst, int ptr) {
		for(int i=0; i<lst.size(); i++) {
			if(lst.get(i).matchPointer(ptr)){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean removeTaskByPointer(ArrayList<Task> lst, int ptr){
		int index = getIndexInList(lst, ptr);
		if(index != -1){
			lst.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
}
