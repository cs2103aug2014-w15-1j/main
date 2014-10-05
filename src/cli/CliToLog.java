package cli;

public class CliToLog{

    private String command;
    private String taskTitle;
    private String taskDiscrib;
    private String rpDate;
    private String startDay;
    private String endDay;
    
    public CliToLog(String command) {
        this.command = command;
    }
    
    public CliToLog(String command, String taskDiscrib) {
        this.command = command;
        this.taskDiscrib = taskDiscrib;
    }

    public CliToLog(String command, String taskTitle, 
            String taskDiscrib, String rpDate, 
            String startDay, String endDay) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.taskDiscrib = taskDiscrib;
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

    public String getDiscribtion(){
        return this.taskDiscrib;
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

