package parser;

import java.util.ArrayList;

import parser.TokenType.TOKEN_TYPE;

/**
 * class <Strong>ValidityChecker</Strong>: 
 * 
 * <p>
 * All the validity checking method in <code>Parser</code> is placed under this class.
 * </p>
 * 
 * @author A0119493X
 * */

class ValidityChecker {

	/**
	 * Check if token type is DT
	 * 
	 * @param tType
	 * @return boolean
	 * */
	static boolean isDT(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.DT;
	}

	/**
	 * Check if token is RP
	 * 
	 * @param tType
	 * @return boolean
	 * */
	static boolean isRP(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.RP;
	}

	/**
	 * Check if token type is UN
	 * 
	 * @param tType
	 * @return boolean
	 * */
	static boolean isUN(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.UN;
	}
	
	/**
	 * Check if token type is QT
	 * 
	 * @param tType
	 * @return boolean
	 * */
	static boolean isQT(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.QT;
	}
	
	/**
	 * Check if token type is NB
	 * 
	 * @param tType
	 * @return boolean
	 * */
	static boolean isNB(TOKEN_TYPE tType) {
		return tType == TOKEN_TYPE.NB;
	}

	/***
	 * Check if string is an Integer
	 * 
	 * @param inputStr
	 * @return boolean
	 * */
	static boolean isInteger(String inputStr) {
		try { 
			Integer.parseInt(inputStr); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * Check if input contains invalid keys or incomplete quotation marks
	 * 
	 * @param inputStr
	 * @return boolean
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
	 * 
	 * @param repDate
	 * @return boolean
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
	 * 
	 * @param rawStr
	 * @return boolean
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
	 * 
	 * @param viewDate
	 * @return boolean
	 * */
	static boolean isValidViewDate(String viewDate) {
		boolean result = false;
		for (int i = 0; i < ParserKeys.VIEW_DATE.length; i++) {
			if (viewDate.equalsIgnoreCase(ParserKeys.VIEW_DATE[i])){
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Check if ends with quotation mark;
	 * 
	 * @param rawStr
	 * @return boolean
	 * */
	static boolean endsWithQuo(String rawStr) {
		return rawStr.endsWith(ParserKeys.SPLITSYMBOL);
	}

	/**
	 * Check if input contains invalid symbols
	 * 
	 * @param inputString
	 * @return boolean
	 * */
	static boolean noInvalidKeys(String inputString) {
		return !inputString.contains(ParserKeys.INVALID_SYMBOL);
	}
	
	// Command Matcher
	static boolean isAdd(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_ADD);	
	}
	
	static boolean isUpdate(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_UPDATE);
	}
	
	static boolean isRename(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_RENAME);
	}
	
	static boolean isReschedule(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_RESCHEDULE);
	}
	
	static boolean isRepeat(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_REPEAT);
	}
	
	static boolean isDescribe(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_DESCRIBE);
	}
	
	static boolean isDelete(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_DELETE);
	}
	
	static boolean isView(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_VIEW);
	}
	
	static boolean isRead(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_READ);
	}
	
	static boolean isUndo(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_UNDO);
	}
	
	static boolean isRestore(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_RESTORE);
	}
	
	static boolean isExit(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_EXIT);
	}
	
	static boolean isNext(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_NEXT);
	}
	
	static boolean isPrevious(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_PREVIOUS);
	}
	
	static boolean isBack(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_BACK);
	}
	
	static boolean isSearch(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_SEARCH);
	}
	
	static boolean isViewDate(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_VIEWDATE);
	}
	
	static boolean isMark(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_MARK);
	}
	
	static boolean isHelp(String rawStr) {
		return matchCommand(rawStr, ParserKeys.KEYS_HELP);
	}
	
	/**
	 * Match the command with given matching symbols
	 * 
	 * @param rawStr
	 * @param matcher
	 * 			an list of matching symbols
	 * @return boolean
	 * */
	static boolean matchCommand(String rawStr, String[] matcher) {
		boolean result = true;
		for (int i = 0; i < matcher.length; i++) {
			if (!rawStr.contains(matcher[i])) {
				result = false;
				break;
			}
		}
		return result;
	}
}
