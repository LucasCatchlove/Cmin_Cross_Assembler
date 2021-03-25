package generators;

import components.IR;

public class TestListing2 {
    public static void main(String[] args) {
        IR ir1 = new IR();
        Listing l1 = new Listing(ir1);

        System.out.println("Test header");
        System.out.println("Line   Addr    Machine Code    Label                Assembly Code   Operand      Comments                 ");
        System.out.print(l1.header());




    }
}
