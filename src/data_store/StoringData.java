package data_store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/* This class stores data to file by passing (String)filename
 * Initialization with parameter (ArrayList<String>) data is required
 * */

class StoringData {
	
	private ArrayList<String> TRASHDATA;
	private ArrayList<String> EVENTDATA;
	private String TRASHERROR = " Trash file writing error ";
	private String EVENTERROR = " Event file writing error ";
	
	public StoringData() {
		this.TRASHDATA = new ArrayList<String>();
		this.EVENTDATA = new ArrayList<String>();
	}
	
	public StoringData(ArrayList<String> trash, ArrayList<String> event) {
		this.TRASHDATA = trash;
		this.EVENTDATA = event;
	}
	
	public void writeAllData(String TrashFileName, String EventFileName) {
		writeTrash(TrashFileName);
		writeEvent(EventFileName);
	}
	
	public void writeTrash(String TrashFileName) {
		try {
			FileWriter fw = new FileWriter (TrashFileName);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fileOut = new PrintWriter (bw);
			writeLineAL(this.TRASHDATA, fileOut);
			fileOut.close();
		}
		catch (Exception e) {
			System.out.println(TRASHERROR + e.toString());
		}
	}
	
	public void writeEvent(String EventFileName) {
		try {
			FileWriter fw = new FileWriter (EventFileName);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fileOut = new PrintWriter (bw);
			writeLineAL(this.EVENTDATA, fileOut);
			fileOut.close();
		}
		catch (Exception e) {
			System.out.println(EVENTERROR + e.toString());
		}
	}
	
	protected void writeLineAL(ArrayList<String> Data, PrintWriter fileOut) {
		for (int i = 0; i < Data.size(); i++) {
			fileOut.println(Data.get(i)); 
		}
	}
	
}
