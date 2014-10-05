package data_store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import logic.Task;
import logic.LogicToStore;

import error_handle.ErrorMSG;

/**
 * This class stores data to file by passing (String)filename
 * */

public class DataStore {
	
	/**
	 * Writing all data to distinctive file
	 */
	public static void writeAllData(LogicToStore allData) {
		writeTrash(allData.getTrashbinList());
		writeTask(allData.getTaskList());
	}

	public static void writeTrash(ArrayList<Task> trashData) {
	    String systemOS = getOS();
	    if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
	        writeFile(trashData, 
	                  SystemInfo.TRASH_NAME_SOLARIS,
	                  ErrorMSG.WRITE_TRASHERROR);
	    } else {
	        writeFile(trashData,
	                  SystemInfo.TRASH_NAME_WINDOWS,
	                  ErrorMSG.WRITE_TRASHERROR);
	    }
	}
	
	public static void writeTask(ArrayList<Task> taskData) {
	    String systemOS = getOS();
        if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
            writeFile(taskData, 
                      SystemInfo.EVENT_NAME_SOLARIS, 
                      ErrorMSG.WRITE_EVENTERROR);
        } else {
            writeFile(taskData, 
                      SystemInfo.EVENT_NAME_WINDOWS, 
                      ErrorMSG.WRITE_EVENTERROR);
        }
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
	    String systemOS = getOS();
        if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
            initalizeWriter(SystemInfo.TRASH_NAME_SOLARIS, ErrorMSG.TRASH_INITERROR);
        } else {
            initalizeWriter(SystemInfo.TRASH_NAME_WINDOWS, ErrorMSG.TRASH_INITERROR);
        }
	}
	
	/**
	 * Initializing Task by creating a empty task file
     * */
	private static void initializeTask() {
        String systemOS = getOS();
        if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
            initalizeWriter(SystemInfo.EVENT_NAME_SOLARIS, ErrorMSG.TASK_INITERROR);
        } else {
            initalizeWriter(SystemInfo.EVENT_NAME_WINDOWS, ErrorMSG.TASK_INITERROR);
        }
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
    
    private static String getOS() {
        return System.getProperty(SystemInfo.OS_NAME);
    }
}
