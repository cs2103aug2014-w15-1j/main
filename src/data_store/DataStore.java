package data_store;
import logic.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class stores data to file by passing (String)filename
 * Initialization with parameter (ArrayList<String>) data is required
 * */

class DataStore {
	
	private ArrayList<String> TRASHDATA;
	private ArrayList<String> EVENTDATA;
	
	private final String TRASHERROR = " Trash file writing error ";
	private final String EVENTERROR = " Event file writing error ";
	
	private final String TRASHFILENAME = "Trashfile.txt";
	private final String EVENTFILENAME = "Eventfile.txt";
	
	/**
	 * ========= Constructor
	 * */
	public DataStore() {
		this.TRASHDATA = new ArrayList<String>();
		this.EVENTDATA = new ArrayList<String>();
	}
	
	public DataStore(ArrayList<String> trash, ArrayList<String> event) {
		this.TRASHDATA = trash;
		this.EVENTDATA = event;
	}
	
	/**
	 * ========= Methods
	 * */
	
	/**
	 * Writing all data to distinctive file
	 */
	public void writeAllData() {
		writeTrash();
		writeEvent();
	}
	
	/**
	 * Only write to trash file
	 */
	public void writeTrash() {
		writeFile(TRASHFILENAME, TRASHDATA, TRASHERROR);
	}
	
	/**
	 * Only write to event file
	 */
	public void writeEvent() {
		writeFile(EVENTFILENAME, EVENTDATA, EVENTERROR);
	}
	
	/**
	 * Writing to specific file
	 * 
	 * @param fileName
	 *            is the (String) name of the event file
	 */
	protected void writeFile(String fileName, ArrayList<String> data, String errorMesg) {
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
	protected void writeLineAL(ArrayList<String> data, PrintWriter fileOut) {
		for (int i = 0; i < data.size(); i++) {
			fileOut.println(data.get(i)); 
		}
	}
}
