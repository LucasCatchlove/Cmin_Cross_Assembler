package interfaces;

import components.MnemonicType;

/**
 * interface for the listing class
 */
public interface IMnemonic {


    String getMnemonicName();

    int getOpCode();

    public MnemonicType getType();

}
