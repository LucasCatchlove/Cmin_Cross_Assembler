import analysers.FileReader;
import analysers.ILexicalAnalyser;
import analysers.LexicalAnalyser;
import analysers.SyntaxAnalyser;
import errorReporters.*;
import components.SymbolTable;
import errorReporters.IErrorReporter;
import generators.CodeGenerator;
import generators.Listing;
import interfaces.*;

/**
 * main class for cross assembler
 */
public class CrossAssembler {

    public static void main(String[] args) {

        //opens input file
        String srcFile = "Sprint Listing/TestImmediate2.asm";
        IFileReader reader = new FileReader(srcFile);

        //Creation of Error Reporter
        IErrorReporter errRep = new ErrorReporter(srcFile);

        //creation of symbol table
        ISymbolTable symbolTable = new SymbolTable();

        //creation of line components.IR and subsequent line statements
        ILexicalAnalyser lexer = new LexicalAnalyser(reader, errRep);
        ISyntaxAnalyser parser = new SyntaxAnalyser(symbolTable, lexer, errRep);

        //creation of objects used to traverse components.IR
        IIR intRep = parser.parse();
        IListing list = new Listing(intRep);

        if (!errRep.isEmpty()) {
            errRep.reportErrors();
        }

        //generates .lst file
        CodeGenerator codeGenerator = new CodeGenerator(list);
        
    }

}
