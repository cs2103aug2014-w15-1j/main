package parser;

import java.util.ArrayList;

/**
 * class ParserProcess: Interpret raw input strings and wrap valid information into RawCommand objects
 * 
 * @author A0119493X        
 * */
public class ParserProcesser {

	/*
	 * ====================================================================
	 * ===================== START OF PUBLIC METHOD =======================
	 * ====================================================================
	 */

	/**
	 * Interpret input string to an executable command
	 * 
	 * @param inputString A string input by the user from the command box
	 * @return CliToLog which contains corresponding information
	 * */
	public static RawCommand interpretCommand(String inputString){

		RawInfoPair RawCmdPair = Tokenizer.SplitRawInput(inputString);
		CMDInfoPair CmdPair = CMDInterpreter.makeCmdPair(RawCmdPair);
		RawCommand interpretedCm = CMDCaller.transformCmd(CmdPair);
		return interpretedCm;
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
}