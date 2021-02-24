import java.io.File;

class FileReader implements IFileReader {

    private File file;

    public FileReader(String srcFile) {
        file = new File(srcFile);
    }

    public File getFileName(){
        return file;
    }

}

