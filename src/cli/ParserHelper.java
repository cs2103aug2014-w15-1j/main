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
