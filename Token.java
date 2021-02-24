public class Token implements InterfaceToken{

    private Position  pos;
    private TypeToken type;
    private String    name;

    Token(Position pos, String name, TypeToken type) {
        this.pos = pos;
        this.name = name;
        this.type = type;
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
    public TypeToken getType() {
        return type;
    }
}
