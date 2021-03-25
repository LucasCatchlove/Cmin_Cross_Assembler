package tests;

import components.Range;


public class TestRange {

    public static void main(String[] args) {

        Range range1 = new Range('u', 5);
        Range range2 = new Range('i', 3);

        System.out.println("Test Range getStart()");

        System.out.println("range1: start=0; range2: start=-4");
        int start1 = range1.getStart();
        int start2 = range2.getStart();
        System.out.println("range1: start=" + start1 + "; range2: start=" + start2);

        System.out.println("Test Range getEnd()");

        System.out.println("range1: end=31; range2: end=3");
        int end1 = range1.getEnd();
        int end2 = range2.getEnd();
        System.out.println("range1: end=" + end1 + "; range2: end=" + end2);

        System.out.println("Test Range getMncSignStr()");

        System.out.println("range1: signStr=unsigned; range2: signStr=signed");
        String signStr1 = range1.getMncSignStr();
        String signStr2 = range2.getMncSignStr();
        System.out.println("range1: signStr=" + signStr1 + "; range2: signStr=" + signStr2);

    }

}
