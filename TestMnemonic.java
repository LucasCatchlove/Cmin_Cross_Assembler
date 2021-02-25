public class TestMnemonic {

    public static void main(String[] args) {

        Mnemonic mne = new Mnemonic("or", 0x0E);

        System.out.println("Test Mnemonic getMnemonicName");

        System.out.println("or");
        System.out.println(mne.getMnemonicName());

        System.out.println("Test Mnemonic getOpCode");
        System.out.println("0E");
        System.out.println(String.format("%02X", mne.getOpCode()));

    }

}
