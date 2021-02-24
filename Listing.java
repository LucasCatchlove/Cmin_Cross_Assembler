import javax.sound.sampled.Line;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
public class Listing{
	private int lineCount = 0;
	private IR intRep;

	public Listing(IR intRep) {
		this.intRep = intRep;
	}



	public void createListingFile(){
		try{
			FileOutputStream writer = new FileOutputStream("Listing.txt", false);
			writer.write(printHeader().getBytes());
			ArrayList<LineStatement> list = intRep.getLineStatementList();
			for ( LineStatement line : list){
				writer.write(String.valueOf(openingLine()).getBytes());
				writer.write(address().getBytes());
				writer.write(code(line).getBytes());
				writer.write(printInstruction(line).getBytes());
				writer.write(closingLine().getBytes());
			}
			writer.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
	}
	private String printHeader(){
		return "Line Addr Code          Label         Mne   Operand       Comments        \n";
	}
	private String printInstruction(LineStatement ls){
		String line;
		String label = ls.getLabel();
		String instruction = ls.getInstruction().getMnemonic().getMnemonicName();
		//String directive = ls.getDirective();
		String comment = ls.getComment();
		line ="           " + label + "              " + instruction + "           " /*+ directive */+ "       "+comment;
		System.out.println(line);
		return line;
	}
	private String openingLine(){
		String TEMP;
		if(lineCount < 9)
			TEMP = Integer.toString(lineCount + 1) + " " ;
		else
			TEMP = Integer.toString(lineCount + 1);
		return TEMP;
	}
	private String address() {
		String addr = String.format("%04X", lineCount);
		return "   " + addr;
	}
	private String code(LineStatement ls){
		String code = String.format("%02X",ls.getInstruction().getMnemonic().getOpCode());
		return "  " + code;
	}
	private String closingLine(){
		lineCount++;
		return "\n";
	}
}
