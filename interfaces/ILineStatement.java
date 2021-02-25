package interfaces;

import components.Instruction;

public interface ILineStatement {

    String getLabel();

    Instruction getInstruction();

    String getDirective();

    String getComment();


}
