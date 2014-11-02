package parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAdd {
    
    RawCommand resultCTL0;
    RawCommand resultCTL1;
    RawCommand resultCTL2;
    String testAddStr0;
    String testAddStr1;
    String testAddStr2;
    String getCommand;
    String getTitle;
    String getDescription;
    String getRPDate;
    String getStartDay;
    String getEndDay;
    
    @Before
    public void setValue() {
        testAddStr0 = "add title";
        testAddStr1 = "add a new task tuesday 2012-10-10 2012-10-15 some description";
        testAddStr2 = "add 1";
    }
    
    @Test
    public void testAdd() {
    	
    	
        resultCTL0 = ParserProcesser.interpretCommand(testAddStr0);
        getCommand = resultCTL0.getCommand();
        getTitle = resultCTL0.getTitle();
        getDescription = resultCTL0.getDescription();
        getRPDate = resultCTL0.getRPdate();
        getStartDay = resultCTL0.getStartDay();
        getEndDay = resultCTL0.getEndDay();
        
        Assert.assertEquals("Get the add command", "ADD", getCommand);
        Assert.assertEquals("Get the command title", "title", getTitle);
        Assert.assertEquals("Get the command discription", "EMPTY DESCRIPTION", getDescription);
        Assert.assertEquals("Get the repeated day", "no_repeat", getRPDate);
        Assert.assertEquals("Get the start day", null, getStartDay);
        Assert.assertEquals("Get the end day", null, getEndDay);
        
        resultCTL1 = ParserProcesser.interpretCommand(testAddStr1);
        getCommand = resultCTL1.getCommand();
        getTitle = resultCTL1.getTitle();
        getDescription = resultCTL1.getDescription();
        getRPDate = resultCTL1.getRPdate();
        getStartDay = resultCTL1.getStartDay();
        getEndDay = resultCTL1.getEndDay();
        
        Assert.assertEquals("Get the add command", "ADD", getCommand);
        Assert.assertEquals("Get the command title", "a new task", getTitle);
        Assert.assertEquals("Get the command discription", "some description", getDescription);
        Assert.assertEquals("Get the repeated day", "tuesday", getRPDate);
        Assert.assertEquals("Get the start day", "20121010", getStartDay);
        Assert.assertEquals("Get the end day", "20121015", getEndDay);
        
        resultCTL2 = ParserProcesser.interpretCommand(testAddStr2);
        getCommand = resultCTL2.getCommand();
        getTitle = resultCTL2.getTitle();
        
        
        Assert.assertEquals("Get the add command", "ADD", getCommand);
        Assert.assertEquals("Get the command title", "1", getTitle);
        
        
    }
}
