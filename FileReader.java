<<<<<<< Updated upstream
import java.io.File;

class FileReader{

   private File file;

   public FileReader(String filename){
        file = filename;
   }

   public String getFileName(){
        return file;
    }

}
=======
import java.io.*;

class FileReader{

    File file;

    public FileReader(String srcFile) {
        file = new File(srcFile);
    }

    File getFileName(){
        return file;
    }

}
>>>>>>> Stashed changes
