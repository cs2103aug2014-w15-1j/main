package parser;

/**
 * class CMDTypes: All the commands supported by the application
 * 
 * @author A0119493X
 * */
class CMDTypes {

	/**
	 * Acceptable command types
	 * */
	enum COMMAND_TYPE {
	    ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT, NEXT, PREVIOUS, SEARCH, BACK,
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
