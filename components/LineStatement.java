package components;

import interfaces.IInstruction;
import interfaces.ILineStatement;

/**
 * parsed representation of the source code for convenient use by the code generator
 */
public class LineStatement implements ILineStatement {

    private int address;
    private Label label;
    private IInstruction instruction;
    private String directive;
    private String comment;

    /**
     * Creates components.LineStatement with an components.Instruction
     *
     * @param label
     * @param instruction
     * @param comment
     */
    public LineStatement(int address, Label label, IInstruction instruction, String comment) {
        this.address = address;
        this.label = label;
        this.instruction = instruction;
        this.comment = comment;
    }

    public LineStatement(int address, Label label, String directive, String comment) {
        this.address = address;
        this.label = label;
        this.directive = directive;
        this.comment = comment;
    }

    public int getAddress() {
        return address;
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

    public String getComment() {
        return comment;
    }

    public String toString() {
        if(instruction == null)
        return getDirective() + " " + getComment();
        else
           return  getInstruction().getMnemonic().getMnemonicName() + " " + getInstruction().getOperand() + " " + getComment();
    }
}