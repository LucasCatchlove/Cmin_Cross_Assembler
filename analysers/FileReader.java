package analysers;

import interfaces.IFileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Creates a file object for the Lexer
 */
public class FileReader implements IFileReader {

    private File file;
    private FileInputStream fin;

    /**parametrized constructor
     * @param srcFile
     */
    public FileReader(String srcFile) {
        file = new File(srcFile);
        try {
            fin = new FileInputStream(file);
        } catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }

    public File getFileName() {
        return file;
    }

    /**
     * returns next byte from input stream
     * @return
     */
    public int getNextFin() {

        int nextByte = -1;

        try {
            nextByte = fin.read();
        } catch (Exception err) {
            System.err.println("Error: " + err);
        }

        return nextByte;
    }

}

