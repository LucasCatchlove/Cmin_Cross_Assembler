package interfaces;

import components.Mnemonic;

public interface IInstruction {

    public IMnemonic getMnemonic();

    public String getOperand();
}
