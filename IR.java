import java.util.ArrayList;

public class IR implements IntermediateRepresentation {

	private ArrayList<LineStatement> lineStatementList = new ArrayList<>();
	
	public void addLineStatement(LineStatement linestatement) {
		lineStatementList.add(linestatement);
	}

	public LineStatement getLineStatement(int index) {
		return lineStatementList.get(index);
	}


}