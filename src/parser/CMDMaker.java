package parser;

import java.util.ArrayList;
import java.util.logging.Logger;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class <Strong>CMDMaker</Strong>: 
 *
 *<p>
 * CMDMaker accept an <code>ArrayList</code> of <code>TokenPairs</code>. It contains all 
 * the supported commands and their information retrieving procedures. All the commands would
 * it will packaging information by creating an object called <code>RawCommand</code>, 
 * which would contains the command and categorized sub-information.
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

	private static Logger logger = Logger.getLogger("CMDMaker");

	/**
	 * Generate a RawCommand for "Add" operation.
	 * 
	 * @param an <code>ArrayList</code> of <code>TokenPair</code> that contains tokenized words
	 * @return a RawCommand object
	 * */
	static RawCommand add(ArrayList<TokenPair> tokenPairs) {
		
		logger.info("Perform add operation");
		try {
			String taskTitle;
			String startDate;
			String endDate;
			String description;
			
			// Get the title and pass on rest information
			RawInfoPair titlePair;
			titlePair = InfoRetrieve.getTaskTitle(tokenPairs);
			taskTitle = titlePair.getFront();
			
			// Get the start date and pass on rest information
			RawInfoPair startPair;
			startPair = InfoRetrieve.getDate(titlePair.getSubInfo());
			startDate = startPair.getFront();
			
			// Get the end date and pass on rest information
			RawInfoPair endPair;
			endPair = InfoRetrieve.getDate(startPair.getSubInfo());
			endDate = endPair.getFront();
			
			description = InfoRetrieve.getDescription(endPair.getSubInfo());
			
			if (endDate == null) {
				endDate = startDate;
				startDate = null;
			}
			return new RawCommand(CMDTypes.COMMAND_TYPE.ADD.name(), 
								  taskTitle, startDate, 
								  endDate, description);
		} catch (Exception e) {
			return makeInvalid();
		}
	}
	
	/**
	 * Generate a RawCommand for "Update" operation
	 * 
	 * @param an ArrayList TokenPair that contains tokenized words
	 * @return a RawCommand object
	 */
	static RawCommand update(ArrayList<TokenPair> tokenPairs){
		
		logger.info("Perform update operation");
		try {
			TokenPair firstPair = tokenPairs.get(0);
			String field1 = firstPair.getCotent();
			
			TokenPair SecondPair = tokenPairs.get(1);
			String field2 = SecondPair.getCotent();
			
			// Check if there is a line number specified. Line number is used for 
			// manipulating a specific task when having tasklist view
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
		
		logger.info("Perform search operation");
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
		
		logger.info("Perform rename operation");
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
		
		logger.info("Perform describe operation");
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
		
		logger.info("Perform reshcedule operation");
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
		
		logger.info("Perform repeat operation");
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
		
		logger.info("Perform read operation");
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
		logger.info("Perform undo operation");
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
		
		logger.info("Perform delete operation");
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
		
		logger.info("Perform view operation");
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
		
		logger.info("Perform viewDate operation");
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
		
		logger.info("Perform mark operation");
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
		
		logger.info("Perform next operation");
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.NEXT.name());

		return commandPackage;
	}

	/** 
	 * Generate a RawCommand for going to previous page for current state of view
	 * 
	 * @return -RawCommand
	 */
	static RawCommand previous(){
		
		logger.info("Perform previous operation");
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
		
		logger.info("Perform restore operation");
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
		
		logger.info("Perform back operation");
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.BACK.name());

		return commandPackage;
	}
	
	/**
	 * Generate a RawCommand of any invalid operation
	 * 
	 * @return -RawCommand
	 * */
	static RawCommand makeInvalid() {
		
		logger.info("Perform makeInvalid operation");
		return new RawCommand(CMDTypes.COMMAND_TYPE.INVALID.name());
	}
	
	/**
	 * Generate a RawCommand of help operation
	 * 
	 * @return -RawCommand
	 * */
	static RawCommand help() {
		
		logger.info("Perform help operation");
		return new RawCommand(CMDTypes.COMMAND_TYPE.HELP.name());
	}
	
	/** 
	 * Generate a RawCommand for exiting the program
	 *
	 * @return a RawCommand object
	 */
	static RawCommand exit(){
		
		logger.info("Perform exit operation");
		RawCommand commandPackage = new RawCommand(CMDTypes.COMMAND_TYPE.EXIT.name());

		return commandPackage;		
	}
}
