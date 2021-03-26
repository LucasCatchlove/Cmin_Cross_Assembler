
package tests;
import components.Range;



public class TestRange1 {

    public static void main(String[] args) {

        Range range1 = new Range('u', 5);
        Range range2 = new Range('i', 3);

        System.out.println("Test Range getStart()");

        System.out.println("range1: start=0; range2: start=-4");
        int start1 = range1.getStart();
        int start2 = range2.getStart();
        System.out.println("range1: start=" + start1 + "; range2: start=" + start2);


    }

}