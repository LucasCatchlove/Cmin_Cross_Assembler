package interfaces;

import interfaces.ILineStatement;

import java.util.ArrayList;

/**
 * interface for the IR class
 */
public interface IIR {

	/**
	 * Adds newly generated line statement to components.IR
	 * @param lineStatement
	 */
	void addLineStatement(ILineStatement lineStatement);

	/**
	 * returns line statement at specified index
	 * @param index
	 * @return
	 */
	ILineStatement getLineStatement(int index);
	public ArrayList<ILineStatement> getLineStatementList();

}