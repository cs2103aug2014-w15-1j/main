package parser;

import java.util.ArrayList;

/**
 * class ParserProcess: Retrieve valid information from raw input string
 * 
 * @author A0119493X
 *          Interpret raw input strings and wrap valid information into Command objects
 *          
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
     * @return CliToLog which contains corresponding information
     * */
    public static RawCommand interpretCommand(String inputString){
        if (InfoRetrieve.noInvalidKeys(inputString)) {
            CmdInfoPair getCmdPair = CmdInterpretor.makeCmdPair(inputString);
            RawCommand interpretedCm = CmdInterpretor.transformCmd(getCmdPair);
            return interpretedCm;

        } else {
            ErrorGenerator.popError(ErrorMSG.INPUT_SYMBOL_ERR);
            return CmdInterpretor.makeInvalid();
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
}