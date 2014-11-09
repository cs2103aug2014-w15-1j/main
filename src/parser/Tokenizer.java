package parser;

import java.util.ArrayList;

import parser.TokenType.TOKEN_TYPE;

/**
 * class <Strong>Tokenizer</Strong>: 
 * 
 * <p>
 * This class is to split <code>String</code> inputs into tokens.
 * </p>
 * 
 * @author A0119493X
 * @see TokenType
 * */
class Tokenizer {
	
	/**
	 * Split raw input into a command and a list of sub info.
	 * 
	 * @param rawInputStr
	 * @return subInfo list is null if there is only a command exists;
	 * */
	public static RawInfoPair splitRawInput(String rawInputStr) {
		String command;
		ArrayList<TokenPair> subInfo;
		
		command = InfoRetrieve.getCommand(rawInputStr);
		
		String commandRemoved = StringCutter.rmAfterCommand(rawInputStr);
		
		if (commandRemoved == null || commandRemoved.isEmpty()) {
			subInfo = new ArrayList<TokenPair>();
		} else {
			String frontSPRemoved = StringCutter.cleanFrontSpace(commandRemoved);
			subInfo = splitInfoString(frontSPRemoved);
		}
		
		return new RawInfoPair(command, subInfo);
	}
	
	/**
	 * Split sub info string into pairs and attach corresponding tokens;
	 * 
	 * @param subInfoStr
	 * 			-String
	 * @return ArrayList<TokenPair>
	 * 			a list of TokenPairs
	 * */
	private static ArrayList<TokenPair> splitInfoString(String subInfoStr) {
		ArrayList<TokenPair> infoList = new ArrayList<TokenPair>();
		String frontBlock;
		TokenPair frontPair;
		while (!subInfoStr.isEmpty()) {
			frontBlock = StringCutter.getFrontBlock(subInfoStr);
			frontPair = judgeContent(frontBlock);
			infoList.add(frontPair);
			
			subInfoStr = StringCutter.rmFrontBlock(subInfoStr);
		}
		
		return infoList;
	}

	/**
	 * Judge contents and form corresponding token pair
	 * 
	 * @param frontBlock
	 * 			-String
	 * @return TokenPair
	 * 			a pair of content and corresponding tokens
	 * */
	private static TokenPair judgeContent(String frontBlock) {
		
		if (ValidityChecker.isValidDate(frontBlock)) {
			return new TokenPair(frontBlock, TOKEN_TYPE.DT);
		} else if (ValidityChecker.isValidRP(frontBlock)) {
			return new TokenPair(frontBlock, TOKEN_TYPE.RP);
		} else if (ValidityChecker.endsWithQuo(frontBlock)) {
			return new TokenPair(frontBlock.substring(0, frontBlock.length() - 1), 
								 TOKEN_TYPE.QT);
		} else if (ValidityChecker.isInteger(frontBlock)) {
			return new TokenPair(frontBlock, TOKEN_TYPE.NB);
		} else {
			return new TokenPair(frontBlock, TOKEN_TYPE.UN);
		}
	}
}
