import jdk.nashorn.internal.parser.TokenType;

import java.util.ArrayList;

public class SyntaxAnalyser {
    /*
    the IR class creates the ArrayList
    private ArrayList<LineStatement> IR = new ArrayList<>();
     */

    private IR intRep;
    private SymbolTable symbolTable;
    private LineStatement lineStatement;

    private SyntaxAnalyser() {
        intRep = new IR();
    }

    //Used by the Lexical Analyser to send the token/identifier
    //tokenLine = [Label, Mnemonic or Directive, Comment]
    public void createLineStatement(Token[] tokenLine) {

        //if tokenLine[1] is an Instruction
        if (tokenLine[1].getType() == TokenType.Mnemonic) 
            lineStatement = new LineStatement(tokenLine[0].getName(), new Instruction(parseToken(tokenLine[1].getName()), ""), tokenLine[2].getName());
        //else tokenLine[1] is a Directive
        else
            lineStatement = new LineStatement(tokenLine[0].getName(), tokenLine[1].getName(), tokenLine[2].getName());
        updateIR();

    }

    public void updateIR() {
        intRep.addLineStatement(lineStatement);
    }

    public Mnemonic parseToken(String identifier) {
        return symbolTable.get(identifier); //hashtable or SymbolTable
    }

}