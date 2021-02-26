package tests;

import components.Position;

public class TestPosition {

    public static void main(String[] args) {

        Position pos1 = new Position(2, 1);
        Position pos2 = new Position(3, 8);
        Position pos3 = new Position(9, 3);

        System.out.println("Test components.Position getLineCounter");
        System.out.println("2 3 9");
        System.out.print(pos1.getLineCounter()+" ");
        System.out.print(pos2.getLineCounter()+" ");
        System.out.println(pos3.getLineCounter());

        System.out.println("Test components.Position getColumnCounter");
        System.out.println("1 8 3");
        System.out.print(pos1.getColumnCounter()+" ");
        System.out.print(pos2.getColumnCounter()+" ");
        System.out.println(pos3.getColumnCounter());

    }
}
