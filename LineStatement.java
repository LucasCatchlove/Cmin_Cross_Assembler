public class LineStatement
{
    private string label;
    private string instruction;
    private string directive;
    private string comment;

    public LineStatement(label, instrcution, directive, comment)
    {
        this.label = label;
        this.instruction = instruction;
        this.directive = directive;
        this.comment = comment;
    }
}