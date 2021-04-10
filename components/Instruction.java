package components;

import interfaces.IInstruction;
import interfaces.IMnemonic;

/**
 * Creates an instruction (an element of a a line statement)
 * with a mnemonic and option operand
 */
public class Instruction implements IInstruction {

    private IMnemonic mnemonic;
    private String operand; //String just for this Sprint

    /**
     * parametrized constructor that creates a new Mnemonic object with an optional operand
     * @param mnemonic
     * @param operand
     */
    public Instruction(IMnemonic mnemonic, String operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
    }

    public IMnemonic getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }

    public String toString() {
        return "Mnemonic: (" + mnemonic + "), Operand: " + operand;
    }

}