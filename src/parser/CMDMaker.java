package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;

public class CMDMaker {

	/**
	 * Interpret "add" command and get its sub-information
	 * Split quotation mark contents with other contents
	 * 
	 * @param subInfoStr -RawCommand
	 * @return -CliToLog
	 * */
	static RawCommand add(String subInfoStr) {
	    ArrayList<Integer> symbolIndex = InfoRetrieve.getQuoteMark(subInfoStr);
	    int markNumber = symbolIndex.size();
	    
	    if (markNumber == 0 || markNumber == 2 || markNumber == 4 ) {
	        // No quotation marks
	        String taskTitle = InfoRetrieve.getTaskTitle(subInfoStr, symbolIndex);
	        String repeatDate = InfoRetrieve.getRepeatDate(subInfoStr, symbolIndex);
	        String startDate = InfoRetrieve.makeDay(InfoRetrieve.getBasicInfoAT(2, subInfoStr, symbolIndex, ParserKeys.EMPTY_DATE));
	        String endDate = InfoRetrieve.makeDay(InfoRetrieve.getBasicInfoAT(3, subInfoStr, symbolIndex, ParserKeys.EMPTY_DATE));
	        String description = InfoRetrieve.getDescription(subInfoStr, symbolIndex);
	        System.out.println(repeatDate);
	        return new RawCommand(CMDTypes.COMMAND_TYPE.ADD.name(), taskTitle, 
	                            repeatDate, startDate, 
	                            endDate, description);
	        
	    } else if (markNumber == 1 || markNumber == 3) {
	        ErrorGenerator.popError(ErrorMSG.QUOTATION_UNCLOSE_ERR);
	        return CMDMaker.makeInvalid();
	
	    } else {
	        ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
	        return CMDMaker.makeInvalid();
	    }
	}

	/**
	 * Return a RawCommand for rename operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand rename(String subInfoStr) {
		if(subInfoStr.isEmpty()){
			return CMDMaker.makeInvalid();
		}
		return new RawCommand(CMDTypes.COMMAND_TYPE.RENAME.name(), subInfoStr);
	}

	/**
	 * Return a RawCommand for re-describe operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand describe(String subInfoStr) {
		if(subInfoStr.isEmpty()){
			return CMDMaker.makeInvalid();
		}
		return new RawCommand(CMDTypes.COMMAND_TYPE.DESCRIBE.name(), subInfoStr);
	}

	/**
	 * Return a RawCommand for re-schedule operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand reschedule(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return a RawCommand for repeat operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand repeat(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * To change/update a field in an input
	 * Change command field
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 */
	static RawCommand update(String subInfoStr){
	   String getUpdateItem;
	   String getUpdateInfo;
	
	    int getCommandEnd = subInfoStr.indexOf(ParserKeys.SPACE);
	    if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
	        ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
	        return CMDMaker.makeInvalid();
	    } else {
	        getUpdateItem = subInfoStr.substring(0, getCommandEnd).trim().toLowerCase();
	        getUpdateInfo = subInfoStr.substring(getCommandEnd + 1, subInfoStr.length());
	    }
	    ArrayList<Integer> symbolIndex = InfoRetrieve.getQuoteMark(getUpdateInfo);
	    int symbolNum = symbolIndex.size();
	    if (symbolNum == 2) {
	        String taskDescription = InfoRetrieve.getTaskTitle(InfoRetrieve.cleanFrontSpace(getUpdateInfo), symbolIndex);
	        
	        if (getUpdateItem.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.NAME.name())){
	            return rename(taskDescription);
	        } else if (getUpdateItem.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DESCRIPTION.name())){
	            return describe(taskDescription);
	        } else if (getUpdateItem.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESCHEDULE.name())){
	            return reschedule(taskDescription);
	        } else if (getUpdateItem.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.REPEAT.name())){
	            return repeat(taskDescription);
	        } else {
	            ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
	            return CMDMaker.makeInvalid();
	        }
	    } else {
	        ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
	        return CMDMaker.makeInvalid();
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
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 */
	static RawCommand read(String readTarget){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.READ.name(), readTarget);
	
	    return commandPackage;
	}

	/**
	 * Return a RawCommand for undo operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand undo(){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.UNDO.name());
	
	    return commandPackage;		
	}

	/**
	 * Return a RawCommand for delete operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand delete(String deleteIndex){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.DELETE.name(), deleteIndex);
	
	    return commandPackage;
	}

	/**
	 * Return a RawCommand of any invalid operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand makeInvalid() {
	    return new RawCommand(CMDTypes.COMMAND_TYPE.INVALID.name());
	}

	/**
	 * Return a RawCommand for view operation, switch to different view modes
	 * 
	 * @param viewTarget
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand view(String viewTarget){
	    if (viewTarget.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.TASKLIST.name()) ||
	            viewTarget.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.BIN.name())) {
	
	        RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.VIEW.name(), viewTarget);
	        return commandPackage;
	    } else {
	        ErrorGenerator.popError(ErrorMSG.VIEW_MODE_ERR);
	        return makeInvalid();
	    }
	}

	/** 
	 * Return a RawCommand for viewing Next page for current state of view
	 * 
	 * @return -RawCommand
	 */
	static RawCommand next(){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.NEXT.name());
	
	    return commandPackage;
	}

	/** 
	 * Go to previous page for current state of view
	 * 
	 * @return -RawCommand
	 */
	static RawCommand previous(){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.PREVIOUS.name());
	
	    return commandPackage;
	}

	/**
	 *  Restore item from bin
	 *  
	 *  @param restoreTarget
	 *         String of target restore index
	 *         
	 *  @return -RawCommand
	 */
	static RawCommand restore(String restoreTarget){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.RESTORE.name(), restoreTarget);
	
	    return commandPackage;
	}

	/**
	 * Back to the previous page
	 * */
	static RawCommand back(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.BACK.name());
		
		return commandPackage;
	}

	/** 
	 * Exiting the program
	 */
	static RawCommand exit(){
	    RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.EXIT.name());
	
	    return commandPackage;		
	}

}
