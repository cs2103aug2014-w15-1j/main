package cli;

import java.util.ArrayList;

public class CliProcess {

    enum COMMAND_TYPE {
        ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT, NEXT, PREVIOUS, SEARCH,
        // VIEW_MODE
        TASKLIST, BIN, 
        // from bin
        RESTORE,
        // UPDATE
        RENAME, RESCHEDULE, DESCRIBE, REPEAT,
        // UPDATE_FIELD
        NAME, DISCRIPTION, DATE, DAY;
    }

    /**
     * Interpret input string to an executable command
     * 
     * @return CliToLog which contains corresponding information
     * */
    public static Command interpretCommand(String inputString){
        if (noInvalidKeys(inputString)) {
            CmdInfoPair getCmdPair = makeCmdPair(inputString);
            Command interpretedCm = transformCmd(getCmdPair);
            return interpretedCm;
            
        } else {
            ErrorGenerator.popError(ErrorMSG.INPUT_SYMBOL_ERR);
            return makeInvalid();
        }
    }
    
    /**
     * Check if input contains invalid symbols
     * */
    private static boolean noInvalidKeys(String inputString) {
        return !inputString.contains(ParserKeys.INVALID_SYMBOL);
    }

    /**
     * Interpret strings by their own commands
     * 
     * @return a pair of Command and its sub-information
     * */
    private static CmdInfoPair makeCmdPair(String rawString){
        String getCommand;
        String getSubInfo;

        int getCommandEnd = rawString.indexOf(ParserKeys.SPACE);
        if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
            getCommand = rawString;
            getSubInfo = null;
        } else {
            getCommand = rawString.substring(0, getCommandEnd);
            getSubInfo = rawString.substring(getCommandEnd + 1, rawString.length());
        }

        if (getCommand.equalsIgnoreCase(COMMAND_TYPE.ADD.name())) {
            return new CmdInfoPair(COMMAND_TYPE.ADD, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.UPDATE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.UPDATE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RENAME.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RENAME, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.DESCRIBE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.DESCRIBE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RESCHEDULE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RESCHEDULE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.REPEAT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.REPEAT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.DELETE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.DELETE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.READ.name())) {
            return new CmdInfoPair(COMMAND_TYPE.READ, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.VIEW.name())) {
            return new CmdInfoPair(COMMAND_TYPE.VIEW, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.NEXT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.NEXT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.PREVIOUS.name())) {
            return new CmdInfoPair(COMMAND_TYPE.PREVIOUS, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.UNDO.name())) {
            return new CmdInfoPair(COMMAND_TYPE.UNDO, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.SEARCH.name())) {
            return new CmdInfoPair(COMMAND_TYPE.SEARCH, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.EXIT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.EXIT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RESTORE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RESTORE, getSubInfo);
        } else {
            return new CmdInfoPair(COMMAND_TYPE.INVALID, getSubInfo);
        }
    }

    /**
     * Transform string command into corresponding CliToLog objects
     * */
    private static Command transformCmd(CmdInfoPair infoPair){
        Command resultCMD;
        COMMAND_TYPE getCMD = infoPair.getCMD();
        String subInfoStr = infoPair.getSubInfo();

        switch(getCMD){
        case ADD:
            resultCMD = add(subInfoStr);
            break;
        case UPDATE:
            resultCMD = update(subInfoStr);
            break;
        case RENAME:
            resultCMD = rename(subInfoStr);
            break;
        case RESCHEDULE:
            resultCMD = reschedule(subInfoStr);
            break;
        case DESCRIBE:
            resultCMD = describe(subInfoStr);
            break;
        case REPEAT:
            resultCMD = repeat(subInfoStr);
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
        case RESTORE:
        	resultCMD = restore(subInfoStr);
        	break;
        default:
            resultCMD = makeInvalid();
            break;
        }
        return resultCMD;
    }

    private static Command rename(String subInfoStr) {
    	if(subInfoStr.isEmpty()){
    		return makeInvalid();
    	}
		return new Command(COMMAND_TYPE.RENAME.name(), subInfoStr);
	}

	private static Command describe(String subInfoStr) {
    	if(subInfoStr.isEmpty()){
    		return makeInvalid();
    	}
		return new Command(COMMAND_TYPE.DESCRIBE.name(), subInfoStr);
	}

	private static Command reschedule(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Command repeat(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Interpret "add" command and get its sub-information
     * Split quotation mark contents with other contents
     * */
    private static Command add(String subInfoStr) {
        String taskTitle;
        String taskDescription;
        String basicInfo;
        ArrayList<Integer> symbolIndex = getQuoteMark(subInfoStr);
        int markNumber = symbolIndex.size();

        if (markNumber == 1 || markNumber == 3) {
            ErrorGenerator.popError(ErrorMSG.QUOTATION_UNCLOSE_ERR);
            return makeInvalid();

        } else if (markNumber == 2) {
        	taskTitle = subInfoStr.substring(symbolIndex.get(0) + 1, symbolIndex.get(1));
        	 basicInfo = ParserKeys.EMPTY_DATE + " " + ParserKeys.EMPTY_DATE +  " " + ParserKeys.EMPTY_DATE;
        	 taskDescription = ParserKeys.EMPTY_DIS;
        	 return makeAddCTL(taskTitle, basicInfo, taskDescription);
        	 
        } else if (markNumber == 4) {
            taskTitle = subInfoStr.substring(symbolIndex.get(0) + 1, symbolIndex.get(1));
            taskDescription = subInfoStr.substring(symbolIndex.get(2) + 1, symbolIndex.get(3));
            basicInfo = ParserKeys.EMPTY_DATE + " " + ParserKeys.EMPTY_DATE +  " " + ParserKeys.EMPTY_DATE;
            return makeAddCTL(taskTitle, basicInfo, taskDescription);

        } else if (markNumber == 6){
            taskTitle = subInfoStr.substring(symbolIndex.get(0) + 1, symbolIndex.get(1));
            taskDescription = subInfoStr.substring(symbolIndex.get(2) + 1, symbolIndex.get(3));
            basicInfo = subInfoStr.substring(symbolIndex.get(3)+1, symbolIndex.get(5));
            return makeAddCTL(taskTitle, basicInfo, taskDescription);

        } else {
            ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
            return makeInvalid();
        }
    }

    /**
     * Return index of quotation mark in raw input String
     * */

    private static ArrayList<Integer> getQuoteMark(String subInfoStr) {
        ArrayList<Integer> quotationIndex = new ArrayList<Integer>();
        String curStr;
        int countSymbol = 0;
        for(int i = 0; i < subInfoStr.length(); i ++) {
            curStr = subInfoStr.substring(i, i+1);
            if (countSymbol == 4) {
                break;
            } else if (curStr.equals(ParserKeys.SPLITSYMBOL)) {
                quotationIndex.add(i);
                countSymbol++;
            }
        }

        return quotationIndex;
    }

    /**
     * Make a CTL object for add, insert 3 component separately into the CTL
     * */
    private static Command makeAddCTL(String taskTitle, String basicInfo, String taskDescription) {
        Command completeCTL;
        if (basicInfo.startsWith(ParserKeys.SPACE)) {
            basicInfo = basicInfo.substring(1, basicInfo.length());
        }
        
        String[] component = basicInfo.split(ParserKeys.SPACE);
        if (component.length == 3) {
            completeCTL = makeCompleteCTL(taskTitle, basicInfo, taskDescription, component); 
            return completeCTL;
        } else {
            ErrorGenerator.popError(ErrorMSG.TASK_INFO_ERR);
            return makeInvalid();
        }
    }

    /**
     * Insert all the basic information into CTL, make a complete CTL;
     * */
    private static Command makeCompleteCTL(String taskTitle, String basicInfo, 
            String taskDescription, String[] component) {
        String getRpDay;
        String startDay = ParserKeys.EMPTY_STR;
        String endDay = ParserKeys.EMPTY_STR;

        getRpDay = component[0];
        String rawStartDay = component[1];
        String rawEndDay = component[2];

        startDay = makeDay(rawStartDay);
        endDay = makeDay(rawEndDay);
/*
        if (startDay.equals(ParserKeys.EMPTY_DATE) || endDay.equals(ParserKeys.EMPTY_DATE)) {
            ErrorGenerator.popError(ErrorMSG.INPUT_DATE_ERR);
            return makeInvalid();
        }
*/
        return new Command(COMMAND_TYPE.ADD.name(), taskTitle, 
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
        String resultDay = ParserKeys.EMPTY_STR;
        String[] startDayArr = rawDay.split(ParserKeys.SPLIT_DATE);
        for (int i = 0; i < startDayArr.length; i++) {
            resultDay += startDayArr[i];
        }

        if (resultDay.length() != ParserKeys.DATE_LENGTH) {
            resultDay = ParserKeys.EMPTY_DATE;
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
    private static Command update(String subInfoStr){
    	// TODO Auto-generated method stub
       String getUpdateItem;
       String getUpdateInfo;

        int getCommandEnd = subInfoStr.indexOf(ParserKeys.SPACE);
        if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
        	ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
            return makeInvalid();
        } else {
        	getUpdateItem = subInfoStr.substring(0, getCommandEnd).trim().toLowerCase();
        	getUpdateInfo = subInfoStr.substring(getCommandEnd + 1, subInfoStr.length());
        }
        
        if (getUpdateItem.equalsIgnoreCase("name")){
        	return rename(getUpdateInfo);
        } else if (getUpdateItem.equalsIgnoreCase("description")){
        	return describe(getUpdateInfo);
        } else if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.RESCHEDULE.name())){
        	return reschedule(getUpdateInfo);
        } else if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.REPEAT.name())){
        	return repeat(getUpdateInfo);
        } else {
        	ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
            return makeInvalid();
        }
             
        
        
        /*
        
        String updateField;
        String newContent;
        String[] component = subInfoStr.split(ParserKeys.SPLITSYMBOL);
        
        // Check update input contents validity
        if (component.length != 2) {
            ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
            return makeInvalid();
        } else {
            updateField = component[0];
            if (updateField.endsWith(ParserKeys.SPACE)) {
                updateField = updateField.substring(0, updateField.length()-1);
            }

            updateField = identifyField(updateField);
            newContent = component[1];
            return new CliToLog(updateField, newContent);
        }*/
    }


    /**
     * Function: Update
     * To identify the field to update
     * 
     * @param inputField
     *          Targeted update field of information on one specific task
     */
 /*   private static String identifyField(String inputField){
        String updateField = null;

        //Check field to be changed
        if (inputField.equalsIgnoreCase(COMMAND_TYPE.NAME.name())){
            updateField = COMMAND_TYPE.RENAME.name();
        } else if (inputField.equals(COMMAND_TYPE.DATE.name()) || 
                   inputField.equals(COMMAND_TYPE.DAY.name())){
            updateField = COMMAND_TYPE.RESCHEDULE.name();
        } else if (inputField.equals(COMMAND_TYPE.DISCRIPTION.name())){
            updateField = COMMAND_TYPE.DESCRIBE.name();
        } else {
            updateField = COMMAND_TYPE.INVALID.name();
            ErrorGenerator.popError(ErrorMSG.UPDATE_FIELD_ERR);
        }
        return updateField;
    }
*/

    /** 
     * Read details of a certain task
     * 
     * @param readTarget
     *          Target reading index
     */
    private static Command read(String readTarget){
        Command commandPackage = new Command(COMMAND_TYPE.READ.name(), readTarget);

        return commandPackage;
    }

    /**
     * Undo the previous action
     */
    private static Command undo(){
        Command commandPackage = new Command(COMMAND_TYPE.UNDO.name());

        return commandPackage;		
    }

    /** 
     * Delete a certain task
     * 
     * @param deletIndex
     *          Index to be deleted
     */
    private static Command delete(String deleteIndex){
        Command commandPackage = new Command(COMMAND_TYPE.DELETE.name(), deleteIndex);

        return commandPackage;
    }

    /**
     * Make a CliToLog with command = "invalid"
     * */
    private static Command makeInvalid() {
        return new Command(COMMAND_TYPE.INVALID.name());
    }

    /** 
     * Switch to different view model
     * 
     * @param viewTarget
     *          Targeted viewing model
     */
    private static Command view(String viewTarget){
        if (viewTarget.equalsIgnoreCase(COMMAND_TYPE.TASKLIST.name()) ||
                viewTarget.equalsIgnoreCase(COMMAND_TYPE.BIN.name())) {

            Command commandPackage = new Command(COMMAND_TYPE.VIEW.name(), viewTarget);
            return commandPackage;
        } else {
            ErrorGenerator.popError(ErrorMSG.VIEW_MODE_ERR);
            return makeInvalid();
        }
    }

    /** 
     * Next page for current state of view
     */
    private static Command next(){
        Command commandPackage = new Command(COMMAND_TYPE.NEXT.name());

        return commandPackage;
    }

    /** 
     * Previous page for current state of view
     */
    private static Command previous(){
        Command commandPackage = new Command(COMMAND_TYPE.PREVIOUS.name());

        return commandPackage;
    }
    
    /* *
     *  Restore item from bin
     *  
     *  @param restoreTarget
     *         Target restore index
     */
    private static Command restore(String restoreTarget){
        Command commandPackage = new Command(COMMAND_TYPE.RESTORE.name(), restoreTarget);

        return commandPackage;
    }
    
    /** 
     * Exiting the program
     */
    private static Command exit(){
        Command commandPackage = new Command(COMMAND_TYPE.EXIT.name());

        return commandPackage;		
    }
}