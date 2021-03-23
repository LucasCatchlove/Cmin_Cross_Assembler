package analysers;

import components.*;
import interfaces.*;
import errorReporters.*;

/**
 * Creates line statements from the tokens passed on from the lexer, and pushes them
 * to the IR
 */
public class SyntaxAnalyser implements ISyntaxAnalyser {

    /*
    the components.IR class creates the ArrayList
    private ArrayList<components.LineStatement> components.IR = new ArrayList<>();
     */

    private ISymbolTable symbolTable;
    private ILexicalAnalyser lexer;
    private IErrorReporter errRep;

    /**
     * parametrized constructor
     * @param symbolTable
     */
    public SyntaxAnalyser(ISymbolTable symbolTable, ILexicalAnalyser lexer, IErrorReporter errRep) {
        this.symbolTable = symbolTable;
        this.lexer = lexer;
        this.errRep = errRep;
    }

    public IIR parse() {

        IR intRep = new IR();

        Token token = lexer.scan();
        IMnemonic mnemonic = null;
        Token operandToken = null;
        LineStatement lineStatement = new LineStatement();

        while (token.getType() != TypeToken.EOF) {

            switch (token.getType()) {

                case EOL:
                    //create line statement and send to IR

                    if (mnemonic != null && mnemonic.getType() == MnemonicType.Immediate && operandToken == null) {
                        errRep.recordError(new ErrorMsg("An immediate instruction requires an operand (number or label).", token.getPosition()));
                    }
                    lineStatement.setInstruction(new Instruction(mnemonic, operandToken != null? operandToken.getName(): null));

                    lineStatement.setComment(token.getName());
                    intRep.addLineStatement(lineStatement);
                    lineStatement = new LineStatement();
                    mnemonic = null;
                    operandToken = null;
                    break;
                case Label:
                    //Save label;
                    lineStatement.setLabel(token.getName());
                    break;
                case Mnemonic:
                    mnemonic = parseToken(token);
                    break;
                case Operand:
                    //Test if operand is needed or not? or if outofbound
                    if (mnemonic == null) {
                        errRep.recordError(new ErrorMsg("Cannot have an operand without a mnemonic.", token.getPosition()));
                    } else if (mnemonic.getType() == MnemonicType.Inherent) {
                        errRep.recordError(new ErrorMsg("An inherent instruction has no operand.", token.getPosition()));
                    }
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
    IMnemonic parseToken(Token token) {
        //TODO: Check if token should have an operand. To do so, Add true/false (or a range/null) to Mnemonic constructor, and change SymbolTable

        IMnemonic mnemonic = symbolTable.get(token.getName());

        if (mnemonic == null) {
            errRep.recordError(new ErrorMsg("Invalid mnemonic or directive.", token.getPosition()));
        }

        return mnemonic; //hashtable or components.SymbolTable
    }

//    /**
//     * getter for testing purposes
//     * @return LineStatement object
//     */
//    LineStatement getLineStatement() {
//        return lineStatement;
//    }
}