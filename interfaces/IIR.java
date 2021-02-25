package interfaces;

import components.LineStatement;

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

}