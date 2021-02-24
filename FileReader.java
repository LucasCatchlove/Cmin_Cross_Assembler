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