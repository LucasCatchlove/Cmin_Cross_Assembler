import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Listing{
	private int lineCount = 0;

	public createListingFile(){
		try{
			File Listing = new File("Listing.txt");
			FileWriter writer = new FileWriter("Listing.txt")
			writer.write(printHeader());
			LineStatement<> list = getLineStatementList();
			for ( LineStatement line : list){
				writer.write(openingLine());
				writer.write(printInstruction(line));
				writer.write(closingLine());
			}
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
		String instruction = ls.getInstruction();
		String directive = ls.getDirective();
		String comment = ls.getComment();
		line = label + " " + instruction + " " + directive + " " + comment;
		return line;
	}
	private String openingLine(){
		return lineCount+1;
	}
	private String closingLine(){
		lineCount++;
		return "/n";
	}
}
