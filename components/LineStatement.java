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



    public LineStatement() {
        this.label = null;
        this.instruction = null;
        this.comment = null;
        this.directive = null;
    }

    /**
     * Creates components.LineStatement with an components.Instruction
     *
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

    public void setDirective(String directive) {
        this.directive = directive;
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
        if(instruction == null)
        return getDirective() + " " + getComment();
        else
           return  getInstruction().getMnemonic().getMnemonicName() + " " + getInstruction().getOperand() + " " + getComment();
    }
}