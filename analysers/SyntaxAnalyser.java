package analysers;

import components.*;
import interfaces.*;
import errorReporters.*;

/**
 * Creates line statements from the tokens passed on from the lexer, and pushes them
 * to the IR
 */
public class SyntaxAnalyser implements ISyntaxAnalyser {

    //Objects
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

    /**
     * Parse the token, create a LineStatement, add the LineStatement to an IR, return the IR when EOF is reached
     * @return
     */
    public IIR parse() {

        IR intRep = new IR();

        //Scan the file, and get the token
        Token token = lexer.scan();

        IMnemonic mnemonic = null;
        Token operandToken = null;
        LineStatement lineStatement = new LineStatement();

        while (token.getType() != TypeToken.EOF) {

            switch (token.getType()) {

                case EOL:
                    //create line statement and add it to IR

                    //If there is not directive, create an Instruction
                    if(lineStatement.getDirective() == null)
                         lineStatement.setInstruction(new Instruction(checkOperand(mnemonic, operandToken), operandToken != null? operandToken.getName(): null));

                    //Add the Comment to the LineStatement
                    lineStatement.setComment(token.getName());

                    //Adding a LineStatement to the IR
                    intRep.addLineStatement(lineStatement);

                    //Reset everything
                    lineStatement = new LineStatement();
                    mnemonic = null;
                    operandToken = null;
                    break;

                case Label:
                    //Save the label
                    lineStatement.setLabel(token.getName());
                    break;

                case Mnemonic:
                    //Save the mnemonic
                    mnemonic = parseMnemonicToken(token); //get the mnemonic object from the SymbolTable
                    break;

                case Directive:
                    //Add the Directive to the LineStatement
                    lineStatement.setDirective(token.getName());
                    break;

                case Operand:

                    //Test if operand is needed or not? or if outOfBound
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
     * returns Mnemonic object from the symbol table using the token.getName()
     * @param token
     * @return
     */
    IMnemonic parseMnemonicToken(Token token) {

        IMnemonic mnemonic = symbolTable.get(token.getName());

        //Check if the mnemonic is invalid
        if (mnemonic == null)
            errRep.recordError(new ErrorMsg("Invalid mnemonic or directive.", token.getPosition()));

        return mnemonic;
    }

    /**
     * Check all Error cases related to the mnemonic and operand
     * Return the right Mnemonic object with the correct opCode
     * @param mnemonic
     * @param operandToken
     * @return
     */
    private IMnemonic checkOperand(IMnemonic mnemonic, Token operandToken) {

        //Check if mnemonic is null
        if (mnemonic == null) {
            return null;
        }

        //Check if mnemonic is an inherent type
        //If there is an operand, report error
        if (mnemonic.getType() == MnemonicType.Inherent && operandToken != null)
            errRep.recordError(new ErrorMsg("An inherent instruction does not require an operand (number or label).", operandToken.getPosition()));

        //Check if mnemonic is an immediate type
        //If there is no operand, report error
        if (mnemonic.getType() == MnemonicType.Immediate && operandToken == null) {
            errRep.recordError(new ErrorMsg("An immediate instruction requires an operand (number or label).", operandToken.getPosition()));
            return mnemonic;
        }

        //Check the range, sign, bit, and create a new Mnemonic with the correct opcode if type is Inherent
        //Bitwise operations
        if (mnemonic.getType() != MnemonicType.Inherent && operandToken != null) {

            //Get the mnemonicName, mnemonic sign char, and mnemonic bit
            String mnemonicName = mnemonic.getMnemonicName();
            char mncSignChar = mnemonicName.charAt(mnemonicName.length()-2);
            int mncBitInt = Integer.parseInt(String.valueOf(mnemonicName.charAt(mnemonicName.length()-1)));

            int operandInt = Integer.parseInt(operandToken.getName());

            Range range = new Range(mncSignChar, mncBitInt);

            //Get the opCode registered by the SymbolTable
            int opcode = mnemonic.getOpCode();

            switch (mncBitInt) {

                //If the Mnemonic bit is 5
                case 5:

                    opcode = (operandInt > 15) ? opcode : 0x80;

                    //If outOfBound range, report error, and create a new Mnemonic with the bitmask opcode
                    if (operandInt < range.getStart() || operandInt > range.getEnd()) {
                        errRep.recordError(new ErrorMsg("The immediate instruction '" + mnemonicName + "' must have a " + mncBitInt + "-bit " + range.getMncSignStr() + " operand number from " + range.getStart() + " to " + range.getEnd() + ".", operandToken.getPosition()));
                        opcode = opcode | (operandInt & 0x1F); //Bitmask
                        return new Mnemonic(mnemonicName, opcode, mnemonic.getType());
                    }

                    opcode = opcode | operandInt;
                    break;

                //If the Mnemonic bit is 3
                case 3:

                    //If outOfBound range, report error, and create a new Mnemonic with the bitmask opcode
                    if (operandInt < range.getStart() || operandInt > range.getEnd()) {
                        errRep.recordError(new ErrorMsg("The immediate instruction '" + mnemonicName + "' must have a " + mncBitInt + "-bit " + range.getMncSignStr() + " operand number from " + range.getStart() + " to " + range.getEnd() + ".", operandToken.getPosition()));
                        opcode = opcode | (operandInt & 0x07); //Bitmask
                        return new Mnemonic(mnemonicName, opcode, mnemonic.getType());
                    }

                    //If the mnemonic sign char is 'i', change get the 1's complement of the operand
                    if (mncSignChar == 'i' && operandInt < 0) {
                        operandInt = (~Math.abs(operandInt) & 0x07) + 1;
                    }
                    opcode = opcode | operandInt;
                    break;

            }

            //Return a new Mnemonic with the new opcode
            return new Mnemonic(mnemonicName, opcode, mnemonic.getType());

            //Not a bitwise operand with the opcode, range, and operand
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

        //return the same mnemonic if none of the above where performed
        return mnemonic;

    }

}