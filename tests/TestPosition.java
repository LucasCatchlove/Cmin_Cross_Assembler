package tests;

import components.Position;

public class TestPosition {

    public static void main(String[] args) {

        Position pos = new Position(2, 1);

        System.out.println("Test components.Position getLineCounter");
        System.out.println("2");
        System.out.println(pos.getLineCounter());

        System.out.println("Test components.Position getColumnCounter");
        System.out.println("1");
        System.out.println(pos.getColumnCounter());

    }
}
