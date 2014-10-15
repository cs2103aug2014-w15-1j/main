package parser;

import parser.ParserKeys;

public class Command{

	//Command Type
    private String command;
    
    //Add
    private String taskTitle;
    private String description;
    private String rpDate;
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
    
    public Command(String command) {
        this.command = command;
    }
    
    public Command(String command, String taskDescribe) {
        this.command = command;
        this.taskDescription = taskDescribe;
        this.rpDate = ParserKeys.RP_EVREYDAY;
        this.startDay = ParserKeys.EMPTY_DATE;
        this.endDay = ParserKeys.EMPTY_DATE;
        this.description = ParserKeys.EMPTY_DIS;
    }
        
    // strictly only for add command
    public Command(String command, String taskTitle, 
                   String repeatDate, String startDay, String endDay, 
                   String description) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.description = description;
        this.rpDate = repeatDate;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getCommand(){
        return this.command;
    }

    public String getTitle(){
        return this.taskTitle;
    }

    public String getDescription(){
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

