package generators;


import components.Instruction;
import components.LineStatement;
import components.Mnemonic;
import components.IR;


public class TestListing1 {
    public static void main(String[] args) {
        IR ir1 = new IR();
        IR ir2 = new IR();
        Listing l1 = new Listing(ir1);
        Listing l2 = new Listing(ir2);
        Mnemonic m1 = new Mnemonic("halt", 0x00);
        Mnemonic m2 = new Mnemonic("pop", 0x01);
        Instruction ins1 = new Instruction(m1, "");
        Instruction ins2 = new Instruction(m2, "");
        LineStatement ls1 = new LineStatement("", ins1, "");
        LineStatement ls2 = new LineStatement("", ins2, "");
        ir1.addLineStatement(ls1);
        ir1.addLineStatement(ls2);
        ir2.addLineStatement(ls1);
        CodeGenerator cg1 = new CodeGenerator(l1);
        CodeGenerator cg2 = new CodeGenerator(l2);



        System.out.println("Test Listing getLineNumber");
        System.out.println("3 2");
        System.out.println(l1.getLineNumber() + " " + l2.getLineNumber());

        System.out.println("Test Listing address");
        System.out.println("0002 0001");
        System.out.println(l1.address()+" "+l2.address());





    }
}
