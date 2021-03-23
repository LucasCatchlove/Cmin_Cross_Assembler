package components;

import interfaces.ILineStatement;

/**
 * parsed representation of the source code for convenient use by the code generator
 */
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
//    public LineStatement(String label, String directive, String comment) {
//        this.label = label;
//        this.directive = directive;
//        this.comment = comment;
//    }

    public LineStatement() {
        this.label = null;
        this.instruction = null;
        this.comment = null;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

//    public void setDirective(String directive) {
//        this.directive = directive;
//    }

    public void setComment(String comment) {
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