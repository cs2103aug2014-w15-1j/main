package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CommandType.*;
import parser.RawCommand;

/**
 * 
 * @author a0119456Y
 *
 */
public class ConvertCommandTest {
	RawCommand RawCommand1;
	RawCommand RawCommand2;
	RawCommand RawCommand3;
	Command expectedResult1;
	Command expectedResult2;
	Command expectedResult3;
	
	@Before
	public void setUp() throws Exception {
		RawCommand1 = new RawCommand("add", "title", "description");
		RawCommand2 = new RawCommand("view", "trashbin");
		RawCommand3 = new RawCommand("lllll");
		expectedResult1 = new Add(new Task("title", "description"), "New task added successfully!", "Task List");
		expectedResult2 = new ViewTrashBin(0, "View mode changed!", "Trash bin");
		expectedResult3 = new Invalid("Invalid Command!");
	}

	@Test
	public void test() {
		Command result1 = ConvertCommand.convert(RawCommand1);
		assertEquals(expectedResult1, result1);
		
		Command result2 = ConvertCommand.convert(RawCommand2);
		assertEquals(expectedResult1, result2);
		
		Command result3 = ConvertCommand.convert(RawCommand3);
		assertEquals(expectedResult1, result3);
	}

}
