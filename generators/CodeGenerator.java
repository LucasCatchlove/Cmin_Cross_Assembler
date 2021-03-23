package generators;

import interfaces.IListing;

/**
 * generates the executable file and listing file
 * (currently only the listing file)
 */
public class CodeGenerator{
	private IListing list;

	/**
	 *Parametrized Constructor that creates the listing file
	 * @param list
	 */
	public CodeGenerator(IListing list) {
		this.list = list;
		this.list.openOutputStream();
	}

}