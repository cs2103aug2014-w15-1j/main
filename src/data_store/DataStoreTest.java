package data_store;

import read_file.ReadFile;
import static org.junit.Assert.*;
import logic.JDate;
import logic.Task;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DataStoreTest {
	
    String name = "Test Name";
    String description = "Buy New Birthday Gift";
    String repeatTimes = "twice a day";
    String repeatDays = "1 month";
    

    JDate startDate = new JDate(2014, 8, 22);
    JDate endDate = new JDate(2014, 9, 22);
    

    private   Task testTask = new Task(name, description, 
                                       repeatDays, startDate, endDate);

    private   ArrayList<Task> TestTasks = new ArrayList<Task>();
    private   ArrayList<Task> TestTrash = new ArrayList<Task>();

    public ReadFile testRead;


	@Before 
    public void setUp() { 
		
		testRead = new ReadFile();
		
        TestTasks.add(testTask);
        TestTrash.add(testTask);

    }
 
	@Test
    public void test() {
		  	
		/*
		 * Test Writing into a task list file and Retrieving it to check it
		 */
        DataStore.writeTask(TestTasks);       
        
        ArrayList<Task> resultTA = new ArrayList<Task>();    
        resultTA = testRead.getEventTask();      
        assertTrue(resultTA.equals(TestTasks));
        
        /*
         * Test Writing into a trash list file and Retrieving it to check
         */
        DataStore.writeTrash(TestTrash);
        
        ArrayList<Task> resultTR = new ArrayList<Task>();    
        resultTR = testRead.getTrashFile(); 
        assertTrue(resultTR.equals(TestTrash));
        
    }

}
