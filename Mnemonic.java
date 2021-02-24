public class Mnemonic {
    private String mnemonicName;
    private int opCode;

    public Mnemonic(String mnemonicName, int opCode) {
        this.mnemonicName = mnemonicName;
        this.opCode = opCode;
    }

    public String getMnemonicName() {
        return mnemonicName;
    }

    public int getOpCode() {
        return opCode;
    }
}

