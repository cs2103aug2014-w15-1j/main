package parser;

import java.util.ArrayList;

/**
 * class ParserProcess: Interpret raw input strings and wrap valid information into RawCommand objects
 * 
 * @author A0119493X        
 * */
public class ParserProcesser {
    
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
        if (InfoRetrieve.noInvalidKeys(inputString)) {
            CMDInfoPair getCmdPair = CMDSwitcher.makeCmdPair(inputString);
            RawCommand interpretedCm = CMDCaller.transformCmd(getCmdPair);
            return interpretedCm;
            
        } else {
            ErrorGenerator.popError(ErrorMSG.INPUT_SYMBOL_ERR);
            return CMDMaker.makeInvalid();
        }
    }
    
   /*
    
    public static void main(String args[]) {
        String subInfoStr = "add title"; 
        RawCommand result = interpretCommand(subInfoStr);
        //System.out.println(getBasicInfoAT(1, subInfoStr, symbolIndex, "DEFAULT"));
        System.out.println(result.getRPdate());
    }
    */
    
    /*
     * ====================================================================
     * ===================== END OF PUBLIC METHOD =========================
     * ====================================================================
     */
}