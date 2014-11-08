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
    protected COMMAND_TYPE cmd;
    protected ArrayList<TokenPair> subInfo;
    
    public CMDInfoPair(COMMAND_TYPE add, ArrayList<TokenPair> getSubInfo) {
        this.cmd = add;
        this.subInfo = getSubInfo;
    }
    
    public COMMAND_TYPE getCMD() {
        return this.cmd;
    }
    
    public ArrayList<TokenPair> getSubInfo() {
        return this.subInfo;
    }
}
