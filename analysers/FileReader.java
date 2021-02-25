package analysers;

import interfaces.IFileReader;
import java.io.File;

/**
 * Creates a file object for the Lexer
 */
public class FileReader implements IFileReader {

    private File file;

    /**parametrized constructor
     * @param srcFile
     */
    public FileReader(String srcFile) {
        file = new File(srcFile);
    }

    public File getFileName() {
        return file;
    }

}

