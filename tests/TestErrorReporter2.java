package tests;

import errorReporters.ErrorReporter;
import errorReporters.ErrorMsg;
import components.Position;

public class TestErrorReporter2 {

    public static void main(String[] args) {

        ErrorReporter errorReporter = new ErrorReporter("testsourcefile.txt");

        System.out.println("Test ErrorReporter isEmpty() and recordError()");

        errorReporter.recordError(new ErrorMsg("error test 1", new Position(4,2)));

        System.out.println("[error test 1] [4,2]");
        System.out.println("["+errorReporter.getErrorMsg(0).getMsg()+"] ["+errorReporter.getErrorMsg(0).getPosition().getLineCounter()+","+errorReporter.getErrorMsg(0).getPosition().getColumnCounter()+"]");

    }
}

