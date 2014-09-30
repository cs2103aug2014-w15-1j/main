package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gui.VIEW_MODE;
import main.StartUp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunLogicTest {

	@Before
	public void setUp() throws Exception {
		GUIStatus initialGUI = new GUIStatus(VIEW_MODE.TASK, false, false, 0);
		ArrayList<Task> initialTaskList = new ArrayList<Task>();
		initialTaskList.add(new Task("Tast1"));
		ArrayList<Task> initialTrashbinList = new ArrayList<Task>();
		int[] initialDisplay = new int[StartUp.MAX_DISPLAY_LINE + 1];
		for(int i : initialDisplay){
			i = -1;
		}
		initialDisplay[1] = 0;
		RunLogic.initialize(initialGUI, initialTaskList, initialTrashbinList, initialDisplay, "20140930");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}