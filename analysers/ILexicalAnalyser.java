package analysers;

import components.Token;

public interface ILexicalAnalyser {

    public Token scan();
    public Token generateToken(StringBuilder sbToken);

}
