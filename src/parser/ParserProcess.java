package parser;

import java.util.ArrayList;

/**
 * class ParserProcess: Interpret raw input strings and wrap valid information into RawCommand objects
 * 
 * @author A0119493X        
 * */
public class ParserProcess {
    
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
            CmdInfoPair getCmdPair = CMDInterpretor.makeCmdPair(inputString);
            RawCommand interpretedCm = CMDInterpretor.transformCmd(getCmdPair);
            return interpretedCm;

        } else {
            ErrorGenerator.popError(ErrorMSG.INPUT_SYMBOL_ERR);
            return CMDInterpretor.makeInvalid();
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