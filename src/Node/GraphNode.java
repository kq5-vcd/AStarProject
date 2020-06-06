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
		
		point.setOnMouseClicked(null);

		info = new Text(this.getX() - 10, this.getY() + 5, "0.0");
		info.setFont(new Font(14));
	}
	
	public Circle getPoint() {
		return point;
	}
	
	public Text getInfo() {
		return info;
	}
	
	public void setDrag() {
		point.setOnMouseClicked(null);
		point.setOnMousePressed(null);
		
		point.setOnMouseDragged(e -> {
			if(e.getX() > 0) {
				point.setCenterX(e.getX());
				info.setX(e.getX() - 10);
				setX(e.getX());
			}
			
			if(e.getY() > 0) {
				point.setCenterY(e.getY());
				info.setY(e.getY() + 5);
				setY(e.getY());
			}
		});
		
		point.setOnMouseReleased(e -> {
			clearDrag();
		});
	}
	
	public void clearDrag() {
		point.setOnMouseDragged(null);
		point.setOnMouseReleased(null);
	}
	
	public void selectNode() {
		point.setStroke(Color.RED);
		point.setOnMousePressed(null);
		point.setOnMouseClicked(null);
		//System.out.println(getNodeId() + " selected");
	}
	
	public void deselectNode() {
		point.setStroke(Color.BLACK);
		//System.out.println(getNodeId() + " deselected");
	}
	
	public void startNode() {
		setStart();
		
		point.setFill(Color.RED);
		info.setText("Start");
	}
	
	public void endNode() {
		setEnd();
		
		point.setFill(Color.RED);
		info.setText("End");
	}
	
	public void clearStart() {
		removeStart();
		
		if(!isEnd()) {
			point.setFill(Color.DODGERBLUE);
			info.setText("0.0");
		} else {
			info.setText("End");
		}
	}
	
	public void clearEnd() {
		removeEnd();
		
		if(!isStart()) {
			point.setFill(Color.DODGERBLUE);
			info.setText("0.0");
		} else {
			info.setText("Start");
		}
	}
	
	@Override
	public void check() {
		setStatus(Status.CHECKED);
		
		point.setFill(Color.TEAL);
		info.setText("" + this.getHeuristic());
	}
	
}
