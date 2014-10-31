package parser;

import java.util.ArrayList;

import parser.TokenType.TOKEN_TYPE;

/**
 * class ValidityChecker: check if information is valid or not
 * 
 * @author A0119493X
 * */

public class ValidityChecker {
	
	/**
	 * Check if token type is UN
	 * */
	static boolean isDT(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.DT;
	}
	
	/**
	 * Check if token is RP
	 * */
	static boolean isRP(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.RP;
	}
	
	/**
	 * Check if token type is DT
	 * */
	static boolean isUN(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.UN;
	}
	
	/**
	 * Check if input contains invalid keys or incomplete quotation marks
	 * */
	static boolean isValidInput(String inputStr) {
		ArrayList<Integer> symbolIndex = InfoRetrieve.getQuoteMark(inputStr);
	    int markNumber = symbolIndex.size();
	    
	    if (markNumber % 2 != 0) {
	    	return false;
	    } else if (ValidityChecker.noInvalidKeys(inputStr)) {
	    	return false;
	    } else {
	    	return true;
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
	 * Check if is valid date
	 * */
	static boolean isValidDate(String rawStr) {
		ArrayList<Integer> dateMarker = InfoRetrieve.getDateMarker(rawStr);
		if (dateMarker.size() == 2 && InfoRetrieve.makeDay(rawStr).length() == 8) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * Judge the validity of repeat date input
     * */
    static boolean isValidViewDate(String viewDate) {
        boolean result = false;
        for (int i = 0; i < ParserKeys.VIEW_DATE.length; i++) {
            if (viewDate.equalsIgnoreCase(ParserKeys.VIEW_DATE[i])){
                result = true;
            }
        }
        return result;
    }
	
	/**
	 * Check if ends with quotation mark;
	 * */
	static boolean endsWithQuo(String rawStr) {
		return rawStr.endsWith(ParserKeys.SPLITSYMBOL);
	}

	/**
	 * Check if input contains invalid symbols
	 * */
	static boolean noInvalidKeys(String inputString) {
	    return !inputString.contains(ParserKeys.INVALID_SYMBOL);
	}
}
