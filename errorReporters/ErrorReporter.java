package errorReporters;

import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter {

    private String srcFile;
    private ArrayList<ErrorMsg> errorLog = new ArrayList<>();

    public ErrorReporter(String srcFile) {
        this.srcFile = srcFile;
    }

    public void recordError(ErrorMsg errorMsg) {
        errorLog.add(errorMsg);
    }

    public boolean isEmpty() {
        return errorLog.isEmpty();
    }

    public void reportErrors() {

        if (errorLog.size() == 1)
            System.out.println("\n 1 error");
        else
            System.out.println("\n" + errorLog.size() + " errors");

        for (ErrorMsg errorMsg : errorLog) {
           System.err.println(srcFile + ":Error:line " + errorMsg.getPosition().getLineCounter() + ": " + errorMsg.getMsg());
        }
    }


}