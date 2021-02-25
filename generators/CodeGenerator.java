package generators;

/**
 * generates the executable file and listing file
 * (currently only the listing file)
 */
public class CodeGenerator{
	private Listing list;

	/**
	 *Parametrized Constructor that creates the listing file
	 * @param list
	 */
	public CodeGenerator(Listing list) {
		this.list = list;
		this.list.openOutputStream();
	}

}