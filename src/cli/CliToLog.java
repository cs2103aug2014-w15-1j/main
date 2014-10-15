package cli;

import cli.ParserKeys;

public class CliToLog{

    private String command;
    private String taskTitle;
    private String description;
    private String rpDate;
    private String startDay;
    private String endDay;
    private String taskDescription;
    
    public CliToLog(String command) {
        this.command = command;
    }
    
    public CliToLog(String command, String taskDescription) {
        this.command = command;
        this.taskDescription = taskDescription;
        this.rpDate = ParserKeys.RP_EVREYDAY;
        this.startDay = ParserKeys.EMPTY_DATE;
        this.endDay = ParserKeys.EMPTY_DATE;
        this.description = ParserKeys.EMPTY_DIS;
    }

    public CliToLog(String command, String taskTitle, 
                    String rpDate, String startDay, String endDay,
                    String taskDescribe) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.description = taskDescribe;
        this.rpDate = rpDate;
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
        return this.rpDate;
    }

    public String getStartDay(){
        return this.startDay;
    }

    public String getEndDay(){
        return this.endDay;
    }
}

