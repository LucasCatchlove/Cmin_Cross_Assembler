package components;

import interfaces.ISymbolTable;

import java.util.HashMap;

/**
 * Stores and allows for retrieval of mnemonic objects based on an
 * input identifier
 */
public class SymbolTable implements ISymbolTable {

	private HashMap<String, Mnemonic> instructions;

	//Default Constructor: define the HashMap and add all the elements
	public SymbolTable() {

        instructions = new HashMap<>();

        //Stack or Inherent Addressing
        instructions.put("halt", new Mnemonic("halt", 0x00));
        instructions.put("pop", new Mnemonic("pop", 0x01));
        instructions.put("dup", new Mnemonic("dup", 0x02));
        instructions.put("exit", new Mnemonic("exit", 0x03));
        instructions.put("ret", new Mnemonic("ret", 0x04));
        instructions.put("not", new Mnemonic("not", 0x0C));
        instructions.put("and", new Mnemonic("and", 0x0D));
        instructions.put("or", new Mnemonic("or", 0x0E));
        instructions.put("xor", new Mnemonic("xor", 0x0F));
        instructions.put("neg", new Mnemonic("neg", 0x10));
        instructions.put("inc", new Mnemonic("inc", 0x11));
        instructions.put("dec", new Mnemonic("dec", 0x12));
        instructions.put("add", new Mnemonic("add", 0x13));
        instructions.put("sub", new Mnemonic("sub", 0x14));
        instructions.put("mul", new Mnemonic("mul", 0x15));
        instructions.put("div", new Mnemonic("div", 0x16));
        instructions.put("rem", new Mnemonic("rem", 0x17));
        instructions.put("shl", new Mnemonic("shl", 0x18));
        instructions.put("shr", new Mnemonic("shr", 0x19));
        instructions.put("teq", new Mnemonic("teq", 0x1A));
        instructions.put("tne", new Mnemonic("tne", 0x1B));
        instructions.put("tlt", new Mnemonic("tlt", 0x1C));
        instructions.put("tgt", new Mnemonic("tgt", 0x1D));
        instructions.put("tle", new Mnemonic("tle", 0x1E));
        instructions.put("tge", new Mnemonic("tge", 0x1F));

        //Immediate Addressing
        instructions.put("enter.u5", new Mnemonic("enter.u5", 0x70, 0x8F));
        instructions.put("ldc.i3", new Mnemonic("ldc.i3", 0x90, 0x97));
        instructions.put("addv.i3", new Mnemonic("addv.i3", 0x98, 0x9F));
        instructions.put("ldv.u3", new Mnemonic("ldv.u3", 0xA0, 0xA7));
        instructions.put("stv.u3", new Mnemonic("stv.u3", 0xA8, 0xAF));

    }

        /**
         * get method receives a token, and returns the components.Mnemonic object value related to the token key in the HashMap
         * @param token
         * @return
         */
    public Mnemonic get(String token){
                return instructions.get(token);
        }

}
