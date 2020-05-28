package Node;

public class GridNode extends Node {
	
	static final int value = 1;

	public GridNode(double x, double y) {
		super(x, y);
	}
	
	public void setBlock() {
		setStatus(Status.BLOCKED);
	}

}
