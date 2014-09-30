package cli;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CliJunit {
	String testString;
	String[] input;
	String[] expected;

	@Before
	public void setUp() throws Exception {
		testString = "add=meeting=300914=null=school canteen";	
		input = new String[7];
		expected = new String[7];
				
	}

	@After
	public void tearDown() throws Exception {
		
	}

	// Function: separate
	@Test 
	public void test() {
		expected[0] = "add";
		expected[1] = "meeting";
		expected[2] = "300914";
		expected[3] = "null";
		expected[4] = "school canteen";
		expected[5] = "null";
		expected[6] = "null";
		
		input = CliProcess.testSeparate(testString);
		
		Assert.assertArrayEquals(expected,input);
			
		fail("Not yet implemented");
	}

}
