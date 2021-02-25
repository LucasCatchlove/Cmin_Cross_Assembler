package components;

import interfaces.ILineStatement;

public class LineStatement implements ILineStatement {

    private String label;
    private Instruction instruction;
    private String directive;
    private String comment;

    /**
     * For Sprint 2 usage: Creates components.LineStatement with a directive
     * @param label
     * @param directive
     * @param comment
     */
    public LineStatement(String label, String directive, String comment) {
        this.label = label;
        this.directive = directive;
        this.comment = comment;
    }

    /**
     * Creates components.LineStatement with an components.Instruction
     * @param label
     * @param instruction
     * @param comment
     */
    public LineStatement(String label, Instruction instruction, String comment) {
        this.label = label;
        this.instruction = instruction;
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public String getDirective() {
        return directive;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return "["+getInstruction().getMnemonic().getMnemonicName() + " " + String.format("%02X",getInstruction().getMnemonic().getOpCode())+"]";
    }

}