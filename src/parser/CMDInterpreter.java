package parser;

import java.util.ArrayList;

import parser.CMDTypes.COMMAND_TYPE;


/**
 * class CmdInterpretor: Identify commands in input string
 * 
 * @author A0119493X
 * 			CmdInterpretor takes in a raw string input, identifies the command
 * 			in the raw input and return a RawCommand object that contains basic
 * 			information 
 * */
public class CMDInterpreter {
	
	/**
     * Interpret strings by their own commands
     * 
     * @param rawString -String
     * @return a CmdInfoPair object that contains a pair of command and sub-information string 
     * 		   of an raw input 
     * */
    static CMDInfoPair makeCmdPair(RawInfoPair rawCMDPair){
        String getCommand = rawCMDPair.getFront();
        ArrayList<TokenPair> getSubInfo = rawCMDPair.getSubInfo();

        if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.ADD.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.ADD, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.UPDATE.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.UPDATE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RENAME.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RENAME, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DESCRIBE.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.DESCRIBE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESCHEDULE.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RESCHEDULE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.REPEAT.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.REPEAT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.DELETE.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.DELETE, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.READ.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.READ, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.VIEW.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.VIEW, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.NEXT.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.NEXT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.PREVIOUS.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.PREVIOUS, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.UNDO.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.UNDO, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.SEARCH.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.SEARCH, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.BACK.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.BACK, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.EXIT.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.EXIT, getSubInfo);
        } else if (getCommand.equalsIgnoreCase(CMDTypes.COMMAND_TYPE.RESTORE.name())) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RESTORE, getSubInfo);
        } else {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.INVALID, getSubInfo);
        }
    }
}
