package analysers;

import components.*;
import interfaces.*;
import errorReporters.*;

/**
 * Creates line statements from the tokens passed on from the lexer, and pushes them
 * to the IR
 */
public class SyntaxAnalyser implements ISyntaxAnalyser {

    private int addrCount = 0;

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

        //Stored Tokens
        Label label = null;
        Label operandLabel = null;
        Token mnemonicToken = null;
        Token operandOffsetToken = null;
        Token directiveToken = null;

        while (token.getType() != TypeToken.EOF) {

            switch (token.getType()) {

                case EOL:
//                    System.out.println("Test");
                    //create line statement and add it to IR
                    ILineStatement lineStatement;

                    //TODO: If both operandLabel and offset use: Error

                    //If there is not directive, create an Instruction
                    if(directiveToken != null) {
                        lineStatement = new LineStatement(addrCount, label, directiveToken.getName(), operandOffsetToken.getName(), token.getName());
                        addrCount += 1 + operandOffsetToken.getName().length();
                    } else {
                        IMnemonic mnemonic = checkOperand(mnemonicToken, operandOffsetToken);
                        IInstruction instruction = null;
                        if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel) {
                            if (operandOffsetToken != null) {
                                //TODO: ERROR
                            }
                            instruction = new Instruction(mnemonic, operandLabel);
                        }
                        else if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeOffset && operandLabel != null)
                            //TODO: ERROR
                            System.out.println();
                        else
                            instruction = new Instruction(mnemonic, operandOffsetToken != null ? operandOffsetToken.getName() : null);

                        lineStatement = new LineStatement(addrCount, label, instruction, token.getName());
//                        System.out.println(label != null ? label.getName(): "");
                        if (mnemonic != null && (mnemonic.getType() == MnemonicType.RelativeLabel || mnemonic.getType() == MnemonicType.RelativeOffset)) {
                            int mncBitInt = Integer.parseInt(String.valueOf(mnemonic.getMnemonicName().charAt(mnemonic.getMnemonicName().length()-1)));
                            if (mncBitInt == 8)
                                addrCount += 2;
                            else if (mncBitInt == 6)
                                addrCount += 3;
                        } else if (mnemonic != null) {
                            addrCount++;
                        }

                    }

                    //Adding a LineStatement to the IR
                    intRep.addLineStatement(lineStatement);

                    //Reset everything
                    label = null;
                    mnemonicToken = null;
                    operandOffsetToken = null;
                    operandLabel = null;
                    directiveToken = null;

                    break;

                case Label:
                    //Save the labelToken
                    String labelName = token.getName();
                    //If Label exists in the table, and was never given an addr
                    if (symbolTable.hasLabel(labelName)) {
                        Label stLabel = symbolTable.getLabel(labelName);
                        if (stLabel.getAddr() == -1)
                            stLabel.setAddr(addrCount);
                        //TODO: else Error cannot have 2 Labels
                    } else {
                        symbolTable.addLabel(new Label(token.getName(), addrCount));
                    }
                    label = symbolTable.getLabel(labelName);
                    break;

                case Mnemonic:
                    //Save the mnemonicToken
                    mnemonicToken = token;
                    //mnemonic = parseMnemonicToken(token); //get the mnemonic object from the SymbolTable
                    break;

                case Directive:
                    //Save the directiveToken
                    directiveToken = token;
                    break;

                case OperandOffset:
                    //Save the operandToken
                    operandOffsetToken = token;
                    break;

                case OperandLabel:
                    String opLabelName = token.getName();
                    if (!symbolTable.hasLabel(opLabelName)) {
                        symbolTable.addLabel(new Label(opLabelName, -1));
                    }
                    operandLabel = symbolTable.getLabel(opLabelName);
                    break;

                case StringOperand:
                    operandOffsetToken = token;
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

        IMnemonic mnemonic = symbolTable.getMnemonic(token.getName());

        return mnemonic;
    }

    /**
     * Check all Error cases related to the mnemonic and operand
     * Return the right Mnemonic object with the correct opCode
     * @param mnemonicToken
     * @param operandToken
     * @return
     */
    private IMnemonic checkOperand(Token mnemonicToken, Token operandToken) {

        //Check if operand is there without mnemonic
        if (mnemonicToken == null && operandToken != null) {
            errRep.recordError(new ErrorMsg("Cannot have an operand without a mnemonic.", operandToken.getPosition()));
            return null;
        }

        //Check if mnemonic is null
        if (mnemonicToken == null) {
//            errRep.recordError(new ErrorMsg("Invalid mnemonic or directive.", mnemonicToken.getPosition()));
            return null;
        }

        IMnemonic mnemonic = parseMnemonicToken(mnemonicToken);

        if (mnemonic == null) {
            errRep.recordError(new ErrorMsg("Invalid mnemonic or directive.", mnemonicToken.getPosition()));
            return null;
        }

        //Check if mnemonic is an inherent type
        //If there is an operand, report error
        if (mnemonic.getType() == MnemonicType.Inherent && operandToken != null)
            errRep.recordError(new ErrorMsg("An inherent instruction does not require an operand (number or label).", operandToken.getPosition()));

        //Check if mnemonic is an immediate type
        //If there is no operand, report error
        if (mnemonic.getType() == MnemonicType.Immediate && operandToken == null) {
            errRep.recordError(new ErrorMsg("An immediate instruction requires an operand (number or label).", mnemonicToken.getPosition()));
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