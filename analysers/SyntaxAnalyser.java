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

    /**
     * parametrized constructor
     * @param symbolTable
     */
    public SyntaxAnalyser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        intRep = new IR();
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

}