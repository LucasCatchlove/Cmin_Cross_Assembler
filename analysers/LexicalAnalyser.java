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
    private int semiColon = 59;

    private int lineCounter = 0;
    private int columnCounter = 0;
    private int tokenColumn = 0;
    private boolean isEOFReached = false;

    //It can consist of:
    //Label(Optional), Mnemonic, null, Comment(Optional)
    //Label(Optional), Mnemonic, Operand, Comment(Optional)
    //Therefore, max of 4 places
    private Token[] tokenLine;

    private FileReader reader;

    public Token[] getTokenLine() {
        return tokenLine;
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
        this.tokenLine = new Token[4]; //Max of 4 places
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

        //Loop until EOF or when break
        while((i= reader.getNextFin()) != EOF) {

            boolean isComment = false;
            columnCounter = 0;
            lineCounter++;
            //Empty the tokenLine
            tokenLine = new Token[4];

            while((i= reader.getNextFin()) != EOL) {

                columnCounter++;

                //We need the spaces of comment sections
                if (i == semiColon || isComment) {
                    sbToken.append((char)i);
                    isComment = true;
                    continue;
                }

                //If we get to a space, we need to generate the token
                if(i == spaces || i == carriageReturn) {

                    //To avoid multiple spaces and then sending an empty sbToken

                    //>=3 means that it is a mnemonic type
                    if (sbToken.length() >= 3) {

                        tokenLine[1] = generateToken(sbToken, TypeToken.Mnemonic); //Generate a token using the sbToken
//                      parser.createLineStatement(token);
                        sbToken.setLength(0); //Restart the sbToken

                    } else if (sbToken.length() >= 1) { //Means that it is the operand

                        tokenLine[2] = generateToken(sbToken, TypeToken.Operand); //Generate a token using the sbToken
                        sbToken.setLength(0); //Restart the sbToken
                    }

                    continue;

                }

                sbToken.append((char)i);

            }

            //Send generate a comment token
            //Always at tokenLine[7]
            if (isComment) {
                tokenLine[3] = generateToken(sbToken, TypeToken.Comment); //Generate a token using the sbToken
                sbToken.setLength(0);
                isComment = false;
            }

            //In order to save the tokenLine and use it in the Syntax
            //The Syntax will continue the traverseFile()
            break;

        }

        //Check if EOF is reached
        //This will allow the Syntax to stop colling traverseFile()
        if (i == EOF)
            isEOFReached = true;

    }

    /**
     * Generates a components.Token object from the sbToken collected
     * @param sbToken
     * @return
     */
    Token generateToken(StringBuilder sbToken, TypeToken typeToken) {

        switch (typeToken) {

            case Label:
                //return label token
                //No need to get Label for this sprint
                //but for next sprint, to differentiate between a Mnemonic and Label, use enters mnemonic> to symboltable
                break;

            case Mnemonic:
                return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic);

            case Operand:
                return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Operand);

            case Comment:
                return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Comment);
        }
        return null;
    }

}

