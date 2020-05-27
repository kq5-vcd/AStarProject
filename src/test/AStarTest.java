package test;

import Algorithm.AStar;
import Node.Node;

public class AStarTest {

	public static void main(String[] args) {
		Node start = new Node("Start", 510f);
		start.setStart();
		
		Node end = new Node("End", 0f);
		end.setEnd();
		
		Node a1 = new Node("a1", 455f);
		start.setTo(a1, 192f);
		
		Node a2 = new Node("a2", 370.5f);
		start.setTo(a2, 177f);
		
		Node a3 = new Node("a3", 389f);
		start.setTo(a3, 309f);
		
		Node b1 = new Node("b1", 360.5f);
		a1.setTo(b1, 186.8f);
		b1.setTo(end, 360.5f);
		
		Node b2 = new Node("b2", 269f);
		a1.setTo(b2, 111.8f);
		a2.setTo(b2, 237.7f);
		b2.setTo(end, 269f);
		b1.setTo(b2, 128f);
		
		Node b3 = new Node("b3", 250.8f);
		a1.setTo(b3, 264.2f);
		b3.setTo(end, 250.8f);
		b2.setTo(b3, 247.6f);
		
		Node b4 = new Node("b4", 291.5f);
		a2.setTo(b4, 176.9f);
		b4.setTo(end, 291.5f);
		b3.setTo(b4, 170f);
		
		Node b5 = new Node("b5", 308f);
		a2.setTo(b5, 247f);
		a3.setTo(b5, 106.3f);
		b5.setTo(end, 308.f);
		
		AStar.AStarSearch(start);
	}

}
