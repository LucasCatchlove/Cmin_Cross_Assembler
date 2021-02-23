public class LineStatement
{
    private String label;
    private Instruction instruction;
    private String directive;
    private String comment;

    public LineStatement(String label, String directive, String comment) {
        this.label = label;
        this.directive = directive;
        this.comment = comment;
    }

    public LineStatement(String label, Instruction instruction, String comment) {
        this.label = label;
        this.instruction = instruction;
        this.comment = comment;
    }
}