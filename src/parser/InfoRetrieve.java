package parser;

import java.util.ArrayList;

/**
 * class InfoRetrieve: Retrieve specific information from raw input string
 * 
 *@author A0119493X
 * 
 * */
public class InfoRetrieve {
	
	/**
     * Get task title, if title is empty, return an system EMPYT_TITLE
     * 
     * @param {String}
     * @param {ArrayList<String>}
     * 
     * @return {RawInfoPair}
     * */
    static RawInfoPair getTaskTitle(ArrayList<String> tokens) {
    	String title = "";
    	String front;
    	
        while(!tokens.isEmpty()) {
        	front = tokens.get(0);
        	
        	if(!ValidityChecker.isValidRP(front) && 
        	   !ValidityChecker.isValidDate(front)){
        		title += front + ParserKeys.SPACE;
        		tokens.remove(0);
        	} else {
        		break;
        	}
        }
        
        return new RawInfoPair(title, tokens);
    }
	
	/**
     * get repeated date for a task
     * if cannot find the return system default repeat
     * */
    static RawInfoPair getRepeatDate(ArrayList<String> tokens) {
    	String repeatDate = "";
    	String cur;
    	
        for (int i = 0; i < tokens.size(); i++) {
        	cur = tokens.get(i);
        	if (ValidityChecker.isValidRP(cur)) {
        		repeatDate = cur;
        		tokens.remove(i);
        		break;
        	}
        }
        
        if (repeatDate.isEmpty()) {
        	repeatDate = ParserKeys.RP_NON;
        }
        
        return new RawInfoPair(repeatDate, tokens);
    }
    
    /**
     * get start day for a task
     * if cannot find the return system default date
     * */
    static RawInfoPair getDate(ArrayList<String> tokens) {
    	String date = "";
    	String cur;
    	
        for (int i = 0; i < tokens.size(); i++) {
        	cur = tokens.get(i);
        	if (ValidityChecker.isValidDate(cur)) {
        		date = makeDay(cur);
        		tokens.remove(i);
        		break;
        	}
        }
        
        if (date.isEmpty()) {
        	date = ParserKeys.EMPTY_DATE;
        }
        
        return new RawInfoPair(date, tokens);
    }
    
    /**
     * Get the description at the end
     * 
     * @param {String}
     * @param {ArrayList<Integer>}
     * 
     * @return {String}
     * */
    static String getDescription(ArrayList<String> tokens) {
    	String description = "";
        for ( int i = 0; i < tokens.size(); i++) {
        	description += tokens.get(i) + ParserKeys.SPACE;
        }
        
        if (description.isEmpty()) {
        	description = ParserKeys.EMPTY_DIS;
        }
        
        return description;
    }
    
    /**
     * Split date information, standardize them to YYYYMMDD;
     * 
     * @param rawDay
     *          String of date
     * */
    static String makeDay(String rawDay) {
        String resultDay = ParserKeys.EMPTY_STR;
        String[] startDayArr = rawDay.split(ParserKeys.SPLIT_DATE);
        for (int i = 0; i < startDayArr.length; i++) {
            resultDay += startDayArr[i];
        }
        
        return resultDay;
    }
    
    /**
     * Clean the white space at the start of a string
     * */
    static String cleanFrontSpace(String rawString) {
        if(rawString.startsWith(ParserKeys.SPACE)) {
            return cleanFrontSpace(rawString.substring(1, rawString.length()));
        } else {
            return rawString;
        }
    }
    
    /**
     * Judge the validity of repeat date input
     * */
    static boolean isValidRP(String repDate) {
        boolean result = false;
        for (int i = 0; i < ParserKeys.REPEAT_KEYS.length; i++) {
            if (repDate.equalsIgnoreCase(ParserKeys.REPEAT_KEYS[i])){
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Return index of quotation mark in raw input String
     * */
    static ArrayList<Integer> getQuoteMark(String subInfoStr) {
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
    
    /**
     * Return index of quotation mark in raw input String
     * */
    static ArrayList<Integer> getDateMarker(String subInfoStr) {
        ArrayList<Integer> dateMarkIndex = new ArrayList<Integer>();
        String curStr;
        
        for(int i = 0; i < subInfoStr.length(); i ++) {
            curStr = subInfoStr.substring(i, i + 1);
            if (curStr.equals(ParserKeys.SPLIT_DATE)) {
                dateMarkIndex.add(i);
            }
        }
        return dateMarkIndex;
    }

	/**
	 * Get front command
	 * */
	public static String getCommand(String rawString) {
		
		String getCommand;
		int getCommandEnd = rawString.indexOf(ParserKeys.SPACE);
		
	    if (getCommandEnd == ParserKeys.INDEX_NOT_EXIST) {
	        getCommand = rawString;
	    } else {
	        getCommand = rawString.substring(0, getCommandEnd);
	    }
	    
	    return getCommand;
	}
}
