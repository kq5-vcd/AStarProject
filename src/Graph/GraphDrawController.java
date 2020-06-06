package Graph;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import Node.GraphNode;

public class GraphDrawController implements Initializable {
	
	private enum GraphStatus {
		BLANK,
		NODE
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
	private Label status;
	@FXML
	private Label endDistanceLabel;
	@FXML
	private Label distanceLabel;
	
	private List<GraphNode> nodes = new ArrayList<GraphNode>();
	private GraphNode currentNode;
	private GraphNode start;
	private GraphNode end;
	
	public void createNode() {
		status.setText("*Status: Click to create node");
		
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				GraphNode node = new GraphNode(evt);
				nodes.add(node);

				drawNode(node);
				chooseNode(node);
				
				refreshEnv();
				
				status.setText("*Status: End node creation");
			}
		});
	}
	
	public void moveNode() {
		status.setText("*Status: Drag node to desired location");
		
		currentNode.setDrag();
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
		for(GraphNode node: nodes) {
			node.clearDrag();
		}
		
//		if(numNode>1) clearDrag();
////		for(ANode node : nodes) {
////			System.out.println(node.getNodeId());
////		}
////		for(Node node : pane.getChildren()) {
////			System.out.println(node.getId());
////		}
////		System.out.println(nodes.size());
		status.setText("*Status: Click a node to create connection");
		
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			Circle c1, c2;
			boolean firstClick = true;
			boolean	secondClick = false;
			
			GraphNode node1, node2;

			@Override
			public void handle(MouseEvent evt) {
				if(firstClick) {
					c1 = (Circle) evt.getTarget();
					c1.setStroke(Color.RED);
					
					node1 = getNodeByCircle(c1);
					
					firstClick = false;
					secondClick = true;						
				} else if(secondClick) {
					status.setText("*Status: Click another node to set distance");
					c2 = (Circle) evt.getTarget();
					
					if(!c1.equals(c2)) {
						c1.setStroke(Color.BLACK);
						
						node2 = getNodeByCircle(c2);
						
						firstClick = true;
						secondClick = false;
						
						setEdge(node1, node2);
					}
				}
//						Line lreverse = new Line(x2,y2,x1,y1);
//						lreverse.setId(c2.getId()+c1.getId());
//						for(Node node : pane.getChildren()) {
//							if(node.getId()!=null)
//								if(node.getId().equals(l.getId())||node.getId().equals(lreverse.getId())) 
//									return;
//						}
//						
//						String inputDistance;
//						do{
//							inputDistance = JOptionPane.showInputDialog(null, "Please input distance:", "Input distance between two nodes", JOptionPane.INFORMATION_MESSAGE);
//							try {
//								double d = Double.parseDouble(inputDistance);
//								
//								for(ANode n : nodes) {
//									if(String.valueOf(n.getNodeId()).equals(c2.getId())) {
//										temp.setX(n.getX());
//										temp.setY(n.getY());
//										temp.setNodeId(n.getNodeId());
//									}
//								}
//								for(ANode n : nodes) {
//									if(String.valueOf(n.getNodeId()).equals(c1.getId())) {
//										n.setTo(temp, d);
//									}
//								}
//								for(ANode n : nodes) {
//									if(String.valueOf(n.getNodeId()).equals(c1.getId())) {
//										temp.setX(n.getX());
//										temp.setY(n.getY());
//										temp.setNodeId(n.getNodeId());
//									}
//								}
//								for(ANode n : nodes) {
//									if(String.valueOf(n.getNodeId()).equals(c2.getId())) {
//										n.setTo(temp, d);
//									}
//								}
//								break;
//							}catch(NumberFormatException e) {
//								JOptionPane.showMessageDialog(null, "Please input a number", "Invalid input", JOptionPane.ERROR_MESSAGE);
//							}
//						}while(true);
//						Text distance = new Text((x1+x2)/2,(y1+y2)/2,inputDistance);
//						distance.setFont(new Font(14));
//						pane.getChildren().add(l);
//						pane.getChildren().add(distance);
//						pane.setOnMousePressed(null);
//						if(!clickTwo) status.setText("*Status: End creating edge");
//					}
//			}	
			}
//			public void handle(MouseEvent evt) {
//				ANode temp = new ANode();
//				if(clickOne) {
//					c1 = (Circle) evt.getTarget();
//					c1.setStroke(Color.RED);
//					x1 = c1.getCenterX();
//					y1 = c1.getCenterY();
//					clickOne = false;
//					clickTwo = true;	
//					
//				}
//				else if(clickTwo) {
//						status.setText("*Status: Click another node to set distance");
//						c2 = (Circle) evt.getTarget();
//						if(!c1.equals(c2)) {
//							c1.setStroke(Color.BLACK);
//							x2 = c2.getCenterX();
//							y2 = c2.getCenterY();
//							clickOne = true;
//							clickTwo = false;
//							Line l = new Line(x1,y1,x2,y2);
//							l.setId(c1.getId()+c2.getId());
//							Line lreverse = new Line(x2,y2,x1,y1);
//							lreverse.setId(c2.getId()+c1.getId());
//							for(Node node : pane.getChildren()) {
//								if(node.getId()!=null)
//									if(node.getId().equals(l.getId())||node.getId().equals(lreverse.getId())) 
//										return;
//							}
//							
//							String inputDistance;
//							do{
//								inputDistance = JOptionPane.showInputDialog(null, "Please input distance:", "Input distance between two nodes", JOptionPane.INFORMATION_MESSAGE);
//								try {
//									double d = Double.parseDouble(inputDistance);
//									
//									for(ANode n : nodes) {
//										if(String.valueOf(n.getNodeId()).equals(c2.getId())) {
//											temp.setX(n.getX());
//											temp.setY(n.getY());
//											temp.setNodeId(n.getNodeId());
//										}
//									}
//									for(ANode n : nodes) {
//										if(String.valueOf(n.getNodeId()).equals(c1.getId())) {
//											n.setTo(temp, d);
//										}
//									}
//									for(ANode n : nodes) {
//										if(String.valueOf(n.getNodeId()).equals(c1.getId())) {
//											temp.setX(n.getX());
//											temp.setY(n.getY());
//											temp.setNodeId(n.getNodeId());
//										}
//									}
//									for(ANode n : nodes) {
//										if(String.valueOf(n.getNodeId()).equals(c2.getId())) {
//											n.setTo(temp, d);
//										}
//									}
//									break;
//								}catch(NumberFormatException e) {
//									JOptionPane.showMessageDialog(null, "Please input a number", "Invalid input", JOptionPane.ERROR_MESSAGE);
//								}
//							}while(true);
//							Text distance = new Text((x1+x2)/2,(y1+y2)/2,inputDistance);
//							distance.setFont(new Font(14));
//							pane.getChildren().add(l);
//							pane.getChildren().add(distance);
//							pane.setOnMousePressed(null);
//							if(!clickTwo) status.setText("*Status: End creating edge");
//						}
//				}	
//			}
		});
	}
	
	public void setEdge(GraphNode node1, GraphNode node2) {
		node1.setTo(node2);
		
	}
		
	public void findPath() {
		
	}
	
	public void showSteps() {
		
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
		
	}
	
	public void setDistance() {
		
	}
	
	public void setSingle() {
		
	}
	
	public void setDouble() {
		
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
		
	}
	
	public void setEnd() {
		if(endBox.isSelected()) {
			try {
				end.clearEnd();
			} catch(NullPointerException e) {}
			
			currentNode.endNode();
			end = currentNode;
		} else {
			currentNode.clearEnd();
			end = null;
		}
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
		canvas.setOnMousePressed(e -> {
			if(envStatus == GraphStatus.NODE) {
//				try {
					if(isOut(e.getX(), e.getY())) {
						clearEnv();
					} //else {
						
//					}
//				} catch (NullPointerException eNull) {
//					try {
//						Circle point;
//						
//						if(envStatus == GraphStatus.NODE) {
//							point = (Circle) e.getTarget();
//							
//							if(!point.equals(null)) {
//								envStatus = GraphStatus.NODE;
//								
//								point.setOnMouseClicked(null);
//								
//								currentNode = getNodeByCircle(point);
//								currentNode.selectNode();
//								moveNodeBtn.setDisable(false);
//							}
//						}
//					} catch(ClassCastException eCast) {
//						moveNodeBtn.setDisable(true);
//						endDistanceLabel.setDisable(true);
//						endDistanceField.setDisable(true);
//						createEdgeBtn.setDisable(true);
//						singleBox.setDisable(true);
//						doubleBox.setDisable(true);
//						distanceLabel.setDisable(true);
//						distanceField.setDisable(true);
//						startBox.setDisable(true);
//						endBox.setDisable(true);
//						deleteNodeBtn.setDisable(true);
//					}
					
//				}
			} else {
				try {
					Circle point;
					
					if(envStatus == GraphStatus.NODE) {
						point = (Circle) e.getTarget();
						
						if(!point.equals(null)) {
							envStatus = GraphStatus.NODE;
							
							point.setOnMouseClicked(null);
							
							currentNode = getNodeByCircle(point);
							chooseNode(currentNode);
						}
					}
				} catch(ClassCastException eCast) {
					disableAll();
				}
			}
			
		});
		
//		canvas.setOnMousePressed(e -> {
//			Circle point;
//
//		
//			if(envStatus == GraphStatus.NODE) {
//				point = (Circle) e.getTarget();
//				
//				if(!point.equals(null)) {
//					envStatus = GraphStatus.NODE;
//					
//					point.setOnMouseClicked(null);
//					
//					currentNode = getNodeByCircle(point);
//					currentNode.selectNode();
//					moveNodeBtn.setDisable(false);
//				}
//			}
//		});
	}
	
	public void chooseNode(GraphNode node) {
		envStatus = GraphStatus.NODE;
		
		try {
			if(!node.equals(currentNode)) {
				currentNode.deselectNode();
			}
		} catch(NullPointerException e) {}
		
		currentNode = node;
		currentNode.selectNode();
		
		moveNodeBtn.setDisable(false);
		startBox.setDisable(false);
		endBox.setDisable(false);
		deleteNodeBtn.setDisable(false);
		
		if(currentNode.isStart()) {
			startBox.setSelected(true);
		}
		if(currentNode.isEnd()) {
			endBox.setSelected(true);
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
		
		currentNode.deselectNode();
		currentNode = null;
		
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
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

}
