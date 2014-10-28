package parser;

import java.util.ArrayList;

/**
 * class RawInfoPair: A pair of input command and command's sub-information
 * 
 * @author A0119493X
 *          
 * */
public class RawInfoPair {
	private String front;
    private ArrayList<String> subInfo;
    
    public RawInfoPair(String front, ArrayList<String> subInfo) {
        this.front = front;
        this.subInfo = subInfo;
    }
    
    public String getFront() {
        return this.front;
    }
    
    public ArrayList<String> getSubInfo() {
        return this.subInfo;
    }
}
