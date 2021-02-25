package tests;

import components.Token;
import components.Position;
import components.TypeToken;

public class TestToken {

    public static void main(String[] args) {

        Token token = new Token(new Position(5,2), "div", TypeToken.Mnemonic);

        System.out.println("Test components.Token getPosition");
        System.out.println("(5, 2)");
        System.out.println(token.getPosition());

        System.out.println("Test components.Token getName");
        System.out.println("div");
        System.out.println(token.getName());

        System.out.println("Test components.Token getType");
        System.out.println("components.Mnemonic");
        System.out.println(token.getType());

    }
}
