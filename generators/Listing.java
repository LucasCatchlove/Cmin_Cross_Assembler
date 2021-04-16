package generators;

import components.Label;
import components.MnemonicType;
import components.Options;
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
	private boolean verbose = false;

	public Listing(IIR intRep) {
		this.intRep = intRep;
	}
	public void setVerbose(Boolean set)
	{
		this.verbose = set;
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
		if(verbose){
			System.out.println(header());
		}

		ArrayList<ILineStatement> list = intRep.getLineStatementList();

		for (ILineStatement lineStatement : list){
			String[] instruction = separateLineStatement(lineStatement);
			String line = LineFormatter(getLineNumber(), instruction[0], instruction[1], instruction[2], instruction[3], instruction[4], instruction[5]);
			if(verbose){
				System.out.print(line);
			}
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
			String operandString = ls.getStringOperand();

			for (int i = 0; i < ls.machineCodeSize(); i++) {
				machineCode += String.format("%02X ", ls.getMachineCode(i));
			}

			return new String[]{addr, machineCode, label, ls.getDirective(), "\"" + operandString + "\"", comment};
		}

		IMnemonic mnemonic = ls.getInstruction().getMnemonic();
		String mnemonicName = mnemonic != null? mnemonic.getMnemonicName(): "";

		Label operandLabel;
		String operand;
		if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel) {
			operandLabel = ls.getInstruction().getOperandLabel();
			operand = operandLabel != null ? operandLabel.getName() : "";
		} else if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeOffset) {
			operand = ls.getInstruction().getOperandOffset() != null ? ls.getInstruction().getOperandOffset(): "";
		} else {
			operand = ls.getInstruction().getOperandOffset() != null ? ls.getInstruction().getOperandOffset() : "";
		}

		String machineCode = "";

		for (int i = 0; i < ls.machineCodeSize(); i++) {
			if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel && i == 1 && Integer.parseInt(String.valueOf(ls.getInstruction().getMnemonic().getMnemonicName().charAt(mnemonic.getMnemonicName().length()-1))) == 6) {
				machineCode += String.format("%04X ", ls.getMachineCode(i));
				continue;
			}
			machineCode += String.format("%02X ", ls.getMachineCode(i));
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
