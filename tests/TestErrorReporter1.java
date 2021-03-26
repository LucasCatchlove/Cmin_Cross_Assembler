package tests;

import errorReporters.ErrorReporter;
import errorReporters.ErrorMsg;
import components.Position;

public class TestErrorReporter1 {

    public static void main(String[] args) {

        ErrorReporter errorReporter = new ErrorReporter("testsourcefile.txt");


        System.out.println("Test ErrorReporter isEmpty");

        System.out.println("ErrorReporter Is Empty = true");
        System.out.println("ErrorReporter Is Empty = " + errorReporter.isEmpty());

    }
}
