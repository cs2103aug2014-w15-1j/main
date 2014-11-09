package read_file;

class ErrorMSG {
    public static final String READ_TASKERROR   = "Error while reading task file line by line:";
    public static final String READ_TRASHERROR  = "Error while reading trash file line by line:";
    
    public static final String DATE_MAKE_ERROR  = "Error: Date string not complete, cannot make Date object";
    public static final String TASK_FORMAT_ERR = "Error: Local task file format error, try modify the file or delete existing file";
    public static final String TRASH_FORMAT_ERR = "Error: Local trash file format error, try modify the file or delete existing file";
}

class ErrorGenerator {
    public static void popError(String msg) {
        System.err.println(msg);
    }
}
