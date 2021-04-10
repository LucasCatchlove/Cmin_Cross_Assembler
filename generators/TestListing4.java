//package generators;
//
//import components.*;
//
//public class TestListing4 {
//    public static void main(String[] args) {
//
//
//        IR ir1 = new IR();
//        Listing l1 = new Listing(ir1);
//        Mnemonic m1 = new Mnemonic("halt", 0x00, MnemonicType.Inherent);
//        Mnemonic m2 = new Mnemonic("pop", 0x01, MnemonicType.Inherent);
//        Mnemonic m3 = new Mnemonic("enter.u5", 0x70, MnemonicType.Immediate);
//        Mnemonic m4 = new Mnemonic("ldc.i3", 0x90, MnemonicType.Immediate);
//        Instruction ins1 = new Instruction(m1, "");
//        Instruction ins2 = new Instruction(m2, "");
//        Instruction ins3 = new Instruction(m3, "");
//        Instruction ins4 = new Instruction(m4, "");
//        LineStatement ls1 = new LineStatement("", ins1, "; a comment");
//        LineStatement ls2 = new LineStatement("", ins2, "; a comment");
//        LineStatement ls3 = new LineStatement("", ins3, "; a comment");
//        LineStatement ls4 = new LineStatement("", ins4, "; a comment");
//
//        System.out.println("Test separateLineStatement (1)");
//        System.out.println("00halt; a comment");
//        String[] testStr1 = l1.separateLineStatement(ls1);
//        for (String s : testStr1) System.out.print(s);
//        System.out.println();
//
//        System.out.println("Test separateLineStatement (2)");
//        System.out.println("01pop; a comment");
//        String[] testStr2 = l1.separateLineStatement(ls2);
//        for (String s : testStr2) System.out.print(s);
//        System.out.println();
//
//        System.out.println("Test separateLineStatement (3)");
//        System.out.println("70enter.u5; a comment");
//        String[] testStr3 = l1.separateLineStatement(ls3);
//        for (String s : testStr3) System.out.print(s);
//        System.out.println();
//
//        System.out.println("Test separateLineStatement (4)");
//        System.out.println("90ldc.i3; a comment");
//        String[] testStr4 = l1.separateLineStatement(ls4);
//        for (String s : testStr4) System.out.print(s);
//        System.out.println();
//
//
//
//    }
//}
