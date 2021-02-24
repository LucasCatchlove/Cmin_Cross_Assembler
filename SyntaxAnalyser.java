import jdk.nashorn.internal.parser.TokenType;

public class SyntaxAnalyser implements  ISyntaxAnalyser {
    /*
    the IR class creates the ArrayList
    private ArrayList<LineStatement> IR = new ArrayList<>();
     */

    private IR intRep;
    private SymbolTable symbolTable;
    private LineStatement lineStatement;

    public SyntaxAnalyser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        intRep = new IR();
    }

    //Used by the Lexical Analyser to send the token/identifier
    //tokenLine = [Label, Mnemonic or Directive, Comment] //For other sprints
    public void createLineStatement(Token token) {

        //if tokenLine[1] is an Instruction
        if (token.getType() == TypeToken.Mnemonic) {
            lineStatement = new LineStatement("", new Instruction(parseToken(token.getName()), ""), "");
            //else tokenLine[1] is a Directive
            updateIR();
        }

    }

    private void updateIR() {
        intRep.addLineStatement(lineStatement);
    }

    private Mnemonic parseToken(String identifier) {
        return symbolTable.get(identifier.replaceAll("\\s+","")); //hashtable or SymbolTable
    }



public IR getIntRep() {
    return intRep;
}

}