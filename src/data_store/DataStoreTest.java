package data_store;

import logic.Task;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataStoreTest {

    private   DataStore testStoreTask; 
    private   DataStore testStoreTrash; 

    String name = "Test Name";
    String description = "Buy New Birthday Gift";
    String repeatTimes = "twice a day";
    String repeatDays = "1 month";
    
    @SuppressWarnings("deprecation")
    Date startDate = new Date(2014, 8, 22);
    Date endDate = new Date(2014, 9, 22);


    private   Task testTask = new Task(name, description, repeatTimes, 
                                       repeatDays, startDate, endDate);

    private   ArrayList<Task> TestTasks = new ArrayList<Task>();
    private   ArrayList<Task> TestTrash = new ArrayList<Task>();

    @Before 
    public void setUp() { 
        testStoreTask= new DataStore(); 
        testStoreTrash= new DataStore(); 

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
}
