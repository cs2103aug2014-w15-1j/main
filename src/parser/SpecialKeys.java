package parser;

/**
 * class <Strong>ParserKeys</Strong>: 
 * 
 * <p>
 * This class contains all the Special characters reserved for Parser, including date fields,
 * special keys for command identifying and some default information to be filled in 
 * <code>RawCommand</code>
 * </p>
 * 
 * @author A0119493X
 *          Containing special keys to help interpreting input strings, including separating
 *          symbols, special field names, and invalid symbols
 * */

class ParserKeys {
	static final String SPLITSYMBOL = "\"";
	static final String SPLIT_DATE = "-";
	static final String SPACE = " ";
	static final String INVALID_SYMBOL = "=";
	static final String EMPTY_STR = "";
	static final String EMPTY_TITLE = "EMPTY TITLE";
	static final String EMPTY_DIS = "EMPTY DESCRIPTION";
	static final String ZERO = "0";
	static final String EMPTY_DATE = null;
	static final String FIELD_TODAY = "today";
	static final String FIELD_TMR = "tomorrow";

	static final String RP_EVREYDAY = "everyday";
	static final String RP_MON = "monday";
	static final String RP_TUES = "tuesday";
	static final String RP_WED = "wednesday"; 
	static final String RP_THUR = "thursday";
	static final String RP_FRI = "friday";
	static final String RP_SAT = "saturday";
	static final String RP_SUN = "sunday";
	static final String RP_NON = "no_repeat";
	static final String[] REPEAT_KEYS = {"everyday", "monday", "tuesday", 
										 "wednesday", "thursday", "friday", 
										 "saturday", "sunday", "no_repeat", ""};
	static final String[] VIEW_DATE = {"yesterday", "today", "tomorrow", "nextweek"};
	static final String[] COMMANDS = {"add", "update", "rename","reschedule",
									  "repeat","describe","delete","view",
									  "read","undo","restore","exit","next", 
									  "previous", "back", "search", "viewdate", "mark", "help"};

	static final String[] KEYS_ADD = 	{"a", "d"};
	static final String[] KEYS_UPDATE = {"u", "p", "d"};
	static final String[] KEYS_RENAME = {"r", "n"};
	static final String[] KEYS_RESCHEDULE = {"r", "s", "c", "d"};
	static final String[] KEYS_REPEAT =   {"r", "p", "t"};
	static final String[] KEYS_DESCRIBE = {"d", "s", "b"};
	static final String[] KEYS_DELETE =   {"d", "l"};
	static final String[] KEYS_VIEW = {"v", "e", "w"};
	static final String[] KEYS_READ = {"r", "d"};
	static final String[] KEYS_UNDO = {"u", "d"};
	static final String[] KEYS_RESTORE = {"r", "s", "t"};
	static final String[] KEYS_EXIT = 	 {"e", "x"};
	static final String[] KEYS_NEXT = 	 {"n", "x"};
	static final String[] KEYS_PREVIOUS = {"p", "r"};
	static final String[] KEYS_BACK = 	  {"b", "c"};
	static final String[] KEYS_SEARCH =   {"s", "r", "c"};
	static final String[] KEYS_VIEWDATE = {"v", "d"};
	static final String[] KEYS_MARK = {"m"};
	static final String[] KEYS_HELP = {"h", "l"};

	static final String INVALID_INFO = "invalid info";

	static final int DATE_LENGTH = 8;
	static final int INDEX_NOT_EXIST = -1;
}