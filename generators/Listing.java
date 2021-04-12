package generators;

import components.Label;
import components.MnemonicType;
import interfaces.IIR;
import interfaces.ILineStatement;
import interfaces.IListing;
import interfaces.IMnemonic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates the listing file  (.lst)
 */
public class Listing implements IListing {

	private int lineCount = 0;
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

		ArrayList<ILineStatement> list = intRep.getLineStatementList();

		for (ILineStatement lineStatement : list){
			String[] instruction = separateLineStatement(lineStatement);
			String line = LineFormatter(getLineNumber(), instruction[0], instruction[1], instruction[2], instruction[3], instruction[4], instruction[5]);
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
	String[] separateLineStatement(ILineStatement ls){

		String addr = String.format("%04X", ls.getAddress());
		String label = ls.getLabel() == null ? "": ls.getLabel().getName();
		String comment = ls.getComment();

		if (ls.getDirective() != null) {
			String machineCode = "";

			return new String[]{addr, machineCode, label, ls.getDirective(), "", comment};
		}

		IMnemonic mnemonic = ls.getInstruction().getMnemonic();
		String mnemonicName = mnemonic != null? mnemonic.getMnemonicName(): "";

		Label operandLabel;
		String operand;
		String machineCode;
		System.out.println(mnemonicName);
		if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel) {
			operandLabel = ls.getInstruction().getOperandLabel();
			operand = operandLabel != null ? operandLabel.getName() : "";
			int machineCodeAddr = operandLabel.getAddr() - ls.getAddress();
			if (machineCodeAddr < 0) {
				machineCodeAddr += 256;
			}
			machineCode = ls.getInstruction().getMnemonic() != null ? String.format("%02X %02X", ls.getInstruction().getMnemonic().getOpCode(), machineCodeAddr): "";
		} else if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeOffset) {
			operand = ls.getInstruction().getOperandOffset() != null ? ls.getInstruction().getOperandOffset(): "";
			machineCode = ls.getInstruction().getMnemonic() != null ? String.format("%02X %02X", ls.getInstruction().getMnemonic().getOpCode(), Integer.parseInt(operand)): "";
		} else {
			operand = ls.getInstruction().getOperandOffset() != null ? ls.getInstruction().getOperandOffset() : "";
			machineCode = ls.getInstruction().getMnemonic() != null ? String.format("%02X", ls.getInstruction().getMnemonic().getOpCode()): "";
		}

		return new String[]{addr, machineCode, label, mnemonicName, operand, comment};
	}

	/**
	 * returns the line number as a string
	 * @return
	 */
	String getLineNumber(){
		return Integer.toString(++lineCount);
	}

}
