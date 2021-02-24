interface IIR {

	/**
	 * Adds newly generated line statement to IR
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