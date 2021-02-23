public class Instruction {

    private Mnemonic mnemonic;
    private String operand; //String just for this Sprint

    public Instruction(Mnemonic mnemonic, String operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
    }
}