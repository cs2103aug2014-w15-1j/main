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
    String testRescheduleStr;
    String testUpdateScheduleStr;
    String testViewDateStr1;
    String testViewDateStr2;
    String testSearchStr;
    String backTestStr;
    String markStr;
    String describeStr;
    
    RawCommand deleteTest;
    RawCommand readTest;
    RawCommand viewTest;
    RawCommand updateTest;
    RawCommand undoTest;
    RawCommand reDescribe;
    RawCommand reScheduleTest;
    RawCommand updateScheduleTest;
    RawCommand viewDateTest1;
    RawCommand viewDateTest2;
    RawCommand searchTest;
    RawCommand backTest;
    RawCommand markTest;
    RawCommand describeTest;
    
    @Before
    public void initTestString() {
        testDeleStr = "delete 1";
        testReadStr = "read 2";
        testViewStr = "view tasklist";
        testUpdateStr = "update 1 name \"a 1 new name\"";
        testUndoStr = "undo";
        testReDescribeStr = "update description \"a 1 new description\"";
        testRescheduleStr = "reschedule 2012-10-12 2012-10-13";
        testUpdateScheduleStr = "update schedule 2012-10-12 2012-10-13";
        testViewDateStr1 = "view Today";
        testViewDateStr2 = "view 2012-10-10";
        testSearchStr = "search a task name";
        markStr = "mark 1 done";
        describeStr = "describe 1";
        backTestStr = "back";
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
        Assert.assertEquals("Test command: ", "1", updateTest.getCMDDescription());
        Assert.assertEquals("Test target index: ", "a 1 new name", updateTest.getTitle());
        
        reDescribe = ParserProcesser.interpretCommand(testReDescribeStr);
        Assert.assertEquals("Test command: ", "DESCRIBE", reDescribe.getCommand());
        Assert.assertEquals("Test target index: ", "a 1 new description", reDescribe.getDescription());
       
        undoTest = ParserProcesser.interpretCommand(testUndoStr);
        Assert.assertEquals("Test command: ", "UNDO", undoTest.getCommand());
        
        reScheduleTest = ParserProcesser.interpretCommand(testRescheduleStr);
        Assert.assertEquals("Test command: RESCHEDULE", "RESCHEDULE", reScheduleTest.getCommand());
        Assert.assertEquals("Test startdate: ", "20121012", reScheduleTest.getStartDay());
        Assert.assertEquals("Test enddate: ", "20121013", reScheduleTest.getEndDay());
        
        updateScheduleTest = ParserProcesser.interpretCommand(testUpdateScheduleStr);
        Assert.assertEquals("Test command: UPDATE", "RESCHEDULE", reScheduleTest.getCommand());
        Assert.assertEquals("Test startdate: ", "20121012", reScheduleTest.getStartDay());
        Assert.assertEquals("Test enddate: ", "20121013", reScheduleTest.getEndDay());
        
        viewDateTest1 = ParserProcesser.interpretCommand(testViewDateStr1);
        Assert.assertEquals("Test command: VIEW", "VIEWDATE", viewDateTest1.getCommand());
        Assert.assertEquals("Test date: ", "20141108", viewDateTest1.getCMDDescription());
        
        viewDateTest2 = ParserProcesser.interpretCommand(testViewDateStr2);
        Assert.assertEquals("Test command: VIEW", "VIEWDATE", viewDateTest2.getCommand());
        Assert.assertEquals("Test date: ", "20121010", viewDateTest2.getCMDDescription());
        
        searchTest = ParserProcesser.interpretCommand(testSearchStr);
        Assert.assertEquals("Test command: Search", "SEARCH", searchTest.getCommand());
        Assert.assertEquals("Test search contents: ", "a task name", searchTest.getCMDDescription());
        
        backTest = ParserProcesser.interpretCommand(backTestStr);
        Assert.assertEquals("Test command: BACK", "BACK", backTest.getCommand());  
        
        markTest = ParserProcesser.interpretCommand(markStr);
        Assert.assertEquals("Test command: Mark", "MARK", markTest.getCommand()); 
        Assert.assertEquals("Test command: Mark", "1", markTest.getCMDDescription()); 
        
        describeTest = ParserProcesser.interpretCommand(describeStr);
        Assert.assertEquals("Test command: Describe", "DESCRIBE", describeTest.getCommand()); 
        Assert.assertEquals("Test command: Describe", "1", describeTest.getCMDDescription()); 
    }
}
