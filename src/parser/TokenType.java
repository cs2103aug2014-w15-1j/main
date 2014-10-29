package parser;

/**
 * Types of token that is recognizable
 * 
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
	    RP
	}
}
