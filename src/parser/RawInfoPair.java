package parser;

import java.util.ArrayList;

/**
 * class <Strong>RawInfoPair</Strong>: 
 * 
 * <p>
 * A pair of input command and command's sub-information. It is used for the first interpretation
 * of raw <code>String</code> input. This class contains 2 attribute, one is the front(retrieved)
 * <code>String</code> information and the rest of the <code>TokenPair</code> list
 * </p>
 * 
 * @author A0119493X    
 * */
class RawInfoPair {
	private String front;
    private ArrayList<TokenPair> subInfo;
    
    public RawInfoPair(String front, ArrayList<TokenPair> subInfo) {
        this.front = front;
        this.subInfo = subInfo;
    }
    
    public String getFront() {
        return this.front;
    }
    
    public ArrayList<TokenPair> getSubInfo() {
        return this.subInfo;
    }
}
