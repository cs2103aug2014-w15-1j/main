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
    	String filePathName = data_store.DataStore.getFilePath(SystemInfo.EVENT_NAME);
        
    	return getOSEventTask(filePathName);
    }

    /**
     * get trash ArrayList<Task>
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
     * @return
     *      Return a Task object if local file format valid
     *      Else return null
     */
    private Task makeTask(String taskString) {

        String[] tempoTaskSplit = taskString.split(SystemInfo.SEPERATESIMBOL);
        
        if (tempoTaskSplit.length == 5) {
            String[] startDateStr = tempoTaskSplit[3].split(SystemInfo.SPLIT_DATE_SYMBOL);
            JDate startDate = dateMaker(startDateStr);
    
            String[] endDateStr = tempoTaskSplit[4].split(SystemInfo.SPLIT_DATE_SYMBOL);
            JDate endDate = dateMaker(endDateStr);
    
            Task curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                    tempoTaskSplit[2], startDate, endDate);
            return curTask;
        } else {
            ErrorGenerator.popError(ErrorMSG.TASK_FORMAT_ERR);
            return null;
        }
    }

    /**
     * Make a Date object
     * */
    private JDate dateMaker(String[] dateInfo) {
        try {
            return new JDate(Integer.parseInt(dateInfo[0]),
                    Integer.parseInt(dateInfo[1]),
                    Integer.parseInt(dateInfo[2]));

        } catch (IndexOutOfBoundsException e) {
            System.err.println(ErrorMSG.DATE_MAKE_ERROR);
            return null;
        }
    }

}
