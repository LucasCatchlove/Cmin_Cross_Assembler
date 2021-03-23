package generators;

import components.IR;
import components.LineStatement;
import interfaces.IIR;
import interfaces.IListing;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates the listing file  (.lst)
 */
public class Listing implements IListing {

	private int lineCount = 0;
	private int addrCount = 0;
	private IIR intRep;

	public Listing(IIR intRep) {
		this.intRep = intRep;
	}

	/**
	 * Opens the output stream
	 */
	public void openOutputStream(){

		try {
			FileOutputStream writer = new FileOutputStream("Sprint Listing/Listing.lst", false);
			writeListingFile(writer);
			writer.close();
    	} catch (Exception e) {
    		System.err.println("An error occurred.");
    		e.printStackTrace();
    		System.exit(1);
    	}

	}

	/**
	 * Writes the header and contents of the generators.Listing File
	 * @param writer
	 * @throws IOException
	 */
	void writeListingFile(FileOutputStream writer) throws IOException {

		writer.write(header().getBytes());

		ArrayList<LineStatement> list = intRep.getLineStatementList();

		for (LineStatement lineStatement : list){
			String[] instruction = separateLineStatement(lineStatement);
			String line = LineFormatter(getLineNumber(), address(), instruction[0], instruction[1], instruction[2], instruction[3], instruction[4]);
			if (instruction[2] != "")
				addrCount++;
			writer.write(line.getBytes());
		}

	}

	/**
	 * Formats the line to look like a table
	 * @param line
	 * @param address
	 * @param code
	 * @param label
	 * @param mne
	 * @param operand
	 * @param comments
	 * @return
	 */
	String LineFormatter(String line, String address, String code, String label, String mne, String operand, String comments) {
		return String.format("%-6s %-7s %-15s %-20s %-15s %-12s %-25s\n", line, address, code, label, mne, operand, comments);
	}

	/**
	 * Returns the header of the generators.Listing File
	 * @return returns the header to the listing file
	 */
	String header(){
		return LineFormatter("Line", "Addr", "Machine Code", "Label", "Assembly Code", "Operand", "Comments");
	}

	/**
	 * gets the [code, label, mnemonic name, operand, comment] from the lineStatement
	 * @param ls
	 * @return
	 */
	String[] separateLineStatement(LineStatement ls){

		if (ls.getInstruction() == null) {
			String machineCode = "";
			String label = ls.getLabel();
			String mnemonicName = "";
			String operand = "";
			//String directive = ls.getDirective();
			String comment = ls.getComment();

			return new String[]{machineCode, label, mnemonicName, operand, comment};
		}

		String label = ls.getLabel() == null ? "": ls.getLabel();
		String mnemonicName = ls.getInstruction().getMnemonic() != null? ls.getInstruction().getMnemonic().getMnemonicName(): "";
		String operand = ls.getInstruction().getOperand() != null ? ls.getInstruction().getOperand(): "";
		String machineCode = ls.getInstruction().getMnemonic() != null ? String.format("%02X", ls.getInstruction().getMnemonic().getOpCode()): "";
		//String directive = ls.getDirective();
		String comment = ls.getComment();

		return new String[]{machineCode, label, mnemonicName, operand, comment};
	}

	/**
	 * returns the line number as a string
	 * @return
	 */
	String getLineNumber(){
		return Integer.toString(++lineCount);
	}

	/**
	 * Generates the hexidecimal memory address
	 * @return
	 */
	String address() {
		return String.format("%04X", addrCount);
	}

}
