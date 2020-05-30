package AStarGraph;
import AStarGraph.Neighbor;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
import AStarAlgorithm.ANode;
import javafx.scene.paint.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

public class Node {
	
	private ANode node;
	private double heuristic, x, y;
	private String id;
	
	
	public Node(String id, Circle c , String h) {
		this.id = id;
		this.x = c.getCenterX();
		this.y = c.getCenterY();
		node = new ANode(Integer.parseInt(id),x,y);
		this.heuristic = Double.parseDouble(h);
	}
	
	

}
