package tests;

import errorReporters.ErrorReporter;
import errorReporters.ErrorMsg;
import components.Position;

public class TestErrorReporter {

    public static void main(String[] args) {

        ErrorReporter errorReporter = new ErrorReporter("testsourcefile.txt");

        System.out.println("Test ErrorReporter isEmpty()");

        System.out.println("ErrorReporter Is Empty = true");
        System.out.println("ErrorReporter Is Empty = " + errorReporter.isEmpty());


        System.out.println("Test ErrorReporter isEmpty() and recordError()");

        errorReporter.recordError(new ErrorMsg("error test 1", new Position(4,2)));

        System.out.println("ErrorReporter Is Empty = false");
        System.out.println("ErrorReporter Is Empty = " + errorReporter.isEmpty());

        System.out.println("Test ErrorReporter reportErrors()");

        errorReporter.recordError(new ErrorMsg("error test 2", new Position(6,3)));
        System.out.println();
    }
}
