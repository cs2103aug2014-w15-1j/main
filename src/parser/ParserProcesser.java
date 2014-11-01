package parser;

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

		// Tokenize command and information, split sentence into words.
		RawInfoPair RawCmdPair = Tokenizer.SplitRawInput(inputString);
		
		// Identify command, form a pair of command and sub information.
		CMDInfoPair CmdPair = CMDInterpreter.makeCmdPair(RawCmdPair);
		
		// Call corresponding commands information retrieval.
		RawCommand interpretedCm = CMDCaller.transformCmd(CmdPair);
		return interpretedCm;
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
}