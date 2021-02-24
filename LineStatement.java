public class LineStatement implements ILineStatement{

    private String label;
    private Instruction instruction;
    private String directive;
    private String comment;

    //For Sprint 2 usage: LineStatement with a directive
    public LineStatement(String label, String directive, String comment) {
        this.label = label;
        this.directive = directive;
        this.comment = comment;
    }

    //LineStatement with an Instruction
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

}