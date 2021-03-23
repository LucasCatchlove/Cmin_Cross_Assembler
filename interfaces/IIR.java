package interfaces;

import components.LineStatement;

import java.util.ArrayList;

public interface IIR {

	/**
	 * Adds newly generated line statement to components.IR
	 * @param lineStatement
	 */
	void addLineStatement(LineStatement lineStatement);

	/**
	 * returns line statement at specified index
	 * @param index
	 * @return
	 */
	LineStatement getLineStatement(int index);
	public ArrayList<LineStatement> getLineStatementList();

}