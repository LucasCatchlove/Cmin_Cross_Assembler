package generators;


import components.*;
import errorReporters.ErrorMsg;
import errorReporters.IErrorReporter;
import interfaces.*;


/**
 * generates the executable file and listing file
 * (currently only the listing file)
 */
public class CodeGenerator {

    private IListing list;
    private IErrorReporter errorReporter;


    /**
     * Parametrized Constructor that creates the listing file
     *
     * @param
     */
    public CodeGenerator(IIR ir, ISymbolTable symbolTable, Options op, IErrorReporter errorReporter) {

        this.errorReporter = errorReporter;
        System.out.println("Verbose en: " + op.verboseEnabled());
        if (op.verboseEnabled()) symbolTable.verboseLabelsTable();

        secondPass(ir);
        if (op.verboseEnabled()) op.verboseListing(ir,2);
        System.out.println("listing en: " + op.listingEnabled());
        if (op.listingEnabled())
            new Listing(ir).openOutputStream();



    }

    void secondPass(IIR ir) {

        for(ILineStatement ls : ir.getLineStatementList()) {

            if (ls.getInstruction() == null)
                continue;

            IMnemonic mnemonic = ls.getInstruction().getMnemonic();

            if (mnemonic == null || mnemonic.getType() != MnemonicType.RelativeLabel)
                continue;

            if (ls.machineCodeSize() != 0)
                continue;

            if (ls.getInstruction().getOperandLabel().getAddr() == -1) {
                errorReporter.recordError(new ErrorMsg("No Label: " + ls.getInstruction().getOperandLabel().getName(), new Position(ir.getLineStatementList().indexOf(ls), 1)));
            }

            int machineCodeAddr = ls.getInstruction().getOperandLabel().getAddr() - ls.getAddress();

            ls.addMachineCode((byte) ls.getInstruction().getMnemonic().getOpCode());
            ls.addMachineCode((byte) machineCodeAddr);

        }
    }

    private int calculateOffset() {
        return 1;
    }


}








