package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;

public class CMDMaker {

	/**
	 * Interpret "add" command and get its sub-information
	 * Split quotation mark contents with other contents
	 * 
	 * @param tokens -RawCommand
	 * @return -CliToLog
	 * */
	static RawCommand add(ArrayList<String> tokens) {
		String taskTitle;
		String repeatDate;
		String startDate;
		String endDate;
		String description;
		
		RawInfoPair titlePair;
		titlePair = InfoRetrieve.getTaskTitle(tokens);
		taskTitle = titlePair.getFront();
		
		RawInfoPair repeatPair;
		repeatPair = InfoRetrieve.getRepeatDate(titlePair.getSubInfo());
		repeatDate = repeatPair.getFront();
		
		RawInfoPair startPair;
		startPair = InfoRetrieve.getDate(repeatPair.getSubInfo());
		startDate = startPair.getFront();
		
		RawInfoPair endPair;
		endPair = InfoRetrieve.getDate(startPair.getSubInfo());
		endDate = endPair.getFront();
		
		
		description = InfoRetrieve.getDescription(endPair.getSubInfo());
		
		return new RawCommand(CMDTypes.COMMAND_TYPE.ADD.name(), taskTitle, 
				repeatDate, startDate, 
				endDate, description);
	}
	
	/**
	 * To change/update a field in an input
	 * Change command field
	 * 
	 * @param tokens
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 */
	static RawCommand update(ArrayList<String> tokens){
		
		try {
			String field = tokens.get(0);
			
			if (field.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.NAME.name())){
				tokens.remove(0);
				return rename(tokens);
			} else if (field.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DESCRIPTION.name())){
				tokens.remove(0);
				return describe(tokens);
			} else if (field.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESCHEDULE.name())){
				tokens.remove(0);
				return reschedule(tokens);
			} else if (field.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.REPEAT.name())){
				return repeat(tokens);
			} else {
				return makeInvalid();
			}
		} catch (Exception e) {
			ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
			return makeInvalid(); 
		}
	}
	
	/**
	 * Return a RawCommand for rename operation
	 * 
	 * @param tokens
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand rename(ArrayList<String> tokens) {
		try {
			if(tokens.isEmpty()){
				return makeInvalid();
			} else {
				return new RawCommand(CMDTypes.COMMAND_TYPE.RENAME.name(), tokens.get(0));
			}
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for re-describe operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand describe(ArrayList<String> tokens) {
		try {
			if(tokens.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.DESCRIBE.name(), tokens.get(0));
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for re-schedule operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @param string 
	 * @return -RawCommand
	 * */
	static RawCommand reschedule(ArrayList<String> tokens) {
		try {
			String startDate = tokens.get(0);
			String endDate = tokens.get(1);
			return new RawCommand(CMDTypes.COMMAND_TYPE.RESCHEDULE.name(), 
								  startDate,
								  endDate);
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for repeat operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand repeat(ArrayList<String> tokens) {
		try {
			if(tokens.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.REPEAT.name(), tokens.get(0));
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/** 
	 * Read details of a certain task
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 */
	static RawCommand read(ArrayList<String> tokens){
		try {
			if(tokens.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.READ.name(), tokens.get(0));
		} catch (Exception e) {
			return makeInvalid();
		}
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
	static RawCommand delete(ArrayList<String> tokens){
		try {
			if(tokens.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.DELETE.name(), tokens.get(0));
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Return a RawCommand for view operation, switch to different view modes
	 * 
	 * @param tokens
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand view(ArrayList<String> tokens){
		try {
			String getFields = tokens.get(0);
			if (getFields.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.TASKLIST.name()) ||
				getFields.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.BIN.name())) {
	
				RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.VIEW.name(), getFields);
				return commandPackage;
			} else {
				ErrorGenerator.popError(ErrorMSG.VIEW_MODE_ERR);
				return makeInvalid();
			}
		} catch (Exception e) {
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
	 *  @param tokens
	 *         String of target restore index
	 *         
	 *  @return -RawCommand
	 */
	static RawCommand restore(ArrayList<String> tokens){
		try {
			RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.RESTORE.name(), 
													   tokens.get(0));
	
			return commandPackage;
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Back to the previous page
	 * */
	static RawCommand back(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.BACK.name());

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
	 * Exiting the program
	 */
	static RawCommand exit(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.EXIT.name());

		return commandPackage;		
	}
}
