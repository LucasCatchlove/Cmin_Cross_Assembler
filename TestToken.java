public class TestToken {

    public static void main(String[] args) {

        Token token = new Token(new Position(5,2), "div", TypeToken.Mnemonic);

        System.out.println("Test Token getPosition");
        System.out.println("(5, 2)");
        System.out.println(token.getPosition());

        System.out.println("Test Token getName");
        System.out.println("div");
        System.out.println(token.getName());

        System.out.println("Test Token getType");
        System.out.println("Mnemonic");
        System.out.println(token.getType());

    }
}
