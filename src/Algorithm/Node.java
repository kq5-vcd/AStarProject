package Algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node implements Comparable<Node> {
	
	private static enum Status {
	    BLANK,
	    CHECKED,
	    USED,
	    START,
	    END
	}
	
	private Node goFrom;
	private Map<Node, Float> to = new HashMap<Node, Float>();
	
	private String title;
	
	private float currentValue;
	private float endDistance;
	private float heuristic;
	private Status status;
	
	public Node(String title, float endDistance) {
		super();
		this.title = title;
		this.currentValue = 0;
		this.endDistance = endDistance;
		this.setStatus(Status.BLANK);
		
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		if(this.currentValue > currentValue || this.currentValue == 0) {
			this.currentValue = currentValue;
		}
	}

	public float getEndDistance() {
		return endDistance;
	}

	public float getHeuristic() {
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

	public void setTo(Node node, float value) {
		to.put(node, value);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public float getPathValue(Node node) {
		return to.get(node);
	}
	
	public void checkNode(Node from, float value) {
		if(isEnd()) {
			setGoFrom(from);
			heuristic = -1;
		} else if(currentValue > value || currentValue == 0) {
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

	public boolean isChecked() {
		return status == Status.CHECKED;
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

	@Override
	public int compareTo(Node node) {
		float result = this.getHeuristic() - node.getHeuristic();
		
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
