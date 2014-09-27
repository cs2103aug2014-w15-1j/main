package cli;

import java.io.*;
import java.util.*;

public class W {
	
	 private static final String MSG_ASK = "Please enter:";
     private static final String NOT_READ = "File was not read.";
     private static final String NOT_SAVED = "File was not saved.";
     private static final String NULLED = "null";
     
     private ArrayList<String> inputArray;
     private static Scanner sc = new Scanner(System.in);
     private static String inputFileName;
     /*
      * Undo Function:
      * new global Stack???
      *
      */
	   
	 public W(String f){
		 inputFileName = f;
	 }
	
	 enum COMMAND_TYPE {
		ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT,
		//UPDATE
		RENAME, RESCHEDULE, DESCRIBE,
		//VIEW
		DAY, MONTH, BIN, NEXT;	
	}
	
	 /* How to pass the package to ? Not sure here
	  * 
	  */
   public static Thingy main(String[] args){
	   String fileName;
	   Thingy commandPackage;
	   
	   try{
		   fileName = args[0];
	   } catch (Exception e){
		   fileName = "testing.txt";
	   }
	  
	   W first = new W(fileName);
	   
	   // Execute the string to get a command package
	   commandPackage = first.runProg();
	 
	  return commandPackage;
	  
   }
   
   private void showToUser(String text){
	   System.out.println(text);
   }
   
   private void readFile(){
       try {

       	FileReader file = new FileReader(inputFileName);
       	BufferedReader inputList = new BufferedReader(file);
       	String input;

       	while((input = inputList.readLine()) != null){
       		inputArray.add(input);
       	}
       	inputList.close();
       	file.close();

       } catch (IOException e){
       	showToUser(NOT_READ);
       }
   }
   
   private void saveFile(){
       int i=0;

		try{

			FileWriter file = new FileWriter(inputFileName, false);
			PrintWriter saveList = new PrintWriter(file);
			for(i=0; i < inputArray.size(); i++){
				saveList.println(inputArray.get(i));
			}
			saveList.close();
			file.close();
		} catch (IOException e){
			showToUser(NOT_SAVED);
		}
	}
   
   private Thingy runProg(){
	   Thingy pack;

	   showToUser(MSG_ASK);
	   while(true){
		   readFile();
		   
		    String[] input = new String[5];
		    input = separate();
		    //Index of array 0,1,2,3,4 as argument
		    pack = executeCommand(input);
		   
		    saveFile();
		    // WHERE DO I RETURN THE FINAL PACKAGE
		    return pack;
	   }   
   }
   
   // Split into string into array of arguments 
   private String[] separate(){
	  //Split into array of index 0,1,2,3,4
	  String[] inputSplit = sc.nextLine().split(" ", 5);
	  for (String stringNode : inputSplit) {
          if (stringNode == null) 
        	  stringNode = NULLED;
          }
	  return inputSplit;
   }
   
   private COMMAND_TYPE determineCommandType(String commandTypeString){
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
		} else {
			return COMMAND_TYPE.INVALID;
		}
	} 
   
   private Thingy executeCommand(String[] inputExecute){
	   COMMAND_TYPE userCommand;
	   Thingy t;
	   
	   userCommand = determineCommandType(inputExecute[0]);
	   
	   switch(userCommand){
	   case ADD:
			t = add(inputExecute);
			break;
	   case UPDATE:
			t = update(inputExecute);
			break;
       case READ:
            t = read(inputExecute);
            break;
		case DELETE:
		    t = delete(inputExecute);
			break;
		case VIEW:
            t = view(inputExecute);
            break;
/*		case UNDO:

	
			break;
			*/
		default:
			//throw an error if the command is not recognized
			throw new Error("NO IDEA WHAT THAT IS");
		}
	   
	   return t;
   }
   
   /* Create a new entry
    * 
    */
   private Thingy add(String[] strArr){
	   Thingy commandPackage = new Thingy(strArr);
	   
	   return commandPackage;
   }
   
   /* To change/update a field in an input
    * 
    * Change command field
    */
   private Thingy update(String[] strArr){
	   String[] newS = null;
	   String s;
	   
	   s = identifyField(strArr[1]);
	   newS = changeCommand(s,strArr);
	   
       Thingy commandPackage = new Thingy(newS);   
	
	return commandPackage;
   }
   
	/* Function: Update
	 * To identify the field to update
	 * 
	 */
	private String identifyField(String s){
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
	private String[] changeCommand(String s, String[] strArr){
	   strArr[0] = s;
	   for(int i = 1; i< 4; i++){
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
	private Thingy read(String[] strArr){
        Thingy commandPackage = new Thingy(strArr);
        
		return commandPackage;		
	}
	
	/* Delete a certain task
	 * 
	 *  @argument strArr[1] will be an index, need to be converted to integer
	 */
	private Thingy delete(String[] strArr){
		Thingy commandPackage = new Thingy(strArr);
		
		return commandPackage;
	}
	
	/* View a page of items
	 * 
	 * Different modes available
	 * Change command field
	 */
	private Thingy view(String[] strArr){
		String[] newS;
		String s;
		
		s = identifyMode(strArr[1]);
		newS = changeCommand(s, strArr);
		
		Thingy commandPackage = new Thingy(newS);
		
		return commandPackage;	
	}
	
	/* Function: View
	 * To identify different modes for View 
	 * 
	 */
	private String identifyMode(String s){
		String sNew;
		
		if(s.equals("nextpage")){
			sNew = COMMAND_TYPE.NEXT.name();
		}else
		if(s.equals("bin")){
			sNew = COMMAND_TYPE.BIN.name();
		}else
		if(s.equals("jan") || s.equals("feb") || s.equals("mar") || s.equals("apr") || s.equals("may") || s.equals("june") ||
				s.equals("jul") || s.equals("aug") || s.equals("sep") || s.equals("oct") ||s.equals("nov") || s.equals("dec")){
			sNew = COMMAND_TYPE.MONTH.name();
		}else{
			sNew = COMMAND_TYPE.DAY.name();
		}
		
		return sNew;
	}

		
}

class Thingy{
	
	private String commandFirst;
	
	/* Assigned from an array of strings behind command word
	 * 
	 * Maximum number of arguments for any command is 4
	 */
	private String argument1;
	private String argument2;
	private String argument3;
	private String argument4;
	
	public Thingy(String[] input){
		this.commandFirst = input[0];
		this.argument1 = input[1];
		this.argument2 = input[2];
		this.argument3 = input[3];
		this.argument4 = input[4];
		
	}
	
	public String getcommand(){
		return commandFirst;
	}
	
	public String getArg1(){
		return this.argument1;
	}
	
	public String getArg2(){
		return this.argument2;
	}
	
	public String getArg3(){
	   return this.argument3;
	}
	
	public String getArg4(){
		return this.argument4;
	}
	

}
