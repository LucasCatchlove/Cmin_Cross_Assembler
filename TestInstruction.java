public class TestInstruction {

    public static void main(String[] args) {

        Instruction inst = new Instruction(new Mnemonic("teq", 0x1A), "");

        System.out.println("Test Instruction getMnemonic");
        System.out.println("Name: teq, Opcode: 1A");
        System.out.println(inst.getMnemonic());

    }
}
