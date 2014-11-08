package read_file;

import data_store.DataStore;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logic.JDate;
import logic.Task;

import org.junit.Before;
import org.junit.Test;

/*
 * @author A0100792 testing
 * 
 */
@SuppressWarnings("unused")
public class ReadFIleTest {

	String name = "Test Name";
    String description = "Buy New Birthday Gift";
    String repeatDays = "1 month";
    

    JDate startDate = null;
    JDate endDate = new JDate(2014, 8, 22);
    //new JDate(2014, 8, 22)

    private   Task testTask = new Task(name, description, 
                                       repeatDays, startDate, endDate);

    private ArrayList<Task> trashData;
    private ArrayList<Task> eventData;
    
    private ReadFile testReadTrash;
    private ReadFile testReadTask;
    
            
    @Before 
    public void setUp() { 
    	testReadTrash = new ReadFile();
    	testReadTask = new ReadFile();
    	
    	trashData = new ArrayList<Task>();
        eventData = new ArrayList<Task>();
    	
    	trashData.add(testTask);
    	eventData.add(testTask);
    }

    @Test
    public void test() {
    	
    	
    	DataStore.writeTrash(trashData);
    	DataStore.writeTask(eventData);
      
        ArrayList<Task> resultTrash = testReadTrash.getTrashFile();
        assertTrue(trashData,resultTrash);
        

        ArrayList<Task> resultEvent = testReadTask.getEventTask();
        assertTrue(eventData,resultEvent);
        
    }

	private boolean assertTrue(ArrayList<Task> event,ArrayList<Task> result){
		if (event.equals(result))
			return true;
		else 
			return false;
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
