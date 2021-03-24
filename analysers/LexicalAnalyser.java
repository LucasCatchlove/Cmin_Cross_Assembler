package analysers;

import errorReporters.IErrorReporter;
import interfaces.IFileReader;
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

    private int lineCounter = 1;
    private int columnCounter = 0;
    private int tokenColumn = 0;
    private boolean isEOFReached = false;

    private IFileReader reader;
    private IErrorReporter errRep;

    /**
     * parametrized constructor that also opens the .asm for tokenizing
     * @param reader
     */
    public LexicalAnalyser(IFileReader reader, IErrorReporter errRep) {
        this.reader = reader;
        this.errRep = errRep;
    }

    public Token scan() {

        int i;
        StringBuilder sbToken = new StringBuilder();
        boolean isComment = false;

        tokenColumn++;

        while ((i = reader.getNextFin()) != EOF) {

            while(i != EOL) {

                //Starting the Comment part
                if ((char)i == ';')
                    isComment = true;

                //Skip the spaces for all except for the Comment part
                if ((i != spaces && i != carriageReturn) || isComment ) {
                    sbToken.append((char)i);
                } else if (sbToken.length() < 1) {
                    //To skip empty spaces
                    return scan();
                } else {
                    return generateToken(sbToken);
                }
                i = reader.getNextFin();
            }

            return new Token(new Position(lineCounter++,tokenColumn), sbToken.toString(), TypeToken.EOL);
        }

        return new Token(new Position(lineCounter,tokenColumn), "", TypeToken.EOF);
    }

    /**
     * Traverse the file byte by byte and collect them in a StringBuilder;
     * when a space or EOL is reached, the StringBuilder is sent to the generateToken() then the token is sent to createLineStatement
     * @throws IOException
     */
//    public void traverseFile() {
//
//        int i = 0;
//        StringBuilder sbToken = new StringBuilder();
//
//        //Loop until EOF or when break
//        while((i= reader.getNextFin()) != EOF) {
//
//            boolean isComment = false;
//            columnCounter = 0;
//            lineCounter++;
//            //Empty the tokenLine
//            tokenLine = new Token[4];
//
//            do {
//
//                columnCounter++;
//
//                //We need the spaces of comment sections
//                if (i == semiColon || isComment) {
//                    sbToken.append((char)i);
//                    isComment = true;
//                    continue;
//                }
//
//                //If we get to a space, we need to generate the token
//                if(i == spaces || i == carriageReturn) {
//
//                    //To avoid multiple spaces and then sending an empty sbToken
//
//                    //>=3 means that it is a mnemonic type
//                    if (sbToken.length() >= 3) {
//
//                        tokenLine[1] = generateToken(sbToken, TypeToken.Mnemonic); //Generate a token using the sbToken
////                      parser.createLineStatement(token);
//                        sbToken.setLength(0); //Restart the sbToken
//
//                    } else if (sbToken.length() >= 1) { //Means that it is the operand
//
//                        tokenLine[2] = generateToken(sbToken, TypeToken.Operand); //Generate a token using the sbToken
//                        sbToken.setLength(0); //Restart the sbToken
//                    }
//
//                    continue;
//
//                }
//
//                sbToken.append((char)i);
//
//            } while((i= reader.getNextFin()) != EOL);
//
//            //Send generate a comment token
//            //Always at tokenLine[7]
//            if (isComment) {
//                tokenLine[3] = generateToken(sbToken, TypeToken.Comment); //Generate a token using the sbToken
//                sbToken.setLength(0);
//                isComment = false;
//            }
//
//            //In order to save the tokenLine and use it in the Syntax
//            //The Syntax will continue the traverseFile()
//            break;
//
//        }
//
//        //Check if EOF is reached
//        //This will allow the Syntax to stop colling traverseFile()
//        if (i == EOF)
//            isEOFReached = true;
//
//    }

    /**
     * Generates a components.Token object from the sbToken collected
     * @param sbToken
     * @return
     */
    Token generateToken(StringBuilder sbToken) {

        if(sbToken.toString().equals(".cstring"))
            return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Directive);

        if (tokenColumn == 1) {
            //label
            return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Label);
        } else if (tokenColumn >= 1) {
            //>=3 means that it is a mnemonic type
            if (sbToken.length() >= 3) {

                return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic); //Generate a token using the sbToken

            } else if (sbToken.length() >= 1) { //Means that it is the operand

                return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Operand); //Generate a token using the sbToken
            }
        } else if (sbToken.charAt(0) == ';') {
            //Comment section
            return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Comment);
        }

        return null;
    }

}

