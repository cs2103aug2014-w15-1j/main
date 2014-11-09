package parser;

/**
 * class <Strong>TokenType</Strong>: 
 * 
 * <p>
 * Types of token that is recognizable
 * </p>
 * @author A0119493X
 * */
class TokenType {
	enum TOKEN_TYPE {
		// QUOTED CONTENTS
	    QT,
	    
	    // UNIDENTIFIED CONTENTS
	    UN,
	    
	    // DATE CONTENTS
	    DT,
	    
	    // REPEATING TIME
	    RP,
	    
	    // NUMBER
	    NB
	}
}
