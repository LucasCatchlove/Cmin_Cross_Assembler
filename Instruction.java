public class Instruction {

    private Mnemonic mnemonic;
    private String operand; //String just for this Sprint

    /**
     * parametrized constructor that creates a new Mnemonic object with an optional operand
     * @param mnemonic
     * @param operand
     */
    public Instruction(Mnemonic mnemonic, String operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
    }

    public Mnemonic getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }

    public String toString() {
        return "Mnemonic: (" + mnemonic + "), Operand: " + operand;
    }

}