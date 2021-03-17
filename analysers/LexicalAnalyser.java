package analysers;

import interfaces.ILexicalAnalyser;
import components.Token;
import components.Position;
import components.TypeToken;

import java.io.IOException;

/**
 * Reads the source file character by character and generates tokens for the parser
 */
public class LexicalAnalyser implements ILexicalAnalyser {

    private int EOL = 10;
    private int EOF = -1;
    private int spaces = 32;
    private int carriageReturn = 13;
    private int lineCounter = 1;
    private int columnCounter = 0;
    private int tokenColumn = 0;
    private boolean isEOFReached = false;

    private FileReader reader;
    private Token token;

    public Token getToken() {
        return token;
    }

    public boolean isEOFReached() {
        return isEOFReached;
    }

    /**
     * parametrized constructor that also opens the .asm for tokenizing
     * @param reader
     */
    public LexicalAnalyser(FileReader reader) {
        this.reader = reader;
        try {
            traverseFile();
        } catch(Exception e){
        System.err.println(e);
        System.exit(1);
        }
    }

    /**
     * Traverse the file byte by byte and collect them in a StringBuilder;
     * when a space or EOL is reached, the StringBuilder is sent to the generateToken() then the token is sent to createLineStatement
     * @throws IOException
     */
    public void traverseFile() throws IOException {

        int i = 0;
        StringBuilder sbToken = new StringBuilder();
        while((i= reader.getNextFin()) != EOF) {

            columnCounter++;

            if(i == EOL || i == spaces || i == carriageReturn) {

                if(sbToken.length() >= 1) {
                    token = generateToken(sbToken);
//                    parser.createLineStatement(token);
                    sbToken.setLength(0);
                    break;
                }

                if(i == EOL){
                    lineCounter++;
                    columnCounter = 0;
                }

                continue;

            }

            sbToken.append((char)i);
            if(sbToken.length() == 1)
                tokenColumn = columnCounter;

        }

        if (i == -1) {
            isEOFReached = true;
        }

    }

    /**
     * Generates a components.Token object from the sbToken collected
     * @param sbToken
     * @return
     */
    Token generateToken(StringBuilder sbToken) {
        return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic);
    }

}

