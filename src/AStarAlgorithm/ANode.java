package AStarAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ANode implements Comparable<ANode> {
	
	private static enum Status {
	    BLANK,
	    CHECKED,
	    USED,
	    START,
	    END
	}
	
	private ANode goFrom;
	private Map<ANode, Double> to = new HashMap<ANode, Double>();
	
	private String title;
	
	private double currentValue;
	private double endDistance;
	private double heuristic;
	private Status status;
	
	public ANode(String title, double endDistance) {
		super();
		this.title = title;
		this.currentValue = 0;
		this.endDistance = endDistance;
		this.setStatus(Status.BLANK);
		
	}
	
	public String getTitle() {
		return this.title;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public double getPathValue(ANode node) {
		return to.get(node);
	}
	
	public void checkNode(ANode from, double value) {
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
		return title;
	}
	
}