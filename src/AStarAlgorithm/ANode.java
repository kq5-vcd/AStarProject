package AStarAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ANode implements Comparable<ANode> {
	
	protected static enum Status {
	    BLANK,
	    BLOKED,
	    CHECKED,
	    USED,
	}
	
	//private static int id = 0;
	private ANode goFrom;
	private Map<ANode, Double> to = new HashMap<ANode, Double>();
	
	private double x, y;
	
	private int nodeId;
	
	private double currentValue;
	private double endDistance;
	private double heuristic;
	private Status status;
	
	private boolean startNode = false;
	private boolean endNode = false;
	
	public ANode() {
		super();
		//this.nodeId = id++;
		this.currentValue = 0;
		setBlank();
	}
	
	public ANode(int id, double x, double y) {
		this();
		this.nodeId = id;
		this.x = x;
		this.y = y;
	}
	
	public int getNodeId() {
		return nodeId;
	}
	
	public void setNodeId(int id) {
		this.nodeId = id;
	}
	
//	public void resetId() {
//		id = 0;
//	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		if(this.currentValue > currentValue || this.currentValue == 0) {
			this.currentValue = currentValue;
		}
	}

	public double getEndDistance() {
		return endDistance;
	}
	
	public void setEndDistance(double endDistance) {
		this.endDistance = endDistance;
	}
	
	public void setEndDistance(ANode end) {
		this.endDistance = getDistance(end);
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic() {
		heuristic = endDistance + currentValue;
	}

	public ANode getGoFrom() {
		return goFrom;
	}

	public void setGoFrom(ANode goFrom) {
		this.goFrom = goFrom;
	}

	public Set<ANode> getTo() {
		return to.keySet();
	}

	public void setTo(ANode node, double value) {
		to.put(node, value);
	}
	
	public void setTo(ANode node) {
		to.put(node, getDistance(node));
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getDistance(ANode node) {
		double nX = node.getX();
		double nY = node.getY();
		
		double xDiff = x - nX;
		double yDiff = y - nY;
		
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
	
	public double getPathValue(ANode node) {
		return to.get(node);
	}
	
	public void checkNode(ANode from, double value) {
		if(isEnd()) {
			setGoFrom(from);
			heuristic = -1;
		} else if(isStart()) {
			heuristic = Math.pow(10, 5);
		} else if(currentValue > value || currentValue == 0) {
			setGoFrom(from);
			
			setCurrentValue(value);
			setHeuristic();
			setStatus(Status.CHECKED);
		}
	}
	
	public void setStart() {
		startNode = true;
	}

	public void setEnd() {
		endNode = true;
	}

	public boolean isStart() {
		return startNode;
	}

	public boolean isEnd() {
		return endNode;
	}

	public boolean isBlank() {
		return status == Status.BLANK;
	}
	
	public void setBlank() {
		this.status = Status.BLANK;
	}
	
	public void check() {
		setStatus(Status.CHECKED);
	}
	
	public void resetNode() {
		setBlank();
		setCurrentValue(0);
		setEndDistance(0);
		startNode = false;
		endNode = false;
	}
	
	public void setUsed() {
		status = Status.USED;
	}
	
	public boolean equals(ANode node) {
		if(nodeId == node.getNodeId()) {
			return true;
		}
			
		return false;
	}

	@Override
	public int compareTo(ANode node) {
		double result = this.getHeuristic() - node.getHeuristic();
		
		if(result > 0) {
			return 1;
		} else if(result < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "" + nodeId;
	}
	
}