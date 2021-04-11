package generators;


import interfaces.IListing;
import components.Options;
import interfaces.IIR;


/**
 * generates the executable file and listing file
 * (currently only the listing file)
 */
public class CodeGenerator {
    private IListing list;


    /**
     * Parametrized Constructor that creates the listing file
     *
     * @param
     */
    public CodeGenerator(IIR ir, Options op) {
        System.out.println("listing en: " + op.listingEnabled());
        if (op.listingEnabled())
            new Listing(ir).openOutputStream();



    }

        //code generation here


		/*this.list = list;
		this.list.openOutputStream();*/
}
//not sure why this method cannot be resolved







