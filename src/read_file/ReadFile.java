package read_file;

import logic.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import data_store.DataStore;

public class ReadFile {
	
    private static final String SOLARIS_OS = "Mac OS X";
    private static final String TRASH_NAME_SOLARIS = "/Users/shared/Trashfile.txt";
    private static final String EVENT_NAME_SOLARIS = "/Users/shared/Taskfile.txt";
    
    private static final String TRASH_NAME_WINDOWS = "c:\\用户\\Trashfile.txt";
    private static final String EVENT_NAME_WINDOWS = "c:\\用户\\Taskfile.txt";
    
	private final String SEPERATESIMBOL = "=";
	private final String READTASKERROR = "Error while reading task file line by line:";
	private final String READTRASHERROR = "Error while reading trash file line by line:";
	
	private ArrayList<Task> EVENTTASK;
    private ArrayList<Task> TRASHFILE;
	
	public ReadFile() {
		this.EVENTTASK = new ArrayList<Task>();
		this.TRASHFILE = new ArrayList<Task>();
	}
	
	/**
	 * get event ArrayList<Task>
	 */
	public ArrayList<Task> getEventTask() {
	    String systemOS = this.getOS();
        if (systemOS.equals(SOLARIS_OS)) {
            return getOSEventTask(EVENT_NAME_SOLARIS);
        } else {
            return getOSEventTask(EVENT_NAME_WINDOWS);
        }
    }
	
	private ArrayList<Task> getOSEventTask(String fileName) {
		try {
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			// Read file line by line and store them into temperal ArrayList
			while ((line = bufferReader.readLine()) != null) {
				this.EVENTTASK.add(makeTask(line));
			}
			bufferReader.close();
			
			return this.EVENTTASK;

		} catch (FileNotFoundException e) {
            DataStore.initializeFile(); 
            return getEventTask();
            
        } catch (Exception e) {
			System.out.println(READTASKERROR + e.getMessage());  
			return null;
		}
	}
	
	/**
     * get trash ArrayList<Task>
     */
	public ArrayList<Task> getTrashFile() {
        String systemOS = this.getOS();
        if (systemOS.equals(SOLARIS_OS)) {
            return getOSTrashFile(TRASH_NAME_SOLARIS);
        } else {
            return getOSTrashFile(TRASH_NAME_WINDOWS);
        }
    }
	
    private ArrayList<Task> getOSTrashFile(String fileName) {
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line;

            // Read file line by line and store them into temporal ArrayList
            while ((line = bufferReader.readLine()) != null) {
                this.TRASHFILE.add(makeTask(line));
            }
            bufferReader.close();
            
            return this.TRASHFILE;

        } catch (FileNotFoundException e) {
            DataStore.initializeFile(); 
            return getTrashFile();
            
        } catch (Exception e) {
            System.out.println(READTRASHERROR + e.getMessage()); 
            return null;
        }
    }
	
	/**
	 * create a event
	 */
	protected Task makeTask(String taskString) {
		String[] tempoTaskSplit = taskString.split(SEPERATESIMBOL);
		
		String[] startDateStr = tempoTaskSplit[4].split("-");
		Date startDate = new Date(Integer.parseInt(startDateStr[0]),
		                          Integer.parseInt(startDateStr[1]),
		                          Integer.parseInt(startDateStr[2]));
		
		String[] endDateStr = tempoTaskSplit[5].split("-");
        Date endDate = new Date(Integer.parseInt(endDateStr[0]),
                                Integer.parseInt(endDateStr[1]),
                                Integer.parseInt(endDateStr[2]));
        
		Task curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
								tempoTaskSplit[2], tempoTaskSplit[3],
								startDate, endDate);
		
		return curTask;
	}
	
	private String getOS() {
        return System.getProperty("os.name");
    }
}
