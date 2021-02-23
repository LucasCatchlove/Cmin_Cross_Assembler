public class Token implements InterfaceToken{

    private Position  pos;
    private TokenType code;
    private String    name;

    Token(Position pos, String name, TokenType code) {
        this.pos = pos;
        this.name = name;
        this.code = code;
    }


    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TokenType getCode() {
        return code;
    }
}
