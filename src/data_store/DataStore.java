package data_store;

import logic.Task;
import logic.LogicToStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class stores data to file by passing (String)filename
 * Initialization with parameter (ArrayList<String>) data is required
 * */

public class DataStore {
	
	private static final String TRASHERROR = " Trash file writing error ";
	private static final String EVENTERROR = " Event file writing error ";
	
	private static final String TRASHINITERROR = " Trash file initializing writing error ";
    private static final String EVENTINITERROR = " Event file initializing writing error ";
	
	private static final String SOLARIS_OS = "Mac OS X";
	private static final String TRASH_NAME_SOLARIS = "/Users/shared/Trashfile.txt";
	private static final String EVENT_NAME_SOLARIS = "/Users/shared/Taskfile.txt";
	
	private static final String TRASH_NAME_WINDOWS = "c:\\Program Files\\Trashfile.txt";
    private static final String EVENT_NAME_WINDOWS = "c:\\Program Files\\Taskfile.txt";
	
	private static final String EMPTY_DATA = "";
	private static final String SEPERATESIMBOL = "=";
	
	/**
	 * Writing all data to distinctive file
	 */
	public static void writeAllData(LogicToStore allData) {
		writeTrash(allData.getTrashbinList());
		writeTask(allData.getTaskList());
	}

	public static void writeTrash(ArrayList<Task> trashData) {
	    String systemOS = getOS();
	    if (systemOS.equals(SOLARIS_OS)) {
	        writeFile(TRASH_NAME_SOLARIS, trashData, TRASHERROR);
	    } else {
	        writeFile(TRASH_NAME_WINDOWS, trashData, TRASHERROR);
	    }
	}
	
	public static void writeTask(ArrayList<Task> fileData) {
	    String systemOS = getOS();
        if (systemOS.equals(SOLARIS_OS)) {
            writeFile(EVENT_NAME_SOLARIS, fileData, EVENTERROR);
        } else {
            writeFile(EVENT_NAME_WINDOWS, fileData, EVENTERROR);
        }
	}
	
	/**Initializing file for the start of the program
     * */
    public static void initializeFile() {
        // Initialize Empty Data
        initializeTrash();
        initializeTask();
    }
	
	/**
	 * Writing to specific file
	 * 
	 * @param fileName
	 *            is the (String) name of the event file
	 */
	protected static void writeFile(String fileName, ArrayList<Task> data, String errorMesg) {
		try {
		    FileWriter fw = new FileWriter (fileName);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fileOut = new PrintWriter (bw);
			writeLineAL(data, fileOut);
			fileOut.close();
		}
		catch (Exception e) {
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
	
	protected static String toSentence(Task taskLine) {
	    return taskLine.toPersonalString();
	}
	
	private static String getOS() {
	    return System.getProperty("os.name");
	}
	
	/**
	 * Initializing Trash by creating a empty trash file
	 * */
	private static void initializeTrash() {
	    String systemOS = getOS();
        if (systemOS.equals(SOLARIS_OS)) {
            initalizeWriter(TRASH_NAME_SOLARIS, TRASHINITERROR);
        } else {
            initalizeWriter(TRASH_NAME_WINDOWS, TRASHINITERROR);
        }
	}
	
	/**
	 * Initializing Task by creating a empty task file
     * */
	private static void initializeTask() {
        String systemOS = getOS();
        if (systemOS.equals(SOLARIS_OS)) {
            initalizeWriter(EVENT_NAME_SOLARIS, EVENTINITERROR);
        } else {
            initalizeWriter(EVENT_NAME_WINDOWS, EVENTINITERROR);
        }
    }
	
	private static void initalizeWriter(String fileName, String errorMesg) {
	    try {
            FileWriter fw = new FileWriter (fileName);
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter fileOut = new PrintWriter (bw);
            fileOut.println(EMPTY_DATA);
            fileOut.close();
        }
        catch (Exception e) {
            System.out.println(errorMesg + e.toString());
        }
	}
}
