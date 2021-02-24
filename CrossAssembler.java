public class CrossAssembler {
    public static void main(String[] args) {
        String srcFile = args[0];

        FileReader reader = new FileReader(srcFile);
        SymbolTable symbolTable = new SymbolTable();
        SyntaxAnalyser parser = new SyntaxAnalyser(symbolTable);
        LexicalAnalyser lexer = new LexicalAnalyser(reader, parser);






    }
}
