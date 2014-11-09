package logic;

/**
 * 
 * @author a0119456Y
 *
 */
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
	public static String HTML_BR = "<br>";
	public static String HTML_TR1 = "<tr>";
	public static String HTML_TR2 = "</tr>";
	public static String HTML_TD1 = "<td>";
	public static String HTML_TD2 = "</td>";
	public static String helpInfo = "<html>" + "<table style='width:100%'>" + 
			HTML_TR1 + HTML_TD1 + "Add" + HTML_TD2  + HTML_TD1 + "Add a new Tasklist "+ HTML_TD2  + HTML_TD1 + "[Add &lt;title&gt; &lt;Start Time YYYY-MM-DD&gt; &lt;End Time YYYY-MM-DD&gt; &lt;Description&gt;]" + HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Back" + HTML_TD2  + HTML_TD1 + "Back to Tasklist View "+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Delete" + HTML_TD2  + HTML_TD1 + "Delete a task"+ HTML_TD2  + HTML_TD1 + "HTML_TD2"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + " [Delete all]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Describe" + HTML_TD2  + HTML_TD1 + "Redescribe a task"+ HTML_TD2  + HTML_TD1 + "[Describe &lt;new description&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Mark" + HTML_TD2  + HTML_TD1 + "Mark the status "+ HTML_TD2  + HTML_TD1 + " [Mark &lt;index&gt; &lt;status 'Done' or 'Undone'&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Next" + HTML_TD2  + HTML_TD1 + "View next page"+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Previous " + HTML_TD2  + HTML_TD1 + "View previous page"+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Read" + HTML_TD2  + HTML_TD1 + "Read a specific task "+ HTML_TD2  + HTML_TD1 + "[Read &lt;index&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Rename" + HTML_TD2  + HTML_TD1 + "Rename a task title"+ HTML_TD2  + HTML_TD1 + " [Rename &lt;index&gt; &lt;new name&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Search" + HTML_TD2  + HTML_TD1 + "Search task title"+ HTML_TD2  + HTML_TD1 + "[Search &lt;keyword&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Update" + HTML_TD2  + HTML_TD1 + "Update task detail"+ HTML_TD2  + HTML_TD1 + "[Update &lt;index (Optional)&gt; name &lt;new name&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + " [Update &lt;index (Optional)&gt; description &lt;new description&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "Undo" + HTML_TD2  + HTML_TD1 + "Undo last operation"+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "View" + HTML_TD2  + HTML_TD1 + "View a field"+ HTML_TD2  + HTML_TD1 + "[View &lt;date&gt;]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + " [View bin]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + " [View tasklist]"+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			HTML_TR1 + HTML_TD1 + "" + HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TD1 + ""+ HTML_TD2  + HTML_TR2 +
			"</table>"+ "" +
			"<H2><center>Shortcuts:</center></H2>"
			+
			"<H2><Strong><center>F1:    view today"
			+ "&nbsp;&nbsp;&nbsp;" +
			"F2: view tomorrow"
			+ "&nbsp;&nbsp;&nbsp;" +
			"F3: view undone"
			+ "&nbsp;&nbsp;&nbsp;" +
			"F4: view tasklist"
			+ "&nbsp;&nbsp;&nbsp;" +
			"F5: view bin"
			+ "&nbsp;&nbsp;&nbsp;" +
			"F6: help</center></Strong></h2>"
			+
			"</html>";
			
 
		
			
			
}
