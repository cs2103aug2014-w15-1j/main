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
    String testReDescribeStr;
    
    RawCommand deleteTest;
    RawCommand readTest;
    RawCommand viewTest;
    RawCommand updateTest;
    RawCommand undoTest;
    RawCommand reDescribe;
    
    
    @Before
    public void initTestString() {
        testDeleStr = "delete 1";
        testReadStr = "read 2";
        testViewStr = "view tasklist";
        testUpdateStr = "update name \"a new name\"";
        testUndoStr = "undo";
        testReDescribeStr = "update description \"a new description\"";
    }
    
    @Test
    public void test() {
        deleteTest = ParserProcesser.interpretCommand(testDeleStr);
        Assert.assertEquals("Test command: ", "DELETE", deleteTest.getCommand());
        Assert.assertEquals("Test target index: ", "1", deleteTest.getCMDDescription());
        
        readTest = ParserProcesser.interpretCommand(testReadStr);
        Assert.assertEquals("Test command: ", "READ", readTest.getCommand());
        Assert.assertEquals("Test target index: ", "2", readTest.getCMDDescription());
        
        viewTest = ParserProcesser.interpretCommand(testViewStr);
        Assert.assertEquals("Test command: ", "VIEW", viewTest.getCommand());
        Assert.assertEquals("Test target index: ", "tasklist", viewTest.getCMDDescription());
        
        updateTest = ParserProcesser.interpretCommand(testUpdateStr);
        Assert.assertEquals("Test command: ", "RENAME", updateTest.getCommand());
        Assert.assertEquals("Test target index: ", "a new name", updateTest.getTitle());
        
        reDescribe = ParserProcesser.interpretCommand(testReDescribeStr);
        Assert.assertEquals("Test command: ", "DESCRIBE", reDescribe.getCommand());
        Assert.assertEquals("Test target index: ", "a new description", reDescribe.getDescription());
        
        undoTest = ParserProcesser.interpretCommand(testUndoStr);
        Assert.assertEquals("Test command: ", "UNDO", undoTest.getCommand());
        
    }
}
