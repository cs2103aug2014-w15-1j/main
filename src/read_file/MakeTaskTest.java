package read_file;

import logic.JDate;

import logic.Task;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 * @author A0100792 more testing
 */
@SuppressWarnings("unused")
public class MakeTaskTest {
	
    String name = "Hello";
    String description = "Go shopping";
    String repeatDays = "1 month";
    
    JDate startDate = new JDate(2014, 9, 22);
    JDate endDate = null;
    
    //5 fields
    private   Task testTask5 = new Task(name, description, 
            repeatDays, startDate, endDate);

    public ReadFile testMakeTask;
	
	@Before
	public void setUp() throws Exception {
		
		testMakeTask = new read_file.ReadFile();		
	}


	@Test
	public void test() {
		//5 fields
		Task test5;
		String case5 = "Hello=Go shopping=1 month=2014-9-22-start=null";
		test5 = ReadFile.makeTask(case5);
		assertTrue(test5,testTask5);						
		
	}
	
	private boolean assertTrue(Task result, Task expected) {
		if(result.equals(expected))
			return true;
		else
			return false;
		}

}
