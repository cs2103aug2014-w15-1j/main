package parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.RawCommand;

public class TestSimpleCMD {


    String testDeleStr;
    String testReadStr;
    String testViewStr;
    String testUpdateStr;
    String testUndoStr;
    
    RawCommand deleteTest;
    RawCommand readTest;
    RawCommand viewTest;
    RawCommand updateTest;
    RawCommand undoTest;
    
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
        deleteTest = ParserProcesser.interpretCommand(testDeleStr);
        Assert.assertEquals("Test delete command: ", "DELETE", deleteTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "1", deleteTest.getCMDDescription());
        
        readTest = ParserProcesser.interpretCommand(testReadStr);
        Assert.assertEquals("Test delete command: ", "READ", readTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "2", readTest.getCMDDescription());
        
        viewTest = ParserProcesser.interpretCommand(testViewStr);
        Assert.assertEquals("Test delete command: ", "VIEW", viewTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "tasklist", viewTest.getCMDDescription());
        
        updateTest = ParserProcesser.interpretCommand(testUpdateStr);
        Assert.assertEquals("Test delete command: ", "RENAME", updateTest.getCommand());
        Assert.assertEquals("Test delete target index: ", "a new name", updateTest.getCMDDescription());
        
        undoTest = ParserProcesser.interpretCommand(testUndoStr);
        Assert.assertEquals("Test delete command: ", "UNDO", undoTest.getCommand());
    }
}
