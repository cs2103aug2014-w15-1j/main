package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.HTMLEditorKit.Parser;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;

public class SentenceParser {
	public static void Parse() throws InvalidFormatException, IOException {
		// http://sourceforge.net/apps/mediawiki/opennlp/index.php?title=Parser#Training_Tool
		InputStream is = new FileInputStream("lib/en-parser-chunking.bin");
	 
		ParserModel model = new ParserModel(is);
	 
		opennlp.tools.parser.Parser parser = ParserFactory.create(model);
	 
		String sentence = "Add a new task for me, I have a birthday party tomorrow";
		opennlp.tools.parser.Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
	 
		for (opennlp.tools.parser.Parse p : topParses)
			p.show();
	 
		is.close();
	 
		/*
		 * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
		 * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
		 */
	}
	
	
}
