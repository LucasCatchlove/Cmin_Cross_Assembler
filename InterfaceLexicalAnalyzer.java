public interface InterfaceLexicalAnalyzer {

    String getEOL();

    int getEOF();

    void getToken();

    void traverseFile();

    void generateToken();

    void getMnemonic();


}