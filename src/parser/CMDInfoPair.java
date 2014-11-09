package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;

/**
 * class <Strong>CmdInfoPair</Strong>: This class stores a pair of command and 
 * command's sub-information. All sub-information are stored in in <code>ArrayList<code> 
 * of <code>TokenPair</code>
 * 
 * @see TokenPair
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
