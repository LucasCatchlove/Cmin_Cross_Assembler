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
    }

    public IR parse() {

        IR intRep = new IR();

        Token token = lexer.scan();
        Token mnemonicToken = null;
        Token operandToken = null;
        LineStatement lineStatement = new LineStatement();

        while (token.getType() != TypeToken.EOF) {

            System.out.println(token.getName());
            switch (token.getType()) {

                case EOL:
                    //create line statement and send to IR
                    lineStatement.setInstruction(new Instruction(mnemonicToken != null? parseToken(mnemonicToken.getName()): null, operandToken != null? operandToken.getName(): null));
                    lineStatement.setComment(token.getName());
                    intRep.addLineStatement(lineStatement);
                    lineStatement = new LineStatement();
                    mnemonicToken = null;
                    operandToken = null;
                    break;
                case Label:
                    //Save label;
                    lineStatement.setLabel(token.getName());
                    break;
                case Mnemonic:
                    mnemonicToken = token;
                    break;
                case Operand:
                    //Test if operand is needed or not? or if outofbound
                    operandToken = token;
                    break;
                //case Invalid:

            }

            token = lexer.scan();

        }

        return intRep;

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