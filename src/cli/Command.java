package cli;

<<<<<<< HEAD:src/cli/CliToLog.java
import cli.ParserKeys;

public class CliToLog{
=======
public class Command{
>>>>>>> 54818b7a4ce089a542fcde4f3c55e8622ac9f8d4:src/cli/Command.java

	//Command Type
    private String command;
    
    //Add
    private String taskTitle;
<<<<<<< HEAD:src/cli/CliToLog.java
    private String description;
    private String rpDate;
=======
    private String taskDiscrib;
    private String repeatDate;
>>>>>>> 54818b7a4ce089a542fcde4f3c55e8622ac9f8d4:src/cli/Command.java
    private String startDay;
    private String endDay;
    private String taskDescription;
    
    //Read
    private String readIndex;
    
    //Delete
    private String deleteIndex;
    
    // View
    private String viewMode;
    
    //Restore
    private String restoreIndex;
    
    //search
    private String searchName;
    
    //
    
    public Command(String command) {
        this.command = command;
    }
    
<<<<<<< HEAD:src/cli/CliToLog.java
    public CliToLog(String command, String taskDescription) {
=======
    public Command(String command, String taskDescribe) {
>>>>>>> 54818b7a4ce089a542fcde4f3c55e8622ac9f8d4:src/cli/Command.java
        this.command = command;
        this.taskDescription = taskDescription;
        this.rpDate = ParserKeys.RP_EVREYDAY;
        this.startDay = ParserKeys.EMPTY_DATE;
        this.endDay = ParserKeys.EMPTY_DATE;
        this.description = ParserKeys.EMPTY_DIS;
    }
<<<<<<< HEAD:src/cli/CliToLog.java

    public CliToLog(String command, String taskTitle, 
                    String rpDate, String startDay, String endDay,
                    String taskDescribe) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.description = taskDescribe;
        this.rpDate = rpDate;
=======
    // strictly only for add command
    public Command(String command, String taskTitle, 
            String taskDescribe, String repeatDate, 
            String startDay, String endDay) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.taskDiscrib = taskDescribe;
        this.repeatDate = repeatDate;
>>>>>>> 54818b7a4ce089a542fcde4f3c55e8622ac9f8d4:src/cli/Command.java
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getCommand(){
        return this.command;
    }

    public String getTitle(){
        return this.taskTitle;
    }

    public String getDiscription(){
        return this.description;
    }
    
    public String getTaskDescription(){
        return this.taskDescription;
    }

    public String getRPdate(){
        return this.repeatDate;
    }

    public String getStartDay(){
        return this.startDay;
    }

    public String getEndDay(){
        return this.endDay;
    }
}

