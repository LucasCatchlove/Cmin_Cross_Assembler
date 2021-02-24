import java.io.File;

class FileReader{

    File file;

    public FileReader(String srcFile) {
        file = new File(srcFile);
    }

    File getFileName(){
        return file;
    }

}

