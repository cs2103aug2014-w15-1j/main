package read_file;

import logic.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
	
	private final String EVENTFILENAME = "Eventfile.txt";
	private final String SEPERATESIMBOL = "\\-\\";
	private final String READERROR = "Error while reading file line by line:";
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
			System.out.println(READERROR + e.getMessage());  
			return null;
		}
	}
	
	/**
	 * create a event
	 */
	protected Task makeTask(String taskString) {
		String[] tempoTaskSplit = taskString.split(SEPERATESIMBOL);
		Task curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
								tempoTaskSplit[2], tempoTaskSplit[3],
								tempoTaskSplit[4], tempoTaskSplit[5]);
		
		return curTask;
	}
}
