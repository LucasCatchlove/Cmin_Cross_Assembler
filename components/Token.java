package components;

import interfaces.InterfaceToken;

public class Token implements InterfaceToken {

    private Position  pos;
    private TypeToken type;
    private String    name;

    public Token(Position pos, String name, TypeToken type) {
        this.pos = pos;
        this.name = name;
        this.type = type;
    }

    public Position getPosition() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public TypeToken getType() {
        return type;
    }

}
