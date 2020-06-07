package Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node implements Comparable<Node> {
	
	protected static enum Status {
	    BLANK,
	    BLOCKED,
	    CHECKED,
	    USED,
	}
	private static int id = 0;
	
	private Node goFrom;
	private Map<Node, Double> to = new HashMap<Node, Double>();
	
	private double x;
	private double y;
	
	private int nodeId;
	
	private double currentValue;
	private double endDistance;
	private double heuristic;
	private Status status;
	
	private boolean startNode = false;
	private boolean endNode = false;
	
	public Node() {
		super();
		this.nodeId = id++;
		this.currentValue = 0;
		setBlank();
	}
	
	public Node(double x, double y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public int getNodeId() {
		return nodeId;
	}

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
	
	public void setEndDistance(Node end) {
		this.endDistance = getDistance(end);
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic() {
		heuristic = endDistance + currentValue;
	}

	public Node getGoFrom() {
		return goFrom;
	}

	public void setGoFrom(Node goFrom) {
		this.goFrom = goFrom;
	}

	public Set<Node> getTo() {
		return to.keySet();
	}

	public void setTo(Node node, double value) {
		to.put(node, value);
	}
	
	public void setTo(Node node) {
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
	
	public double getDistance(Node node) {
		double nX = node.getX();
		double nY = node.getY();
		
		double xDiff = x - nX;
		double yDiff = y - nY;
		
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
	
	public double getPathValue(Node node) {
		return to.get(node);
	}
	
	public void checkNode(Node from, double value) {
		if(isEnd()) {
			setGoFrom(from);
			
			heuristic = -1;
		} else if(isStart()) {
			heuristic = Math.pow(10, 5);
		} else if(currentValue > value || currentValue == 0) {
			setGoFrom(from);
			
			setCurrentValue(value);
			setHeuristic();
			check();
		}
	}
	
	public void setStart() {
		startNode = true;
	}

	public void setEnd() {
		endNode = true;
	}
	
	public void removeStart() {
		startNode = false;
	}

	public void removeEnd() {
		endNode = false;
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

	public boolean isCheck() {
		return status == Status.CHECKED;
	}
	
	public void setBlank() {
		setStatus(Status.BLANK);
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
	
	public void useNode() {
		setStatus(Status.USED);
	}
	
	public boolean equals(Node node) {
		if(nodeId == node.getNodeId()) {
			return true;
		}
			
		return false;
	}

	@Override
	public int compareTo(Node node) {
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