package parser;

import java.util.logging.Logger;

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

	private static Logger logger = Logger.getLogger("ParserProcessor");

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

		assert inputString == null : "input String passed to parser cannot be null";
		
		// Tokenize command and information, split sentence into words.
		RawInfoPair rawCmdPair = Tokenizer.splitRawInput(inputString);
		logger.info("Raw String splitted");
		
		// Identify command, form a pair of command and sub information.
		CMDInfoPair cmdPair = CMDInterpreter.makeCmdPair(rawCmdPair);
		logger.info("CMDInfoPair generated, current interpreted command: " + cmdPair.getCMD());
		
		// Call corresponding commands information retrieval.
		RawCommand interpretedCm = CMDCaller.transformCmd(cmdPair);
		logger.info("RawCommand generated, command passed to Logic: " + interpretedCm.getCommand());
		return interpretedCm;
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
}