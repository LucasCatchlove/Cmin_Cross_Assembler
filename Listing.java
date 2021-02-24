import javax.sound.sampled.Line;
import java.io.File;
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
			FileOutputStream writer = new FileOutputStream("Listing.txt");
			writer.write(printHeader().getBytes());
			ArrayList<LineStatement> list = intRep.getLineStatementList();
			for ( LineStatement line : list){
				writer.write(openingLine());
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
		return "Line Addr Code          Label         Mne   Operand       Comments        ";
	}
	private String printInstruction(LineStatement ls){
		String line;
		String label = ls.getLabel();
		String instruction = "placeholder"; //ls.getInstruction().getMnemonic().getMnemonicName();
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
