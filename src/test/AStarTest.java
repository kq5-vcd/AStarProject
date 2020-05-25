package test;

import Algorithm.AStar;
import Algorithm.Node;

public class AStarTest {

	public static void main(String[] args) {
		Node start = new Node("Start", 200f);
		start.setStart();
		
		Node end = new Node("End", 0f);
		end.setEnd();
		
		Node n1 = new Node("n1", 80f);
		start.setTo(n1, 30f);
		n1.setTo(end, 30f);
		
		Node n2 = new Node("n2", 120f);
		start.setTo(n2, 40f);
		n2.setTo(end, 50f);
		
		AStar.AStarSearch(start);
	}

}
