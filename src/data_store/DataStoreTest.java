package data_store;

import read_file.ReadFile;
import static org.junit.Assert.*;
import logic.JDate;
import logic.Task;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author A0100792 testing
 * 
 */

@SuppressWarnings("unused")
public class DataStoreTest {
	
    String name = "Test Name";
    String description = "Buy New Birthday Gift";
    String repeatDays = "1 month";
    

    JDate startDate = new JDate(2014, 8, 22);
    JDate endDate = null;
    

    private   Task testTask = new Task(name,  description,
                                       startDate, endDate);

    private   ArrayList<Task> TestTasks;
    private   ArrayList<Task> TestTrash;

    public ReadFile testReadFile;


	@Before 
    public void setUp() { 
		TestTasks = new ArrayList<Task>();
	    TestTrash = new ArrayList<Task>();
		
		testReadFile = new read_file.ReadFile();
		
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
        resultTA = testReadFile.getEventTask();      
        assertTrue(resultTA,TestTasks);
        
		
        /*
         * Test Writing into a trash list file and Retrieving it to check
         */
	    DataStore.writeTrash(TestTrash);
        
        ArrayList<Task> resultTR = new ArrayList<Task>();    
        resultTR = testReadFile.getTrashFile(); 
       
		assertTrue(resultTR,TestTrash);
    }

	private boolean assertTrue(ArrayList<Task> result, ArrayList<Task> testing ) {
		if (result.equals(testing))
			return true;
			else
			return false;	
	}

}
