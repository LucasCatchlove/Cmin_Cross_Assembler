package interfaces;

import components.Instruction;

public interface ILineStatement {

    String getLabel();

    IInstruction getInstruction();

    String getDirective();

    String getComment();


}
