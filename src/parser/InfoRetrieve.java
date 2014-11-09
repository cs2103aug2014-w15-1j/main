package parser;

import java.util.ArrayList;
import java.util.Calendar;

import parser.TokenType.TOKEN_TYPE;

/**
 * class <Strong>InfoRetrieve</Strong>: 
 * 
 * <p>
 * This class retrieve specific information from raw input string.
 * All types of information including <code>tasktitle</code>, <code>description</code>,
 * <code>startDate</code>, <code>endDate</code>, <code>viewField</code> could be retrieved 
 * from input 
 * </p>
 * 
 * @author A0119493X
 * 
 * */
class InfoRetrieve {
	
	/**
     * Get task title, 
     * 
     * @param tokenPairs
     * 			a <code>ArrayList<code> of <code>TokenPair<code> object 
     * @return RawInfoPair
     * 			Return a RawInfoPair containing tasktitle and subInformation, 
     * 			Title would be EMPYT_TITLE if title is empty, 
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
    
        	return judgeUN(tokenPairs, frontToken);
    	} else {
    		// numOfQuotes == 2
    		return getOneQT(tokenPairs);
    	}
    }

    /**
     * Judge if the front element has the token UN
     * 
     * @param tokenPairs
     * 			an list of tokenPairs
     * @param frontToken
     * 			front element's token
     * */
	private static RawInfoPair judgeUN(ArrayList<TokenPair> tokenPairs,
									   TOKEN_TYPE frontToken) {
		if (ValidityChecker.isUN(frontToken)) {
			return getFrontUN(tokenPairs);
		} else {
			return getOneQT(tokenPairs);
		}
	}
    
    /**
     * Get the first contents that is Quoted
     * 
     * @param tokenPairs
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object
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
     * Get front contents that is unidentifiable
     * 
     * @param tokenPairs
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object
     * */
    static RawInfoPair getFrontUN(ArrayList<TokenPair> tokenPairs) {
    	String result = ParserKeys.EMPTY_STR;
    	TokenPair front;
    	TOKEN_TYPE frontToken;
    	
    	while(!tokenPairs.isEmpty()) {
			front = tokenPairs.get(0);
			frontToken = front.getToken();
			
			if (ValidityChecker.isUN(frontToken) || 
				ValidityChecker.isNB(frontToken)){
        		result += front.getCotent() + ParserKeys.SPACE;
        		tokenPairs.remove(0);
        	} else {
        		break;
        	}
        }
    	
    	return cleanEndSpace(tokenPairs, result);
    }

    
    /**
     * Fet all the sub contents in the token-pair list
     * 
     * @param tokenPairs
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object containing a subInformation
     * */
    static RawInfoPair getAllSubInfo(ArrayList<TokenPair> tokenPairs) {
    	String result = ParserKeys.EMPTY_STR;
    	TokenPair front;
    	
    	while(!tokenPairs.isEmpty()) {
			front = tokenPairs.get(0);
        	result += front.getCotent() + ParserKeys.SPACE;
        	tokenPairs.remove(0);
        }
    	
    	return cleanEndSpace(tokenPairs, result);
    }
    
    /**
     * Count number of quoted contents in the tokenPairs
     * 
     * @param tokens
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object
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
     * Get repeated date for a task
     * 
     * @param tokens
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object, return system default repeat if cannot find a valid repeat date
     * */
    static RawInfoPair getRepeatDate(ArrayList<TokenPair> tokens) {
    	String repeatDate = ParserKeys.EMPTY_STR;
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
     * Get tokenPairs with token NB
     * 
     * @param tokens
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object
     * */
    static RawInfoPair getNB(ArrayList<TokenPair> tokens) {
    	String number = ParserKeys.EMPTY_STR;
    	TokenPair curPair;
    	
        for (int i = 0; i < tokens.size(); i++) {
        	curPair = tokens.get(i);
        	if (ValidityChecker.isNB(curPair.getToken())) {
        		number = curPair.getCotent();
        		tokens.remove(i);
        		break;
        	}
        }
        
        return new RawInfoPair(number, tokens);
    }
    
    /**
     * Get identifiable dates
     * 
     * @param tokens
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object, return EMPTY_DATE if cannot find date
     * */
    static RawInfoPair getDate(ArrayList<TokenPair> tokens) {
    	String date = ParserKeys.EMPTY_STR;
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
     * @param tokenPairs 
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return String 
	 * 			a string containing descriptions
     * */
    static String getDescription(ArrayList<TokenPair> tokenPairs) {
    	String description = ParserKeys.EMPTY_STR;
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
     * @return String
     * 			a standardized date YYYYMMDD
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
     * Get the index of quotation mark in raw input String
     * 
     * @param subInfoStr
     * @return ArrayList<Integer>
     * 			an list containing index of date marker
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
     * Get the index of date mark in raw input String
     * 
     * @param subInfoStr
     * @return ArrayList<Integer>
     * 			an list containing index of date marker
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
	 * 
	 * @param rawString
	 * @return String
	 * */
    static String getCommand(String rawString) {
		
		String command = ParserKeys.EMPTY_STR;
		command = StringCutter.getFrontBlock(rawString);
		
	    return command;
	}

	/**
	 * Translate plain date to standardized date
	 * 
	 * @param getFields
	 * 			a string containing dates fields
	 * @return RawCommand
	 * */
	static RawCommand translateDate(String getFields) {
		Calendar cal = Calendar.getInstance();
		String date = ParserKeys.EMPTY_STR;
		
		if (getFields.equalsIgnoreCase(ParserKeys.FIELD_TODAY)) {
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit(cal.get(Calendar.DATE));
			return CMDMaker.viewDate(makeDay(date));
		} else if (getFields.equalsIgnoreCase(ParserKeys.FIELD_TMR)) {
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit((cal.get(Calendar.DATE) + 1));
			return CMDMaker.viewDate(makeDay(date));
		} else {
			// Yesterday
			date += cal.get(Calendar.YEAR) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit(cal.get(Calendar.MONTH) + 1) + ParserKeys.SPLIT_DATE;
			date += InfoRetrieve.toTwoDigit((cal.get(Calendar.DATE) - 1));
			return CMDMaker.viewDate(makeDay(date));
		} 
	}

	/**
     * Clean the white space at the start of a string
     * 
     * @param rawString
     * 			-String
     * @return String
     * */
    static String cleanFrontSpace(String rawString) {
        if(rawString.startsWith(ParserKeys.SPACE)) {
            return cleanFrontSpace(rawString.substring(1, rawString.length()));
        } else {
            return rawString;
        }
    }
    
	/**
     * Remove the end space of a string
     * 
     * @param tokenPairs
     * 			an ArrayList<TokenPair> that contains tokenized words
	 * @return RawInfoPair
	 * 			a RawInfoPair object		
     * */
	private static RawInfoPair cleanEndSpace(ArrayList<TokenPair> tokenPairs,
										  String result) {
		if (result.endsWith(ParserKeys.SPACE)) {
        	return new RawInfoPair(result.substring(0, result.length()-1), tokenPairs);
        } else {
        	return new RawInfoPair(result, tokenPairs);
        }
	}
	
	/**
	 * Make integer to two digit string
	 * */
	static String toTwoDigit(int rawNum) {
		if (rawNum < 10 && rawNum >0) {
			return ParserKeys.ZERO + rawNum;
		} else {
			return (rawNum + ParserKeys.EMPTY_STR);
		}
	}
}
