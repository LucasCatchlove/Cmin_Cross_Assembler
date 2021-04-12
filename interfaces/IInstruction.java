package interfaces;

import components.Label;

public interface IInstruction {

    public IMnemonic getMnemonic();

    public String getOperandOffset();

    public Label getOperandLabel();
}
