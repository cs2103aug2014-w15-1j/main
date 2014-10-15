package parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD:src/cli/CliToLogTest_Simple_CMD.java
<<<<<<< HEAD
import cli.CliToLog;

=======
import cli.Command;

=======
import parser.Command;
>>>>>>> 9c0b21785df46d8467a4a1db9be40f27e297bb52:src/parser/CliToLogTest_Simple_CMD.java
public class CliToLogTest_Simple_CMD {

    String testDeleStr;
    String testReadStr;
    String testViewStr;
    String testUpdateStr;
    String testUndoStr;
    
    Command deleteTest;
    Command readTest;
    Command viewTest;
    Command updateTest;
    Command undoTest;
    
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
        deleteTest = ParserProcess.interpretCommand(testDeleStr);
        Assert.assertEquals("Test delete command: ", "DELETE", deleteTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "1", deleteTest.getTaskDescription());
        
        readTest = ParserProcess.interpretCommand(testReadStr);
        Assert.assertEquals("Test delete command: ", "READ", readTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "2", readTest.getTaskDescription());
        
        viewTest = ParserProcess.interpretCommand(testViewStr);
        Assert.assertEquals("Test delete command: ", "VIEW", viewTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "tasklist", viewTest.getTaskDescription());
        
        updateTest = ParserProcess.interpretCommand(testUpdateStr);
        Assert.assertEquals("Test delete command: ", "RENAME", updateTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "a new name", updateTest.getTaskDescription());
        
        undoTest = ParserProcess.interpretCommand(testUndoStr);
        Assert.assertEquals("Test delete command: ", "UNDO", undoTest.getCommand());
    }
}
