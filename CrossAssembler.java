import analysers.FileReader;
import analysers.LexicalAnalyser;
import analysers.SyntaxAnalyser;
import components.IR;
import components.SymbolTable;
import generators.CodeGenerator;
import generators.Listing;

/**
 * main class for cross assembler
 */
public class CrossAssembler {

    public static void main(String[] args) {

        //opens input file
        String srcFile = "TestInherentMnemonics.asm";
        FileReader reader = new FileReader(srcFile);

        //creation of symbol table
        SymbolTable symbolTable = new SymbolTable();

        //creation of line components.IR and subsequent line statements
        SyntaxAnalyser parser = new SyntaxAnalyser(symbolTable);
        LexicalAnalyser lexer = new LexicalAnalyser(reader, parser);

        //creation of objects used to traverse components.IR
        IR intRep = parser.getIntRep();
        Listing list = new Listing(intRep);

        //generates .lst file
        CodeGenerator codeGenerator = new CodeGenerator(list);
        
    }

}
