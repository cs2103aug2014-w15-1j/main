package read_file;

import static org.junit.Assert.*;

import java.util.ArrayList;
import logic.Task;

import org.junit.Before;
import org.junit.Test;


public class ReadFIleTest {

    private ArrayList<Task> trashData;
    private ArrayList<Task> eventData;
    
    private ReadFile testTrash;
    private ReadFile testTask;
            
    @Before 
    public void setUp() { 
    	testTrash = new ReadFile();
    	testTask = new ReadFile();   	
      
    }

    @Test
    public void test() {
        trashData = testTask.getTrashFile();
        String resultTA = trashData.get(0).toString();
        assertEquals("Testing Trash read", resultTA, resultTA);

        eventData = testTrash.getEventTask();
        String resultTR = eventData.get(0).toString();
        assertEquals("Testing Task read", resultTR, resultTR);
        
    }

	public ArrayList<Task> getTrashData() {
		return trashData;
	}

	public void setTrashData(ArrayList<Task> trashData) {
		this.trashData = trashData;
	}

	public ArrayList<Task> getEventData() {
		return eventData;
	}

	public void setEventData(ArrayList<Task> eventData) {
		this.eventData = eventData;
	}
}
