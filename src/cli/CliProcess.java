package cli;

import cli.CliProcess.COMMAND_TYPE;

class CmdInfoPair {
    private COMMAND_TYPE cmd;
    private String subInfo;
    
    public CmdInfoPair(COMMAND_TYPE cmd, String subInfo) {
        this.cmd = cmd;
        this.subInfo = subInfo;
    }
    
    public COMMAND_TYPE getCMD() {
        return this.cmd;
    }
    
    public String getSubInfo() {
        return this.subInfo;
    }
}

public class CliProcess {

    private static final String SPLITSYMBOL = "\"";
    private static final String SPLIT_DATE = "-";
    private static final String SPACE = " ";
    private static final String EMPTY_STR = "";
    private static final String EMPTY_DIS = "EMPTY DISCRIPTION";
    private static final String EMPTY_DATE = "20010101";
    
    private static final int DATE_LENGTH = 8;
    private static final int INDEX_NOT_EXIST = -1;

    enum COMMAND_TYPE {
        ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT, NEXT, PREVIOUS,
        //UPDATE
        RENAME, RESCHEDULE, DESCRIBE,
        //VIEW_MODE
        TASKLIST, BIN,
        //
        REPEAT, SEARCH;
    }

    /**
     * Interpret input string to an executable command
     * 
     * @return CliToLog which contains corrsponding information
     * */
    public static CliToLog interpretCommand(String s){ 
        CmdInfoPair getCmdPair = makeCmdPair(s);
        CliToLog interpretedCm = transformCmd(getCmdPair);
        
        return interpretedCm;	   
    }
   
    /**
     * Interpret strings by their own commands
     * 
     * @return a pair of Command and its sub-information
     * */
    private static CmdInfoPair makeCmdPair(String rawString){
        String getCommand;
        String getSubInfo;
        
        int getCommandEnd = rawString.indexOf(SPACE);
        if (getCommandEnd == INDEX_NOT_EXIST) {
            getCommand = rawString;
            getSubInfo = null;
        } else {
            getCommand = rawString.substring(0, getCommandEnd);
            getSubInfo = rawString.substring(getCommandEnd + 1, rawString.length());
        }
        
        if (getCommand.equalsIgnoreCase("add")) {
            return new CmdInfoPair(COMMAND_TYPE.ADD, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("update")) {
            return new CmdInfoPair(COMMAND_TYPE.UPDATE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("delete")) {
            return new CmdInfoPair(COMMAND_TYPE.DELETE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("read")) {
            return new CmdInfoPair(COMMAND_TYPE.READ, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("view")) {
            return new CmdInfoPair(COMMAND_TYPE.VIEW, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("next")) {
            return new CmdInfoPair(COMMAND_TYPE.NEXT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("previous")) {
            return new CmdInfoPair(COMMAND_TYPE.PREVIOUS, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("undo")) {
            return new CmdInfoPair(COMMAND_TYPE.UNDO, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("search")) {
            return new CmdInfoPair(COMMAND_TYPE.SEARCH, getSubInfo);
        } else if (getCommand.equalsIgnoreCase("exit")) {
            return new CmdInfoPair(COMMAND_TYPE.EXIT, getSubInfo);
        } else {
            return new CmdInfoPair(COMMAND_TYPE.INVALID, getSubInfo);
        }
    }
    
    /**
     * Transform string command into corresponding CliToLog objects
     * */
    private static CliToLog transformCmd(CmdInfoPair infoPair){
        CliToLog resultCMD;
        COMMAND_TYPE getCMD = infoPair.getCMD();
        String subInfoStr = infoPair.getSubInfo();
        
        switch(getCMD){
        case ADD:
            resultCMD = add(subInfoStr);
            break;
        case UPDATE:
            resultCMD = update(subInfoStr);
            break;
        case READ:
            resultCMD = read(subInfoStr);
            break;
        case DELETE:
            resultCMD = delete(subInfoStr);
            break;
        case VIEW:
            resultCMD = view(subInfoStr);
            break;
        case UNDO:
            resultCMD = undo();
            break;
        case NEXT:
            resultCMD = next();
            break;
        case PREVIOUS:
            resultCMD = previous();
            break;
        case EXIT:
            resultCMD = exit();
            break;
        default:
            resultCMD = makeInvalid();
            break;
        }
        return resultCMD;
    }
    
    /**
     * Interpret "add" command and get its sub-information
     * */
    private static CliToLog add(String subInfoStr) {
       String taskTitle;
       String taskDescription;
       String basicInfo;
       
       int[] symbolIndex = new int[4];
       int countSymbol = 0;
       String curStr;
       
       // Count quotation mark, see if miss any
       for(int i = 0; i < subInfoStr.length(); i ++) {
           curStr = subInfoStr.substring(i, i+1);
           if (countSymbol == 4) {
               break;
           } else if (curStr.equals(SPLITSYMBOL)) {
               symbolIndex[countSymbol] = i;
               countSymbol++;
           }
       }
       
       // Split quotation mark contents with other contents
       if (countSymbol == 1 || countSymbol == 3) {
           System.err.println("Error at CLI: Quotation mark unclosed or missing title/discription");
           return makeInvalid();
       } else if (countSymbol == 2) {
           taskTitle = subInfoStr.substring(symbolIndex[0]+1, symbolIndex[1]);
           basicInfo = subInfoStr.substring(symbolIndex[1]+1, symbolIndex[2]);
           taskDescription = EMPTY_DIS;
           return makeAddCTL(taskTitle, basicInfo, taskDescription);
           
       } else if (countSymbol == 4){
           taskTitle = subInfoStr.substring(symbolIndex[0]+1, symbolIndex[1]);
           basicInfo = subInfoStr.substring(symbolIndex[1]+1, symbolIndex[2]);
           taskDescription = subInfoStr.substring(symbolIndex[2]+1, symbolIndex[3]);
           return makeAddCTL(taskTitle, basicInfo, taskDescription);
           
       } else {
           System.err.println("Error at CLI: Input task contain unexpected quotation mark");
           return makeInvalid();
       }
    }
    
    /**
     * Make a CTL object for add, insert 3 component separately into the CTL
     * */
    private static CliToLog makeAddCTL(String taskTitle, String basicInfo, String taskDescription) {
        CliToLog completeCTL;
        if (basicInfo.startsWith(SPACE)) {
            basicInfo = basicInfo.substring(1, basicInfo.length());
        }
        String[] component = basicInfo.split(SPACE);
        if (component.length == 3) {
            completeCTL = makeCompleteCTL(taskTitle, basicInfo, taskDescription, component); 
            return completeCTL;
        } else {
            System.err.println("Error at CLI: Invalid task information, check repeat time/start day/end day");
            return makeInvalid();
        }
    }
    
    
    /**
     * Insert all the basic information into CTL, make a complete CTL;
     * */
    private static CliToLog makeCompleteCTL(String taskTitle, String basicInfo, 
                                            String taskDescription, String[] component) {
        String getRpDay;
        String startDay = EMPTY_STR;
        String endDay = EMPTY_STR;
        
        getRpDay = component[0];
        String rawStartDay = component[1];
        String rawEndDay = component[2];
        
        startDay = makeDay(rawStartDay);
        endDay = makeDay(rawEndDay);
        
        if (startDay.equals(EMPTY_DATE) || endDay.equals(EMPTY_DATE)) {
            System.err.println("Error at CLI: Invalid date input");
            return makeInvalid();
        }
       
        return new CliToLog(COMMAND_TYPE.ADD.name(), taskTitle, 
                            taskDescription, getRpDay, 
                            startDay, endDay);
    }
    
    /**
     * Split date information, standardize them to YYYYMMDD;
     * 
     * @param rawDay
     *          String of date
     * */
    private static String makeDay(String rawDay) {
        String resultDay = EMPTY_STR;
        String[] startDayArr = rawDay.split(SPLIT_DATE);
        for (int i = 0; i < startDayArr.length; i++) {
            resultDay += startDayArr[i];
        }
        
        if (resultDay.length() != DATE_LENGTH) {
            resultDay = EMPTY_DATE;
        }
        return resultDay;
    }
    
    /**
     * To change/update a field in an input
     * Change command field
     * 
     * @param subInfoStr
     *              String of sub-information following the update command
     */
    private static CliToLog update(String subInfoStr){
        String updateField;
        String newContent;
        String[] component = subInfoStr.split(SPLITSYMBOL);
        
        // Check update input contents validity
        if (component.length != 2) {
            System.err.println("Error at CLI: Invalid update input");
            return makeInvalid();
        } else {
            updateField = component[0];
            if (updateField.endsWith(SPACE)) {
                updateField = updateField.substring(0, updateField.length()-1);
            }
            
            updateField = identifyField(updateField);
            newContent = component[1];
            return new CliToLog(updateField, newContent);
        }
    }

    /**
     * Function: Update
     * To identify the field to update
     * 
     * @param inputField
     *          Targeted update field of information on one specific task
     */
    private static String identifyField(String inputField){
        String updateField = null;

        //Check field to be changed
        if (inputField.equals("name")){
            updateField = COMMAND_TYPE.RENAME.name();
        }else
            if (inputField.equals("date") || inputField.equals("day")){
                updateField = COMMAND_TYPE.RESCHEDULE.name();
            }else
                if (inputField.equals("description")){
                    updateField = COMMAND_TYPE.DESCRIBE.name();
                }
        return updateField;
    }

    /** 
     * Read details of a certain task
     * 
     * @param readTarget
     *          Target reading index
     */
    private static CliToLog read(String readTarget){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.READ.name(), readTarget);

        return commandPackage;
    }

    /**
     * Undo the previous action
     */
    private static CliToLog undo(){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.UNDO.name());
        
        return commandPackage;		
    }

    /** 
     * Delete a certain task
     * 
     * @param deletIndex
     *          Index to be deleted
     */
    private static CliToLog delete(String deleteIndex){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.DELETE.name(), deleteIndex);

        return commandPackage;
    }
    
    /**
     * Make a CliToLog with command = "invalid"
     * */
    private static CliToLog makeInvalid() {
        return new CliToLog(COMMAND_TYPE.INVALID.name());
    }

    /** 
     * Switch to different view model
     * 
     * @param viewTarget
     *          Targeted viewing model
     */
    private static CliToLog view(String viewTarget){
        if (viewTarget.equalsIgnoreCase(COMMAND_TYPE.TASKLIST.name()) ||
            viewTarget.equalsIgnoreCase(COMMAND_TYPE.BIN.name())) {
            
            CliToLog commandPackage = new CliToLog(COMMAND_TYPE.VIEW.name(), viewTarget);
            return commandPackage;
        } else {
            System.err.println("Error at CLI: Invalid view mode input");
            return makeInvalid();
        }
    }

    /** 
     * Next page for current state of view
     */
    private static CliToLog next(){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.NEXT.name());

        return commandPackage;
    }
    
    /** 
     * Previous page for current state of view
     */
    private static CliToLog previous(){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.PREVIOUS.name());

        return commandPackage;
    }

    /** 
     * Exiting the program
     */
    private static CliToLog exit(){
        CliToLog commandPackage = new CliToLog(COMMAND_TYPE.EXIT.name());

        return commandPackage;		
    }
}