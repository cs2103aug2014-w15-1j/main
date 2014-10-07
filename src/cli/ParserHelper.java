package cli;

import cli.CliProcess.COMMAND_TYPE;

class CmdInfoPair {
    private COMMAND_TYPE cmd;
    private String subInfo;
    
    public CmdInfoPair(COMMAND_TYPE cmd, String subInfo) {
        this.cmd = cmd;
        this.subInfo = subInfo;
    }
    
    public COMMAND_TYPE getCMD() {
        return this.cmd;
    }
    
    public String getSubInfo() {
        return this.subInfo;
    }
}

class ParserKeys {
    public static final String SPLITSYMBOL = "\"";
    public static final String SPLIT_DATE = "-";
    public static final String SPACE = " ";
    public static final String INVALID_SYMBOL = "=";
    public static final String EMPTY_STR = "";
    public static final String EMPTY_DIS = "EMPTY DISCRIPTION";
    public static final String EMPTY_DATE = "20000101";
    
    public static final int DATE_LENGTH = 8;
    public static final int INDEX_NOT_EXIST = -1;
}