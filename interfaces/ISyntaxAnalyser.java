package interfaces;

import components.IR;
import components.Token;

public interface ISyntaxAnalyser {

    IR getIntRep();
    void createLineStatement(Token token);

}
