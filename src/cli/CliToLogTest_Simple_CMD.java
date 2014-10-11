package cli;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cli.CliToLog;

public class CliToLogTest_Simple_CMD {

    String testDeleStr;
    String testReadStr;
    String testViewStr;
    String testUpdateStr;
    String testUndoStr;
    
    CliToLog deleteTest;
    CliToLog readTest;
    CliToLog viewTest;
    CliToLog updateTest;
    CliToLog undoTest;
    
    @Before
    public void initTestString() {
        testDeleStr = "delete 1";
        testReadStr = "read 2";
        testViewStr = "view tasklist";
        testUpdateStr = "update name \"a new name\"";
        testUndoStr = "undo";
    }
    
    @Test
    public void test() {
        deleteTest = CliProcess.interpretCommand(testDeleStr);
        Assert.assertEquals("Test delete command: ", "DELETE", deleteTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "1", deleteTest.getDiscription());
        
        readTest = CliProcess.interpretCommand(testReadStr);
        Assert.assertEquals("Test delete command: ", "READ", readTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "2", readTest.getDiscription());
        
        viewTest = CliProcess.interpretCommand(testViewStr);
        Assert.assertEquals("Test delete command: ", "VIEW", viewTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "tasklist", viewTest.getDiscription());
        
        updateTest = CliProcess.interpretCommand(testUpdateStr);
        Assert.assertEquals("Test delete command: ", "RENAME", updateTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "a new name", updateTest.getDiscription());
        
        undoTest = CliProcess.interpretCommand(testUndoStr);
        Assert.assertEquals("Test delete command: ", "UNDO", undoTest.getCommand());
    }

}
