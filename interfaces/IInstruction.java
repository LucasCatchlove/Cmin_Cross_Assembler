package interfaces;

import components.Label;

/**
 * interface for the instruction class
 */
public interface IInstruction {

    public IMnemonic getMnemonic();

    public String getOperandOffset();

    public Label getOperandLabel();
}
