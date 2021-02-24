
public class CrossAssembler {

    public static void main(String[] args) {

        //opens input file
        String srcFile = "TestInherentMnemonics.asm";
        FileReader reader = new FileReader(srcFile);

        //creation of symbol table
        SymbolTable symbolTable = new SymbolTable();

        //creation of line IR and subsequent line statements
        SyntaxAnalyser parser = new SyntaxAnalyser(symbolTable);
        LexicalAnalyser lexer = new LexicalAnalyser(reader, parser);

        //creation of objects used to traverse IR
        IR intRep = parser.getIntRep();
        Listing list = new Listing(intRep);

        //generates .lst file
        CodeGenerator codeGenerator = new CodeGenerator(list);
        
    }

}
