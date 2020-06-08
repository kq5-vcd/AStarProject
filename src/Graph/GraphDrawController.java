package Graph;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Exception.PopUp;
import Algorithm.AStar;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import Node.GraphNode;
import application.MainApp;

public class GraphDrawController implements Initializable {
	
	private enum GraphStatus {
		BLANK,
		NODE,
		EDGE
	}
	private GraphStatus envStatus;
	
	@FXML
	private Button createNodeBtn;
	@FXML
	private Button moveNodeBtn;
	@FXML
	private Button createEdgeBtn;
	@FXML
	private Button deleteNodeBtn;
	@FXML
	private Button findPathBtn;
	@FXML
	private Button showStepsBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private TextField endDistanceField;
	@FXML
	private TextField distanceField;
	@FXML
	private CheckBox singleBox;
	@FXML
	private CheckBox doubleBox;
	@FXML
	private CheckBox startBox;
	@FXML
	private CheckBox endBox;
	@FXML
	private Pane canvas;
	@FXML
	private Label endDistanceLabel;
	@FXML
	private Label distanceLabel;
	
	private List<GraphNode> nodes = new ArrayList<GraphNode>();
	private GraphNode currentNode;
	private GraphNode tempNode;
	private GraphNode start;
	private GraphNode end;
	
	private boolean bothDirection;
	
	public void createNode() {
		canvas.setOnMousePressed(e -> {
			GraphNode node = new GraphNode(e);
			nodes.add(node);
			
			if(end != null) {
				node.setEndDistance(end);
			}

			drawNode(node);
			chooseNode(node);
			
			refreshEnv();
		});
	}
	
	public void moveNode() {
		currentNode.setDrag();
		refreshEnv();
	}
	
	public void eraseNode(GraphNode node) {
		canvas.getChildren().remove(node.getPoint());
		canvas.getChildren().remove(node.getInfo());
	}
	
	public void drawNode(GraphNode node) {
		canvas.getChildren().add(node.getPoint());
		canvas.getChildren().add(node.getInfo());
	}
	
	public void createEdge() {
		currentNode.clearDrag();
		
		singleBox.setDisable(false);
		doubleBox.setDisable(false);
	}
	
	public void setEdge(GraphNode node1, GraphNode node2, boolean direction) {
		if(!node1.equals(node2)) {
			if(!node1.searchConnection(node2)) {
				node1.setTo(node2);
				
				if(direction) {
					node2.setTo(node1);
				}
				
				eraseNode(node1);
				eraseNode(node2);
				
				Line edge = new Line(node1.getX(), node1.getY(), node2.getX(), node2.getY());
				
				node1.setLine(edge);
				node2.setLine(edge);
				
				canvas.getChildren().add(edge);
				drawNode(node1);
				drawNode(node2);
				
				envStatus = GraphStatus.NODE;
				refreshEnv();
			}
			
			disableAll();
				
			distanceLabel.setDisable(false);
			distanceField.setDisable(false);
			distanceField.setText(Math.round(node1.getDistance(node2)) + "");
		}
	}
		
	public void findPath() {
		for(GraphNode node: nodes) {
			if(!node.isStart() && !node.isEnd()) {
				node.clearNode();
			}
		}
		
		AStar.AStarSearch(start, end);
		showStepsBtn.setDisable(false);
	}
	
	public void showSteps() {
		for(GraphNode node: nodes) {
			node.displayStep();
		}
	}
	
	public GraphNode getNodeByCircle(Circle point) {
		for(GraphNode node: nodes) {
			if(point.equals(node.getPoint())) {
				return node;
			}
		}
		
		return null;
	}
	
	public void setEndDistance() {
		try {
			double heuristic = Double.parseDouble(endDistanceField.getText());
			if(heuristic <= 0) {
				PopUp p = new PopUp();
				p.popUp();
			}
			else currentNode.setEndDistance(heuristic);
		}catch(NumberFormatException e) {
			PopUp p = new PopUp();
			p.popUp();
		}
	}
	
	public void setDistance() {
		try {
			double value = Double.parseDouble(distanceField.getText());
			if(value <= 0) {
				PopUp p = new PopUp();
				p.popUp();
			}
			else {
				currentNode.setTo(tempNode, value);
				if(bothDirection) {
					tempNode.setTo(currentNode);
				}
			}
		}catch(NumberFormatException e) {
			PopUp p = new PopUp();
			p.popUp();
		}	
	}
	
	public void setSingle() {
		if(doubleBox.isSelected()) {
			doubleBox.setSelected(false);
		}
		
		envStatus = GraphStatus.EDGE;
		System.out.println("Create edge");
		
		for(GraphNode node: nodes) {
			node.getPoint().setOnMouseClicked(null);
			node.getPoint().setOnMousePressed(null);
		}
		refreshEnv();
	}
	
	public void setDouble() {
		if(singleBox.isSelected()) {
			singleBox.setSelected(false);
		}
		
		envStatus = GraphStatus.EDGE;
		System.out.println("Create edge");
		
		for(GraphNode node: nodes) {
			node.getPoint().setOnMouseClicked(null);
			node.getPoint().setOnMousePressed(null);
		}
		refreshEnv();
	}
	
	public void setStart() {
		if(startBox.isSelected()) {
			try {
				start.clearStart();
			} catch(NullPointerException e) {}
			
			currentNode.startNode();
			start = currentNode;
		} else {
			currentNode.clearStart();
			start = null;
		}
		
		refreshEnv();
	}
	
	public void setEnd() {
		if(endBox.isSelected()) {
			try {
				end.clearEnd();
			} catch(NullPointerException e) {}
			
			for(GraphNode node: nodes) {
				node.setEndDistance(currentNode);
			}
			
			currentNode.endNode();
			end = currentNode;
		} else {
			currentNode.clearEnd();
			end = null;
		}
		
		refreshEnv();
	}
	
	public void deleteNode() {
		if(currentNode.isStart()) {
			currentNode.clearStart();
			start = null;
		}
		
		if(currentNode.isEnd()) {
			currentNode.clearEnd();
			end = null;
		}
		
		eraseNode(currentNode);
		nodes.remove(currentNode);
		
		clearEnv();
	}

	public void refreshEnv() {
		if(start != null && end != null) {
			findPathBtn.setDisable(false);
		} else {
			findPathBtn.setDisable(true);
		}
		
		canvas.setOnMousePressed(e -> {
			if(envStatus == GraphStatus.NODE) {
				if(isOut(e.getX(), e.getY())) {
					clearEnv();
				}
			} else if(envStatus == GraphStatus.EDGE) {
				System.out.println("Edge");
				if(isOut(e.getX(), e.getY())) {
					System.out.println("Edge");
					try {
						Circle point;
						bothDirection = true;
						
						if(singleBox.isSelected()) {
							bothDirection = false;
						}
						
						point = (Circle) e.getTarget();
						point.setOnMouseClicked(null);
						
						currentNode.deselectNode();
							
						tempNode = getNodeByCircle(point);
						
						setEdge(currentNode, tempNode, bothDirection);
					} catch(ClassCastException eCast) {
						clearEnv();
					}
				}
			} else {
				try {
					Circle point;
					
					point = (Circle) e.getTarget();
					
					envStatus = GraphStatus.NODE;
						
					point.setOnMouseClicked(null);
						
					currentNode = getNodeByCircle(point);
					chooseNode(currentNode);
				} catch(ClassCastException eCast) {
					disableAll();
				}
			}
			
		});
	}
	
	public void chooseNode(GraphNode node) {
		envStatus = GraphStatus.NODE;
		
		if(currentNode != null) {
			if(!node.equals(currentNode)) {
				currentNode.deselectNode();
				refreshEnv();
			}
		}
		
		if(end != null) {
			if(!node.equals(end)) {
				endDistanceLabel.setDisable(false);
				endDistanceField.setDisable(false);
				
				endDistanceField.setText(Math.round(node.getEndDistance()) + "");
			}
		}
		
		currentNode = node;
		currentNode.selectNode();
		
		if(!node.hasConnections()) {
			moveNodeBtn.setDisable(false);
			deleteNodeBtn.setDisable(false);
		}
		
		startBox.setDisable(false);
		endBox.setDisable(false);
		
		if(currentNode.isStart()) {
			startBox.setSelected(true);
		} else {
			startBox.setSelected(false);
		}
		if(currentNode.isEnd()) {
			endBox.setSelected(true);
		} else {
			endBox.setSelected(false);
		}
		
		if(nodes.size() > 1) {
			createEdgeBtn.setDisable(false);
		}
	}
	
	public boolean isOut(double x, double y) {
		double xDiff = x - currentNode.getX();
		double yDiff = y - currentNode.getY();
		
		double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		
		if(dist > 35) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clearEnv() {
		envStatus = GraphStatus.BLANK;
		
		for(GraphNode node: nodes) {
			node.clearDrag();
			node.getPoint().setOnMouseClicked(e -> {
				chooseNode(node);
			});
		}
		
		if(currentNode != null) {
			currentNode.deselectNode();
			currentNode = null;
		}
		
		tempNode = null;
		
		disableAll();
	}
	
	public void disableAll() {
		moveNodeBtn.setDisable(true);
		endDistanceLabel.setDisable(true);
		endDistanceField.setDisable(true);
		createEdgeBtn.setDisable(true);
		singleBox.setDisable(true);
		doubleBox.setDisable(true);
		distanceLabel.setDisable(true);
		distanceField.setDisable(true);
		startBox.setDisable(true);
		endBox.setDisable(true);
		deleteNodeBtn.setDisable(true);
		
		singleBox.setSelected(false);
		doubleBox.setSelected(false);
		startBox.setSelected(false);
		endBox.setSelected(false);
		
		distanceField.setText("");
		endDistanceField.setText("");
		
		showStepsBtn.setDisable(true);
	}
	
	public void reset() {
		nodes.clear();
		start = null;
		end = null;
		
		canvas.getChildren().clear();
		clearEnv();
	}
	
	public void rooftop() {
		//System.out.println("End");
		reset();
		MainApp.getHomeController().home();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//System.out.println(MainApp.getHomeController());
	}

}
