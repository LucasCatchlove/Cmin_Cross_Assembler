package components;

import interfaces.IIR;

import java.util.ArrayList;

public class IR implements IIR {

	private ArrayList<LineStatement> lineStatementList;

	public IR() {
		lineStatementList = new ArrayList<LineStatement>();
	}
	
	public void addLineStatement(LineStatement lineStatement) {
		lineStatementList.add(lineStatement);
	}

	public LineStatement getLineStatement(int index) {
		if (index >= lineStatementList.size())
			return null;
		return lineStatementList.get(index);
	}

	public ArrayList<LineStatement> getLineStatementList() {
		return lineStatementList;
	}



}