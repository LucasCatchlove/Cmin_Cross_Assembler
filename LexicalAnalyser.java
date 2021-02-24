import java.io.FileInputStream;


public class LexicalAnalyser {

    int EOL = 10;
    int EOF = -1;
    int spaces = 32;
    int lineCounter = 1;
    int columnCounter = 0;
    int tokenColumn = 0;
    SyntaxAnalyser parser;

    public LexicalAnalyser(FileReader reader, SyntaxAnalyser parser){
        this.parser = parser;
        this.traverseFile(reader);
    }

    public int getEOL() {
        return EOL;
    }

    public int getEOF() {
        return EOF;
    }

    private Token generateToken(StringBuilder sbToken){
       return new Token(new Position(lineCounter,tokenColumn), sbToken.toString(), TypeToken.Mnemonic);
    }

    void traverseFile(FileReader file){
        try{
            FileInputStream fin=new FileInputStream(file.getFileName());
            int i=0;
            StringBuilder sbToken = new StringBuilder();
            while((i=fin.read())!=EOF){
                if(i==EOL || i==spaces){
                    columnCounter++;
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
                    sbToken.setLength(0);}
                    if(i==EOL ){
                        lineCounter++;
                        columnCounter = 0;}
                }
                else{
                    columnCounter++;
                sbToken.append((char)i);
                    if(sbToken.length() == 1)
                        tokenColumn = columnCounter;
                }
            }
            fin.close();
        }catch(Exception e){System.out.println(e);}
    }

/*
    public static void main(String[] args) {
        LexicalAnalyser test = new LexicalAnalyser();
        test.traverseFile();

    }

 */

}

