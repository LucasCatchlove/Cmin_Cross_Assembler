package components;

import components.Label;
import components.MnemonicType;
import interfaces.IIR;
import interfaces.ILineStatement;
import interfaces.IMnemonic;
import java.util.ArrayList;


public class Options {

    private boolean listing = false;
    private boolean verbose = false;
    private boolean banner = false;
    private boolean help = false;

    public Options(String[] args) {
        setOptions(args);
    }

    public boolean listingEnabled() {
        return listing;
    }

    public boolean verboseEnabled() {
        return verbose;
    }
    public boolean helpEnabled(){
        return help;
    }
    private void setOptions(String[] args) {
        for(String argument : args) {
            switch(argument) {
                case "-l":
                case "-listing":
                    listing = true;
                    break;
                case "-v":
                case "-verbose":
                    verbose = true;
                    break;
                case "-h":
                case "-help":
                    help = true;
                    printGuide();
                    break;
                case "-b":
                case "-banner":
                    printBanner();

            }
        }

    }
    private void printGuide() {
        System.out.println("\nCross-Assembler guide\n=====================\n");
        System.out.println("The proper command line formatting is as follows: \"source file\" \"-option1\" \"-option2\" \"-option3\"");
        System.out.println("The options can be entered in any order, however the source file name must be listed first\n");
        System.out.println("The \"-listing\" flag produces a source listing of the assembly program");
        System.out.println("The \"-verbose\" flag produces a source listing of the assembly program as well as its corresponding label table\n");
        System.out.println("=====================\n");
        
    }

    private void printBanner(){
        System.out.println("Cm Cross-Assembler Version 1.4 - Developed by Team 5. \n");
    }



public void verboseListing(IIR ir, int passNumber) {

        if(passNumber == 1)
            System.out.println("After first pass");
        else
            System.out.println("After Second Pass");



    int lineCount = 0;

    ArrayList<ILineStatement> list = ir.getLineStatementList();

    header();

    for (ILineStatement lineStatement : list) {
        String[] instruction = separateLineStatement(lineStatement, passNumber);
        lineCount++;
        if(instruction == null){continue;}
        String line = LineFormatter(Integer.toString(lineCount), instruction[0], instruction[1], instruction[2], instruction[3], instruction[4], instruction[5]);

        System.out.print(line);
    }


}

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
    String[] separateLineStatement(ILineStatement ls, int passNumber){

        String addr = String.format("%04X", ls.getAddress());
        String label = ls.getLabel() == null ? "": ls.getLabel().getName();
        String comment = "";

        if (ls.getDirective() != null) {

            String machineCode = "";
            String operandString = ls.getStringOperand();

            for (int i = 0; i < ls.machineCodeSize(); i++) {
                machineCode += String.format("%02X ", ls.getMachineCode(i));
            }

            return new String[]{addr, machineCode, label, ls.getDirective(), "\"" + operandString + "\"", comment};
        }
        if(ls.getInstruction().getMnemonic()==null){return null;}
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

            if(ls.offsetIsResolved()) {
                for (int i = 0; i < ls.machineCodeSize(); i++) {
                    if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel && i == 1 && Integer.parseInt(String.valueOf(ls.getInstruction().getMnemonic().getMnemonicName().charAt(mnemonic.getMnemonicName().length() - 1))) == 6) {
                        machineCode += String.format("%04X ", ls.getMachineCode(i));
                        continue;
                    }
                    machineCode += String.format("%02X ", ls.getMachineCode(i));
                }
                if(passNumber == 1)
                    comment = "<---  Offset resolved when backward branching";
                if(ls.hadForwardBranch()&&passNumber==2)
                    comment = "<---  Offset NOW resolved when forward branching";
            } else {
                machineCode = String.format("%02X ", ls.getMachineCode(0)) + "??";
                comment = "<---  Offset NOT resolved when backward branching ";
            }



        return new String[]{addr, machineCode, label, mnemonicName, operand, comment};
    }

    /**
     * returns the line number as a string
     * @return
     */

}

