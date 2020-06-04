package Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GraphNode extends Node {
	
	Circle point;
	Text info;
	
	public GraphNode(MouseEvent evt) {
		super(evt.getX(), evt.getY());
		generateCircle();
	}
	
	public void generateCircle() {
		point = new Circle(this.getX(), this.getY(), 35);
		
		point.setFill(Color.DODGERBLUE);
		point.setStroke(Color.BLACK);

		info = new Text(this.getX() - 10, this.getY() + 5, "0.0");
		info.setFont(new Font(14));
		
		setDrag();
	}
	
	public Circle getPoint() {
		return point;
	}
	
	public Text getInfo() {
		return info;
	}
	
	public void setDrag() {
		point.setOnMouseDragged(e -> {
			double tempx = e.getX();
			double tempy = e.getY();
			
			point.setCenterX(tempx);
			point.setCenterY(tempy);
			
			info.setX(e.getX() - 10);
			info.setY(e.getY() + 5);
		});
	}
	
	public void clearDrag() {
		point.setOnMouseDragged(null);
		point.setOnMouseReleased(null);
	}
	
	@Override
	public void check() {
		setStatus(Status.CHECKED);
		
		point.setFill(Color.TEAL);
		info.setText("" + this.getHeuristic());
	}
	
}
