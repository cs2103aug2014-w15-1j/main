package read_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import data_store.DataStore;
import data_store.SystemInfo;
import logic.Task;

/**
 * This class read task files and trash files and return them in ArrayList<Task>
 * */
public class ReadFile {

	private ArrayList<Task> EVENTTASK;
    private ArrayList<Task> TRASHFILE;
    
    private ArrayList<Task> EMPTYDATA = new ArrayList<Task>();
	
	public ReadFile() {
		this.EVENTTASK = new ArrayList<Task>();
		this.TRASHFILE = new ArrayList<Task>();
	}
	
	/**
	 * get event ArrayList<Task>
	 */
	public ArrayList<Task> getEventTask() {
	    String systemOS = this.getOS();
        if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
            return getOSEventTask(SystemInfo.EVENT_NAME_SOLARIS);
        } else {
            return getOSEventTask(SystemInfo.EVENT_NAME_WINDOWS);
        }
    }
	
	/**
     * get trash ArrayList<Task>
     */
	public ArrayList<Task> getTrashFile() {
        String systemOS = this.getOS();
        if (systemOS.equals(SystemInfo.SOLARIS_OS)) {
            return getOSTrashFile(SystemInfo.TRASH_NAME_SOLARIS);
        } else {
            return getOSTrashFile(SystemInfo.TRASH_NAME_WINDOWS);
        }
    }
	
	/** 
     * Read tasks file line by line and store them into temporal ArrayList
     * 
     * @param
     *     fileName, is the file name of target reading file
     * @return
     *     return null if not no file exist, else return file content
     */
    private ArrayList<Task> getOSEventTask(String fileName) {
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line = bufferReader.readLine();

            if (line != null) {
                if (!line.isEmpty()) {
                    while (line != null) {
                        this.EVENTTASK.add(makeTask(line));
                        line = bufferReader.readLine();
                    }
                }
            }
            bufferReader.close();
            
            return this.EVENTTASK;

        } catch (FileNotFoundException e) {
            DataStore.initializeFile(); 
            return EMPTYDATA;
            
        } catch (Exception e) {
            System.out.println(ErrorMSG.READ_TASKERROR + e.getMessage());  
            return null;
        }
    }
	
	/** 
	 * Read trash file line by line and store them into temporal ArrayList
	 * 
	 * @param
	 *     fileName, is the file name of target reading file
	 * @return
	 *     return null if not no file exist, else return file content
	 */
    private ArrayList<Task> getOSTrashFile(String fileName) {
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line = bufferReader.readLine();

            if (line != null) {
                if (!line.isEmpty()) {
                    while (line != null) {
                        this.TRASHFILE.add(makeTask(line));
                        line = bufferReader.readLine();
                    }
                }
            }
            bufferReader.close();
            
            return this.TRASHFILE;

        } catch (FileNotFoundException e) {
            DataStore.initializeFile(); 
            return EMPTYDATA;
            
        } catch (Exception e) {
            System.out.println(ErrorMSG.READ_TRASHERROR + e.getMessage()); 
            return null;
        }
    }
    
	/**
	 * create a event
	 */
	private Task makeTask(String taskString) {
		String[] tempoTaskSplit = taskString.split(SystemInfo.SEPERATESIMBOL);
		
		String[] startDateStr = tempoTaskSplit[4].split(SystemInfo.SPLIT_DATE_SYMBOL);
		Date startDate = dateMaker(startDateStr);
		
		String[] endDateStr = tempoTaskSplit[5].split(SystemInfo.SPLIT_DATE_SYMBOL);
        Date endDate = dateMaker(endDateStr);
        
		Task curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
								tempoTaskSplit[2], tempoTaskSplit[3],
								startDate, endDate);
		return curTask;
	}
	
	/**
	 * Make a Date object
	 * */
	@SuppressWarnings("deprecation")
    private Date dateMaker(String[] dateInfo) {
	    try {
    	    return new Date(Integer.parseInt(dateInfo[0]),
                            Integer.parseInt(dateInfo[1]),
                            Integer.parseInt(dateInfo[2]));
    	    
	    } catch (IndexOutOfBoundsException e) {
	        System.err.println(ErrorMSG.DATE_MAKE_ERROR);
	        return null;
	    }
	}
	
	private String getOS() {
        return System.getProperty(SystemInfo.OS_NAME);
    }
}
