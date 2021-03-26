package tests;

import components.Range;


public class TestRange3 {

    public static void main(String[] args) {

        Range range1 = new Range('u', 5);
        Range range2 = new Range('i', 3);


        System.out.println("Test Range getMncSignStr()");

        System.out.println("range1: signStr=unsigned; range2: signStr=signed");
        String signStr1 = range1.getMncSignStr();
        String signStr2 = range2.getMncSignStr();
        System.out.println("range1: signStr=" + signStr1 + "; range2: signStr=" + signStr2);

    }

}
