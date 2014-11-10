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
	Task task;
	int[] display = new int[Default.MAX_DISPLAY_LINE + 1];
	private static boolean arrayEquals(int[] a,int[] b) {
		if(a == b) return true;
		if(a.length != b.length) return false;
		for(int i = 0; i<a.length; i++){
			if(a[i] != b[i]) return false;			
		}
		return true;
	}
	
	@Before
	public void setUp() throws Exception {
		RunLogic.initialize();
		RawCommand1 = new RawCommand("add", "title", null, null, "description");
		task = new Task("title", "description", null, null);
		RawCommand2 = new RawCommand("read", "1");
		for(int i = 0; i < display.length; i++){
			display[i] = -1;
		}
	}

	@Test
	public void test() {
		Command result1 = ConvertCommand.convert(RawCommand1);
		result1.execute();
		assertEquals(true, RunLogic.getTaskList().get(RunLogic.getTaskList().size() - 1).equalIgnorePointer(task));
		
		Command help = new ViewTaskList(null, null);
		help.execute();
		Command result2 = ConvertCommand.convert(RawCommand2);
		result2.execute();
		display[1] = 0;
		assertEquals(true, arrayEquals(RunLogic.getCurrentDisplay(), display));
		
		result1.undo();
	}

}
