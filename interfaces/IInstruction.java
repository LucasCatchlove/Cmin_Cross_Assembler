package interfaces;

import components.Mnemonic;

public interface IInstruction {

    public Mnemonic getMnemonic();

    public String getOperand();
}
