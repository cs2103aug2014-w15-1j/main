package data_store;

import logic.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class stores data to file by passing (String)filename
 * Initialization with parameter (ArrayList<String>) data is required
 * */

public class DataStore {
	
	private final String TRASHERROR = " Trash file writing error ";
	private final String EVENTERROR = " Event file writing error ";
	
	private final String TRASHFILENAME = "Trashfile.txt";
	private final String EVENTFILENAME = "Eventfile.txt";
	
	private final String SEPERATESIMBOL = "\\-\\";
	
	/**
	 * ========= Constructor
	 * */
	public DataStore() {}
	
	/**
	 * ========= Methods
	 * */
	
	/**
	 * Writing all data to distinctive file
	 */
	public void writeAllData(ArrayList<Task> trashData, ArrayList<Task> fileData) {
		writeTrash(trashData);
		writeEvent(fileData);
	}

	public void writeTrash(ArrayList<Task> trashData) {
		writeFile(TRASHFILENAME, trashData, TRASHERROR);
	}
	
	public void writeEvent(ArrayList<Task> fileData) {
		writeFile(EVENTFILENAME, fileData, EVENTERROR);
	}
	
	/**
	 * Writing to specific file
	 * 
	 * @param fileName
	 *            is the (String) name of the event file
	 */
	protected void writeFile(String fileName, ArrayList<Task> data, String errorMesg) {
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
	protected void writeLineAL(ArrayList<Task> data, PrintWriter fileOut) {
		for (int i = 0; i < data.size(); i++) {
			fileOut.println(toSentence(data.get(i))); 
		}
	}
	
	protected String toSentence(Task taskLine) {
		return taskLine.getName() + SEPERATESIMBOL + 
			   taskLine.getDescription() + SEPERATESIMBOL +
			   taskLine.getRepeatTimes() + SEPERATESIMBOL +
			   taskLine.getRepeatDays() + SEPERATESIMBOL + 
			   taskLine.getStartDate() + SEPERATESIMBOL + 
			   taskLine.getEndDate(); 
	}
}
