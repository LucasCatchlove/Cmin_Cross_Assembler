public class TestLineStatement {

    public static void main(String[] args) {

        Instruction inst = new Instruction(new Mnemonic("mul", 0x15), "");
        LineStatement lineStatement = new LineStatement("", inst, "");

        System.out.println("Test LineStatement getLabel");
        System.out.println("");
        System.out.println(lineStatement.getLabel());

        System.out.println("Test LineStatement getInstruction");
        System.out.println("Mnemonic: (Name: mul, Opcode: 15), Operand: ");
        System.out.println(lineStatement.getInstruction());

        System.out.println("Test LineStatement getComment");
        System.out.println("");
        System.out.println(lineStatement.getComment());

    }
}
