package parser;

import parser.TokenType.TOKEN_TYPE;

/**
 * A pair of contents and it's type token.
 * 
 * @author A0119493X
 * */
class TokenPair {
	private String content;
	private TOKEN_TYPE token;
	
	public TokenPair(String content, TOKEN_TYPE token) {
		this.content = content;
		this.token = token;
	}
	
	public String getCotent() {
		return this.content;
	}
	
	public TOKEN_TYPE getToken() {
		return this.token;
	}
}
