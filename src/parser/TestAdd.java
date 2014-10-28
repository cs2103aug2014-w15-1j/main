package parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAdd {
    
    RawCommand resultCTL;
    String testAddStr;
    String getCommand;
    String getTitle;
    String getDescription;
    String getRPDate;
    String getStartDay;
    String getEndDay;
    
    @Before
    public void setValue() {
        testAddStr = "add title";
    }
    
    @Test
    public void testAdd() {
        resultCTL = ParserProcesser.interpretCommand(testAddStr);
        getCommand = resultCTL.getCommand();
        getTitle = resultCTL.getTitle();
        getDescription = resultCTL.getDescription();
        getRPDate = resultCTL.getRPdate();
        getStartDay = resultCTL.getStartDay();
        getEndDay = resultCTL.getEndDay();
        
        Assert.assertEquals("Get the add command", "ADD", getCommand);
        Assert.assertEquals("Get the command title", "title", getTitle);
        Assert.assertEquals("Get the command discription", "EMPTY DESCRIPTION", getDescription);
        Assert.assertEquals("Get the repeated day", "no_repeat", getRPDate);
        Assert.assertEquals("Get the start day", "20000101", getStartDay);
        Assert.assertEquals("Get the end day", "20000101", getEndDay);
    }
}
