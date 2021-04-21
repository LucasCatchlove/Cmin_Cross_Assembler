package components;

import interfaces.IInstruction;
import interfaces.ILineStatement;
import interfaces.IMnemonic;

import java.util.ArrayList;

/**
 * parsed representation of the source code for convenient use by the code generator
 */
public class LineStatement implements ILineStatement {

    private int address;
    private ArrayList<Byte> machineCode;
    private Label label;
    private IInstruction instruction;
    private String directive;
    private String stringOperand;
    private String comment;
    private boolean forwardBranch;

    /**
     * Creates components.LineStatement with an components.Instruction
     *
     * @param label
     * @param instruction
     * @param comment
     */
    public LineStatement(int address, ArrayList<Byte> machineCode, Label label, IInstruction instruction, String comment) {
        this.address = address;

        if (machineCode == null)
            this.machineCode = new ArrayList<>();
        else
            this.machineCode = machineCode;

        this.label = label;
        this.instruction = instruction;
        this.comment = comment;
        this.forwardBranch = !offsetIsResolved();
    }

    public LineStatement(int address, ArrayList<Byte> machineCode, Label label, String directive, String stringOperand, String comment) {
        this.address = address;
        this.machineCode = machineCode;
        this.label = label;
        this.directive = directive;
        this.stringOperand = stringOperand;
        this.comment = comment;
        this.forwardBranch = !offsetIsResolved();
    }

    public void addMachineCode(byte machineCode) {
        this.machineCode.add(machineCode);
    }

    public int getAddress() {
        return address;
    }

    public byte getMachineCode(int index) {
        return machineCode.get(index);
    }

    public int machineCodeSize() {
        return machineCode.size();
    }

    public Label getLabel() {
        return label;
    }

    public IInstruction getInstruction() {
        return instruction;
    }

    public String getDirective() {
        return directive;
    }

    public String getStringOperand() {
        return stringOperand;
    }

    public String getComment() {
        return comment;
    }

    /**
     * checks if offset was resolved
     * @return
     */
    public boolean offsetIsResolved() {

        if (this.getInstruction() == null)
            return true;

        if (this.getInstruction().getMnemonic() == null || this.getInstruction().getMnemonic().getType() != MnemonicType.RelativeLabel)
            return true;

        if (this.machineCodeSize() != 1)
            return true;

        return false;

    }

    /**checks of forward branch exists
     *
     * @return
     */
    public boolean hadForwardBranch(){return forwardBranch;}

    public String toString() {
        if(instruction == null)
        return getDirective() + " " + getComment();
        else
           return  getInstruction().getMnemonic().getMnemonicName() + " " + getInstruction().getOperandOffset() + " " + getComment();
    }
}