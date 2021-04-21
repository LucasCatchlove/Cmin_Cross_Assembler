package interfaces;

import components.Label;

/**interface for the line statement class
 *
 */
public interface ILineStatement {

    void addMachineCode(byte machineCode);

    int getAddress();

    byte getMachineCode(int index);

    int machineCodeSize();

    Label getLabel();

    IInstruction getInstruction();

    String getDirective();

    String getStringOperand();

    boolean offsetIsResolved();

    boolean hadForwardBranch();

    String getComment();


}
