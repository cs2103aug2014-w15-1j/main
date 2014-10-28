package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class Tokenizer {
	
	public static void Tokenize() throws IOException {
		InputStream modelIn = new FileInputStream("lib/en-token.bin");

		try {
		  TokenizerModel model = new TokenizerModel(modelIn);
		  TokenizerME tokenizer = new TokenizerME(model);
		  String tokens[] = tokenizer.tokenize("Please Add a new task, tomorrow is my birthday!");
		  
		  for (String word: tokens) {
			  System.out.println(word);
		  }
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}
}
