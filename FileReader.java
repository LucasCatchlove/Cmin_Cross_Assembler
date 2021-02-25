import java.io.File;

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

