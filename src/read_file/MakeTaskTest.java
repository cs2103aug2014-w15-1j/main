package read_file;

import logic.JDate;
import logic.Task;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
    
    //4 fields - No End Date
    private   Task testTask4 = new Task(name, description, 
             startDate, repeatDays);
    
    //4 fields - No Start Date
    private   Task testTask44 = new Task(name, description,
    		 repeatDays, endDate);
    
    //3 fields - No Start Date No End Date
    private   Task testTask3 = new Task(name, description, 
            repeatDays);
   
    public ReadFile testMakeTask;
	
	@Before
	public void setUp() throws Exception {
		
		testMakeTask = new read_file.ReadFile();		
	}


	@Test
	public void test() {
		//5 fields
		Task test5;
		String case5 = "Hello=Go shopping=1 month=2014-9-22-start=2014-12-22-end";
		test5 = ReadFile.makeTask(case5);
		assertTrue(test5,testTask5);
		
		//4 fields
		Task test4;
		String case4 = "Hello=Go shopping=1 month=2014-9-22-start";
		test4 = ReadFile.makeTask(case4);
		assertTrue(test4,testTask4);
		
		Task test44;
		String case44 = "Hello=Go shopping=1 month=2014-12-22-end";
		test44 = ReadFile.makeTask(case44);
		assertTrue(test44, testTask44);
						
		//3 fields
		Task test3;
		String case3 = "Hello=Go shopping=1 month";
		test3 = ReadFile.makeTask(case3);
		assertTrue(test3,testTask3);
						
		
	}
	
	private boolean assertTrue(Task result, Task expected) {
		if(result.equals(expected))
			return true;
		else
			return false;
		}

}
