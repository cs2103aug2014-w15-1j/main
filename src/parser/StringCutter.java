package parser;

/**
 * class StringCutter: modifying strings.
 * 
 * @author A0119493X
 * */
public class StringCutter {
	
	/**
	 * Get front block
	 * */
	static String getFrontBlock(String originStr) {
		String frontBlock;
		String frontSPclean = StringCutter.cleanFrontSpace(originStr);
		if (frontSPclean.startsWith(ParserKeys.SPLITSYMBOL)) {
			frontBlock = StringCutter.getFrontQuoted(frontSPclean);
		} else {
			frontBlock = StringCutter.getFrontUnquoted(frontSPclean);
		}
		
		return frontBlock;
	}
	
	/**
	 * Get front unquoted block
	 * */
	private static String getFrontUnquoted(String frontSPclean) {
		int getFirstSpace = frontSPclean.indexOf(ParserKeys.SPACE);
		
		if (getFirstSpace != ParserKeys.INDEX_NOT_EXIST) {
			return frontSPclean.substring(0, getFirstSpace);
		} else {
			return frontSPclean;
		}
	}

	/**
	 * Get front quoted block
	 * */
	private static String getFrontQuoted(String frontSPclean) {
		String frontQuotClean = frontSPclean.substring(1, frontSPclean.length());
		int quoteEndIndex = frontQuotClean.indexOf(ParserKeys.SPLITSYMBOL);
		
		return frontQuotClean.substring(0, quoteEndIndex) + ParserKeys.SPLITSYMBOL;
	}

	/**
	 * Remove one front block of word;
	 * */
	static String rmFrontBlock(String originStr) {
		String resultStr;
		String frontSPclean = StringCutter.cleanFrontSpace(originStr);
		if (frontSPclean.startsWith(ParserKeys.SPLITSYMBOL)) {
			resultStr = StringCutter.rmFrontQuoted(frontSPclean);
		} else {
			resultStr = StringCutter.rmFrontUnquoted(frontSPclean);
		}
		
		return resultStr;
	}

	/**
	 * Remove front quoted block
	 * */
	static String rmFrontQuoted(String orginStr) {
		String frontQuotClean = orginStr.substring(1, orginStr.length());
		int quoteEndIndex = frontQuotClean.indexOf(ParserKeys.SPLITSYMBOL);
		
		return frontQuotClean.substring(quoteEndIndex + 1, frontQuotClean.length());
	}

	/**
	 * Remove front unquoted block
	 * */
	static String rmFrontUnquoted(String orginStr) {
		int getFirstSpace = orginStr.indexOf(ParserKeys.SPACE);
		
		if (getFirstSpace != ParserKeys.INDEX_NOT_EXIST) {
			return orginStr.substring(getFirstSpace + 1, orginStr.length());
		} else {
			return null;
		}
	}
	
	/**
	 * Remove front blocks until commands
	 * */
	static String rmAfterCommand(String originStr) {
		String curFront = ParserKeys.EMPTY_STR;
		while (!ValidityChecker.isCommand(curFront)) {
			curFront = getFrontBlock(originStr);
			originStr = rmFrontBlock(originStr);
		}
		
		return originStr;
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
}
