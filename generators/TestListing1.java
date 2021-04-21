
package generators;


import components.*;
import errorReporters.IErrorReporter;
import interfaces.IInstruction;
import interfaces.ISymbolTable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;



public class TestListing1 {
    public static void main(String[] args) throws IOException {

        IR ir1 = new IR();
        IR ir2 = new IR();
        Listing l1 = new Listing(ir1);
        Listing l2 = new Listing(ir2);
        Mnemonic m1 = new Mnemonic("halt", 0x00, MnemonicType.Inherent);
        Mnemonic m2 = new Mnemonic("pop", 0x01, MnemonicType.Inherent);
        Instruction ins1 = new Instruction(m1, "");
        Instruction ins2 = new Instruction(m2, "");
        LineStatement ls1 = new LineStatement(0, null, null, ins1, "");
        LineStatement ls2 = new LineStatement(0, null, null, ins2, "");

        ir1.addLineStatement(ls1);
        ir1.addLineStatement(ls2);

        CodeGenerator cg1 = new CodeGenerator(ir1 ,null, null, null, null);
        CodeGenerator cg2 = new CodeGenerator(ir1 ,null, null, null, null);



        System.out.println("Test Listing getLineNumber");
        System.out.println("3 2");
        System.out.println(l1.getLineNumber() + " " + l2.getLineNumber());


        System.out.println("Test writeListingFile & openOutputStream");
        System.out.println("true");

        l1.openOutputStream();
        l2.openOutputStream();
        Path path1 = FileSystems.getDefault().getPath("Sprint Listing/Listing.lst");
        byte[] f1 = Files.readAllBytes(path1);
        Path path2 = FileSystems.getDefault().getPath("Sprint Listing/ListingTestFile.lst");
        byte[] f2 = Files.readAllBytes(path2);
        System.out.println(Arrays.equals(f2, f1));



    }
}

