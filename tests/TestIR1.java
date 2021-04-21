
package tests;

import components.*;


 //* tests addLineStatement() and getLineStatement() methods
public class TestIR1 {
    public static void main(String[] args) {




        IR testIR = new IR();
        Mnemonic m1 = new Mnemonic("halt", 0x00, MnemonicType.Inherent);
        Mnemonic m2 = new Mnemonic("pop", 0x01 , MnemonicType.Inherent);
        Instruction ins1 = new Instruction(m1, "");
        Instruction ins2 = new Instruction(m2, "");
        LineStatement ls1 = new LineStatement(0, null, null, ins1, "");
        LineStatement ls2 = new LineStatement(0, null, null, ins2, "");


        System.out.println("Test IR1");
        System.out.println("halt  pop  ");

        testIR.addLineStatement(ls1);
        testIR.addLineStatement(ls2);
        System.out.print(testIR.getLineStatement(0)); //ls1
        System.out.print(testIR.getLineStatement(1)); //ls2
        System.out.println();



    }
}

