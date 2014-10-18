package parser;

import java.util.ArrayList;

/**
 * class InfoRetrieve: Retrieve specific information from raw input string
 * */
public class InfoRetrieve {
	/**
     * get repeated date for a task
     * if cannot find the return system default repeat
     * */
    static String getRepeatDate(String subInfoStr, ArrayList<Integer> symbolIndex) {
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
    static String getBasicInfoAT(int location, String subInfoStr, 
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
    static String getDescription(String subInfoStr, ArrayList<Integer> symbolIndex) {
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
    static String getTaskTitle(String subInfoStr, ArrayList<Integer> symbolIndex) {
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
    static String makeDay(String rawDay) {
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
     * Check if input contains invalid symbols
     * */
    static boolean noInvalidKeys(String inputString) {
        return !inputString.contains(ParserKeys.INVALID_SYMBOL);
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
}
