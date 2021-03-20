package analysers;

import components.*;
import interfaces.ISyntaxAnalyser;

/**
 * Creates line statements from the tokens passed on from the lexer, and pushes them
 * to the IR
 */
public class SyntaxAnalyser implements ISyntaxAnalyser {

    /*
    the components.IR class creates the ArrayList
    private ArrayList<components.LineStatement> components.IR = new ArrayList<>();
     */

    private IR intRep;
    private SymbolTable symbolTable;
    private LineStatement lineStatement;
    private LexicalAnalyser lexer;

    /**
     * parametrized constructor
     * @param symbolTable
     */
    public SyntaxAnalyser(SymbolTable symbolTable, LexicalAnalyser lexer) {
        this.symbolTable = symbolTable;
        this.lexer = lexer;
        intRep = new IR();
        this.receiveToken();
    }

    private void receiveToken() {
        try {
            int i;
            while(!lexer.isEOFReached()){
                this.createLineStatement(lexer.getTokenLine());
                lexer.traverseFile();
            }
        } catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }

    public IR getIntRep() {
        return intRep;
    }


    //tokenLine = [Label, Mnemonic, Operand, Comment]

    /**
     * Used by the Lexical Analyser to send the token/identifier TODO: CHANGE
     * @param tokenLine
     */
    public void createLineStatement(Token[] tokenLine) {

        //if there is no Mnemonic
        if (tokenLine[1] == null) {
            lineStatement = new LineStatement("", null, (tokenLine[3]==null ? "": tokenLine[3].getName()));
        }

        //if tokenLine[1] is an Instruction
        if (tokenLine[1] != null && tokenLine[1].getType() == TypeToken.Mnemonic) {

            //TODO: ERRORS
//            if (mnemonic should have operand, but tokenLine[3] == null) {
//                //error
//            }
//
//            if (tokenLine[2] != null) {
//                if (mnemonic operand is outOfBound) {
//                //error
//                }
//                if (mnemonic should not have operand) {
//                    //error
//                }
//            }

            //Creating the LineStatement
            lineStatement = new LineStatement((tokenLine[0]==null? "": tokenLine[0].getName()), new Instruction(parseToken(tokenLine[1].getName()), tokenLine[2]==null? "": tokenLine[2].getName()), (tokenLine[3]==null ? "": tokenLine[3].getName()));
        }

        //if tokenLine[1] is a Directive //For other sprints

        updateIR();

    }

    /**
     * adds line statement to components.IR
     */
    void updateIR() {
        intRep.addLineStatement(lineStatement);
    }

    /**
     * returns components.Mnemonic object from the symbol table
     * @param token
     * @return
     */
    Mnemonic parseToken(String token) {
        //TODO: Check if token should have an operand. To do so, Add true/false (or a range/null) to Mnemonic constructor, and change SymbolTable
       return symbolTable.get(token); //hashtable or components.SymbolTable
    }

    /**
     * getter for testing purposes
     * @return LineStatement object
     */
    LineStatement getLineStatement() {
        return lineStatement;
    }
}