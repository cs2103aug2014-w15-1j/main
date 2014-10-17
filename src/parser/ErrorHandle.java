package parser;

/**
 * class ErrorMSG: String type error messages
 * 
 * @author A0119493X
 *          Parser error messages of all scenarios.
 * */

class ErrorMSG {
    public static final String QUOTATION_UNCLOSE_ERR = "Error at Parser: Quotation mark unclosed or missing title/discription";
    public static final String UNEXPECTED_QUOTATION_ERR = "Error at Parser: Input task contain unexpected quotation mark";
    public static final String TASK_INFO_ERR = "Error at Parser: Invalid task information, check repeat time/start day/end day";
    public static final String INPUT_DATE_ERR = "Error at Parser: Invalid date input";
    public static final String UPDATE_INPUT_ERR = "Error at Parser: Invalid update input";
    public static final String UPDATE_FIELD_ERR = "Error at Parser: Invalid update field";
    public static final String VIEW_MODE_ERR = "Error at Parser: Invalid view mode input";
    public static final String INPUT_SYMBOL_ERR = "Error at Parser: Input contains invalid symbols, delete symbol such as \"=\"";
    public static final String REPEAT_ERR = "Error at Parser: invalide repeat date";
}

class ErrorGenerator {
    /**
     * Print out error
     * */
    public static void popError(String errorMSG) {
        System.err.println(errorMSG);
    }
}
