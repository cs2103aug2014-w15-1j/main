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
 * @author A0100792M/A0119493X
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
     * create a event
     * @return 
     */
    public static Task makeTask(String taskString) {

        String[] tempoTaskSplit = taskString.split(SystemInfo.SEPERATESIMBOL);       
        
        if (tempoTaskSplit.length == 5) {       	
        	/*
        	 if(     tempoTaskSplit[1]=="null" &&
        	 
        			tempoTaskSplit[3].endsWith(SystemInfo.CHECKSTART) && 
        			tempoTaskSplit[4].endsWith(SystemInfo.CHECKEND))
        	{
        		String[] startDateStr = tempoTaskSplit[3].split(SystemInfo.SPLIT_DATE_SYMBOL);
                JDate startDate = dateMaker(startDateStr);
                
                String[] endDateStr = tempoTaskSplit[4].split(SystemInfo.SPLIT_DATE_SYMBOL);
                JDate endDate = dateMaker(endDateStr);
                
                curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[2], startDate, endDate);
                
        	}else if(
        			  tempoTaskSplit[1]!="null" &&
        			  tempoTaskSplit[3]=="null" &&
        			  tempoTaskSplit[4].endsWith(SystemInfo.CHECKEND))
        	{   // add start
        		
                JDate startDate = new JDate();
        		
                String[] endDateStr = tempoTaskSplit[4].split(SystemInfo.SPLIT_DATE_SYMBOL);
                JDate endDate = dateMaker(endDateStr);
                
                curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[1], tempoTaskSplit[2], startDate,endDate);
        	}else if(
        			  tempoTaskSplit[1]!="null" &&
        			  tempoTaskSplit[3].endsWith(SystemInfo.CHECKSTART) &&
        			  tempoTaskSplit[4]=="null")
        	{
        		//add end
        		JDate endDate = new JDate();
        		
        		String[] startDateStr = tempoTaskSplit[3].split(SystemInfo.SPLIT_DATE_SYMBOL);
            	JDate startDate = dateMaker(startDateStr);
            
            	curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[1], tempoTaskSplit[2], startDate,endDate);
            	
            	  curTask = new Task(tempoTaskSplit[0],
            			tempoTaskSplit[1], startDate, tempoTaskSplit[2]);
            	
        	}else if(
        			  tempoTaskSplit[1]!="null" &&
        			  tempoTaskSplit[3]=="null" &&
        			  tempoTaskSplit[4]=="null")
        	{	
        		JDate startDate = new JDate();
        		JDate endDate = new JDate();
        		
        		curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[1], tempoTaskSplit[2], startDate,endDate);
        		
        		 curTask = new Task(tempoTaskSplit[0],
                    tempoTaskSplit[1], tempoTaskSplit[2]);
                    
        	}else if(
        			tempoTaskSplit[1]=="null" &&
        		    tempoTaskSplit[3]=="null" &&
              	    tempoTaskSplit[4]=="null")
        	{
        		JDate startDate = new JDate();
        		JDate endDate = new JDate();
        		
        		curTask = new Task(tempoTaskSplit[0],
                         tempoTaskSplit[2], startDate,endDate);
        		
        		curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[2]);  
                         
        	}else if(
        			  tempoTaskSplit[1]=="null" &&
        			  tempoTaskSplit[3]=="null" &&
        			  tempoTaskSplit[4].endsWith(SystemInfo.CHECKEND))
        	{
        		JDate startDate = new JDate();
        		
        		String[] endDateStr = tempoTaskSplit[2].split(SystemInfo.SPLIT_DATE_SYMBOL);
                JDate endDate = dateMaker(endDateStr);
                
                curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[2], startDate,endDate);
                curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[1], endDate);
                        
        	}else if(
        			  tempoTaskSplit[1]=="null" &&
        			  tempoTaskSplit[3].endsWith(SystemInfo.CHECKSTART) &&
        			  tempoTaskSplit[4]=="null")
        	{
        		JDate endDate = new JDate();
        		
        		String[] startDateStr = tempoTaskSplit[2].split(SystemInfo.SPLIT_DATE_SYMBOL);
                JDate startDate = dateMaker(startDateStr);
                
                curTask = new Task(tempoTaskSplit[0],
                        tempoTaskSplit[2], startDate,endDate);
                curTask = new Task(tempoTaskSplit[0],
                        startDate,tempoTaskSplit[1]);
                        
        	
        	}else{
        	*/
        		// ALL 5 FIELDS
          		
        		String[] startDateStr = tempoTaskSplit[3].split(SystemInfo.SPLIT_DATE_SYMBOL);                

                String[] endDateStr = tempoTaskSplit[4].split(SystemInfo.SPLIT_DATE_SYMBOL);              

                if(startDateStr[0].equals("null") && endDateStr[0].equals("null"))
                {
          			
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                            tempoTaskSplit[2]);
                    
                }else if(startDateStr[0].equals("null") && !endDateStr.equals("null"))
                {
                	JDate endDate = dateMaker(endDateStr);
                	
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                            tempoTaskSplit[2], endDate);
                	
                }else if (endDateStr[0].equals("null") && !startDateStr.equals("null"))
                {
                	JDate startDate = dateMaker(startDateStr);
              		
          			curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                        startDate, tempoTaskSplit[2]);

                }else{
                	
                	JDate startDate = dateMaker(startDateStr);
                	JDate endDate = dateMaker(endDateStr);
                	
                	curTask = new Task(tempoTaskSplit[0], tempoTaskSplit[1],
                        tempoTaskSplit[2], startDate, endDate);
                }      	
        	
        } else{
            ErrorGenerator.popError(ErrorMSG.TASK_FORMAT_ERR);
            return null;       
        }
        
        return curTask;
    }

    /**
     * Make a Date object
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
