package tests;

import components.Instruction;
import components.Mnemonic;
import components.MnemonicType;

public class TestInstruction {

    public static void main(String[] args) {


        Instruction inst1 = new Instruction(new Mnemonic("teq", 0x1A, MnemonicType.Inherent), "");
        Instruction inst2 = new Instruction(new Mnemonic("add", 0x0C, MnemonicType.Inherent), "");
        Instruction inst3 = new Instruction(new Mnemonic("sub", 0x0D, MnemonicType.Inherent), "");

        System.out.println("Test components.Instruction getMnemonic");
        System.out.println("Name: teq, Opcode: 1A Name: add, Opcode: 0C Name: sub, Opcode: 0D");
        System.out.print(inst1.getMnemonic()+" ");
        System.out.print(inst2.getMnemonic()+ " ");
        System.out.println(inst3.getMnemonic());


    }
}
