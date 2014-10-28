package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class CmdInfoPair: A pair of command type and command's sub-information
 * 
 * @author A0119493X
 *          
 * */

class CMDInfoPair {
    private COMMAND_TYPE cmd;
    private ArrayList<String> subInfo;
    
    public CMDInfoPair(COMMAND_TYPE add, ArrayList<String> getSubInfo) {
        this.cmd = add;
        this.subInfo = getSubInfo;
    }
    
    public COMMAND_TYPE getCMD() {
        return this.cmd;
    }
    
    public ArrayList<String> getSubInfo() {
        return this.subInfo;
    }
}
