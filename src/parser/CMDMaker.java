package parser;

import java.util.ArrayList;
import java.util.Calendar;

import parser.CMDTypes.COMMAND_TYPE;

public class CMDMaker {

	/**
	 * Interpret "add" command and get its sub-information
	 * Split quotation mark contents with other contents
	 * 
	 * @param tokenPairs -RawCommand
	 * @return -CliToLog
	 * */
	static RawCommand add(ArrayList<TokenPair> tokenPairs) {
		try {
			String taskTitle;
			String repeatDate;
			String startDate;
			String endDate;
			String description;
			
			RawInfoPair titlePair;
			titlePair = InfoRetrieve.getTaskTitle(tokenPairs);
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
			
			if (endDate == null) {
				endDate = startDate;
				startDate = null;
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.ADD.name(), taskTitle, 
								  repeatDate, startDate, 
								  endDate, description);
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * To change/update a field in an input
	 * Change command field
	 * 
	 * @param tokenPairs
	 *              String of sub-information following the update command
	 * @param lacateLine
	 * 				The line number that the task is locating at
	 * @return -RawCommand
	 */
	static RawCommand update(ArrayList<TokenPair> tokenPairs){
		
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String field1 = firstPair.getCotent();
			
			TokenPair SecondPair = tokenPairs.get(1);
			String field2 = SecondPair.getCotent();
			
			if (field1.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.NAME.name())){
				tokenPairs.remove(0);
				return rename(tokenPairs);
			} else if (field2.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.NAME.name())){
				tokenPairs.remove(1);
				return rename(tokenPairs);
			} else if (field1.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DESCRIPTION.name())){
				tokenPairs.remove(0);
				return describe(tokenPairs);
			} else if (field2.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DESCRIPTION.name())){
				tokenPairs.remove(1);
				return describe(tokenPairs);
			} else if (field1.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESCHEDULE.name())){
				tokenPairs.remove(0);
				return reschedule(tokenPairs);
			} else if (field2.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESCHEDULE.name())){
				tokenPairs.remove(1);
				return reschedule(tokenPairs);
			} else if (field1.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.REPEAT.name())){
				tokenPairs.remove(0);
				return repeat(tokenPairs);
			} else if (field2.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.REPEAT.name())){
				tokenPairs.remove(1);
				return repeat(tokenPairs);
			} else {
				return makeInvalid();
			}
		} catch (Exception e) {
			ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
			return makeInvalid(); 
		}
	}
	
	/**
	 * Return a RawCommand for Search operation
	 * 
	 * @param tokenPairs
	 *              String of sub-information following the search command
	 * @return -RawCommand
	 * */
	static RawCommand search(ArrayList<TokenPair> tokenPairs) {
		try {
			String getContent = InfoRetrieve.getFrontUN(tokenPairs).getFront();
			return new RawCommand(COMMAND_TYPE.SEARCH.name(), getContent);
		} catch (Exception e) {
			return makeInvalid(); 
		}
	}
	
	/**
	 * Return a RawCommand for rename operation
	 * 
	 * @param tokenPairs
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand rename(ArrayList<TokenPair> tokenPairs) {
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String locateLine;
			
			// Get locate Line
			if (firstPair.getToken() == TokenType.TOKEN_TYPE.NB){
				locateLine = tokenPairs.remove(0).getCotent();
			} else {
				locateLine = null;
			}
			
			if(tokenPairs.isEmpty()){
				return makeInvalid();
			} else {
				return new RawCommand(CMDTypes.COMMAND_TYPE.RENAME.name(), 
									  InfoRetrieve.getAllSubInfo(tokenPairs).getFront(),
									  locateLine);
			}
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for re-describe operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the re-describe command
	 * @return -RawCommand
	 * */
	static RawCommand describe(ArrayList<TokenPair> tokenPairs) {
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String locateLine;
			
			// Get locate Line
			if (firstPair.getToken() == TokenType.TOKEN_TYPE.NB){
				locateLine = tokenPairs.remove(0).getCotent();
			} else {
				locateLine = null;
			}
			
			if(tokenPairs.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.DESCRIBE.name(), 
								  InfoRetrieve.getAllSubInfo(tokenPairs).getFront(),
								  locateLine);
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for re-schedule operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the reschedule command
	 * @param string 
	 * @return -RawCommand
	 * */
	static RawCommand reschedule(ArrayList<TokenPair> tokenPairs) {
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String locateLine;
			
			// Get locate Line
			if (firstPair.getToken() == TokenType.TOKEN_TYPE.NB){
				locateLine = tokenPairs.remove(0).getCotent();
			} else {
				locateLine = null;
			}
			if (tokenPairs.size() > 1){
				String startDate = InfoRetrieve.makeDay(tokenPairs.get(0).getCotent());
				String endDate = InfoRetrieve.makeDay(tokenPairs.get(1).getCotent());
				return new RawCommand(CMDTypes.COMMAND_TYPE.RESCHEDULE.name(), 
									  startDate,
									  endDate,
									  locateLine);
			} else {
				String endDate = InfoRetrieve.makeDay(tokenPairs.get(0).getCotent());
				return new RawCommand(CMDTypes.COMMAND_TYPE.RESCHEDULE.name(), 
									  null,
									  endDate,
									  locateLine);
			}
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Return a RawCommand for repeat operation
	 * 
	 * @param subInfoStr
	 *              String of sub-information following the repeat command
	 * @return -RawCommand
	 * */
	static RawCommand repeat(ArrayList<TokenPair> tokenPairs) {
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String locateLine;
			
			// Get locate Line
			if (firstPair.getToken() == TokenType.TOKEN_TYPE.NB){
				locateLine = tokenPairs.remove(0).getCotent();
			} else {
				locateLine = null;
			}
			
			if(tokenPairs.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.REPEAT.name(), 
								  tokenPairs.get(0).getCotent(),
								  locateLine);
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
	static RawCommand read(ArrayList<TokenPair> tokenPairs){
		try {
			if(tokenPairs.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.READ.name(), 
								  tokenPairs.get(0).getCotent());
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
	static RawCommand delete(ArrayList<TokenPair> tokenPairs){
		try {
			if(tokenPairs.isEmpty()){
				return makeInvalid();
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.DELETE.name(), 
								  tokenPairs.get(0).getCotent());
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Return a RawCommand for view operation, switch to different view modes
	 * 
	 * @param tokenPairs
	 *              String of sub-information following the update command
	 * @return -RawCommand
	 * */
	static RawCommand view(ArrayList<TokenPair> tokenPairs){
		try {
			String getFields = tokenPairs.get(0).getCotent();
			if (getFields.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.TASKLIST.name()) ||
				getFields.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.BIN.name())) {
	
				return new RawCommand(CMDTypes.COMMAND_TYPE.VIEW.name(), getFields);
			} else if (ValidityChecker.isValidViewDate(getFields)) {
				return translateDate(getFields);
			} else if (ValidityChecker.isValidDate(getFields)) {
				return viewDate(InfoRetrieve.makeDay(getFields));
			} else {
				ErrorGenerator.popError(ErrorMSG.VIEW_MODE_ERR);
				return makeInvalid();
			}
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Translate plain date to standardized date
	 * 
	 * @return RawCommand
	 * */
	private static RawCommand translateDate(String getFields) {
		Calendar cal = Calendar.getInstance();
		String date = ParserKeys.EMPTY_STR;
		
		if (getFields.equalsIgnoreCase("Today")) {
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit(cal.get(Calendar.DATE));
			return viewDate(InfoRetrieve.makeDay(date));
		} else if (getFields.equalsIgnoreCase("Tomorrow")) {
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit((cal.get(Calendar.DATE) + 1));
			return viewDate(InfoRetrieve.makeDay(date));
		} else {
			// getFields.equals("Yesterday")
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += toTwoDigit((cal.get(Calendar.DATE) - 1));
			return viewDate(InfoRetrieve.makeDay(date));
		} 
	}
	
	private static String toTwoDigit(int rawNum) {
		if (rawNum < 10 && rawNum >0) {
			return ParserKeys.ZERO + rawNum;
		} else {
			return (rawNum + ParserKeys.EMPTY_STR);
		}
	}

	/** 
	 * Return a RawCommand for viewing a certain date
	 * 
	 * @return -RawCommand
	 */
	private static RawCommand viewDate(String getDate) {
		try {
			return new RawCommand(CMDTypes.COMMAND_TYPE.VIEWDATE.name(), getDate);
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Return a RawCommand for mark a task
	 * 
	 * @return -RawCommand
	 * */
	static RawCommand mark(ArrayList<TokenPair> tokenPairs) {
		try {
			RawInfoPair getLinePair = InfoRetrieve.getNB(tokenPairs);
			String getLine = getLinePair.getFront();
			
			String getMarkInfo = InfoRetrieve.getFrontUN(tokenPairs).getFront();
			
			return new RawCommand(CMDTypes.COMMAND_TYPE.MARK.name(), getMarkInfo, getLine);
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
	 *  @param tokenPairs
	 *         String of target restore index
	 *         
	 *  @return -RawCommand
	 */
	static RawCommand restore(ArrayList<TokenPair> tokenPairs){
		try {
			RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.RESTORE.name(), 
													   tokenPairs.get(0).getCotent());
	
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
