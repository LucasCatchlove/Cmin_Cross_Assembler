import java.util.ArrayList;

public class IR implements IntermediateRepresentation {

	private ArrayList<LineStatement> intRep = new ArrayList<>();
	
	public void addLineStatement(LineStatement linestatement) {
		intRep.add(linestatement);
	}

	public LineStatement getLineStatement(int index) {
		return intRep.get(index);
	}


}