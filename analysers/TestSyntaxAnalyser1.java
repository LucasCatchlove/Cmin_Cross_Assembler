package analysers;

import components.Position;
import components.Token;
import components.TypeToken;
import components.SymbolTable;

public class TestSyntaxAnalyser1 {
    public static void main(String[] args) {
        SymbolTable st1 = new SymbolTable();
        SyntaxAnalyser sa1 = new SyntaxAnalyser(st1);
        Token t1 = new Token(new Position(5,2), "div", TypeToken.Mnemonic);
        Token t2 = new Token(new Position(3,2), "halt", TypeToken.Mnemonic);
        Token t3 = new Token(new Position(9,5), "pop", TypeToken.Mnemonic);

        System.out.println("Test parseToken");
        System.out.println("div halt pop");
        System.out.print(sa1.parseToken("div").getMnemonicName()+" ");
        System.out.print(sa1.parseToken("halt").getMnemonicName()+" ");
        System.out.println(sa1.parseToken("pop").getMnemonicName());

        System.out.println("Test createLineStatement & createIR (method call within createLineStatement)");
        System.out.println("div halt pop");
        sa1.createLineStatement(t1);
        System.out.print(sa1.getLineStatement().getInstruction().getMnemonic().getMnemonicName()+" ");
        sa1.createLineStatement(t2);
        System.out.print(sa1.getLineStatement().getInstruction().getMnemonic().getMnemonicName()+" ");
        sa1.createLineStatement(t3);
        System.out.println(sa1.getLineStatement().getInstruction().getMnemonic().getMnemonicName());



    }
}
