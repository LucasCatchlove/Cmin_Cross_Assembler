import java.util.ArrayList;

public class IR implements IIR {

	private ArrayList<LineStatement> lineStatementList = new ArrayList<>();
	
	public void addLineStatement(LineStatement lineStatement) {
		lineStatementList.add(lineStatement);
	}

	public LineStatement getLineStatement(int index) {
		return lineStatementList.get(index);
	}

	public ArrayList<LineStatement> getLineStatementList() {
		return lineStatementList;
	}



}