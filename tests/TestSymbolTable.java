package tests;

import components.Mnemonic;
import components.SymbolTable;

public class TestSymbolTable {

    public static void main(String[] args) {

        SymbolTable symbolTable = new SymbolTable();

        System.out.println("Test components.SymbolTable");

        System.out.println("Name: pop, Opcode: 01; Name: halt, Opcode: 00");
        Mnemonic mne1 = symbolTable.getMnemonic("pop");
        Mnemonic mne2 = symbolTable.getMnemonic("halt");
        System.out.println(mne1 + "; " + mne2);

    }
}
