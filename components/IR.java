package components;

import interfaces.IIR;
import interfaces.ILineStatement;

import java.util.ArrayList;

/**
 * Creates an intermediate representation in the form of an ArrayList
 */
public class IR implements IIR {

	private ArrayList<ILineStatement> lineStatementList;

	public IR() {
		lineStatementList = new ArrayList<ILineStatement>();
	}
	
	public void addLineStatement(ILineStatement lineStatement) {
		lineStatementList.add(lineStatement);
	}

	public ILineStatement getLineStatement(int index) {
		if (index >= lineStatementList.size())
			return null;
		return lineStatementList.get(index);
	}

	public ArrayList<ILineStatement> getLineStatementList() {
		return lineStatementList;
	}



}