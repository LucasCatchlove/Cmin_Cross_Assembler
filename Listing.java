import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileWriter;
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
			File Listing = new File("Listing.txt");
			FileWriter writer = new FileWriter("Listing.txt");
			writer.write(printHeader());
			ArrayList<LineStatement> list = intRep.getLineStatementList();
			for ( LineStatement line : list){
				writer.write(openingLine());
				writer.write(printInstruction(line));
				writer.write(closingLine());
			}
			writer.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
	}
	private String printHeader(){
		return "Line Addr Code          Label         Mne   Operand       Comments        ";
	}
	private String printInstruction(LineStatement ls){
		String line;
		String label = ls.getLabel();
		String instruction = ls.getInstruction().getMnemonic().getMnemonicName();
		String directive = ls.getDirective();
		String comment = ls.getComment();
		line = label + " " + instruction + " " + directive + " " + comment;
		return line;
	}
	private int openingLine(){
		return lineCount+1;
	}
	private String closingLine(){
		lineCount++;
		return "\n";
	}
}
