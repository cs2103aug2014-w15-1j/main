package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CommandType.*;
import parser.RawCommand;

public class ConvertCommandTest {
	RawCommand RawCommand1 = new RawCommand("add", "title", "description");
	RawCommand RawCommand2 = new RawCommand("rename", "1", "new name");
	RawCommand RawCommand3 = new RawCommand("view", "trashbin");
	RawCommand RawCommand4 = new RawCommand("lllll");
	Command result1 = new Add(new Task("title", "description"), "New task added successfully!", "Task List");
	Command result2 = new Rename("1", "new name", "Task rename successfully!", )
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
