package analysers;

import components.IR;
import components.SymbolTable;
import errorReporters.ErrorReporter;
import errorReporters.IErrorReporter;

import java.io.File;
import java.io.FileOutputStream;

public class TestLexicalAnalyser2 {

    public static void main(String[] args) {

        String srcFile = "Sprint Listing/testScan.asm";
        FileReader r1 = new FileReader(srcFile);
        IErrorReporter er1 = new ErrorReporter(srcFile);
        LexicalAnalyser l1 = new LexicalAnalyser(r1, er1);



        System.out.println("Test Lexical Analyser scan and getNextFin (1)");
        System.out.println(".cstring ; directive");
        System.out.print(l1.scan().getName() + " ");
        System.out.println(l1.scan().getName());
        System.out.println("Test Lexical Analyser scan and getNextFin (2)");
        System.out.println("enter.u5 4 ; OK, number <u5> [0..31].");
        System.out.print(l1.scan().getName() + " ");
        System.out.print(l1.scan().getName() + " ");
        System.out.println(l1.scan().getName());



    }

}
