package tests;

import analysers.FileReader;

public class TestFileReader {

    public static void main(String[] args) {

        FileReader fr = new FileReader("TestLexicalAnalyser.txt");

        System.out.println("Test analysers.FileReader getFileName");
        System.out.println("TestLexicalAnalyser.txt");
        System.out.println(fr.getFileName());

    }

}
