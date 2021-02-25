package analysers;

import interfaces.ILexicalAnalyser;
import components.Token;
import components.Position;
import components.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;

public class LexicalAnalyser implements ILexicalAnalyser {

    private int EOL = 10;
    private int EOF = -1;
    private int spaces = 32;
    private int carriageReturn = 13;
    private int lineCounter = 1;
    private int columnCounter = 0;
    private int tokenColumn = 0;
    private SyntaxAnalyser parser;

    /**
     * parametrized constructor that also opens the .asm for tokenizing
     * @param reader
     * @param parser
     */
    public LexicalAnalyser(FileReader reader, SyntaxAnalyser parser) {
        this.parser = parser;
        this.openStream(reader);
    }

    /**
     * Opens the input stream and calls for the traverseFile()
     * @param file
     */
    private void openStream(FileReader file) {

        try {
            FileInputStream fin = new FileInputStream(file.getFileName());
            traverseFile(fin);
            fin.close();
        } catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }

    }

    /**
     * Traverse the file byte by byte and collect them in a StringBuilder;
     * when a space or EOL is reached, the StringBuilder is sent to the generateToken() then the token is sent to createLineStatement
     * @param fin
     * @throws IOException
     */
    private void traverseFile(FileInputStream fin) throws IOException {

        int i = 0;
        StringBuilder sbToken = new StringBuilder();

        while((i=fin.read()) != EOF) {

            columnCounter++;

            if(i == EOL || i == spaces || i == carriageReturn) {

                if(sbToken.length() >= 1) {
                    Token token = generateToken(sbToken);
                    parser.createLineStatement(token);
                    sbToken.setLength(0);
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

    }

    /**
     * Generates a components.Token object from the sbToken collected
     * @param sbToken
     * @return
     */
    private Token generateToken(StringBuilder sbToken) {
        return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic);
    }

}

