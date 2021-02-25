public class TestSyntaxAnalyser {

    public static void main(String[] args) {

        SymbolTable symbolTable = new SymbolTable();
        SyntaxAnalyser syntaxAnalyser = new SyntaxAnalyser(symbolTable);

        System.out.println("Test SyntaxAnalyser getIntRep");
        System.out.println("true");
        System.out.println(syntaxAnalyser.getIntRep().getLineStatement(0) == null);

        System.out.println("Test SyntaxAnalyser createLineStatement");
        System.out.println("[dec 12]");
        syntaxAnalyser.createLineStatement(new Token(new Position(7,2), "dec", TypeToken.Mnemonic));
        System.out.println(syntaxAnalyser.getIntRep().getLineStatement(0));

    }

}
