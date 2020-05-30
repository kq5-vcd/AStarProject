package AStarAlgorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AStarAlgorithm {
	
    public static List<String> reconstructPath(ANode node) {
    	List<String> rPath = new ArrayList<String>();
    	List<ANode> path = new ArrayList<ANode>();
    	
    	while(true) {
    		System.out.println(node);
    		path.add(0, node);
    		if(node.isStart()) break;
    		node.setUsed();
    		node = node.getGoFrom();
    		
    	}
    	
    	
    	for(ANode step: path) {
    		System.out.print(step + " ");
    		rPath.add(String.valueOf(step.getNodeId()));
    	}
    	System.out.println();
    	return rPath;
    }
    
    public static List<ANode> expandNode(ANode node, ANode end) {
    	Set<ANode> paths = node.getTo();
    	List<ANode> expandedPaths = new ArrayList<ANode>();
    	
    	node.check();
    	
    	for(ANode path: paths) {
    		boolean add = false;
    		path.setEndDistance(end);
    		if(path.isBlank()||path.isEnd()) {
        		add = true;
    		}
    		
    		double value = node.getPathValue(path) + node.getCurrentValue();
    		path.checkNode(node, value);
    		
    		if(add) {
    			System.out.println(path);
        		expandedPaths.add(path);
    		}
    	}
    	
    	return expandedPaths;
    }
    
    public static boolean AStarSearch(ANode start, ANode end, List<String> path) {
    	if(start.equals(end)) {
    		reconstructPath(start);
    		return true;
    	}
    	else {
	    	List<ANode> queue = new ArrayList<ANode>();
	    	start.setStart();
	    	end.setEnd();
	    	queue.add(start);
	    	while(true) {
	    		ANode node = queue.get(0);
	    		System.out.println("A* " + node);
	    		queue.remove(0);
	    		
	    		if(node.isEnd()) {
	    			path.clear();
	    			path.addAll(reconstructPath(node));
	    			return true;
	    		}
	    		
	    		queue.addAll(expandNode(node, end));
	    		
	    		if(queue.isEmpty()) {
	    			System.out.println("No possible path");
	    			return false;
	    			
	    		}
	    		
	    		Collections.sort(queue);
	    	}
    	}
    }

}
