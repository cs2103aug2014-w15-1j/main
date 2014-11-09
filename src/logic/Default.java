package logic;

public class Default {
	// the line number to display in GUI
	public static int MAX_DISPLAY_LINE = 10;
	// standard format of date from parser
	public static int LENGTH_OF_DATE_FORMAT = 8;	
	// maximum tasks to store
	public static int MAX_TASKS = 65535;
	// define highlight part index for GUI display
	public static int NO_HIGHLIGHT = -1;
	public static int HIGHLIGHT_LINE = 1;
	public static int HIGHLIGHT_PROPERTY = 2;
	public static int HIGHLIGHT_DATE = 3;
	public static int HIGHLIGHT_LINES = 4;
	// define properties of a task to integers
	public static int NAME = 1;
	public static int STARTDATE = 2;
	public static int ENDDATE = 3;
	public static int BOTHDATE = 4;
	public static int DESCRIPTION = 5;
	public static int MARK = 6;
	public static String LINE_BREAK = "\n";
	public static String helpInfo = 
			"Help Mode:" 
					+ LINE_BREAK + LINE_BREAK +
			"Commands:"
					+ LINE_BREAK +
			
			"Add      : Add a new Tasklist                   [Add <title> <Start Time YYYY-MM-DD> <End Time YYYY-MM-DD> <Description>]"
				+ LINE_BREAK +
			"Back     : Back to Tasklist View                [Delete <index>]"
				+ LINE_BREAK +
			"                                                [Delete all]"
				+ LINE_BREAK +
			"Delete   : Delete a task"
				+ LINE_BREAK +
			"Describe : Redescribe a task                    [Describe <new description>]"
				+ LINE_BREAK +
			"Mark     : Mark the status                      [Mark <index> <status 'Done' or 'Undone'>]"
				+ LINE_BREAK +
			"Next     : View next page"
				+ LINE_BREAK +
			"Previous : View previous page"
				+ LINE_BREAK +
			"Read     : Read a specific task                 [Read <index>]"
				+ LINE_BREAK +
			"Rename   : Rename a task title                  [Rename <new name>]"
				+ LINE_BREAK +
			"Search   : Search task title                    [Search <keyword>]"
				+ LINE_BREAK +
			"Update   : Update task detail                   [Update <index (Optional)> name <new name>]"
				+ LINE_BREAK +
			"                                                [Update <index (Optional)> description <new description>]"
			+ LINE_BREAK +	
			"Undo     : Undo last operation"
			+ LINE_BREAK + LINE_BREAK + 
			"View     : View a field                         [View <date>]"
			+ LINE_BREAK +
			"                                                [View bin]"
			+ LINE_BREAK +
			"                                                [View tasklist]"
			+ LINE_BREAK +
			
			"Shortcuts:"
			+ LINE_BREAK +
			"F1:    view today"
			+ LINE_BREAK +
			"F2:    view tomorrow"
			+ LINE_BREAK +
			"F3:    view undone"
			+ LINE_BREAK +
			"F4:    view tasklist"
			+ LINE_BREAK +
			"F5:    view bin";
}
