package parser;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class CmdInfoPair: A pair of command type and command's sub-information
 * 
 * @author A0119493X
 *          
 * */

class CmdInfoPair {
    private CMDTypes.COMMAND_TYPE cmd;
    private String subInfo;
    
    public CmdInfoPair(CMDTypes.COMMAND_TYPE cmd, String subInfo) {
        this.cmd = cmd;
        this.subInfo = subInfo;
    }
    
    public CMDTypes.COMMAND_TYPE getCMD() {
        return this.cmd;
    }
    
    public String getSubInfo() {
        return this.subInfo;
    }
}

/**
 * class ParserKeys: Special characters reserved for Parser
 * 
 * @author A0119493X
 *          Containing special keys to help interpreting input strings, including separating
 *          symbols, special field names, and invalid symbols
 *          
 * */

class ParserKeys {
    public static final String SPLITSYMBOL = "\"";
    public static final String SPLIT_DATE = "-";
    public static final String SPACE = " ";
    public static final String INVALID_SYMBOL = "=";
    public static final String EMPTY_STR = "";
    public static final String EMPTY_TITLE = "EMPTY TITLE";
    public static final String EMPTY_DIS = "EMPTY DESCRIPTION";
    public static final String EMPTY_DATE = "20000101";
    
    public static final String RP_EVREYDAY = "everyday";
    public static final String RP_MON = "monday";
    public static final String RP_TUES = "tuesday";
    public static final String RP_WED = "wednesday"; 
    public static final String RP_THUR = "thursday";
    public static final String RP_FRI = "friday";
    public static final String RP_SAT = "saturday";
    public static final String RP_SUN = "sunday";
    public static final String RP_NON = "no_repeat";
    public static final String[] REPEAT_KEYS = {"everyday", "monday", "tuesday", 
                                                "wednesday", "thursday", "friday", 
                                                "saturday", "sunday", "no_repeat", ""};
    public static final String INVALID_INFO = "invalid info";
    
    public static final int DATE_LENGTH = 8;
    public static final int INDEX_NOT_EXIST = -1;
}