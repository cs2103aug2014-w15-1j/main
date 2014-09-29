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
	
	private static final String TRASHFILENAME = "Trashfile.txt";
	private static final String EVENTFILENAME = "Eventfile.txt";
	
	private static final String SEPERATESIMBOL = "\\-\\";
	
	/**
	 * Writing all data to distinctive file
	 */
	public static void writeAllData(LogicToStore allData) {
		writeTrash(allData.getTrashbinList());
		writeTask(allData.getTaskList());
	}

	public static void writeTrash(ArrayList<Task> trashData) {
		writeFile(TRASHFILENAME, trashData, TRASHERROR);
	}
	
	public static void writeTask(ArrayList<Task> fileData) {
		writeFile(EVENTFILENAME, fileData, EVENTERROR);
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
		return taskLine.getName() + SEPERATESIMBOL + 
			   taskLine.getDescription() + SEPERATESIMBOL +
			   taskLine.getRepeatTimes() + SEPERATESIMBOL +
			   taskLine.getRepeatDays() + SEPERATESIMBOL + 
			   taskLine.getStartDate() + SEPERATESIMBOL + 
			   taskLine.getEndDate(); 
	}
}
