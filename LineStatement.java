public class LineStatement
{
    private String label;
    private Instruction instruction;
    private String directive;
    private String comment;

    public LineStatement(String label, Instruction instruction, String directive, String comment)
    {
        this.label = label;
        this.instruction = instruction;
        this.directive = directive;
        this.comment = comment;
    }
}