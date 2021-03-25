package generators;


import components.IR;

public class TestListing3 {
    public static void main(String[] args) {
        IR ir3 = new IR();
        Listing l3 = new Listing(ir3);

        System.out.println("Test Lineformatter(1)");
        System.out.println("3      0001    83                                   enter.u5        3            ; OK, number <u5> [0..31].");
        System.out.print(l3.LineFormatter("3","0001","83","","enter.u5","3","; OK, number <u5> [0..31]."));

        System.out.println("Test Lineformatter(2)");
        System.out.println("37     0022    94                                   ldc.i3          -4           ; OK, number <i3> [-4..3].");
        System.out.print(l3.LineFormatter("37","0022","94","","ldc.i3","-4","; OK, number <i3> [-4..3]."));
        System.out.println("Test Lineformatter(3)");
        System.out.println("2      0000                                         .cstring                     ; directive              ");
        System.out.print(l3.LineFormatter("2","0000","","",".cstring","","; directive"));
        System.out.println("Test Lineformatter(4)");
        System.out.println("2      0000                                         pop                          ; inherent instruction   ");
        System.out.print(l3.LineFormatter("2","0000","","","pop","","; inherent instruction"));





    }
}
