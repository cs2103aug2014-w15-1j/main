package parser;

import java.util.ArrayList;

public class ValidityChecker {
	
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
	 * Check if input contains invalid symbols
	 * */
	static boolean noInvalidKeys(String inputString) {
	    return !inputString.contains(ParserKeys.INVALID_SYMBOL);
	}
}
