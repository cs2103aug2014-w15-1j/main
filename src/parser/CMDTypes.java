package parser;

/**
 * class <Strong>CMDTypes</Strong>: 
 * 
 * <p>
 * This class all the commands and special fields supported by the application.
 * </p>
 * 
 * <p>
 * Commands including all the basic task manipulations. Fields including all the identifiable
 * fields such as date and view objects
 * </p>
 * 
 * @author A0119493X
 * */
class CMDTypes {

	/**
	 * Acceptable command types
	 * */
	enum COMMAND_TYPE {
	    ADD, DELETE, UPDATE, READ, VIEW, VIEWDATE, UNDO, INVALID, 
	    EXIT, NEXT, PREVIOUS, SEARCH, BACK, MARK, HELP,
	    // VIEW_MODE
	    TASKLIST, BIN, 
	    // from bin
	    RESTORE,
	    // UPDATE
	    RENAME, RESCHEDULE, DESCRIBE, REPEAT,
	    // UPDATE_FIELD
	    NAME, DESCRIPTION, DATE, DAY;
	}
}
