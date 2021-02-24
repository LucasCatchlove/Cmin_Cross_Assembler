public class Mnemonic implements IMnemonic {

    private String mnemonicName; //Mnemonic Name: "halt",...
    private int opCode; //Mnemonic opCode: 00,0A...

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



