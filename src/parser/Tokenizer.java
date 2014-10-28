package parser;

import java.util.ArrayList;

public class Tokenizer {
	
	/**
	 * Split raw input into a command and a list of sub info.
	 * 
	 * @return subInfo list is null if there is only a command exists;
	 * */
	public static RawInfoPair SplitRawInput(String rawInputStr) {
		String command;
		ArrayList<String> subInfo;
		
		command = InfoRetrieve.getCommand(rawInputStr);
		
		String commandRemoved = StringCutter.rmFrontBlock(rawInputStr);
		
		if (commandRemoved == null || commandRemoved.isEmpty()) {
			subInfo = null;
		} else {
			String frontSPRemoved = StringCutter.cleanFrontSpace(commandRemoved);
			subInfo = splitInfoString(frontSPRemoved);
		}
		
		return new RawInfoPair(command, subInfo);
	}
	
	/**
	 * Split sub info string into blocks and store in a ArrayList<String>;
	 * */
	private static ArrayList<String> splitInfoString(String subInfoStr) {
		ArrayList<String> infoList = new ArrayList<String>();
		String frontBlock;
		while (subInfoStr != null) {
			frontBlock = StringCutter.getFrontBlock(subInfoStr);
			infoList.add(frontBlock);
			
			subInfoStr = StringCutter.rmFrontBlock(subInfoStr);
		}
		
		return infoList;
	}
}
