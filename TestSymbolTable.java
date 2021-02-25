public class TestSymbolTable {

    public static void main(String[] args) {

        SymbolTable symbolTable = new SymbolTable();

        System.out.println("Test SymbolTable");

        System.out.println("Name: pop, Opcode: 01; Name: halt, Opcode: 00");
        Mnemonic mne1 = symbolTable.get("pop");
        Mnemonic mne2 = symbolTable.get("halt");
        System.out.println(mne1 + "; " + mne2);

    }
}
