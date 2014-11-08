package parser;

import java.util.ArrayList;

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

        if (ValidityChecker.isAdd(getCommand) &&
        	!ValidityChecker.isRead(getCommand) &&
        	!ValidityChecker.isUpdate(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.ADD, getSubInfo);
        } else if (ValidityChecker.isUpdate(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.UPDATE, getSubInfo);
        } else if (ValidityChecker.isRename(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RENAME, getSubInfo);
        } else if (ValidityChecker.isDescribe(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.DESCRIBE, getSubInfo);
        } else if (ValidityChecker.isReschedule(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RESCHEDULE, getSubInfo);
        } else if (ValidityChecker.isRepeat(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.REPEAT, getSubInfo);
        } else if (ValidityChecker.isDelete(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.DELETE, getSubInfo);
        } else if (ValidityChecker.isRead(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.READ, getSubInfo);
        } else if (ValidityChecker.isView(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.VIEW, getSubInfo);
        } else if (ValidityChecker.isNext(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.NEXT, getSubInfo);
        } else if (ValidityChecker.isPrevious(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.PREVIOUS, getSubInfo);
        } else if (ValidityChecker.isUndo(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.UNDO, getSubInfo);
        } else if (ValidityChecker.isSearch(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.SEARCH, getSubInfo);
        } else if (ValidityChecker.isBack(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.BACK, getSubInfo);
        } else if (ValidityChecker.isExit(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.EXIT, getSubInfo);
        } else if (ValidityChecker.isRestore(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.RESTORE, getSubInfo);
        } else if (ValidityChecker.isMark(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.MARK, getSubInfo);
        } else if (ValidityChecker.isHelp(getCommand)) {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.HELP, getSubInfo);
        } else {
            return new CMDInfoPair(CMDTypes.COMMAND_TYPE.INVALID, getSubInfo);
        }
    }
}
