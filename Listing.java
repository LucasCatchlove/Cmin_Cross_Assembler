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
		String directive = ls.getDirective();
		String comment = ls.getComment();
		line ="           " + label + "            " + instruction + "           " + directive + "       " + comment;
		System.out.println(line);
		return line;
	}
	private int openingLine(){
		int TEMP = lineCount+1;
		return TEMP;
	}
	private String closingLine(){
		lineCount++;
		return "\n";
	}
}
