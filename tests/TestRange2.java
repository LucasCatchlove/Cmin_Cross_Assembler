
package tests;
import components.Range;


public class TestRange2 {

    public static void main(String[] args) {

        Range range1 = new Range('u', 5);
        Range range2 = new Range('i', 3);


        System.out.println("Test Range getEnd()");

        System.out.println("range1: end=31; range2: end=3");
        int end1 = range1.getEnd();
        int end2 = range2.getEnd();
        System.out.println("range1: end=" + end1 + "; range2: end=" + end2);


    }

}