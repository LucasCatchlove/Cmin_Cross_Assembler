import java.io.FileInputStream;
import java.io.IOException;


public class LexicalAnalyser implements InterfaceLexicalAnalyser{

    private int EOL = 10;
    private int EOF = -1;
    private int spaces = 32;
    private int lineCounter = 1;
    private int columnCounter = 0;
    private int tokenColumn = 0;
    private SyntaxAnalyser parser;

    public LexicalAnalyser(FileReader reader, SyntaxAnalyser parser){
        this.parser = parser;
        this.openStream(reader);
    }

    private void openStream(FileReader file) {

        FileInputStream fin = null;

        try {
            fin = new FileInputStream(file.getFileName());
            traverseFile(fin);
            fin.close();
        } catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }

    }

    private void traverseFile(FileInputStream fin) throws IOException {

        int i = 0;
        StringBuilder sbToken = new StringBuilder();

        while((i=fin.read())!=EOF){

            columnCounter++;

            if(i==EOL || i==spaces) {

                if(sbToken.length()>=1) {
                    Token token = generateToken(sbToken);
                    parser.createLineStatement(token);
                        /*
                        System.out.println(token.getName());
                        System.out.println(token.getPosition().getLineCounter());
                        System.out.println(token.getPosition().getColumnCounter());
                        System.out.println(token.getCode());
                        //System.out.println(sbToken.toString());

                         */
                    sbToken.setLength(0);
                }

                if(i==EOL ){
                    lineCounter++;
                    columnCounter = 0;
                }

            } else {
                sbToken.append((char)i);
                if(sbToken.length() == 1)
                    tokenColumn = columnCounter;
            }
        }

    }

    private Token generateToken(StringBuilder sbToken){
        return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic);
    }

/*
    public static void main(String[] args) {
        LexicalAnalyser test = new LexicalAnalyser();
        test.traverseFile();

    }

 */

}

