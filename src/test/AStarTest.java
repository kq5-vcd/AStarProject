package test;

import Algorithm.AStar;
import Node.Node;

public class AStarTest {

	public static void main(String[] args) {
		Node start = new Node(200, 300);
		
		Node end = new Node(500, 700);
		
		Node a1 = new Node(210, 455);
		start.setTo(a1);
		
		Node a2 = new Node(100, 370.5);
		start.setTo(a2);
		
		Node a3 = new Node(150, 389);
		start.setTo(a3);
		
		Node b1 = new Node(200, 360.5);
		a1.setTo(b1, 186.8f);
		b1.setTo(end, 360.5f);
		
		Node b2 = new Node(177, 269);
		a1.setTo(b2);
		a2.setTo(b2);
		b2.setTo(end);
		b1.setTo(b2);
		
		Node b3 = new Node(50, 250.8);
		a1.setTo(b3);
		b3.setTo(end);
		b2.setTo(b3);
		
		Node b4 = new Node(333, 291.5f);
		a2.setTo(b4);
		b4.setTo(end);
		b3.setTo(b4);
		
		Node b5 = new Node(400, 308f);
		a2.setTo(b5);
		a3.setTo(b5);
		b5.setTo(end);
		
		AStar.AStarSearch(start, end);
	}

}
