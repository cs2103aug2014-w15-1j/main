package cli;

public class Command{

	//Command Type
    private String command;
    
    //Add
    private String taskTitle;
    private String taskDiscrib;
    private String repeatDate;
    private String startDay;
    private String endDay;
    
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
    
    public Command(String command, String taskDescribe) {
        this.command = command;
        this.taskDiscrib = taskDescribe;
    }
    // strictly only for add command
    public Command(String command, String taskTitle, 
            String taskDescribe, String repeatDate, 
            String startDay, String endDay) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.taskDiscrib = taskDescribe;
        this.repeatDate = repeatDate;
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
        return this.taskDiscrib;
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

