package main.Algorithm;

import main.Grid.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class AStar {

    public static void reconstructPath(Node node) {
        List<Node> path = new ArrayList<Node>();

        while(true) {
            //System.out.println(node);
            path.add(0, node);

            if(node.isStart()) {
                node.setBlank();
                break;
            }

            if (!node.isEnd()){
                node.useNode();
            }
            node = node.getGoFrom();


        }

        for(Node step: path) {
            System.out.print(step + " ");
        }
        System.out.println();
    }

    public static List<Node> expandNode(Node node, Node end) {
        Set<Node> paths = node.getTo();
        List<Node> expandedPaths = new ArrayList<Node>();
        //System.out.println(node.getX() + " - " + node.getY() + " - " + node.getStatus());


        node.check();
        for(Node path: paths) {
            if(!path.isBlocked()) {
                boolean add = false;
                path.setEndDistance(end);

                if (path.isBlank() || path.isEnd()) {
                    add = true;
                }

                double value = node.getPathValue(path) + node.getCurrentValue();

                path.checkNode(node, value);


                if (add) {
                    //System.out.println(path);
                    expandedPaths.add(path);
                }
            }
        }

        return expandedPaths;
    }

    public static void AStarSearch(Node start, Node end) {
        if(start.equals(end)) {
            reconstructPath(start);
        } else {
            List<Node> queue = new ArrayList<Node>();
            start.setStart();
            end.setEnd();

            queue.add(start);
            while(true) {

                Node node = queue.get(0);
                //System.out.println("A* " + node);
                System.out.println("A* " + node.getX() + " - " + node.getY() + " " + node.getStatus());
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