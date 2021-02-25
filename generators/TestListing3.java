package generators;


import components.IR;

public class TestListing3 {
    public static void main(String[] args) {
        IR ir3 = new IR();
        Listing l3 = new Listing(ir3);

        System.out.println("Test Lineformatter(1)");
        System.out.println("1      0000    00                          halt                                     ");
        System.out.print(l3.LineFormatter("1", "0000", "00", "", "halt", "", ""));
        System.out.println("Test Lineformatter(2)");
        System.out.println( "2      0001    01                          pop                                      ");
        System.out.print(l3.LineFormatter("2", "0001", "01", "", "pop", "", ""));
        System.out.println("Test Lineformatter(3)");
        System.out.println("3      0002    02                          dud                                      ");
        System.out.print(l3.LineFormatter("3", "0002", "02", "", "dud", "", ""));





    }
}
