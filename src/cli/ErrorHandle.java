package cli;

class ErrorMSG {
    public static final String QUOTATION_UNCLOSE_ERR = "Error at CLI: Quotation mark unclosed or missing title/discription";
    public static final String UNEXPECTED_QUOTATION_ERR = "Error at CLI: Input task contain unexpected quotation mark";
    public static final String TASK_INFO_ERR = "Error at CLI: Invalid task information, check repeat time/start day/end day";
    public static final String INPUT_DATE_ERR = "Error at CLI: Invalid date input";
    public static final String UPDATE_INPUT_ERR = "Error at CLI: Invalid update input";
    public static final String UPDATE_FIELD_ERR = "Error at CLI: Invalid update field";
    public static final String VIEW_MODE_ERR = "Error at CLI: Invalid view mode input";
    public static final String INPUT_SYMBOL_ERR = "Error at CLI: Input contains invalid symbols, delete symbol such as \"=\"";
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
