package interfaces;

import components.Position;
import components.TypeToken;

public interface IToken {

    Position getPosition();
    String getName();
    TypeToken getType();

}
