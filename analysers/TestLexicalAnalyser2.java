package analysers;

import components.IR;
import components.SymbolTable;

import java.io.File;
import java.io.FileOutputStream;

public class TestLexicalAnalyser2 {

    public static void main(String[] args) {

        File file = new File("TestLexicalAnalyser.txt");

        FileOutputStream writer = null;
        try {
           writer = new FileOutputStream(file, false);
           writer.write("xor\n\r    \r rem \r\n".getBytes());
           writer.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        SyntaxAnalyser parser = new SyntaxAnalyser(new SymbolTable());
        LexicalAnalyser lexicalAnalyser = new LexicalAnalyser(new FileReader(file.getName()), parser);

        System.out.println("Test Lexical Analyser openStream and traverseFile");
        System.out.println("[xor 0F], [rem 17]");
        lexicalAnalyser.openStream(new FileReader(file.getName()));
        IR ir = parser.getIntRep();
        System.out.println(ir.getLineStatement(0) + ", " + ir.getLineStatement(1));


    }

}
