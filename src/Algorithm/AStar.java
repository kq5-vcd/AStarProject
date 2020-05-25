package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AStar {
	
    public static void reconstructPath(Node node) {
    	List<Node> path = new ArrayList<Node>();
    	
    	while(!node.isStart()) {
    		System.out.println(node);
    		path.add(0, node);
    		node = node.getGoFrom();
    	}
    	
    	path.add(0, node);
    	
    	for(Node step: path) {
    		System.out.print(step + " ");
    	}
    	System.out.println();
    }
    
    public static List<Node> expandNode(Node node) {
    	Set<Node> paths = node.getTo();
    	List<Node> expandedPaths = new ArrayList<Node>();
    	
    	node.check();
    	
    	for(Node path: paths) {
    		boolean add = true;
    		if(path.isChecked()) {
        		add = false;
    		}
    		
    		float value = node.getPathValue(path) + node.getCurrentValue();
    		path.checkNode(node, value);
    		
    		if(add) {
    			System.out.println(path);
        		expandedPaths.add(path);
    		}
    	}
    	
    	return expandedPaths;
    }
    
    public static void AStarSearch(Node start) {
    	List<Node> queue = new ArrayList<Node>();
    	
    	queue.add(start);
    	while(true) {
    		Node node = queue.get(0);
    		System.out.println("A* " + node);
    		queue.remove(0);
    		
    		if(node.isEnd()) {
    			reconstructPath(node);
    			break;
    		}
    		
    		queue.addAll(expandNode(node));
    		
    		if(queue.isEmpty()) {
    			System.out.println("No possible path");
    			break;
    		}
    		
    		Collections.sort(queue);
    	}
    }

}
