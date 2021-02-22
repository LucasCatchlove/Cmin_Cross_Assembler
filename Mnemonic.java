public class Mnemonic {
    private String mnemonicName;
    private String opCode;

    public Mnemonic(String mnemonicName, String opCode) {
        this.mnemonicName = mnemonicName;
        this.opCode = opCode;
    }

    public String getMnemonicName() {
        return mnemonicName;
    }

    public String getOpCode() {
        return opCode;
    }
}

