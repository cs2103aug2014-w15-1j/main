package read_file;

import static org.junit.Assert.*;

import java.util.ArrayList;
import logic.Task;

import org.junit.Before;
import org.junit.Test;


public class ReadFIleTest {

    private ReadFile testRead;
    private ArrayList<Task> trashData;
    private ArrayList<Task> eventData;
            
    @Before 
    public void setUp() { 
        testRead= new ReadFile(); 
    }

    @Test
    public void test() {
        trashData = testRead.getTrashFile();
        String resultTA = trashData.get(0).toString();
        assertEquals("Testing Trash read", resultTA, resultTA);

        eventData = testRead.getEventTask();
        String resultTR = eventData.get(0).toString();
        assertEquals("Testing Task read", resultTR, resultTR);
    }
}
