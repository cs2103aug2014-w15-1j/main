package parser;

/**
 * class CmdInfoPair: A pair of command type and command's sub-information
 * 
 * @author A0119493X
 *          
 * */

class CMDInfoPair {
    private CMDTypes.COMMAND_TYPE cmd;
    private String subInfo;
    
    public CMDInfoPair(CMDTypes.COMMAND_TYPE cmd, String subInfo) {
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