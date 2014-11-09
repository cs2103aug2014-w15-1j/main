package parser;

/**
 * class <Strong>ParserProcess</Strong>: 
 * 
 * <p>
 * This class interpret raw <code>String</code> inputs, and wrap valid <code>String</code> information 
 * into <code>RawCommand</code> objects.
 * </p>
 * 
 * <p>
 * When taking in a input, it will cut input and tokenize each word with corresponding tokens.
 * Then it calls <code>CMDInterpreter</code> to identify commands and then calls <code>CMDMaker</code>
 * to generate a <code>RawCommand</code> that contains all the basic information.
 * </p>
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
		RawInfoPair rawCmdPair = Tokenizer.splitRawInput(inputString);
		
		// Identify command, form a pair of command and sub information.
		CMDInfoPair cmdPair = CMDInterpreter.makeCmdPair(rawCmdPair);
		
		// Call corresponding commands information retrieval.
		RawCommand interpretedCm = CMDCaller.transformCmd(cmdPair);
		return interpretedCm;
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
}