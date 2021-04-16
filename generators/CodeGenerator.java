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
        secondPass(ir);

        System.out.println("listing en: " + op.listingEnabled());
        if (op.listingEnabled())
            list = new Listing(ir);
            //list.setVerbose(op.verboseEnabled());
            list.openOutputStream();
        System.out.println("Verbose en: " + op.verboseEnabled());
        if (op.verboseEnabled())
            symbolTable.verboseLabelsTable();


    }

    void secondPass(IIR ir) {

        for(ILineStatement ls : ir.getLineStatementList()) {

            //.cstring
            if (ls.getDirective() != null) {

                String operandString = ls.getStringOperand();
                for (int i = 0; i < operandString.length(); i++) {
                    ls.addMachineCode((byte) operandString.charAt(i));
                }
                ls.addMachineCode((byte) 0);
                continue;
            }

            IMnemonic mnemonic = ls.getInstruction().getMnemonic();

            if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeLabel) {

                if (ls.getInstruction().getOperandLabel().getAddr() == -1) {
                    errorReporter.recordError(new ErrorMsg("No Label: " + ls.getInstruction().getOperandLabel().getName(), new Position(ir.getLineStatementList().indexOf(ls),0)));
                }

                int machineCodeAddr = ls.getInstruction().getOperandLabel().getAddr() - ls.getAddress();
                if (machineCodeAddr < 0) {
                    machineCodeAddr += 256;
                }
                ls.addMachineCode((byte) ls.getInstruction().getMnemonic().getOpCode());
                ls.addMachineCode((byte) machineCodeAddr);
            } else if (mnemonic != null && mnemonic.getType() == MnemonicType.RelativeOffset) {
                String operand = ls.getInstruction().getOperandOffset() != null ? ls.getInstruction().getOperandOffset(): "";
                ls.addMachineCode((byte) ls.getInstruction().getMnemonic().getOpCode());
                ls.addMachineCode((byte) Integer.parseInt(operand));
            } else if (mnemonic != null){
                ls.addMachineCode((byte) ls.getInstruction().getMnemonic().getOpCode());
            }

        }
    }

    private int calculateOffset() {
        return 1;
    }


}








