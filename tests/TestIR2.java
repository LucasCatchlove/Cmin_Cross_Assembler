package tests;

import components.Instruction;
import components.IR;
import components.Mnemonic;
import components.LineStatement;
import java.util.ArrayList;

/**
 * tests getLineStatementList() method in components.IR class
 */
public class TestIR2 {
    public static void main(String[] args) {
        IR testIR = new IR();
        ArrayList<LineStatement> lsl1 = testIR.getLineStatementList();
        Mnemonic m1 = new Mnemonic("halt", 0x00);
        Mnemonic m2 = new Mnemonic("pop", 0x01);
        Instruction ins1 = new Instruction(m1, "");
        Instruction ins2 = new Instruction(m2, "");
        LineStatement ls1 = new LineStatement("", ins1, "");
        LineStatement ls2 = new LineStatement("", ins2, "");
        testIR.addLineStatement(ls1);
        testIR.addLineStatement(ls2);



        System.out.println("Test IR2");
        System.out.println("truetrue");

        System.out.print(lsl1.get(0) instanceof LineStatement);
        System.out.print(lsl1.get(1) instanceof LineStatement);


    }


}
