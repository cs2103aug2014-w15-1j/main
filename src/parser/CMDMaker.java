package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class <Strong>CMDMaker</Strong>: 
 *
 *<p>
 * CMDMaker accept an <code>ArrayList</code> of <code>TokenPairs</code>, it retrieves valid information 
 * regarding to different commands. it will packaging all the information by creating 
 * an object called <code>RawCommand</code>, it would contains the command and categorized sub-information.
 *</p>
 *
 *<p>
 * Each type of commands possess their own information retrieving patterns
 *</p>
 * 
 * @author A0119493X
 * @see RawCommand
 * */
class CMDMaker {

	/**
	 * Generate a RawCommand for "Add" operation.
	 * 
	 * @param an <code>ArrayList</code> of <code>TokenPair</code> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for "Update" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for "Search" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for "Rename" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
			
			return new RawCommand(CMDTypes.COMMAND_TYPE.RENAME.name(), 
								  InfoRetrieve.getAllSubInfo(tokenPairs).getFront(),
								  locateLine);
			
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Generate a RawCommand for "Re-describe" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
		
			return new RawCommand(CMDTypes.COMMAND_TYPE.DESCRIBE.name(), 
								  InfoRetrieve.getAllSubInfo(tokenPairs).getFront(),
								  locateLine);
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/**
	 * Generate a RawCommand for "Re-schedule" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for "Repeat" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for "Read" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Return a RawCommand for "Undo" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
	 * */
	static RawCommand undo(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.UNDO.name());

		return commandPackage;		
	}

	/**
	 * Return a RawCommand for "Delete" operation
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Return a RawCommand for "View" operation.
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
	 * */
	static RawCommand view(ArrayList<TokenPair> tokenPairs){
		try {
			String getFields = tokenPairs.get(0).getCotent();
			if (ValidityChecker.isValidViewDate(getFields)) {
				return InfoRetrieve.translateDate(getFields);
			} else if (ValidityChecker.isValidDate(getFields)) {
				return viewDate(InfoRetrieve.makeDay(getFields));
			} else {
				return new RawCommand(CMDTypes.COMMAND_TYPE.VIEW.name(), getFields);
			}
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/** 
	 * Generate a RawCommand for viewing a certain date
	 * 
	 * @param String date
	 * @return -RawCommand
	 */
	static RawCommand viewDate(String getDate) {
		try {
			return new RawCommand(CMDTypes.COMMAND_TYPE.VIEWDATE.name(), getDate);
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Generate a RawCommand for marking a task
	 * 
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
	 * */
	static RawCommand mark(ArrayList<TokenPair> tokenPairs) {
		try {
			RawInfoPair getLinePair = InfoRetrieve.getNB(tokenPairs);
			String getLine = getLinePair.getFront();
			
			String getMarkInfo = InfoRetrieve.getFrontUN(getLinePair.getSubInfo()).getFront();
			System.out.println("!" + getMarkInfo + "!");
			return new RawCommand(CMDTypes.COMMAND_TYPE.MARK.name(), getMarkInfo, getLine);
		} catch (Exception e) {
			return makeInvalid();
		}
	}

	/** 
	 * Generate a RawCommand for viewing Next page for current state of view
	 * 
	 * @return -RawCommand
	 */
	static RawCommand next(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.NEXT.name());

		return commandPackage;
	}

	/** 
	 * Generate a RawCommand for going to previous page for current state of view
	 * 
	 * @return -RawCommand
	 */
	static RawCommand previous(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.PREVIOUS.name());

		return commandPackage;
	}

	/**
	 * Generate a RawCommand for restoring item from bin
	 *  
	 * @param an ArrayList<TokenPair> that contains tokenized words
	 * @return a RawCommand object
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
	 * Generate a RawCommand for backing to the previous page
	 * 
	 * @return a RawCommand object
	 * */
	static RawCommand back(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.BACK.name());

		return commandPackage;
	}
	
	/**
	 * Generate a RawCommand of any invalid operation
	 * 
	 * @return -RawCommand
	 * */
	static RawCommand makeInvalid() {
		return new RawCommand(CMDTypes.COMMAND_TYPE.INVALID.name());
	}
	
	/**
	 * Generate a RawCommand of help operation
	 * 
	 * @return -RawCommand
	 * */
	static RawCommand help() {
		return new RawCommand(CMDTypes.COMMAND_TYPE.HELP.name());
	}
	
	/** 
	 * Generate a RawCommand for exiting the program
	 *
	 * @return a RawCommand object
	 */
	static RawCommand exit(){
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.EXIT.name());

		return commandPackage;		
	}
}
