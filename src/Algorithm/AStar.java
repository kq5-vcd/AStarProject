package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import node.ANode;

public class AStar {
	
    public static void reconstructPath(ANode node) {
    	List<ANode> path = new ArrayList<ANode>();
    	
    	while(true) {
    		path.add(0, node);
    		
    		if(node.isStart()) {
    			break;
    		}
    		
    		if(!node.isEnd()) {
    			node.useNode();
    		}
    		node = node.getGoFrom();
    	}
    	
    	for(ANode step: path) {
    		System.out.print(step + " ");
    	}
    	System.out.println();
    }
    
    public static List<ANode> expandNode(ANode node, ANode end) {
    	Set<ANode> paths = node.getTo();
    	List<ANode> expandedPaths = new ArrayList<ANode>();
    	
    	node.check();
    	
    	for(ANode path: paths) {
    		boolean add = false;
    		path.setEndDistance(end);
    		
    		if(path.isBlank() || path.isEnd()) {
        		add = true;
    		}
    		
    		double value = node.getPathValue(path) + node.getCurrentValue();
    		path.checkNode(node, value);
    		
    		if(add) {
    			//System.out.println(path);
        		expandedPaths.add(path);
    		}
    	}
    	
    	return expandedPaths;
    }
    
    public static void AStarSearch(ANode start, ANode end) {
    	if(start.equals(end)) {
    		reconstructPath(start);
    	} else {
    		List<ANode> queue = new ArrayList<ANode>();
    		start.setStart();
    		end.setEnd();
        	
        	queue.add(start);
        	while(true) {
        		ANode node = queue.get(0);
        		//System.out.println("A* " + node);
        		queue.remove(0);
        		
        		if(node.isEnd()) {
        			reconstructPath(node);
        			break;
        		}
        		
        		queue.addAll(expandNode(node, end));
        		
        		if(queue.isEmpty()) {
        			System.out.println("No possible path");
        			break;
        		}
        		
        		Collections.sort(queue);
        	}
    	}
    }

}