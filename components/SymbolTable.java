package components;

import interfaces.ISymbolTable;

import java.util.HashMap;

/**
 * Stores and allows for retrieval of mnemonic objects based on an
 * input identifier
 */
public class SymbolTable implements ISymbolTable {

	private HashMap<String, Mnemonic> instructions;
	private HashMap<String, Label> labels;

	//Default Constructor: define the HashMap and add all the elements
	public SymbolTable() {

        instructions = new HashMap<>();
        labels = new HashMap<>();

        //Stack or Inherent Addressing
        instructions.put("halt", new Mnemonic("halt", 0x00, MnemonicType.Inherent));
        instructions.put("pop", new Mnemonic("pop", 0x01, MnemonicType.Inherent));
        instructions.put("dup", new Mnemonic("dup", 0x02, MnemonicType.Inherent));
        instructions.put("exit", new Mnemonic("exit", 0x03, MnemonicType.Inherent));
        instructions.put("ret", new Mnemonic("ret", 0x04, MnemonicType.Inherent));
        instructions.put("not", new Mnemonic("not", 0x0C, MnemonicType.Inherent));
        instructions.put("and", new Mnemonic("and", 0x0D, MnemonicType.Inherent));
        instructions.put("or", new Mnemonic("or", 0x0E, MnemonicType.Inherent));
        instructions.put("xor", new Mnemonic("xor", 0x0F, MnemonicType.Inherent));
        instructions.put("neg", new Mnemonic("neg", 0x10, MnemonicType.Inherent));
        instructions.put("inc", new Mnemonic("inc", 0x11, MnemonicType.Inherent));
        instructions.put("dec", new Mnemonic("dec", 0x12, MnemonicType.Inherent));
        instructions.put("add", new Mnemonic("add", 0x13, MnemonicType.Inherent));
        instructions.put("sub", new Mnemonic("sub", 0x14, MnemonicType.Inherent));
        instructions.put("mul", new Mnemonic("mul", 0x15, MnemonicType.Inherent));
        instructions.put("div", new Mnemonic("div", 0x16, MnemonicType.Inherent));
        instructions.put("rem", new Mnemonic("rem", 0x17, MnemonicType.Inherent));
        instructions.put("shl", new Mnemonic("shl", 0x18, MnemonicType.Inherent));
        instructions.put("shr", new Mnemonic("shr", 0x19, MnemonicType.Inherent));
        instructions.put("teq", new Mnemonic("teq", 0x1A, MnemonicType.Inherent));
        instructions.put("tne", new Mnemonic("tne", 0x1B, MnemonicType.Inherent));
        instructions.put("tlt", new Mnemonic("tlt", 0x1C, MnemonicType.Inherent));
        instructions.put("tgt", new Mnemonic("tgt", 0x1D, MnemonicType.Inherent));
        instructions.put("tle", new Mnemonic("tle", 0x1E, MnemonicType.Inherent));
        instructions.put("tge", new Mnemonic("tge", 0x1F, MnemonicType.Inherent));

        //Immediate Addressing
        instructions.put("enter.u5", new Mnemonic("enter.u5", 0x70, MnemonicType.Immediate));
        instructions.put("ldc.i3", new Mnemonic("ldc.i3", 0x90, MnemonicType.Immediate));
        instructions.put("addv.u3", new Mnemonic("addv.u3", 0x98, MnemonicType.Immediate));
        instructions.put("ldv.u3", new Mnemonic("ldv.u3", 0xA0, MnemonicType.Immediate));
        instructions.put("stv.u3", new Mnemonic("stv.u3", 0xA8, MnemonicType.Immediate));

        //Relative Addressing
        instructions.put("br.i8", new Mnemonic("br.i8", 0xE0, MnemonicType.Relative));
        instructions.put("brf.i8", new Mnemonic("brf.i8", 0xE3, MnemonicType.Relative));
        instructions.put("ldc.i8", new Mnemonic("ldc.i8", 0xD9, MnemonicType.Relative));
        instructions.put("ldv.u8", new Mnemonic("ldv.u8", 0xB1, MnemonicType.Relative));
        instructions.put("stv.u8", new Mnemonic("stv.u8", 0xB2, MnemonicType.Relative));
        instructions.put("lda.i16", new Mnemonic("lda.i16", 0xD5, MnemonicType.Relative));

    }

        /**
        * get method receives a token, and returns the components.Mnemonic object value related to the token key in the HashMap
        * @param token
        * @return
        */
    public Mnemonic getMnemonic(String token){
        return instructions.get(token);
    }

    public boolean hasMnemonic(String token) {
        return instructions.get(token) != null;
    }

    public void addLabel(Label label) {
        labels.put(label.getName(), label);
    }

    public Label getLabel(String labelName) {
        return labels.get(labelName);
    }

    public boolean hasLabel(String labelName) {
        return labels.get(labelName) != null;
    }



}
