package cli;


public class CliProcess {

     private static final String NULLED = "null";
	
	 enum COMMAND_TYPE {
		ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT, NEXT,
		//UPDATE
		RENAME, RESCHEDULE, DESCRIBE,
		//VIEW
		DATE, MONTH, BIN,
		//
		REPEAT, SEARCH;
	}
   
    /* Command line will be processed and
     * a pack or arguments will be passed on
     */
	public static CliToLog interpretCommand(String s){ 
		    String[] inputLine = new String[7]; 
		    inputLine = separate(s);
		    //Index of array 0,1,2,3,4,5,6 as argument	
		    return doCommand(inputLine);		   
   }
   
   // Split into string into array of arguments 
   private static String[] separate(String s){
	   String[] inputSplit = new String[7];
	   //Split into array of index 0,1,2,3,4,5,6
	  inputSplit = s.split(" ", 7);
	  for (String stringNode : inputSplit) {
          if (stringNode == null) 
        	  stringNode = NULLED;
          }
	  return inputSplit;
   }
   
   private static COMMAND_TYPE determineCommandType(String commandTypeString){
	   if (commandTypeString == null)
			throw new Error("COMMAND_TYPE type string cannot be null!");

		if (commandTypeString.equalsIgnoreCase("add")) {
			return COMMAND_TYPE.ADD;
		} else if (commandTypeString.equalsIgnoreCase("delete")) {
			return COMMAND_TYPE.DELETE;
		} else if (commandTypeString.equalsIgnoreCase("update")) {
		 	return COMMAND_TYPE.UPDATE;
		} else if (commandTypeString.equalsIgnoreCase("exit")) {
			return COMMAND_TYPE.EXIT;
		} else if (commandTypeString.equalsIgnoreCase("view")) {
			return COMMAND_TYPE.VIEW;
		} else if (commandTypeString.equalsIgnoreCase("read")) {
			return COMMAND_TYPE.READ;
		} else if (commandTypeString.equalsIgnoreCase("undo")) {
			return COMMAND_TYPE.UNDO;
		} else if (commandTypeString.equalsIgnoreCase("next")) {
			return COMMAND_TYPE.NEXT;
		} else {
			return COMMAND_TYPE.INVALID;
		}
	} 
   
   private static CliToLog doCommand(String[] inputExecute){
	   COMMAND_TYPE userCommand;
	   
	   userCommand = determineCommandType(inputExecute[0]);
	   
	   switch(userCommand){
	   case ADD:
			return add(inputExecute);
	   case UPDATE:
			return update(inputExecute);
       case READ:
            return read(inputExecute);
       case DELETE:
    	   	return delete(inputExecute);
        case VIEW:
            return view(inputExecute);
		case UNDO:
			return undo(inputExecute);
		case NEXT:
		    return next(inputExecute);
		case EXIT:
			return exit(inputExecute);
		default:
			return invalid(inputExecute);
	   }
   }
   
   /* Create a new entry
    * 
    */
   private static CliToLog add(String[] strArr){
	   CliToLog commandPackage = new CliToLog(strArr);
	   
	   return commandPackage;
   }
   
   /* To change/update a field in an input
    * 
    * Change command field
    */
   private static CliToLog update(String[] strArr){
	   String[] newS = null;
	   String s;
	   
	   s = identifyField(strArr[1]);
	   newS = changeCommand(s,strArr);
	   
	   CliToLog commandPackage = new CliToLog(newS); 
	   
	   return commandPackage;
   }
   
	/* Function: Update
	 * To identify the field to update
	 * 
	 */
	private static String identifyField(String s){
		String sNew = null;
		
		//Check field to be changed
		   if (s.equals("name")){
			   sNew = COMMAND_TYPE.RENAME.name();
		   }else
		   if (s.equals("date") || s.equals("day")){
			   sNew = COMMAND_TYPE.RESCHEDULE.name();
		   }else
		   if (s.equals("description")){
			   sNew = COMMAND_TYPE.DESCRIBE.name();
		   }
		return sNew;
	}
   
	/* Change commands for Update and View
	 * 
	 * Also shift up the arguments after new command
	 * @argument New command , array of strings to be changed
	 */
	private static String[] changeCommand(String s, String[] strArr){
	   strArr[0] = s;
	   for(int i = 1; i<6; i++){
		   strArr[i]= strArr[i+1];
	   }	   
	   return strArr; 
	}
	
	
	/* Read details of a certain task
	 * 
	 * Precondition: (View) identifyMode() must be called before this
	 * To open up an indexed view
	 * @argument strArr[1] will be an index, need to be converted to integer
	 */
	private static CliToLog read(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;
	}
	
	private static CliToLog undo(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;		
	}
	
	/* Delete a certain task
	 * 
	 *  @argument strArr[1] will be an index, need to be converted to integer
	 */
	private static CliToLog delete(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;
	}
	
	/* View a page of items
	 * 
	 * Different modes available
	 * Change command field
	 */
	private static CliToLog view(String[] strArr){
		String[] newS = new String[7];
		String s;
		
		s = identifyMode(strArr[1]);
		
		if(s.equals(COMMAND_TYPE.BIN.name())){
			newS = changeViewCommand(s, strArr);
		} else
		if (s.equals(COMMAND_TYPE.NEXT.name())){
			newS = changeCommand(s, strArr);		
		} else{
			newS = addViewCommand(s, strArr);
		}		
		
		CliToLog commandPackage = new CliToLog(newS);
		
		return commandPackage;
	}
	
	/* Add new command for View
	 * 
	 * Also shift down the arguments after new command
	 * @argument 2 commands in total, array of strings to be changed
	 */
	private static String[] addViewCommand(String s, String[] strArr){
		//date, month
		String temp = strArr[1];
		strArr[2] = temp;
		strArr[1] = s;
		
		return strArr;
	}
	
	/* Change commands for Update
	 * 
	 * Also shift up the arguments after new command
	 * @argument New command , array of strings to be changed
	 */
	private static String[] changeViewCommand(String s, String[] strArr){
		//bin
		strArr[1] = s;
		
		return strArr;
	}
	
	
	
	/* Function: View
	 * To identify different modes for View 
	 * 
	 */
	private static String identifyMode(String s){
		String sNew;
		
		if(s.equalsIgnoreCase("nextpage")){
			sNew = COMMAND_TYPE.NEXT.name();
		}else
		if(s.equalsIgnoreCase("bin")){
			sNew = COMMAND_TYPE.BIN.name();
		}else
		if(s.equalsIgnoreCase("jan") || s.equalsIgnoreCase("feb") || s.equalsIgnoreCase("mar") || s.equalsIgnoreCase("apr") || s.equalsIgnoreCase("may") || s.equalsIgnoreCase("june") ||
				s.equalsIgnoreCase("jul") || s.equalsIgnoreCase("aug") || s.equalsIgnoreCase("sep") || s.equalsIgnoreCase("oct") ||s.equalsIgnoreCase("nov") || s.equalsIgnoreCase("dec")){
			sNew = COMMAND_TYPE.MONTH.name();
		}else{
			//date
			
			sNew = COMMAND_TYPE.DATE.name();
		}
		
		return sNew;
	}
    
	/* Next page for current state of view
	 * 
	 */
	private static CliToLog next(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;
	}
	
	/* Invalid command was read
	 * 
	 */
	private static CliToLog invalid(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;
	}
	
	/* Exiting the program
	 * 
	 */
	private static CliToLog exit(String[] strArr){
		CliToLog commandPackage = new CliToLog(strArr);
		
		return commandPackage;		
	}
}
