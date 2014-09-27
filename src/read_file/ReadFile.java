package read_file;

import data_store.DataStore;
import logic.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
	
	private final String EVENTFILENAME = "Eventfile.txt";
	private final String SEPERATESIMBOL = "\\-\\";
	private ArrayList<Task> EVENTTASK;
	
	public ReadFile() {
		this.EVENTTASK = new ArrayList<Task>();
	}
	
	/**
	 * get event ArrayList<Task>
	 */
	public ArrayList<Task> getEventTask() {
		try {
			FileReader inputFile = new FileReader(EVENTFILENAME);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			// Read file line by line and store them into temperal ArrayList
			while ((line = bufferReader.readLine()) != null) {
				this.EVENTTASK.add(makeTask(line));
			}
			bufferReader.close();
			
			return this.EVENTTASK;

		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());  
			return null;
		}
	}

	public Task makeTask(String taskString) {
		String[] tempoTaskSplit = taskString.split(SEPERATESIMBOL);
		Task curTask = new Task();
		curTask = addtoTask(tempoTaskSplit, curTask);
		
		return curTask;
	}
	
	protected Task addtoTask(String[] tempoTaskSplit, Task curTask) {
		return curTask;
	}
}
