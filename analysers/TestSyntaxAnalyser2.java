package analysers;
import components.IR;
import components.SymbolTable;
import errorReporters.ErrorReporter;
import errorReporters.IErrorReporter;
import interfaces.IIR;
import interfaces.ISymbolTable;

public class TestSyntaxAnalyser2 {
    public static void main(String[] args) {

        String srcFile = "Sprint Listing/testScan.asm";
        FileReader r1 = new FileReader(srcFile);
        ISymbolTable st1 = new SymbolTable();
        IErrorReporter er1 = new ErrorReporter(srcFile);
        LexicalAnalyser l1 = new LexicalAnalyser(r1, st1, er1);
        SyntaxAnalyser sa1 = new SyntaxAnalyser(st1,l1,er1);
        IIR ir1 = sa1.parse();


        System.out.println("Test parse (1)");
        System.out.println(".cstring ; directive");
        System.out.println(ir1.getLineStatement(0).toString());

        System.out.println("Test parse (2)");
        System.out.println("enter.u5 4 ; OK, number <u5> [0..31].");
        System.out.println(ir1.getLineStatement(1).toString());
        System.out.println("Test parse (3)");
        System.out.println("ldc.i3 3 ; OK, number <i3> [-4..3].");
        System.out.println(ir1.getLineStatement(2).toString());
        System.out.println("Test parse (4)");
        System.out.println("ldc.i3 -4 ; OK, number <i3> [-4..3].");
        System.out.println(ir1.getLineStatement(3).toString());




    }
}
