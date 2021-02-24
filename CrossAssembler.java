import com.sun.tools.javac.jvm.Code;

public class CrossAssembler {
    public static void main(String[] args) {
        String srcFile = "TestInherentMnemonics.asm";

        FileReader reader = new FileReader(srcFile);
        SymbolTable symbolTable = new SymbolTable();
        SyntaxAnalyser parser = new SyntaxAnalyser(symbolTable);
        LexicalAnalyser lexer = new LexicalAnalyser(reader, parser);

        IR intRep = parser.getIntRep();
        Listing list = new Listing(intRep);

        CodeGenerator codeGenerator = new CodeGenerator(list);








    }
}
