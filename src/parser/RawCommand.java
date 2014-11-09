package parser;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class <Strong>RawCommand</Strong>: 
 * 
 * <p>
 * This class is used to wrapping all basic command information and have them 
 * passed to <code>Logic</code>. Each objects contains basic information regarding to different
 * command types.
 * </p>
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
    private String startDay;
    private String endDay;
    private String cmdDescription;
    
    /*
     * ====================================================================
     * ===================== END OF PRIVATE FIELD =========================
     * ====================================================================
     */
    
    // For commands with no subInfo
    public RawCommand(String command) {
    	this.command = command;
    }
    
    // For commands with only one subInfo
    public RawCommand(String command, String subInfo) {
    	this.command = command;
    	this.cmdDescription = subInfo;
    }
    
    // For update info with locateLine
    public RawCommand(String command, String fieldInfo, String locateLine) {
    	
    	this.command = command;
    	if (command.equals(COMMAND_TYPE.RENAME.name())) {
    		this.taskTitle = fieldInfo;
    		this.cmdDescription = locateLine;
    	} else if (command.equals(COMMAND_TYPE.DESCRIBE.name())) {
    		this.description = fieldInfo;
    		this.cmdDescription = locateLine;
    	} else if (command.equals(COMMAND_TYPE.MARK.name())){
    		this.description = fieldInfo;
    		this.cmdDescription = locateLine;
    	}
    }
    
    // For reschedule only
    public RawCommand(String command, String startDay, String endDay, String locateLine) {
    	this.command = command;
    	this.startDay = startDay;
    	this.endDay = endDay;
    	this.cmdDescription = locateLine;
    } 
        
    // Strictly only for add command
    public RawCommand(String command, String taskTitle, 
                   	  String startDay, String endDay, 
                      String description) {
        this.command = command;
        this.taskTitle = taskTitle;
        this.description = description;
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

    public String getStartDay(){
        return this.startDay;
    }

    public String getEndDay(){
        return this.endDay;
    }
}

