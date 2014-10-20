package data_store;
import logic.JDate;
import logic.Task;


import java.util.ArrayList;

import static org.junit.Assert.*;

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


    private   DataStore testStoreTask; 
    private   DataStore testStoreTrash; 
    
    @Before 
    public void setUp() { 
        setTestStoreTask(new DataStore()); 
        setTestStoreTrash(new DataStore()); 

        TestTasks.add(testTask);
        TestTasks.add(testTask);

        TestTrash.add(testTask);
        TestTrash.add(testTask);
    }

    @Test
    public void test() {
        DataStore.writeTask(TestTasks);
        String resultTA = "Tasks results";
        assertEquals("Testing Tasks store", "Tasks results",resultTA);

        DataStore.writeTrash(TestTrash);
        String resultTR = "Store Trash results";

        assertEquals("Testing Trash store", "Store Trash results", resultTR);
    }

	public DataStore getTestStoreTrash() {
		return testStoreTrash;
	}

	public void setTestStoreTrash(DataStore testStoreTrash) {
		this.testStoreTrash = testStoreTrash;
	}

	public DataStore getTestStoreTask() {
		return testStoreTask;
	}

	public void setTestStoreTask(DataStore testStoreTask) {
		this.testStoreTask = testStoreTask;
	}
}
