package interfaces;

import components.Label;

public interface ILineStatement {

    void addMachineCode(byte machineCode);

    int getAddress();

    byte getMachineCode(int index);

    int machineCodeSize();

    Label getLabel();

    IInstruction getInstruction();

    String getDirective();

    String getStringOperand();

    String getComment();


}
