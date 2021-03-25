package analysers;


import errorReporters.ErrorReporter;
import errorReporters.IErrorReporter;



public class TestLexicalAnalyser1 {
    public static void main(String[] args) {

        String srcFile = "Sprint Listing/TestImmediate.asm";
        FileReader r1 = new FileReader(srcFile);
        IErrorReporter er1 = new ErrorReporter(srcFile);
        ILexicalAnalyser l1 = new LexicalAnalyser(r1, er1);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb1.append("enter.u5");
        sb2.append("pop");
        sb3.append("\".cstring\"");

        System.out.println("Test generateToken");
        System.out.println("enter.u5 pop \".cstring\"");
        System.out.println("enter.u5 pop \".cstring\"");
      /*  System.out.print(l1.generateToken(sb1).getName()+ " ");
        System.out.print(l1.generateToken(sb2).getName()+ " ");
        System.out.println(l1.generateToken(sb3).getName()+" ");*/

    }
}
