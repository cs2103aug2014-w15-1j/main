package cli;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CliToLogTestAdd {
    
    CliToLog resultCTL;
    String testAddStr;
    String getCommand;
    String getTitle;
    String getDiscription;
    String getRPTime;
    String getStartDay;
    String getEndDay;
    
    @Before
    public void setValue() {
        testAddStr = "add \"a new title\" everyday 2014-10-10 2014-10-15 \"here is the discription!\"";
    }
    
    @Test
    public void testAdd() {
        resultCTL = CliProcess.interpretCommand(testAddStr);
        getCommand = resultCTL.getCommand();
        getTitle = resultCTL.getTitle();
        getDiscription = resultCTL.getDiscription();
        getRPTime = resultCTL.getRPdate();
        getStartDay = resultCTL.getStartDay();
        getEndDay = resultCTL.getEndDay();
        
        Assert.assertEquals("Get the add command", "ADD", getCommand);
        Assert.assertEquals("Get the command title", "a new title", getTitle);
        Assert.assertEquals("Get the command discription", "here is the discription!", getDiscription);
        Assert.assertEquals("Get the repeated day", "everyday", getRPTime);
        Assert.assertEquals("Get the start day", "20141010", getStartDay);
        Assert.assertEquals("Get the end day", "20141015", getEndDay);
    }
}
