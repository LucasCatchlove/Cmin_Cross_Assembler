package components;

import interfaces.IMnemonic;

/**
 * class to hold the mnemonic identifier and its opcode
 */
public class Mnemonic implements IMnemonic {

    private String mnemonicName; //components.Mnemonic Name: "halt",...
    private int opCode; //components.Mnemonic opCode: 00,0A...
    private int endOpCode;
    private boolean hasOpCodeRange;

    /**
     * parametrized constructor for Stack or Inherent Mnemonics
     * @param mnemonicName
     * @param opCode
     */
    public Mnemonic(String mnemonicName, int opCode) {
        this.mnemonicName = mnemonicName;
        this.opCode = opCode;
        this.endOpCode = -1;
        this.hasOpCodeRange = false;
    }

    /**
     * parametrized constructor for Immediate Mnemonics
     * @param mnemonicName
     * @param opCode
     * @param endOpCode
     */
    public Mnemonic(String mnemonicName, int opCode, int endOpCode) {
        this.mnemonicName = mnemonicName;
        this.opCode = opCode;
        this.endOpCode = endOpCode;
        this.hasOpCodeRange = true;
    }

    public String getMnemonicName() {
        return mnemonicName;
    }

    public int getOpCode() {
        return opCode;
    }

    public int getEndOpCode() {
        return endOpCode;
    }

    public boolean hasOpCodeRange() {
        return hasOpCodeRange;
    }

    public String toString() {
        return "Name: " + mnemonicName + ", Opcode: " + String.format("%02X", opCode);
    }

}



