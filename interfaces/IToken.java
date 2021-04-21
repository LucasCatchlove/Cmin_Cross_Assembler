package interfaces;

import components.Position;
import components.TypeToken;

/**
 * interface for token class
 */
public interface IToken {

    Position getPosition();
    String getName();
    TypeToken getType();

}
