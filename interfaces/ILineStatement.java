package interfaces;

import components.Label;

public interface ILineStatement {

    int getAddress();

    Label getLabel();

    IInstruction getInstruction();

    String getDirective();

    String getStringOperand();

    String getComment();


}
