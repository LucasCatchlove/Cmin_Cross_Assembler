import java.util.ArrayList;

public class SyntaxAnalyser {
    /*
    the IR class creates the ArrayList
    private ArrayList<LineStatement> IR = new ArrayList<>();
     */

    private IR intRep;

    private String[] lineStatementElements;

    private SyntaxAnalyser() {
        intRep = new IR();
    }

    /* no longer needed as the creation of the IR is taken care of in the constructor

    public void createIR() {
    }

     */

    public LineStatement createLineStatement() {

    }

    public void updateIR() {

    }

    public void parseToken() { //returns what?

    }


}