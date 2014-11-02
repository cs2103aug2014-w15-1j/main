package parser;

import java.util.ArrayList;

import parser.TokenType.TOKEN_TYPE;

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
     * @param {ArrayList<TokenPair>}
     * 
     * @return {RawInfoPair}
     * */
    static RawInfoPair getTaskTitle(ArrayList<TokenPair> tokenPairs) {
    	
    	TokenPair front;
    	TOKEN_TYPE frontToken;
    	int numOfQuotes;
    	
    	numOfQuotes = countQuoted(tokenPairs);
    	
    	if (numOfQuotes == 0) {
    		return getFrontUN(tokenPairs);
    	} else if (numOfQuotes == 1) {
    		front = tokenPairs.get(0);
        	frontToken = front.getToken();
    
        	if (ValidityChecker.isUN(frontToken)) {
        		return getFrontUN(tokenPairs);
        	} else {
        		return getOneQT(tokenPairs);
        	}
    	} else {
    		// numOfQuotes == 2
    		return getOneQT(tokenPairs);
    	}
    }
    
    /**
     * get the first contents that is Quoted
     * */
    static RawInfoPair getOneQT(ArrayList<TokenPair> tokenPairs) {
    	String result = ParserKeys.EMPTY_STR;
    	TokenPair curPair;
    	for (int i = 0; i < tokenPairs.size(); i++) {
    		curPair = tokenPairs.get(i);
    		if (curPair.getToken() == TOKEN_TYPE.QT) {
    			result = curPair.getCotent();
    			tokenPairs.remove(i);
    		}
    	}
    	return new RawInfoPair(result, tokenPairs);
    }
    
    /**
     * get front contents that is unidentifiable
     * */
    static RawInfoPair getFrontUN(ArrayList<TokenPair> tokenPair) {
    	String result = ParserKeys.EMPTY_STR;
    	TokenPair front;
    	TOKEN_TYPE frontToken;
    	
    	while(!tokenPair.isEmpty()) {
			front = tokenPair.get(0);
			frontToken = front.getToken();
			
			if (ValidityChecker.isUN(frontToken) || 
				ValidityChecker.isNB(frontToken)){
        		result += front.getCotent() + ParserKeys.SPACE;
        		tokenPair.remove(0);
        	} else {
        		break;
        	}
        }
    	if (result.endsWith(ParserKeys.SPACE)) {
        	return new RawInfoPair(result.substring(0, result.length()-1), tokenPair);
        } else {
        	return new RawInfoPair(result, tokenPair);
        }
    }
    
    /**
     * get front contents that is unidentifiable
     * 
     * @return RawInfoPair
     * */
    static RawInfoPair getAllSubInfo(ArrayList<TokenPair> tokenPair) {
    	String result = ParserKeys.EMPTY_STR;
    	TokenPair front;
    	
    	while(!tokenPair.isEmpty()) {
			front = tokenPair.get(0);
        	result += front.getCotent() + ParserKeys.SPACE;
        	tokenPair.remove(0);
        }
    	
    	if (result.endsWith(ParserKeys.SPACE)) {
        	return new RawInfoPair(result.substring(0, result.length()-1), tokenPair);
        } else {
        	return new RawInfoPair(result, tokenPair);
        }
    }
    
    /**
     * Count number of quoted contents in the tokenPairs
     * 
     * @return total quotation count
     * */
    static int countQuoted(ArrayList<TokenPair> tokenPairs) {
    	int result = 0;
    	for (int i = 0; i < tokenPairs.size(); i++) {
    		if (tokenPairs.get(i).getToken() == TOKEN_TYPE.QT) {
    			result ++;
    		}
    	}
    	
    	return result;
    }
	
	/**
     * get repeated date for a task
     * if cannot find the return system default repeat
     * */
    static RawInfoPair getRepeatDate(ArrayList<TokenPair> tokens) {
    	String repeatDate = "";
    	TokenPair curPair;
    	
        for (int i = 0; i < tokens.size(); i++) {
        	curPair = tokens.get(i);
        	if (ValidityChecker.isRP(curPair.getToken())) {
        		repeatDate = curPair.getCotent();
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
    static RawInfoPair getDate(ArrayList<TokenPair> tokens) {
    	String date = "";
    	TokenPair curPair;
    	
        for (int i = 0; i < tokens.size(); i++) {
        	curPair = tokens.get(i);
        	if (ValidityChecker.isDT(curPair.getToken())) {
        		date = makeDay(curPair.getCotent());
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
    static String getDescription(ArrayList<TokenPair> tokenPairs) {
    	String description = "";
    	int numOfQuotes;
    	numOfQuotes = countQuoted(tokenPairs);
    	
    	if (numOfQuotes == 0) {
    		for ( int i = 0; i < tokenPairs.size(); i++) {
            	description += tokenPairs.get(i).getCotent() + ParserKeys.SPACE;
            }
    	} else {
    		return getOneQT(tokenPairs).getFront();
    	}
        
        if (description.isEmpty()) {
        	description = ParserKeys.EMPTY_DIS;
        }
        
        if (description.endsWith(ParserKeys.SPACE)) {
        	return description.substring(0, description.length()-1);
        } else {
        	return description;
        }
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
		
		String command = ParserKeys.EMPTY_STR;
		String curFront;
		
		curFront = StringCutter.getFrontBlock(rawString);
		if (ValidityChecker.isCommand(curFront)) {
			command = curFront;
		}
		rawString = StringCutter.rmFrontBlock(rawString);
		
	    return command;
	}
}
