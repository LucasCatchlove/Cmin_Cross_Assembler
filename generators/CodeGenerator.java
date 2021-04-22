package generators;


import components.*;
import errorReporters.ErrorMsg;
import errorReporters.IErrorReporter;
import interfaces.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;


/**
 * generates the executable file and listing file
 * (currently only the listing file)
 */
public class CodeGenerator {

    private IListing listing;
    private IErrorReporter errorReporter;


    /**
     * Parametrized Constructor that creates the listing file
     *
     * @param
     */
    public CodeGenerator(IIR ir, ISymbolTable symbolTable, Options op, IErrorReporter errorReporter, String srcFile) {

        this.errorReporter = errorReporter;
        if (op != null && op.verboseEnabled()) {
            symbolTable.verboseLabelsTable();
            op.verboseListing(ir,1);
        }
        secondPass(ir);
        if (op != null && op.verboseEnabled()) op.verboseListing(ir,2);
        if (op != null && op.listingEnabled()) {
            this.listing = new Listing(ir);
            listing.openOutputStream();
        }
        if (srcFile != null) {
            srcFile = srcFile.substring(0, srcFile.length() - 4);
            executableGenerator(ir, srcFile);
        }


    }

    /**
     * performs the second pass of the cross assembler by setting the unresolved offsets
     * @param ir
     */
    void secondPass(IIR ir) {

        for(ILineStatement ls : ir.getLineStatementList()) {

            if (ls.offsetIsResolved())
                continue;

            if (ls.getInstruction().getOperandLabel().getAddr() == -1) {
                errorReporter.recordError(new ErrorMsg("No Label: " + ls.getInstruction().getOperandLabel().getName(), new Position(ir.getLineStatementList().indexOf(ls), 1)));
            }

            int machineCodeAddr = ls.getInstruction().getOperandLabel().getAddr() - ls.getAddress();

            ls.addMachineCode((byte) machineCodeAddr);

        }
    }

    private int calculateOffset() {
        return 1;
    }

    /**
     * generates the executable file from the IR with resolved offsets
     * @param ir
     * @param name
     */
    private void executableGenerator(IIR ir,String name){
        try{
            FileOutputStream file = new FileOutputStream(name+".exe");
            DataOutputStream data = new DataOutputStream(file);
            for(ILineStatement ls : ir.getLineStatementList()){
                for(int i =0;i < ls.machineCodeSize();i++) {
                    if (ls.getInstruction()!=null && ls.getInstruction().getMnemonic() != null && ls.getInstruction().getMnemonic().getType() == MnemonicType.RelativeLabel && i == 1 && Integer.parseInt(String.valueOf(ls.getInstruction().getMnemonic().getMnemonicName().charAt(ls.getInstruction().getMnemonic().getMnemonicName().length()-1))) == 6){
                        data.write(0);
                    }
                    data.write(ls.getMachineCode(i));
                }
            }
            data.flush();
            data.close();
        }
        catch(Exception e) {
            System.err.println("An error occurred. while creating executable");
            e.printStackTrace();
            System.exit(69);

        }
    }

}








