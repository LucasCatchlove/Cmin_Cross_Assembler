package generators;

import components.IR;
import components.Instruction;
import components.LineStatement;
import components.Mnemonic;

public class TestListing4 {
    public static void main(String[] args) {

        IR ir1 = new IR();
        Listing l1 = new Listing(ir1);
        Mnemonic m1 = new Mnemonic("halt", 0x00);
        Mnemonic m2 = new Mnemonic("pop", 0x01);
        Instruction ins1 = new Instruction(m1, "");
        Instruction ins2 = new Instruction(m2, "");
        LineStatement ls1 = new LineStatement("", ins1, "");
        LineStatement ls2 = new LineStatement("", ins2, "");

        System.out.println("Test separateLineStatement");
        System.out.println("00halt01pop");
        String[] testStr1 = l1.separateLineStatement(ls1);
        for (String s : testStr1) System.out.print(s);
        String[] testStr2 = l1.separateLineStatement(ls2);
        for (String s : testStr2) System.out.print(s);
        System.out.println();



    }
}
