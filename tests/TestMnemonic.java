package tests;

import components.Mnemonic;
import components.MnemonicType;

public class TestMnemonic {

    public static void main(String[] args) {


        Mnemonic mne1 = new Mnemonic("or", 0x0E, MnemonicType.Inherent);
        Mnemonic mne2 = new Mnemonic("enter.u5", 0x1C, MnemonicType.Immediate);
        Mnemonic mne3 = new Mnemonic("tle", 0x1E, MnemonicType.Inherent);
        System.out.println("Test components.Mnemonic getMnemonicName");

        System.out.println("or enter.u5 tle");
        System.out.print(mne1.getMnemonicName()+" ");
        System.out.print(mne2.getMnemonicName()+" ");
        System.out.println(mne3.getMnemonicName());

        System.out.println("Test components.Mnemonic getOpCode");
        System.out.println("0E 1C 1E");
        System.out.print(String.format("%02X", mne1.getOpCode())+" ");
        System.out.print(String.format("%02X", mne2.getOpCode())+" ");
        System.out.println(String.format("%02X", mne3.getOpCode()));

        System.out.println("Test components.Mnemonic getType");

        System.out.println("Inherent Immediate Inherent");
        System.out.print(mne1.getType()+" ");
        System.out.print(mne2.getType()+" ");
        System.out.println(mne3.getType());


    }

}
