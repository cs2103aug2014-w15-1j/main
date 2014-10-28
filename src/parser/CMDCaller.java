package parser;

import parser.CMDTypes.COMMAND_TYPE;

public class CMDCaller {

	/**
	 * Transform string command into corresponding CliToLog objects
	 * 
	 * @param infoPair -CmdInfoPair
	 * @return -RawCommand
	 * */
	static RawCommand transformCmd(CMDInfoPair infoPair){
	    RawCommand resultCMD;
	    CMDTypes.COMMAND_TYPE getCMD = infoPair.getCMD();
	    String subInfoStr = infoPair.getSubInfo();
	
	    switch(getCMD){
	    case ADD:
	        resultCMD = CMDMaker.add(subInfoStr);
	        break;
	    case UPDATE:
	        resultCMD = CMDMaker.update(subInfoStr);
	        break;
	    case RENAME:
	        resultCMD = CMDMaker.rename(subInfoStr);
	        break;
	    case RESCHEDULE:
	        resultCMD = CMDMaker.reschedule(subInfoStr);
	        break;
	    case DESCRIBE:
	        resultCMD = CMDMaker.describe(subInfoStr);
	        break;
	    case REPEAT:
	        resultCMD = CMDMaker.repeat(subInfoStr);
	        break;
	    case READ:
	        resultCMD = CMDMaker.read(subInfoStr);
	        break;
	    case DELETE:
	        resultCMD = CMDMaker.delete(subInfoStr);
	        break;
	    case VIEW:
	        resultCMD = CMDMaker.view(subInfoStr);
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
	    	resultCMD = CMDMaker.restore(subInfoStr);
	    	break;
	    case BACK:
	    	resultCMD = CMDMaker.back();
	    	break;
	    default:
	        resultCMD = CMDMaker.makeInvalid();
	        break;
	    }
	    return resultCMD;
	}

}
