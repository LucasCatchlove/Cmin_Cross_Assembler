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

                    if(lineStatement.getDirective() == null)
                         lineStatement.setInstruction(new Instruction(checkOperand(mnemonic, operandToken), operandToken != null? operandToken.getName(): null));

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

                case Directive:
                    lineStatement.setDirective(token.getName());
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

    private IMnemonic checkOperand(IMnemonic mnemonic, Token operandToken) {

        if (mnemonic == null || operandToken == null) {
            return null;
        }

        if (mnemonic != null && mnemonic.getType() == MnemonicType.Immediate && operandToken == null) {
            errRep.recordError(new ErrorMsg("An immediate instruction requires an operand (number or label).", operandToken.getPosition()));
            return mnemonic;
        }

        if (mnemonic.getType() != MnemonicType.Inherent) {

            String mnemonicName = mnemonic.getMnemonicName();
            char mncSignChar = mnemonicName.charAt(mnemonicName.length()-2);
            int mncBitInt = Integer.parseInt(String.valueOf(mnemonicName.charAt(mnemonicName.length()-1)));

            int operandInt = Integer.parseInt(operandToken.getName());

            Range range = new Range(mncSignChar, mncBitInt);

            int opcode = mnemonic.getOpCode();

            switch (mncBitInt) {

                case 5:
                    opcode = (operandInt > 15) ? opcode : 0x80;

                    if (operandInt < range.getStart() || operandInt > range.getEnd()) {
                        errRep.recordError(new ErrorMsg("The immediate instruction '" + mnemonicName + "' must have a " + mncBitInt + "-bit " + range.getMncSignStr() + " operand number from " + range.getStart() + " to " + range.getEnd() + ".", operandToken.getPosition()));
                        opcode = opcode | (operandInt & 0x1F); //Bitmask
                        return new Mnemonic(mnemonicName, opcode, mnemonic.getType());
                    }

                    opcode = opcode | operandInt;
                    break;

                case 3:

                    if (operandInt < range.getStart() || operandInt > range.getEnd()) {
                        errRep.recordError(new ErrorMsg("The immediate instruction '" + mnemonicName + "' must have a " + mncBitInt + "-bit " + range.getMncSignStr() + " operand number from " + range.getStart() + " to " + range.getEnd() + ".", operandToken.getPosition()));
                        opcode = opcode | (operandInt & 0x07); //Bitmask
                        return new Mnemonic(mnemonicName, opcode, mnemonic.getType());
                    }

                    if (mncSignChar == 'i' && operandInt < 0) {
                        operandInt = (~Math.abs(operandInt) & 0x07) + 1;
                    }
                    opcode = opcode | operandInt;
                    break;

            }

            return new Mnemonic(mnemonicName, opcode, mnemonic.getType());

//            int range = (int) Math.pow(2, mncBitInt);
//
//            int start;
//            int end;
//            String mncSignStr;
//
//            if (mncSignChar == 'u') { //Unsigned Bit; char is u
//                start = 0;
//                end = range-1;
//                mncSignStr = "unsigned";
//            } else { //Signed Bit; char is i
//                start = -range/2;
//                end = range/2 -1;
//                mncSignStr = "signed";
//            }
//
//            int operandInt = Integer.parseInt(operandToken.getName());
//
//            //Checking for OutofBound Range
//            if (operandInt < start || operandInt > end) {
//                errRep.recordError(new ErrorMsg("The immediate instruction '" + mnemonicName + "' must have a " + mncBitInt + "-bit " + mncSignStr + " operand number from " + start + " to " + end + ".", operandToken.getPosition()));
//                return mnemonic;
//            }
//
//            int newOpCode;
//
//            if (operandInt >= 0)
//                newOpCode = operandInt + mnemonic.getOpCode();
//            else
//                newOpCode = operandInt + range + mnemonic.getOpCode();
//
//            return new Mnemonic(mnemonicName, newOpCode, mnemonic.getType());
        }

        return mnemonic;

    }

//    /**
//     * getter for testing purposes
//     * @return LineStatement object
//     */
//    LineStatement getLineStatement() {
//        return lineStatement;
//    }
}