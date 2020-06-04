package Graph;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.Node;
import javax.swing.JOptionPane;

import Node.GraphNode;

public class GraphDrawController {
	
//	private List<ANode> nodes = new ArrayList<ANode>();
//	private int numNode = 1;
//	private double tempx, tempy, x1,y1,x2,y2;
//	private boolean clickOne = true, clickTwo = false;
//	private Circle c1, c2;
	
	@FXML
	private Pane pane;
	@FXML
	private Label status;
	
	@FXML
	private void createNode() {
		status.setText("*Status: Click to create node");
		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				GraphNode node = new GraphNode(evt);

				pane.getChildren().add(node.getPoint());
				pane.getChildren().add(node.getInfo());
				
				pane.setOnMousePressed(null);
				status.setText("*Status: End creating a node");
			}
		});
	}
	
//	private void clearDrag() {
//		for(Node n : pane.getChildren()) {
//			n.setOnMouseDragged(null);
//			n.setOnMouseReleased(null);
//			
//		}
//		if(nodes.size()<numNode-1) {
//			ANode node = new ANode(numNode-1,tempx,tempy);
//			nodes.add(node);
//		}
//		
//	}
	
	@FXML
	private void createEdge() {
//		if(numNode>1) clearDrag();
////		for(ANode node : nodes) {
////			System.out.println(node.getNodeId());
////		}
////		for(Node node : pane.getChildren()) {
////			System.out.println(node.getId());
////		}
////		System.out.println(nodes.size());
//		status.setText("*Status: Click a node to create connection");
//		
//		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
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
//		});
	}
	
	@FXML
	private void selectStart() {
		
	}
	
	@FXML
	private void selectEnd() {
		
	}
	
	@FXML
	private void findPath() {
		
	}
	
	
	

}
