package parser;

/**
 * class <Strong>StringCutter</Strong>: 
 * 
 * <p>
 * This class modifying <code>String</code> inputs.
 * </p>
 * 
 * <p>
 * <Strong>Note</Strong>: Here, <code>Block</code> is defined to be either a word that is 
 * separated by a space from other contents or a quoted chunk of words/sentence.
 * </p>
 * 
 * @author A0119493X
 * */
public class StringCutter {
	
	/**
	 * Get front block separated by the space or quoted by quotation mark
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front block of content
	 * */
	static String getFrontBlock(String originStr) {
		String frontBlock;
		String frontSPclean = StringCutter.cleanFrontSpace(originStr);
		if (originStr != null && frontSPclean.startsWith(ParserKeys.SPLITSYMBOL)) {
			frontBlock = StringCutter.getFrontQuoted(frontSPclean);
		} else {
			frontBlock = StringCutter.getFrontUnquoted(frontSPclean);
		}
		
		return frontBlock;
	}
	
	/**
	 * Get front unquoted block separated by the space;
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front block of content
	 * */
	private static String getFrontUnquoted(String frontSPclean) {
		if (frontSPclean != null) {
			int getFirstSpace = frontSPclean.indexOf(ParserKeys.SPACE);
			
			if (getFirstSpace != ParserKeys.INDEX_NOT_EXIST) {
				return frontSPclean.substring(0, getFirstSpace);
			} else {
				return frontSPclean;
			}
		} else {
			return ParserKeys.EMPTY_STR;
		}
	}

	/**
	 * Get front quoted block
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front quoted block of content
	 * */
	private static String getFrontQuoted(String frontSPclean) {
		String frontQuotClean = frontSPclean.substring(1, frontSPclean.length());
		int quoteEndIndex = frontQuotClean.indexOf(ParserKeys.SPLITSYMBOL);
		
		return frontQuotClean.substring(0, quoteEndIndex) + ParserKeys.SPLITSYMBOL;
	}

	/**
	 * Remove one front block of word, separated by space or quoted by quotation marks
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front block of content
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
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			a string with the front quoted block removed
	 * */
	static String rmFrontQuoted(String orginStr) {
		String frontQuotClean = orginStr.substring(1, orginStr.length());
		int quoteEndIndex = frontQuotClean.indexOf(ParserKeys.SPLITSYMBOL);
		
		return frontQuotClean.substring(quoteEndIndex + 1, frontQuotClean.length());
	}

	/**
	 * Remove front unquoted block
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			a string with the front block, separated by space, being removed
	 * */
	static String rmFrontUnquoted(String orginStr) {
		if (orginStr != null) {
			int getFirstSpace = orginStr.indexOf(ParserKeys.SPACE);
			
			if (getFirstSpace != ParserKeys.INDEX_NOT_EXIST) {
				return orginStr.substring(getFirstSpace + 1, orginStr.length());
			} else {
				return ParserKeys.EMPTY_STR;
			}
		} else {
			return ParserKeys.EMPTY_STR;
		}
	}
	
	/**
	 * Remove front blocks until commands
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front block of content
	 * */
	static String rmAfterCommand(String originStr) {
		
		originStr = rmFrontBlock(originStr);
		
		return originStr;
	}

	/**
	 * Clean the white space at the start of a string
	 * 
	 * @param originStr
	 * 			-String
	 * @return String
	 * 			the front space being cleaned
	 * */
	static String cleanFrontSpace(String rawString) {
		if(rawString != null && rawString.startsWith(ParserKeys.SPACE)) {
	        return cleanFrontSpace(rawString.substring(1, rawString.length()));
	    } else {
	        return rawString;
	    }
	}
}
