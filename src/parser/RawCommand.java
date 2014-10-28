package parser;

import parser.CMDTypes.COMMAND_TYPE;
import parser.ParserKeys;

/**
 * class RawCommand: Wrapping all command information
 * 
 * @author A0119493X
 *          Command information includes command type, command description, 
 *          task title, task description, repeat time, start date, end date. All of them
 *          are in String type
 *          
 * */

public class RawCommand{

    /*
     * ====================================================================
     * ===================== START OF PRIVATE FIELD =======================
     * ====================================================================
     */
    
    private String command;
    private String taskTitle;
    private String description;
    private String rpDate;
    private String startDay;
    private String endDay;
    private String cmdDescription;
    
    /*
     * ====================================================================
     * ===================== END OF PRIVATE FIELD =========================
     * ====================================================================
     */
    
    public RawCommand(String command) {
        this.command = command;
    }
    
    public RawCommand(String command, String subInfo) {
    	
    	if (command.equals(COMMAND_TYPE.RENAME.name())) {
    		this.taskTitle = subInfo;
    	} else if (command.equals(COMMAND_TYPE.DESCRIBE.name())) {
    		this.description = subInfo;
    	} else {
    		this.cmdDescription = subInfo;
    	}
    	
        this.command = command;
        /*
        this.rpDate = ParserKeys.RP_EVREYDAY;
        this.startDay = ParserKeys.EMPTY_DATE;
        this.endDay = ParserKeys.EMPTY_DATE;
        this.description = ParserKeys.EMPTY_DIS;
        */
    }
    
    public RawCommand(String command, String startDay, String endDay) {
    	this.command = command;
        //this.rpDate = ParserKeys.RP_EVREYDAY;
        this.startDay = startDay;
        this.endDay = endDay;
        //this.description = ParserKeys.EMPTY_DIS;
    }
        
    // Strictly only for add command
    public RawCommand(String command, String taskTitle, 
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
    
    public String getCMDDescription(){
        return this.cmdDescription;
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

