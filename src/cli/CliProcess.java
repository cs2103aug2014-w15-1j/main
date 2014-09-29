package cli;


import java.io.*;
import java.util.*;

public class CliProcess {

    private static final String NULLED = "null";
    private static Scanner sc = new Scanner(System.in);


    enum COMMAND_TYPE {
        ADD, DELETE, UPDATE, READ, VIEW, UNDO, INVALID, EXIT,
        //UPDATE
        RENAME, RESCHEDULE, DESCRIBE,
        //VIEW
        DAY, MONTH, BIN, NEXT;	
    }



    /* Command line will be processed and
     * a pack or arguments will be passed on
     */
    @SuppressWarnings("unused")
    private void takeInput(String s) { 
        String [] inputLine = separate(s);
        //Index of array 0,1,2,3,4 as argument	
        executeCommand(inputLine);		   
    }

    // Split into string into array of arguments 
    private String[] separate(String s) {
        //Split into array of index 0,1,2,3,4
        String[] inputSplit = sc.nextLine().split(" ", 5);
        for (String stringNode : inputSplit) {
            if (stringNode == null) 
                stringNode = NULLED;
        }
        return inputSplit;
    } 

    private COMMAND_TYPE determineCommandType(String commandTypeString) {
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

    private void executeCommand(String[] inputExecute) {
        COMMAND_TYPE userCommand;

        userCommand = determineCommandType(inputExecute[0]);

        switch (userCommand) {
        case ADD:
            add(inputExecute);
            break;

        case UPDATE:
            update(inputExecute);
            break;

        case READ:
            read(inputExecute);
            break;

        case DELETE:
            delete(inputExecute);
            break;

        case VIEW:
            view(inputExecute);
            break;

        case UNDO:
            undo(inputExecute);

        default:
            //throw an error if the command is not recognized
            throw new Error("no idea");
        }
    }

    /* Create a new entry
     * 
     */
    private CliToLog add(String[] strArr) {
        CliToLog commandPackage = new CliToLog(strArr);

        return commandPackage;
    }

    /* To change/update a field in an input
     * 
     * Change command field
     */
    private CliToLog update(String[] strArr) {
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
    private String identifyField(String s) {
        String sNew = null;

        //Check field to be changed
        if (s.equals("name")) {
            sNew = COMMAND_TYPE.RENAME.name();
        }else if (s.equals("date") || s.equals("day")) {
            sNew = COMMAND_TYPE.RESCHEDULE.name();
        }else if (s.equals("description")) {
            sNew = COMMAND_TYPE.DESCRIBE.name();
        }
        return sNew;
    }

    /* Change commands for Update and View
     * 
     * Also shift up the arguments after new command
     * @argument New command , array of strings to be changed
     */
    private String[] changeCommand(String s, String[] strArr) {
        strArr[0] = s;
        for (int i = 1; i< 4; i++) {
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
    private CliToLog read(String[] strArr) {
        CliToLog commandPackage = new CliToLog(strArr);

        return commandPackage;
    }

    private CliToLog undo(String[] strArr) {
        CliToLog commandPackage = new CliToLog(strArr);

        return commandPackage;		
    }

    /* Delete a certain task
     * 
     *  @argument strArr[1] will be an index, need to be converted to integer
     */
    private CliToLog delete(String[] strArr) {
        CliToLog commandPackage = new CliToLog(strArr);

        return commandPackage;
    }

    /* View a page of items
     * 
     * Different modes available
     * Change command field
     */
    private CliToLog view(String[] strArr) {
        String[] newS;
        String s;

        s = identifyMode(strArr[1]);
        newS = changeCommand(s, strArr);

        CliToLog commandPackage = new CliToLog(newS);

        return commandPackage;
    }

    /* Function: View
     * To identify different modes for View 
     * 
     */
    private String identifyMode(String s) {
        String sNew;

        if (s.equals("nextpage")) {
            sNew = COMMAND_TYPE.NEXT.name();
        } else if (s.equals("bin")) {
            sNew = COMMAND_TYPE.BIN.name();
        } else if (s.equalsIgnoreCase("jan") || s.equalsIgnoreCase("feb") || s.equalsIgnoreCase("mar") ||
                s.equalsIgnoreCase("apr") || s.equalsIgnoreCase("may") || s.equalsIgnoreCase("june")||
                s.equalsIgnoreCase("jul") || s.equalsIgnoreCase("aug") || s.equalsIgnoreCase("sep") || 
                s.equalsIgnoreCase("oct") || s.equalsIgnoreCase("nov") || s.equalsIgnoreCase("dec")) {
            sNew = COMMAND_TYPE.MONTH.name();
        } else {
            sNew = COMMAND_TYPE.DAY.name();
        }
        return sNew;
    }
}

