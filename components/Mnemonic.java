package components;

import interfaces.IMnemonic;

/**
 * class to hold the mnemonic identifier and its opcode
 */
public class Mnemonic implements IMnemonic {

    private String mnemonicName; //components.Mnemonic Name: "halt",...
    private int opCode; //components.Mnemonic opCode: 00,0A...
    private MnemonicType type;

    /**
     * parametrized constructor for Stack or Inherent Mnemonics
     * @param mnemonicName
     * @param opCode
     */
    public Mnemonic(String mnemonicName, int opCode, MnemonicType type) {
        this.mnemonicName = mnemonicName;
        this.opCode = opCode;
        this.type = type;
    }

    public String getMnemonicName() {
        return mnemonicName;
    }

    public int getOpCode() {
        return opCode;
    }

    public MnemonicType getType() {
        return type;
    }

    public String toString() {
        return "Name: " + mnemonicName + ", Opcode: " + String.format("%02X", opCode);
    }

}



