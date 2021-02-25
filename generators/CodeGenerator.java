package generators;

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