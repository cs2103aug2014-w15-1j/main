package read_file;
import data_store.DataStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import data_store.SystemInfo;
import logic.JDate;
import logic.Task;

/**
 * @author A0100792M
 *        
 * 
 * This class read task files and trash files and return them in ArrayList<Task>
 *            creation of Task objects that includes null fields from string in files
 * */
public class ReadFile {

    private  static ArrayList<Task> EVENTTASK;
    private  static ArrayList<Task> TRASHFILE;

    private static ArrayList<Task> EMPTYDATA = new ArrayList<Task>();
	private static Task curTask;

    public ReadFile() {
        this.setEVENTTASK(new ArrayList<Task>());
        this.setTRASHFILE(new ArrayList<Task>());
    }
    
    
    /**
     * Retrieve ArrayList of Taskfile
     * 
     * @return
     * 		return event ArrayList<Task> object
     */
    public ArrayList<Task> getEventTask() {
    	String filePathName = data_store.DataStore.getFilePath(SystemInfo.EVENT_NAME);
        
    	return getOSEventTask(filePathName);
    }

    /**
     * 
     * Retrieve ArrayList of Trashfile
     * 
     * @return
     * 		return trash ArrayList<Task>
     */
    public ArrayList<Task> getTrashFile() {
      String filePathName = data_store.DataStore.getFilePath(SystemInfo.TRASH_NAME);
       
      return getOSTrashFile(filePathName);
    }

    /** 
     * Read tasks file line by line and store them into temporal ArrayList
     * 
     * @param
     *     fileName, is the file name of target reading file
     * @return
     *     return null if not no file exist, else return file content
     */
    private static ArrayList<Task> getOSEventTask(String fileName) {
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line = bufferReader.readLine();

            if (line != null) {
                if (!line.isEmpty()) {
                    while (line != null) {
                        EVENTTASK.add(makeTask(line));
                        line = bufferReader.readLine();
                    }
                }
            }
            bufferReader.close();

            return EVENTTASK;

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
    private static ArrayList<Task> getOSTrashFile(String fileName) {
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line = bufferReader.readLine();
            
            if (line != null) {
            	  
                if (!line.isEmpty()) { 
                    while (line != null) {
                        TRASHFILE.add(makeTask(line));
                        line = bufferReader.readLine();
                      
                    }
                }
            }
            bufferReader.close();

            return TRASHFILE;

        } catch (FileNotFoundException e) {
            DataStore.initializeFile(); 
            return EMPTYDATA;

        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(ErrorMSG.READ_TRASHERROR + e.getMessage()); 
            return null;
        }
 
    }

    /**
     * Make a Task object from string in file
     * 
     * @return
     * 		Task object
     */
    public static Task makeTask(String taskString) {

        String[] tempoTaskSplit = taskString.split(SystemInfo.SEPERATESIMBOL);       
        
        if (tempoTaskSplit.length == 5) {       	
        	
          		
        		String[] startDateStr = tempoTaskSplit[2].split(SystemInfo.SPLIT_DATE_SYMBOL);                

                String[] endDateStr = tempoTaskSplit[3].split(SystemInfo.SPLIT_DATE_SYMBOL);              

                /*
                if(startDateStr[0].equals("null") && endDateStr[0].equals("null"))
                {
          			
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1]);
                    
                }else if(startDateStr[0].equals("null") && !endDateStr[0].equals("null"))
                {
                	JDate endDate = dateMaker(endDateStr);
                	
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1], endDate);
                	
                }else if (endDateStr[0].equals("null") && !startDateStr[0].equals("null"))
                {
                	JDate startDate = dateMaker(startDateStr);
                	JDate endDate = null;
              		
          			curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                        startDate, endDate);

                }else{
                	
                	JDate startDate = dateMaker(startDateStr);
                	JDate endDate = dateMaker(endDateStr);
                	
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1], startDate, endDate);
                }    
                */
                JDate startDate = null;
                JDate endDate = null;
                if(!startDateStr[0].equals("null")){
                	startDate = dateMaker(startDateStr);
                }
                if(!endDateStr[0].equals("null")){
                	endDate = dateMaker(endDateStr);
                }
                curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1], startDate, endDate);
                
                if(tempoTaskSplit[4].equalsIgnoreCase(SystemInfo.CHECKDONE)){
                	curTask.setDone();
                } else {
                	curTask.setUndone();
                }
        	
        } else{
            ErrorGenerator.popError(ErrorMSG.TASK_FORMAT_ERR);
            return null;       
        }
        
        return curTask;
    }

    /**
     * Make a JDate object from a string representing date
     * 
     * @return
     *  	JDate object of date
     * */
    private static JDate dateMaker(String[] dateInfo) {
        try {
            return new JDate(Integer.parseInt(dateInfo[0]),
                    Integer.parseInt(dateInfo[1]),
                    Integer.parseInt(dateInfo[2]));

        } catch (IndexOutOfBoundsException e) {
            System.err.println(ErrorMSG.DATE_MAKE_ERROR);
            return null;
        }
    }


	public ArrayList<Task> getEVENTTASK() {
		return EVENTTASK;
	}


	public void setEVENTTASK(ArrayList<Task> eVENTTASK) {
		EVENTTASK = eVENTTASK;
	}


	public ArrayList<Task> getTRASHFILE() {
		return TRASHFILE;
	}


	public void setTRASHFILE(ArrayList<Task> tRASHFILE) {
		TRASHFILE = tRASHFILE;
	}
	

}
