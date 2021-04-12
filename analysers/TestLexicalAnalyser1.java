package analysers;


import components.SymbolTable;
import errorReporters.ErrorReporter;
import errorReporters.IErrorReporter;
import interfaces.ISymbolTable;


public class TestLexicalAnalyser1 {
    public static void main(String[] args) {

        String srcFile = "Sprint Listing/TestImmediate.asm";
        FileReader r1 = new FileReader(srcFile);
        ISymbolTable st = new SymbolTable();
        IErrorReporter er1 = new ErrorReporter(srcFile);
        LexicalAnalyser l1 = new LexicalAnalyser(r1, st, er1);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
         StringBuilder sb5 = new StringBuilder();

        sb1.append("enter.u5");
        sb2.append("pop");
        sb3.append("\".cstring\"");
        sb4.append("1");
        sb5.append("; hello");


        System.out.println("Test generateToken");
        System.out.println("enter.u5 pop \".cstring\" 1 ; hello");
        l1.setTokenColumn(3); //tokenColumn = 0 would imply that sbToken was a label
        System.out.print(l1.generateToken(sb1, false).getName()+ " ");
        System.out.print(l1.generateToken(sb2, false).getName()+ " ");
        System.out.print(l1.generateToken(sb3, false).getName() + " ");
        System.out.print(l1.generateToken(sb4, false).getName() + " ");
        System.out.println(l1.generateToken(sb5, false).getName());




    }
}
