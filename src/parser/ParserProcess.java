package parser;

import java.util.ArrayList;

/**
 * class ParserProcess: Retrieve valid information from raw input string
 * 
 * @author A0119493X
 *          Interpret raw input strings and wrap valid information into Command objects
 *          
 * */


public class ParserProcess {

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
    
    /*
     * ====================================================================
     * ===================== START OF PUBLIC METHOD =======================
     * ====================================================================
     */
    
    /**
     * Interpret input string to an executable command
     * 
     * @return CliToLog which contains corresponding information
     * */
    public static RawCommand interpretCommand(String inputString){
        if (noInvalidKeys(inputString)) {
            CmdInfoPair getCmdPair = makeCmdPair(inputString);
            RawCommand interpretedCm = transformCmd(getCmdPair);
            return interpretedCm;

        } else {
            ErrorGenerator.popError(ErrorMSG.INPUT_SYMBOL_ERR);
            return makeInvalid();
        }
    }
    
    /*
     * ====================================================================
     * ===================== END OF PUBLIC METHOD =========================
     * ====================================================================
     */

    /**
     * Interpret strings by their own commands
     * 
     * @return a pair of Command and its sub-information
     * */
    private static CmdInfoPair makeCmdPair(String rawString){
        String getCommand;
        String getSubInfo;

        int getCommandEnd = rawString.indexOf(ParserKeys.SPACE);
        if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
            getCommand = rawString;
            getSubInfo = null;
        } else {
            getCommand = rawString.substring(0, getCommandEnd);
            getSubInfo = cleanFrontSpace(rawString.substring(getCommandEnd + 1, rawString.length()));
        }

        if (getCommand.equalsIgnoreCase(COMMAND_TYPE.ADD.name())) {
            return new CmdInfoPair(COMMAND_TYPE.ADD, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.UPDATE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.UPDATE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RENAME.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RENAME, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.DESCRIBE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.DESCRIBE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RESCHEDULE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RESCHEDULE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.REPEAT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.REPEAT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.DELETE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.DELETE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.READ.name())) {
            return new CmdInfoPair(COMMAND_TYPE.READ, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.VIEW.name())) {
            return new CmdInfoPair(COMMAND_TYPE.VIEW, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.NEXT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.NEXT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.PREVIOUS.name())) {
            return new CmdInfoPair(COMMAND_TYPE.PREVIOUS, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.UNDO.name())) {
            return new CmdInfoPair(COMMAND_TYPE.UNDO, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.SEARCH.name())) {
            return new CmdInfoPair(COMMAND_TYPE.SEARCH, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.BACK.name())) {
            return new CmdInfoPair(COMMAND_TYPE.BACK, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.EXIT.name())) {
            return new CmdInfoPair(COMMAND_TYPE.EXIT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(COMMAND_TYPE.RESTORE.name())) {
            return new CmdInfoPair(COMMAND_TYPE.RESTORE, getSubInfo);
        } else {
            return new CmdInfoPair(COMMAND_TYPE.INVALID, getSubInfo);
        }
    }
    
    /**
     * Transform string command into corresponding CliToLog objects
     * */
    private static RawCommand transformCmd(CmdInfoPair infoPair){
        RawCommand resultCMD;
        COMMAND_TYPE getCMD = infoPair.getCMD();
        String subInfoStr = infoPair.getSubInfo();

        switch(getCMD){
        case ADD:
            resultCMD = add(subInfoStr);
            break;
        case UPDATE:
            resultCMD = update(subInfoStr);
            break;
        case RENAME:
            resultCMD = rename(subInfoStr);
            break;
        case RESCHEDULE:
            resultCMD = reschedule(subInfoStr);
            break;
        case DESCRIBE:
            resultCMD = describe(subInfoStr);
            break;
        case REPEAT:
            resultCMD = repeat(subInfoStr);
            break;
        case READ:
            resultCMD = read(subInfoStr);
            break;
        case DELETE:
            resultCMD = delete(subInfoStr);
            break;
        case VIEW:
            resultCMD = view(subInfoStr);
            break;
        case UNDO:
            resultCMD = undo();
            break;
        case NEXT:
            resultCMD = next();
            break;
        case PREVIOUS:
            resultCMD = previous();
            break;
        case EXIT:
            resultCMD = exit();
            break;
        case RESTORE:
        	resultCMD = restore(subInfoStr);
        	break;
        case BACK:
        	resultCMD = back();
        	break;
        default:
            resultCMD = makeInvalid();
            break;
        }
        return resultCMD;
    }

    private static RawCommand rename(String subInfoStr) {
    	if(subInfoStr.isEmpty()){
    		return makeInvalid();
    	}
		return new RawCommand(COMMAND_TYPE.RENAME.name(), subInfoStr);
	}

	private static RawCommand describe(String subInfoStr) {
    	if(subInfoStr.isEmpty()){
    		return makeInvalid();
    	}
		return new RawCommand(COMMAND_TYPE.DESCRIBE.name(), subInfoStr);
	}

	private static RawCommand reschedule(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	private static RawCommand repeat(String subInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Interpret "add" command and get its sub-information
     * Split quotation mark contents with other contents
     * 
     * @return 
     *      return a CliToLog object.
     * */
	
    private static RawCommand add(String subInfoStr) {
        ArrayList<Integer> symbolIndex = getQuoteMark(subInfoStr);
        int markNumber = symbolIndex.size();
        
        if (markNumber == 0 || markNumber == 2 || markNumber == 4 ) {
            // No quotation marks
            String taskTitle = getTaskTitle(subInfoStr, symbolIndex);
            String repeatDate = getRepeatDate(subInfoStr, symbolIndex);
            String startDate = makeDay(getBasicInfoAT(2, subInfoStr, symbolIndex, ParserKeys.EMPTY_DATE));
            String endDate = makeDay(getBasicInfoAT(3, subInfoStr, symbolIndex, ParserKeys.EMPTY_DATE));
            String description = getDescription(subInfoStr, symbolIndex);
            System.out.println(repeatDate);
            return new RawCommand(COMMAND_TYPE.ADD.name(), taskTitle, 
                                repeatDate, startDate, 
                                endDate, description);
            
        } else if (markNumber == 1 || markNumber == 3) {
            ErrorGenerator.popError(ErrorMSG.QUOTATION_UNCLOSE_ERR);
            return makeInvalid();

        } else {
            ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
            return makeInvalid();
        }
    }
    
    /**
     * get repeated date for a task
     * if cannot find the return system default repeat
     * */
    private static String getRepeatDate(String subInfoStr, ArrayList<Integer> symbolIndex) {
        String repDate = getBasicInfoAT(1, subInfoStr, symbolIndex, ParserKeys.RP_NON);
        if (repDate.contains(ParserKeys.SPLIT_DATE)) {
            return ParserKeys.RP_NON;
        }else if (isValidRP(repDate)) {
            if (repDate.isEmpty()) {
                return ParserKeys.RP_NON;
            } else {
                return repDate;
            }
        } else {
            String getLatter = getBasicInfoAT(2, subInfoStr, symbolIndex, ParserKeys.EMPTY_STR);
            if (getLatter.equals(ParserKeys.EMPTY_STR)) {
                
                ErrorGenerator.popError(ErrorMSG.REPEAT_ERR);
                return ParserKeys.INVALID_INFO;
            } else {
                return ParserKeys.RP_NON;
            }
        }
    }
    
    /**
     * Get basic information at location 
     * (1) repeat date, (2) start date, (3) end date
     * */
    private static String getBasicInfoAT(int location, String subInfoStr, 
                                         ArrayList<Integer> symbolIndex, 
                                         String defaultStr) {
        if (symbolIndex.size() == 2 || symbolIndex.size() == 4) {
            // remove the front quotation
            if (subInfoStr.startsWith(ParserKeys.SPLITSYMBOL)) {
                String removedStr = subInfoStr.substring(symbolIndex.get(1) + 1, subInfoStr.length());
                
                if (location == 1){
                    return getFirstWord(removedStr, defaultStr);
                    
                } else if (location == 2) {
                    return getStartDate(removedStr, defaultStr);
                } else {
                    return getEndDate(removedStr, defaultStr);
                }
                
            } else {
                String frontRemoved = removeBlocks(subInfoStr, location);
                return getFirstWord(frontRemoved, defaultStr);
            }
        } else {
            // No symbols
            if (location == 1){
                String frontRemoved = removeBlocks(subInfoStr, 1);
                return getFirstWord(frontRemoved, defaultStr);
                
            } else if (location == 2) {
                String frontRemoved = removeBlocks(subInfoStr, 1);
                return getStartDate(frontRemoved, defaultStr);
            } else {
                String frontRemoved = removeBlocks(subInfoStr, 1);
                return getEndDate(frontRemoved, defaultStr);
            }
        }
    }
    
    /**
     * get start day for a task
     * if cannot find the return system default date
     * */
    private static String getStartDate(String subInfoStr, String defaultStr) {
        String frontWord = getFirstWord(subInfoStr, defaultStr);
        if (frontWord.contains(ParserKeys.SPLIT_DATE)) {
            return frontWord;
        } else {
            String frontRemoved = removeBlocks(subInfoStr, 1);
            return getFirstWord(frontRemoved, defaultStr);
        }
    }
    
    /**
     * get End date for a task
     * if cannot find the return system default end date
     * */
    private static String getEndDate(String subInfoStr, String defaultStr) {
        
        String frontWord1 = getFirstWord(subInfoStr, defaultStr);
        if (!frontWord1.contains(ParserKeys.SPLIT_DATE)) {
            // Has Repeat Date
            String front1Removed = removeBlocks(subInfoStr, 1);
            String frontWord2 = getFirstWord(front1Removed, defaultStr);
            
            if (frontWord2.contains(ParserKeys.SPLIT_DATE)) {
                // Has Start Date
                String front2Removed = removeBlocks(front1Removed, 1);
                String frontWord3 = getFirstWord(front2Removed, defaultStr);
                
                if (frontWord3.contains(ParserKeys.SPLIT_DATE)) {
                    // Has End Date
                    return getFirstWord(front2Removed, defaultStr);
                } else {
                    // Has no End Date
                    return defaultStr;
                }  
            } else {
                // Has no Start Date
                return defaultStr;
            }
        } else if (frontWord1.contains(ParserKeys.SPLIT_DATE)){
            // Has no Repeat Date, But has Start Day
            String front2Removed = removeBlocks(subInfoStr, 1);
            String frontWord3 = getFirstWord(front2Removed, defaultStr);
            
            if (frontWord3.contains(ParserKeys.SPLIT_DATE)) {
                // Has End Date
                return getFirstWord(front2Removed, defaultStr);
            } else {
                // Has no End Date
                return defaultStr;
            }  
        } else {
            // Has no Repeat Date nor Start Day
            return defaultStr;
        }
    }
    
    /**
     * Check if input contains invalid symbols
     * */
    private static boolean noInvalidKeys(String inputString) {
        return !inputString.contains(ParserKeys.INVALID_SYMBOL);
    }
    
    /**
     * Clean the white space at the start of a string
     * */
    private static String cleanFrontSpace(String rawString) {
        if(rawString.startsWith(ParserKeys.SPACE)) {
            return cleanFrontSpace(rawString.substring(1, rawString.length()));
        } else {
            return rawString;
        }
    }
    
    /**
     * Judge the validity of repeat date input
     * */
    private static boolean isValidRP(String repDate) {
        boolean result = false;
        for (int i = 0; i < ParserKeys.REPEAT_KEYS.length; i++) {
            if (repDate.equalsIgnoreCase(ParserKeys.REPEAT_KEYS[i])){
                result = true;
            }
        }
        return result;
    }

    /**
     * Remove front blocks by spaces
     * 
     * @param {String]
     * @param {Number}
     * */
    private static String removeBlocks(String originStr, int numToRemove) {
        originStr = cleanFrontSpace(originStr);
        if (numToRemove > 0) {
            int getSpace = originStr.indexOf(ParserKeys.SPACE);
            
            if (getSpace != -1) {
                String curRemove =  originStr.substring(getSpace + 1, originStr.length());
                return removeBlocks(curRemove, numToRemove - 1);
            } else {
                return originStr;
            }
        } else {
            return originStr;
        }
    }

    /**
     * Get the specific word section
     * */
    private static String getFirstWord(String removedStr, String defaultStr) {
        removedStr = cleanFrontSpace(removedStr);
        int firstSpace = removedStr.indexOf(ParserKeys.SPACE);
        if (firstSpace == -1 && removedStr.isEmpty()) {
            return defaultStr;
        } else if (firstSpace == -1 && !removedStr.isEmpty()){
            return removedStr;
        } else {
            return removedStr.substring(0, firstSpace);
        }
    }

    /**
     * Get the description at the end
     * 
     * @param {String}
     * @param {ArrayList<Integer>}
     * 
     * @return {String}
     * */
    private static String getDescription(String subInfoStr, ArrayList<Integer> symbolIndex) {
        if (symbolIndex.size() == 0) {
            String[] splitedString = subInfoStr.split(ParserKeys.SPACE);
            
            if (splitedString.length > 1) {
                String getEnd = splitedString[splitedString.length - 1];
                if (isValidRP(getEnd)) {
                    return ParserKeys.EMPTY_DIS; 
                } else if (isValidDate(getEnd)){
                    return ParserKeys.EMPTY_DIS;
                } else {
                    return getEnd;
                }
            } else {
                return ParserKeys.EMPTY_DIS;
            }
        }else if (symbolIndex.size() == 2) {
            if (subInfoStr.startsWith(ParserKeys.SPLITSYMBOL)) {
                return ParserKeys.EMPTY_DIS;
            } else {
                return retrieveQuotedStr(subInfoStr, 
                                         symbolIndex.get(0), 
                                         symbolIndex.get(1), 
                                         ParserKeys.EMPTY_DIS);
            }
        } else {
            // 4 Quotation Markers
            return retrieveQuotedStr(subInfoStr, 
                                     symbolIndex.get(2), 
                                     symbolIndex.get(3),
                                     ParserKeys.EMPTY_DIS);
        }
    }
    
    /**
     * Get task title, if title is empty, return an system EMPYT_TITLE
     * 
     * @param {String}
     * @param {ArrayList<Integer>}
     * 
     * @return {String}
     * */
    private static String getTaskTitle(String subInfoStr, ArrayList<Integer> symbolIndex) {
        if (symbolIndex.size() == 0) {
            return getFirstWord(subInfoStr, ParserKeys.EMPTY_TITLE);
        }
        if (symbolIndex.size() == 2) {
            if (subInfoStr.startsWith(ParserKeys.SPLITSYMBOL)) {
                return retrieveQuotedStr(subInfoStr, 
                                         symbolIndex.get(0), 
                                         symbolIndex.get(1), 
                                         ParserKeys.EMPTY_TITLE);
            } else {
                int firstSpace = subInfoStr.indexOf(ParserKeys.SPACE);
                return subInfoStr.substring(0, firstSpace);
            }
        } else {
            // 4 Quotation Markers
            return retrieveQuotedStr(subInfoStr, 
                                     symbolIndex.get(0), 
                                     symbolIndex.get(1), 
                                     ParserKeys.EMPTY_TITLE);
        }
    }
    
    /**
     * Retrieve a string by the start and end index of quotation marker
     * 
     * @param {string}
     * @param {number}
     * @param {number}
     * */
    private static String retrieveQuotedStr(String subInfoStr, int startIndex, int endIndex, String defaultStr) {
        String getStr = subInfoStr.substring(startIndex + 1, endIndex);
        if (getStr.isEmpty()) {
            return defaultStr;
        } else {
            return getStr;
        }
    }

    /**
     * Split date information, standardize them to YYYYMMDD;
     * 
     * @param rawDay
     *          String of date
     * */
    private static String makeDay(String rawDay) {
        String resultDay = ParserKeys.EMPTY_STR;
        String[] startDayArr = rawDay.split(ParserKeys.SPLIT_DATE);
        for (int i = 0; i < startDayArr.length; i++) {
            resultDay += startDayArr[i];
        }

        if (resultDay.length() != ParserKeys.DATE_LENGTH) {
            resultDay = ParserKeys.EMPTY_DATE;
        }
        return resultDay;
    }
    
    /**
     * Check if is valid date
     * */
    private static boolean isValidDate(String rawStr) {
        return !makeDay(rawStr).equals(ParserKeys.EMPTY_DATE);
    }

    /**
     * To change/update a field in an input
     * Change command field
     * 
     * @param subInfoStr
     *              String of sub-information following the update command
     */
    private static RawCommand update(String subInfoStr){
       String getUpdateItem;
       String getUpdateInfo;

        int getCommandEnd = subInfoStr.indexOf(ParserKeys.SPACE);
        if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
            ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
            return makeInvalid();
        } else {
            getUpdateItem = subInfoStr.substring(0, getCommandEnd).trim().toLowerCase();
            getUpdateInfo = subInfoStr.substring(getCommandEnd + 1, subInfoStr.length());
        }
        ArrayList<Integer> symbolIndex = getQuoteMark(getUpdateInfo);
        int symbolNum = symbolIndex.size();
        if (symbolNum == 2) {
            String taskDescription = getTaskTitle(cleanFrontSpace(getUpdateInfo), symbolIndex);
            
            if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.NAME.name())){
                return rename(taskDescription);
            } else if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.DESCRIPTION.name())){
                return describe(taskDescription);
            } else if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.RESCHEDULE.name())){
                return reschedule(taskDescription);
            } else if (getUpdateItem.equalsIgnoreCase(COMMAND_TYPE.REPEAT.name())){
                return repeat(taskDescription);
            } else {
                ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
                return makeInvalid();
            }
        } else {
            ErrorGenerator.popError(ErrorMSG.UNEXPECTED_QUOTATION_ERR);
            return makeInvalid();
        }
        

        
        /*

        String updateField;
        String newContent;
        String[] component = subInfoStr.split(ParserKeys.SPLITSYMBOL);

        // Check update input contents validity
        if (component.length != 2) {
            ErrorGenerator.popError(ErrorMSG.UPDATE_INPUT_ERR);
            return makeInvalid();
        } else {
            updateField = component[0];
            if (updateField.endsWith(ParserKeys.SPACE)) {
                updateField = updateField.substring(0, updateField.length()-1);
            }

            updateField = identifyField(updateField);
            newContent = component[1];
            return new CliToLog(updateField, newContent);
        }*/
    }


    /**
     * Function: Update
     * To identify the field to update
     * 
     * @param inputField
     *          Targeted update field of information on one specific task
     */
    /*   private static String identifyField(String inputField){
        String updateField = null;

        //Check field to be changed
        if (inputField.equalsIgnoreCase(COMMAND_TYPE.NAME.name())){
            updateField = COMMAND_TYPE.RENAME.name();
        } else if (inputField.equals(COMMAND_TYPE.DATE.name()) || 
                   inputField.equals(COMMAND_TYPE.DAY.name())){
            updateField = COMMAND_TYPE.RESCHEDULE.name();
        } else if (inputField.equals(COMMAND_TYPE.DISCRIPTION.name())){
            updateField = COMMAND_TYPE.DESCRIBE.name();
        } else {
            updateField = COMMAND_TYPE.INVALID.name();
            ErrorGenerator.popError(ErrorMSG.UPDATE_FIELD_ERR);
        }
        return updateField;
    }
     */

    /** 
     * Read details of a certain task
     * 
     * @param readTarget
     *          Target reading index
     */
    private static RawCommand read(String readTarget){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.READ.name(), readTarget);

        return commandPackage;
    }

    /**
     * Undo the previous action
     */
    private static RawCommand undo(){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.UNDO.name());

        return commandPackage;		
    }

    /** 
     * Delete a certain task
     * 
     * @param deletIndex
     *          Index to be deleted
     */
    private static RawCommand delete(String deleteIndex){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.DELETE.name(), deleteIndex);

        return commandPackage;
    }

    /**
     * Make a CliToLog with command = "invalid"
     * */
    private static RawCommand makeInvalid() {
        return new RawCommand(COMMAND_TYPE.INVALID.name());
    }

    /** 
     * Switch to different view model
     * 
     * @param viewTarget
     *          Targeted viewing model
     */
    private static RawCommand view(String viewTarget){
        if (viewTarget.equalsIgnoreCase(COMMAND_TYPE.TASKLIST.name()) ||
                viewTarget.equalsIgnoreCase(COMMAND_TYPE.BIN.name())) {

            RawCommand commandPackage = new RawCommand(COMMAND_TYPE.VIEW.name(), viewTarget);
            return commandPackage;
        } else {
            ErrorGenerator.popError(ErrorMSG.VIEW_MODE_ERR);
            return makeInvalid();
        }
    }

    /** 
     * Next page for current state of view
     */
    private static RawCommand next(){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.NEXT.name());

        return commandPackage;
    }

    /** 
     * Previous page for current state of view
     */
    private static RawCommand previous(){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.PREVIOUS.name());

        return commandPackage;
    }

    /**
     *  Restore item from bin
     *  
     *  @param restoreTarget
     *         Target restore index
     */
    private static RawCommand restore(String restoreTarget){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.RESTORE.name(), restoreTarget);

        return commandPackage;
    } 
    
    private static RawCommand back(){
    	RawCommand commandPackage = new RawCommand(COMMAND_TYPE.BACK.name());
    	
    	return commandPackage;
    }
    
    /** 
     * Exiting the program
     */
    private static RawCommand exit(){
        RawCommand commandPackage = new RawCommand(COMMAND_TYPE.EXIT.name());

        return commandPackage;		
    }
    
    public static void main(String args[]) {
        String subInfoStr = "add title"; 
        RawCommand result = interpretCommand(subInfoStr);
        //System.out.println(getBasicInfoAT(1, subInfoStr, symbolIndex, "DEFAULT"));
        System.out.println(result.getRPdate());
    }
    
    
    /**
     * Return index of quotation mark in raw input String
     * */
    private static ArrayList<Integer> getQuoteMark(String subInfoStr) {
        ArrayList<Integer> quotationIndex = new ArrayList<Integer>();
        String curStr;
        int countSymbol = 0;
        for(int i = 0; i < subInfoStr.length(); i ++) {
            curStr = subInfoStr.substring(i, i+1);
            if (countSymbol == 4) {
                break;
            } else if (curStr.equals(ParserKeys.SPLITSYMBOL)) {
                quotationIndex.add(i);
                countSymbol++;
            }
        }
        return quotationIndex;
    }
}