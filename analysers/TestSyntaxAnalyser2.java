package analysers;
import components.IR;
import components.SymbolTable;

public class TestSyntaxAnalyser2 {
    public static void main(String[] args) {
        IR ir = new IR();
        SymbolTable st2 = new SymbolTable();
        SyntaxAnalyser sa2 = new SyntaxAnalyser(st2);
        SyntaxAnalyser sa3 = new SyntaxAnalyser(st2);

        System.out.println("Test getIntRep");
        System.out.println("true true");
        System.out.print(sa2.getIntRep() instanceof IR);
        System.out.print(" ");
        System.out.print(sa3.getIntRep() instanceof IR);

    }
}
