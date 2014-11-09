package parser;

import java.util.ArrayList;

/**
 * class <Strong>CMDCaller</Strong>: Calling corresponding command operation regarding 
 * different command types. 
 * 
 * @see CMDTypes
 * @author A0119493X
 * */
class CMDCaller {

	/**
	 * Transform string command into corresponding CliToLog objects
	 * 
	 * @param infoPair -CmdInfoPair
	 * @return -RawCommand
	 * */
	static RawCommand transformCmd(CMDInfoPair infoPair){
	    RawCommand resultCMD;
	    CMDTypes.COMMAND_TYPE getCMD = infoPair.getCMD();
	    ArrayList<TokenPair> subInfoPairs = infoPair.getSubInfo();
	
	    switch(getCMD){
	    case ADD:
	        resultCMD = CMDMaker.add(subInfoPairs);
	        break;
	    case UPDATE:
	        resultCMD = CMDMaker.update(subInfoPairs);
	        break;
	    case RENAME:
	        resultCMD = CMDMaker.rename(subInfoPairs);
	        break;
	    case RESCHEDULE:
	        resultCMD = CMDMaker.reschedule(subInfoPairs);
	        break;
	    case DESCRIBE:
	        resultCMD = CMDMaker.describe(subInfoPairs);
	        break;
	    case REPEAT:
	        resultCMD = CMDMaker.repeat(subInfoPairs);
	        break;
	    case READ:
	        resultCMD = CMDMaker.read(subInfoPairs);
	        break;
	    case DELETE:
	        resultCMD = CMDMaker.delete(subInfoPairs);
	        break;
	    case VIEW:
	        resultCMD = CMDMaker.view(subInfoPairs);
	        break;
	    case UNDO:
	        resultCMD = CMDMaker.undo();
	        break;
	    case NEXT:
	        resultCMD = CMDMaker.next();
	        break;
	    case PREVIOUS:
	        resultCMD = CMDMaker.previous();
	        break;
	    case EXIT:
	        resultCMD = CMDMaker.exit();
	        break;
	    case RESTORE:
	    	resultCMD = CMDMaker.restore(subInfoPairs);
	    	break;
	    case BACK:
	    	resultCMD = CMDMaker.back();
	    	break;
	    case SEARCH:
	    	resultCMD = CMDMaker.search(subInfoPairs);
	    	break;
	    case MARK:
	    	resultCMD = CMDMaker.mark(subInfoPairs);
	    	break;
	    case HELP:
	    	resultCMD = CMDMaker.help();
	    	break;
	    default:
	        resultCMD = CMDMaker.makeInvalid();
	        break;
	    }
	    return resultCMD;
	}
}
