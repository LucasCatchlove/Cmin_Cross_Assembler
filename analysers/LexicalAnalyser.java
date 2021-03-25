package analysers;


import errorReporters.ErrorMsg;
import errorReporters.IErrorReporter;
import interfaces.IFileReader;
import components.Token;
import components.Position;
import components.TypeToken;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

/**
 * Reads the source file character by character and generates tokens for the parser
 */
public class LexicalAnalyser implements ILexicalAnalyser {

    List<Integer> invalidChars = Arrays.asList(0,1,2,3,4,5,6,7,8,9,11,12,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,33,35,36,37,38,39,40,41,42,43,44,47,58,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126);
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
        boolean isDirective = false;

        tokenColumn++;

        while ((i = reader.getNextFin()) != EOF) {

            while(i != EOL && i != EOF) {

                if(!isComment && invalidChars.contains(i)) {
                    errRep.recordError(new ErrorMsg("invalid character", new Position(lineCounter, tokenColumn)));
                    i = reader.getNextFin();
                    continue;
                }


                //Starting the Comment part
                if ((char)i == ';')
                    isComment = true;
                if((char)i == '"') {
                    isDirective = !isDirective;
                    i = reader.getNextFin();
                    continue;
                }


                //Skip the spaces for all except for the Comment part
                if ((i != spaces && i != carriageReturn) || isComment || isDirective) {
                    sbToken.append((char)i);
                } else if (sbToken.length() < 1) {
                    //To skip empty spaces
                    return scan();
                } else {
                    return generateToken(sbToken);
                }
                i = reader.getNextFin();
            }
            if(i == EOL) {
                if (isDirective)
                    errRep.recordError(new ErrorMsg("EOL in string", new Position(lineCounter, tokenColumn)));

                return new Token(new Position(lineCounter++,tokenColumn), sbToken.toString(), TypeToken.EOL);
            }


        }

        if(isDirective && i == EOF)
            errRep.recordError(new ErrorMsg("EOF in string", new Position(lineCounter, tokenColumn)));

        return new Token(new Position(lineCounter,tokenColumn), "", TypeToken.EOF);
    }

    /**
     * Generates a components.Token object from the sbToken collected
     * @param sbToken
     * @return
     */
    public Token generateToken(StringBuilder sbToken) {

        if(sbToken.toString().equals(".cstring"))
            return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Directive);

        if (tokenColumn == 1) {
            //label
            return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Label);
        } else if (tokenColumn >= 1) {
            //>=3 means that it is a mnemonic type
            if (isInteger(sbToken.toString())) { //Means that it is the operand

                return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Operand); //Generate a token using the sbToken
            }
            else if (sbToken.length() >= 3) {

                return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic); //Generate a token using the sbToken

            }
        } else if (sbToken.charAt(0) == ';') {
            //Comment section
            return new Token(new Position(lineCounter, tokenColumn), sbToken.toString(), TypeToken.Comment);
        }

        return null;
    }

    private boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    //helper method for testing
    public void setTokenColumn(int tokenColumn) {
        this.tokenColumn = tokenColumn;
    }
}

