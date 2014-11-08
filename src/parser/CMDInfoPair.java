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
    protected RawCommand retrievedInfo;
    
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
    
    public RawCommand getInformation() {
    	return this.retrievedInfo;
    }
}

class Add extends CMDInfoPair {

	public Add(COMMAND_TYPE add, ArrayList<TokenPair> getSubInfo) {
		super(add, getSubInfo);
		// TODO Auto-generated constructor stub
	}
	
}
