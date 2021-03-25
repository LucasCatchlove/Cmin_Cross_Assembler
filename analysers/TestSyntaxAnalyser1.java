package analysers;

import components.Position;
import components.Token;
import components.TypeToken;
import components.SymbolTable;
import errorReporters.ErrorReporter;
import errorReporters.IErrorReporter;
import interfaces.ISymbolTable;

public class TestSyntaxAnalyser1 {
    public static void main(String[] args) {

        String srcFile = "Sprint Listing/TestImmediate.asm";
        FileReader r1 = new FileReader(srcFile);
        ISymbolTable st1 = new SymbolTable();
        IErrorReporter er1 = new ErrorReporter(srcFile);
        LexicalAnalyser l1 = new LexicalAnalyser(r1, er1);
        SyntaxAnalyser sa1 = new SyntaxAnalyser(st1,l1,er1);

        Token t1 = new Token(new Position(5,2), "enter.u5", TypeToken.Mnemonic);
        Token t2 = new Token(new Position(3,2), "pop", TypeToken.Mnemonic);
        Token t3 = new Token(new Position(9,5), "1", TypeToken.Operand);
        Token t4 = new Token(new Position(5,2), "ldc.i3", TypeToken.Mnemonic);
        Token t5 = new Token(new Position(3,2), "; comment", TypeToken.Comment);
        Token t6 = new Token(new Position(9,5), "\".cstring\"", TypeToken.Directive);

        System.out.println("Test parseToken (1)");
        System.out.println("enter.u5 pop true");
        System.out.print(sa1.parseToken(t1).getMnemonicName() + " ");
        System.out.print(sa1.parseToken(t2).getMnemonicName() + " ");
        System.out.println(sa1.parseToken(t3) == null);

        System.out.println("Test parseToken (2)");
        System.out.println("ldc.i3 true true");
        System.out.print(sa1.parseToken(t4).getMnemonicName() + " ");
        System.out.print((sa1.parseToken(t5) == null) + " ");
        System.out.println(sa1.parseToken(t6) == null);









    }
}
