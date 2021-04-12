package components;

import interfaces.IInstruction;
import interfaces.IMnemonic;

/**
 * Creates an instruction (an element of a a line statement)
 * with a mnemonic and option operand
 */
public class Instruction implements IInstruction {

    private IMnemonic mnemonic;
    private String operandOffset; //String just for this Sprint
    private Label operandLabel;

    /**
     * parametrized constructor that creates a new Mnemonic object with an optional operand
     * @param mnemonic
     * @param operandOffset
     */
    public Instruction(IMnemonic mnemonic, String operandOffset) {
        this.mnemonic = mnemonic;
        this.operandOffset = operandOffset;
    }

    public Instruction(IMnemonic mnemonic, Label operandLabel) {
        this.mnemonic = mnemonic;
        this.operandLabel = operandLabel;
    }

    public IMnemonic getMnemonic() {
        return mnemonic;
    }

    public String getOperandOffset() {
        return operandOffset;
    }

    public Label getOperandLabel() {
        return operandLabel;
    }

    public String toString() {
        return "Mnemonic: (" + mnemonic + "), Operand: " + operandOffset;
    }

}