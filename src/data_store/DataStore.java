package data_store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import logic.Task;
import logic.LogicToStore;

import java.io.File;

/*
 * @author A0100792M
 * 
 * DataStore will store object LogicToStore containing commands input strings into text files Trashlist.txt and Tasklist.txt
 * 
 */

public class DataStore {
	
	/**
	 * Get the respective path of working directory OS-dependent
	 */
	public static String getFilePath(String nameList){
		
		String workingDirectory = System.getProperty("user.home");  
		String absoluteEvent = workingDirectory + File.separator + nameList;
		
        return absoluteEvent;
	}
	
	/**
	 * Writing all data to distinctive file
	 */
	public static void writeAllData(LogicToStore allData) {
		writeTrash(allData.getTrashbinList());
		writeTask(allData.getTaskList());
	}

	public static void writeTrash(ArrayList<Task> trashData) {
		String absoluteFile = getFilePath(SystemInfo.TRASH_NAME);   	
        
	    writeFile(trashData,
	              absoluteFile,
	              ErrorMSG.WRITE_TRASHERROR);
	}
	
	public static void writeTask(ArrayList<Task> taskData) {
	    String absoluteFile = getFilePath(SystemInfo.EVENT_NAME);
       
        writeFile(taskData, 
        		  absoluteFile, 
                  ErrorMSG.WRITE_EVENTERROR);
	}
	
	
	/**Initializing files with empty data
     * */
    public static void initializeFile() {
        initializeTrash();
        initializeTask();
    }
	
	/**
	 * Writing to specific file
	 * 
	 * @param fileName
	 *            is the (String) name of the event file
	 */
	protected static void writeFile(ArrayList<Task> data,
	                                String fileName,
	                                String errorMesg) {
		try {
		    FileWriter fw = new FileWriter (fileName);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fileOut = new PrintWriter (bw);
			writeLineAL(data, fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			System.out.println(errorMesg + e.toString());
		}
	}
	
	/**
	 * Write to file line by line
	 * 
	 * @param Data
	 *            is the (ArrayList<String>) that stores data
	 * @param fileOut
	 *            (PrintWriter) of the file
	 */
	protected static void writeLineAL(ArrayList<Task> data, PrintWriter fileOut) {
		for (int i = 0; i < data.size(); i++) {
			fileOut.println(toSentence(data.get(i))); 
		}
	}
	
	/**
	 * Initializing Trash by creating a empty trash file
	 * */
	private static void initializeTrash() {
		 String absolutePath = getFilePath(SystemInfo.TRASH_NAME);
	        
	     initalizeWriter(absolutePath, ErrorMSG.TRASH_INITERROR);
	}
	
	/**
	 * Initializing Task by creating a empty task file
     * */
	private static void initializeTask() {      	    	
        String absolutePath = getFilePath(SystemInfo.EVENT_NAME);
        
        initalizeWriter(absolutePath, ErrorMSG.TASK_INITERROR);

    }
	
	private static void initalizeWriter(String fileName, String errorMesg) {
	    try {
            FileWriter fw = new FileWriter (fileName);
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter fileOut = new PrintWriter (bw);
            fileOut.println(SystemInfo.INIT_EMPTY_DATA);
            fileOut.close();
        } catch (Exception e) {
            System.out.println(errorMesg + e.toString());
        }
	}
	
	/**
	 * Helper functions
	 * */
	protected static String toSentence(Task taskLine) {
        return taskLine.toPersonalString();
    }
    
}