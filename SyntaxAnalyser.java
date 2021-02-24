public class SyntaxAnalyser implements  ISyntaxAnalyser {

    /*
    the IR class creates the ArrayList
    private ArrayList<LineStatement> IR = new ArrayList<>();
     */

    private IR intRep;
    private SymbolTable symbolTable;
    private LineStatement lineStatement;

    /**
     * parametrized constructor
     * @param symbolTable
     */
    public SyntaxAnalyser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        intRep = new IR();
    }

    public IR getIntRep() {
        return intRep;
    }


    //tokenLine = [Label, Mnemonic or Directive, Comment] //For other sprints

    /**
     * Used by the Lexical Analyser to send the token/identifier
     * @param token
     */
    public void createLineStatement(Token token) {

        //if tokenLine[1] is an Instruction
        if (token.getType() == TypeToken.Mnemonic) {
            lineStatement = new LineStatement("", new Instruction(parseToken(token.getName()), ""), "");
        }
        //else tokenLine[1] is a Directive
        updateIR();

    }

    /**
     * adds line statement to IR
     */
    private void updateIR() {
        intRep.addLineStatement(lineStatement);
    }

    /**
     * returns Mnemonic object from the symbol table
     * @param token
     * @return
     */
    private Mnemonic parseToken(String token) {
       return symbolTable.get(token); //hashtable or SymbolTable
    }

}