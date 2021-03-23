package interfaces;

import components.MnemonicType;

public interface IMnemonic {


    String getMnemonicName();

    int getOpCode();

    public MnemonicType getType();

}
