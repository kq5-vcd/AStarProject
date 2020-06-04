package Graph;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Node.Node;

public class TravelPoint extends Circle {
	
	private Node node;
	Text info;
	
	public TravelPoint(MouseEvent evt) {
		super(evt.getX(), evt.getY(), 35);
		info = new Text(evt.getX() - 10, evt.getY() + 5, "0.0");
		info.setFont(new Font(14));
		setFill(Color.DODGERBLUE);
		setStroke(Color.BLACK);
		
		node = new Node(this.getCenterX(), this.getCenterY());
	}
	
}
