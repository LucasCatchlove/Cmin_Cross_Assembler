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
import components.Options;

/**
 * main class for cross assembler
 */
public class CrossAssembler {

    public static void main(String[] args) {

        System.out.println("\nFor more information about the cross-assemblers optional features, please include the " +
                "\"-h\" flag following the file name");

        Options options = new Options(args);

        String srcFile = "";
                //"Sprint Listing/rela02.asm";

        //for command line file name specification
       if(args.length > 0) {
            if (args.length != 1 && !options.helpEnabled())
                srcFile = args[args.length - 1] + ".asm";
            else
            {
                System.out.println("File not specified");
                System.exit(404);
            }
        }else
        {
            System.out.println("File not specified");
            System.exit(404);
        }

        IFileReader reader = new FileReader(srcFile);

        //Creation of Error Reporter
        IErrorReporter errRep = new ErrorReporter(srcFile);

        //creation of symbol table
        ISymbolTable symbolTable = new SymbolTable();

        //creation of line components.IR and subsequent line statements
        ILexicalAnalyser lexer = new LexicalAnalyser(reader, symbolTable, errRep);
        ISyntaxAnalyser parser = new SyntaxAnalyser(symbolTable, lexer, errRep);


        //creation of objects used to traverse components.IR
        IIR intRep = parser.parse();
        //IListing list = new Listing(intRep);

        if (!errRep.isEmpty()) {
            errRep.reportErrors();
        }
        //generates .lst file
        CodeGenerator codeGenerator = new CodeGenerator(intRep, symbolTable, options, errRep, srcFile);

    }

}
