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
                this.createLineStatement(lexer.getToken());
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


    //tokenLine = [Label, components.Mnemonic or Directive, Comment] //For other sprints

    /**
     * Used by the Lexical Analyser to send the token/identifier
     * @param token
     */
    public void createLineStatement(Token token) {

        //if tokenLine[1] is an components.Instruction
        if (token.getType() == TypeToken.Mnemonic) {
            lineStatement = new LineStatement("", new Instruction(parseToken(token.getName()), ""), "");
        }
        //else tokenLine[1] is a Directive
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