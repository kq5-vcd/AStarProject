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
	    START,
	    END
	}
	
	private Node goFrom;
	private Map<Node, Double> to = new HashMap<Node, Double>();
	
	private String title;
	private double x;
	private double y;
	
	private double currentValue;
	private double endDistance;
	private double heuristic;
	private Status status;
	
	public Node(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Node(double endDistance) {
		super();
		this.currentValue = 0;
		this.endDistance = endDistance;
		this.setStatus(Status.BLANK);
		
	}
	
	public Node(String title, double endDistance) {
		this(endDistance);
		this.title = title;
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
		
		return Math.abs(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
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
		}
		
		else if(currentValue > value || currentValue == 0) {
			setGoFrom(from);
			
			setCurrentValue(value);
			setHeuristic();
			setStatus(Status.CHECKED);
		}
	}

	public boolean isStart() {
		return status == Status.START;
	}

	public boolean isEnd() {
		return status == Status.END;
	}

	public boolean isBlank() {
		return status == Status.BLANK;
	}
	
	public void check() {
		if(!isStart()) {
			status = Status.CHECKED;
		}
	}
	
	public void setStart() {
		status = Status.START;
	}
	
	public void setEnd() {
		status = Status.END;
	}
	
	public void setBlank() {
		status = Status.BLANK;
	}
	
	public void useNode() {
		status = Status.USED;
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
		return title;
	}
	
}
