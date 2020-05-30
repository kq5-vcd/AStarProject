package AStarGraph;
import AStarGraph.Node;

import AStarAlgorithm.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javax.swing.JOptionPane;


public class GraphController {
	
	@FXML
	private ChoiceBox<Integer> startNode;
	@FXML
	private ChoiceBox<Integer> endNode;
	@FXML
	private Circle S;
	@FXML
	private Circle A1;
	@FXML
	private Circle A2;
	@FXML
	private Circle A3;
	@FXML
	private Circle B1;
	@FXML
	private Circle B2;
	@FXML
	private Circle B3;
	@FXML
	private Circle B4;
	@FXML
	private Circle B5;
	@FXML
	private Circle C1;
	@FXML
	private Circle C2;
	@FXML
	private Circle C3;
	@FXML
	private Circle C4;
	@FXML
	private Circle C5;
	@FXML
	private Circle E;
	
	@FXML
	private Text hS;
	@FXML
	private Text hA1;
	@FXML
	private Text hA2;
	@FXML
	private Text hA3;
	@FXML
	private Text hB1;
	@FXML
	private Text hB2;
	@FXML
	private Text hB3;
	@FXML
	private Text hB4;
	@FXML
	private Text hB5;
	@FXML
	private Text hC1;
	@FXML
	private Text hC2;
	@FXML
	private Text hC3;
	@FXML
	private Text hC4;
	@FXML
	private Text hC5;
	@FXML
	private Text hE;
	
	@FXML
	private Line SA1;
	@FXML
	private Line SA2;
	@FXML
	private Line SA3;
	@FXML
	private Line A1B1;
	@FXML
	private Line A1B2;
	@FXML
	private Line A1B3;
	@FXML
	private Line A2B3;
	@FXML
	private Line A2B4;
	@FXML
	private Line A2B5;
	@FXML
	private Line A3B5;
	@FXML
	private Line B1B2;
	@FXML
	private Line B2B3;
	@FXML
	private Line B3B4;
	@FXML
	private Line B1C1;
	@FXML
	private Line B2C1;
	@FXML
	private Line B3C2;
	@FXML
	private Line B3E;
	@FXML
	private Line B3C3;
	@FXML
	private Line B4C4;
	@FXML
	private Line B5C4;
	@FXML
	private Line B5C5;
	@FXML
	private Line C1C2;
	@FXML
	private Line C1E;
	@FXML
	private Line C2E;
	@FXML
	private Line C3E;
	@FXML
	private Line C4E;
	@FXML
	private Line C5C4;
	@FXML
	private Line C5E;
	
	@FXML
	private Label printPath;
	
	
	private DisplayGraph graph;
	
	private List<Node> nodeData;
	
	private double endx, endy, startx, starty;
	
	
	ANode n1 = new ANode("1",0d);
	ANode n2 = new ANode("2",0d);
	ANode n3 = new ANode("3",0d);
	ANode n4 = new ANode("4",0d);
	ANode n5 = new ANode("5",0d);
	ANode n6 = new ANode("6",0d);
	ANode n7 = new ANode("7",0d);
	ANode n8 = new ANode("8",0d);
	ANode n9 = new ANode("9",0d);
	ANode n10 = new ANode("10",0d);
	ANode n11 = new ANode("11",0d);
	ANode n12 = new ANode("12",0d);
	ANode n13 = new ANode("13",0d);
	ANode n14 = new ANode("14",0d);
	ANode n15 = new ANode("15",0d);
	double h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15;
	String s,e;
	List<ANode> graphNode = new ArrayList<ANode>();
	List<String> path = new ArrayList<String>();
	
	
	private ArrayList<Neighbor> neighbor1 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor2 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor3 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor4 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor5 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor6 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor7 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor8 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor9 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor10 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor11 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor12 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor13 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor14 = new ArrayList<Neighbor>();
	private ArrayList<Neighbor> neighbor15 = new ArrayList<Neighbor>();
	
	
	private ObservableList<Integer> listNode = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
	
	
	
	public void setGraph(DisplayGraph graph) {
		this.graph = graph;
	}
	
	@FXML
    private void initialize() {
		
		setChoiceBox();
		n1.setTo(n2, getEdge(S,A1));
		n1.setTo(n3, getEdge(S,A2));
		n1.setTo(n4, getEdge(S,A3));
		n2.setTo(n1, getEdge(S,A1));
		n2.setTo(n5, getEdge(A1,B1));
		n2.setTo(n6, getEdge(A1,B2));
		n2.setTo(n7, getEdge(A1,B3));
		n3.setTo(n1, getEdge(S,A2));
		n3.setTo(n7, getEdge(A2,B3));
		n3.setTo(n8, getEdge(A2,B4));
		n3.setTo(n9, getEdge(A2,B5));
		n4.setTo(n1, getEdge(S,A3));
		n4.setTo(n9, getEdge(A3,B5));
		n5.setTo(n2, getEdge(A1,B1));
		n5.setTo(n6, getEdge(B1,B2));
		n5.setTo(n10, getEdge(B1,C1));
		n6.setTo(n2, getEdge(A1,B2));
		n6.setTo(n5, getEdge(B1,B2));
		n6.setTo(n7, getEdge(B2,B3));
		n6.setTo(n10, getEdge(B2,C1));
		n7.setTo(n2, getEdge(A1,B3));
		n7.setTo(n3, getEdge(A2,B3));
		n7.setTo(n6, getEdge(B2,B3));
		n7.setTo(n8, getEdge(B3,B4));
		n7.setTo(n11, getEdge(B3,C2));
		n7.setTo(n12, getEdge(B3,C3));
		n7.setTo(n15, getEdge(B3,E));
		n8.setTo(n3, getEdge(B4,A2));
		n8.setTo(n7, getEdge(B3,B4));
		n8.setTo(n13, getEdge(B4,C4));
		n9.setTo(n3, getEdge(A2,B5));
		n9.setTo(n4, getEdge(A3,B5));
		n9.setTo(n13, getEdge(B5,C4));
		n9.setTo(n14, getEdge(B5,C5));
		n10.setTo(n5, getEdge(B1,C1));
		n10.setTo(n6, getEdge(B2,C1));
		n10.setTo(n11, getEdge(C1,C2));
		n10.setTo(n15, getEdge(C1,E));
		n11.setTo(n7, getEdge(C2,B3));
		n11.setTo(n10, getEdge(C1,C2));
		n11.setTo(n15, getEdge(C2,E));
		n12.setTo(n7, getEdge(B3,C3));
		n12.setTo(n15, getEdge(C3,E));
		n13.setTo(n8, getEdge(B4,C4));
		n13.setTo(n9, getEdge(B5,C4));
		n13.setTo(n14, getEdge(C5,C4));
		n13.setTo(n15, getEdge(E,C4));
		n14.setTo(n9, getEdge(B5,C5));
		n14.setTo(n13, getEdge(C4,C5));
		n14.setTo(n15, getEdge(E,C5));
		n15.setTo(n7, getEdge(E,B3));
		n15.setTo(n10, getEdge(E,C1));
		n15.setTo(n11, getEdge(E,C2));
		n15.setTo(n12, getEdge(E,C3));
		n15.setTo(n13, getEdge(E,C4));
		n15.setTo(n14, getEdge(E,C5));
		
	}
	
	private boolean getChoiceBox() {
		int sN = startNode.getSelectionModel().getSelectedItem();
		//System.out.println(sN);
		//if(sN != 1 && sN != 2 && sN != 3 && sN != 4 && sN != 5 && sN != 6 && sN != 7 && sN != 8 && sN != 9 && sN != 10 && sN != 11 && sN != 12 && sN != 13 && sN != 14 && sN != 15) {
		if(null == startNode.getSelectionModel().getSelectedItem()) {
			JOptionPane.showMessageDialog(null, "Please choose a node", "Invalid Node", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		int eN = endNode.getSelectionModel().getSelectedItem();
		//System.out.println(eN);
		if(eN != 1 && eN != 2 && eN != 3 && eN != 4 && eN != 5 && eN != 6 && eN != 7 && eN != 8 && eN != 9 && eN != 10 && eN != 11 && eN != 12 && eN != 13 && eN != 14 && eN != 15) {
			JOptionPane.showMessageDialog(null, "Please choose a node", "Invalid Node", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		switch(sN) {
		case 1:
			startx = S.getCenterX();
			starty = S.getCenterY();
			s = S.getId();
			break;
		case 2:
			startx = A1.getCenterX();
			starty = A1.getCenterY();
			s = A1.getId();
			break;
		case 3:
			startx = A2.getCenterX();
			starty = A2.getCenterY();
			s = A2.getId();
			break;
		case 4:
			startx = A3.getCenterX();
			starty = A3.getCenterY();
			s = A3.getId();
			break;
		case 5:
			startx = B1.getCenterX();
			starty = B1.getCenterY();
			s = B1.getId();
			break;
		case 6:
			startx = B2.getCenterX();
			starty = B2.getCenterY();
			s = B2.getId();
			break;
		case 7:
			startx = B3.getCenterX();
			starty = B3.getCenterY();
			s = B3.getId();
			break;
		case 8:
			startx = B4.getCenterX();
			starty = B4.getCenterY();
			s = B4.getId();
			break;
		case 9:
			startx = B5.getCenterX();
			starty = B5.getCenterY();
			s = B5.getId();
			break;
		case 10:
			startx = C1.getCenterX();
			starty = C1.getCenterY();
			s = C1.getId();
			break;
		case 11:
			startx = C2.getCenterX();
			starty = C2.getCenterY();
			s = C2.getId();
			break;
		case 12:
			startx = C3.getCenterX();
			starty = C3.getCenterY();
			s = C3.getId();
			break;
		case 13:
			startx = C4.getCenterX();
			starty = C4.getCenterY();
			s = C4.getId();
			break;
		case 14:
			startx = C5.getCenterX();
			starty = C5.getCenterY();
			s = C5.getId();
			break;
		case 15:
			startx = E.getCenterX();
			starty = E.getCenterY();
			s = E.getId();
			break;
		}
		switch(eN) {
		case 1:
			endx = S.getCenterX();
			endy = S.getCenterY();
			e = S.getId();
			break;
		case 2:
			endx = A1.getCenterX();
			endy = A1.getCenterY();
			e = A1.getId();
			break;
		case 3:
			endx = A2.getCenterX();
			endy = A2.getCenterY();
			e = A2.getId();
			break;
		case 4:
			endx = A3.getCenterX();
			endy = A3.getCenterY();
			e = A3.getId();
			break;
		case 5:
			endx = B1.getCenterX();
			endy = B1.getCenterY();
			e = B1.getId();
			break;
		case 6:
			endx = B2.getCenterX();
			endy = B2.getCenterY();
			e = B2.getId();
			break;
		case 7:
			endx = B3.getCenterX();
			endy = B3.getCenterY();
			e = B3.getId();
			break;
		case 8:
			endx = B4.getCenterX();
			endy = B4.getCenterY();
			e = B4.getId();
			break;
		case 9:
			endx = B5.getCenterX();
			endy = B5.getCenterY();
			e = B5.getId();
			break;
		case 10:
			endx = C1.getCenterX();
			endy = C1.getCenterY();
			e = C1.getId();
			break;
		case 11:
			endx = C2.getCenterX();
			endy = C2.getCenterY();
			e = C2.getId();
			break;
		case 12:
			endx = C3.getCenterX();
			endy = C3.getCenterY();
			e = C3.getId();
			break;
		case 13:
			endx = C4.getCenterX();
			endy = C4.getCenterY();
			e = C4.getId();
			break;
		case 14:
			endx = C5.getCenterX();
			endy = C5.getCenterY();
			e = C5.getId();
			break;
		case 15:
			endx = E.getCenterX();
			endy = E.getCenterY();
			e = E.getId();
			break;
		}
		return true;
	}
	
	private void setChoiceBox() {
		
		startNode.setItems(listNode);
		endNode.setItems(listNode);
//		startNode = new ChoiceBox<int[]>(listNode);
//		endNode = new ChoiceBox<int[]>(listNode);
	}
	
	
	private void getHeuristicValue() {
		h1 = Math.sqrt(Math.pow(endx-S.getCenterX(), 2) + Math.pow(endy-S.getCenterY(), 2));	h1 = Math.round(h1*10.0)/10.0;
		h2 = Math.sqrt(Math.pow(endx-A1.getCenterX(), 2) + Math.pow(endy-A1.getCenterY(), 2));	h2 = Math.round(h2*10.0)/10.0;
		h3 = Math.sqrt(Math.pow(endx-A2.getCenterX(), 2) + Math.pow(endy-A2.getCenterY(), 2));	h3 = Math.round(h3*10.0)/10.0;
		h4 = Math.sqrt(Math.pow(endx-A3.getCenterX(), 2) + Math.pow(endy-A3.getCenterY(), 2));	h4 = Math.round(h4*10.0)/10.0;
		h5 = Math.sqrt(Math.pow(endx-B1.getCenterX(), 2) + Math.pow(endy-B1.getCenterY(), 2));	h5 = Math.round(h5*10.0)/10.0;
		h6 = Math.sqrt(Math.pow(endx-B2.getCenterX(), 2) + Math.pow(endy-B2.getCenterY(), 2));	h6 = Math.round(h6*10.0)/10.0;
		h7 = Math.sqrt(Math.pow(endx-B3.getCenterX(), 2) + Math.pow(endy-B3.getCenterY(), 2));	h7 = Math.round(h7*10.0)/10.0;
		h8 = Math.sqrt(Math.pow(endx-B4.getCenterX(), 2) + Math.pow(endy-B4.getCenterY(), 2));	h8 = Math.round(h8*10.0)/10.0;
		h9 = Math.sqrt(Math.pow(endx-B5.getCenterX(), 2) + Math.pow(endy-B5.getCenterY(), 2));	h9 = Math.round(h9*10.0)/10.0;
		h10 = Math.sqrt(Math.pow(endx-C1.getCenterX(), 2) + Math.pow(endy-C1.getCenterY(), 2));	h10 = Math.round(h10*10.0)/10.0;
		h11 = Math.sqrt(Math.pow(endx-C2.getCenterX(), 2) + Math.pow(endy-C2.getCenterY(), 2));	h11 = Math.round(h11*10.0)/10.0;
		h12 = Math.sqrt(Math.pow(endx-C3.getCenterX(), 2) + Math.pow(endy-C3.getCenterY(), 2));	h12 = Math.round(h12*10.0)/10.0;
		h13 = Math.sqrt(Math.pow(endx-C4.getCenterX(), 2) + Math.pow(endy-C4.getCenterY(), 2));	h13 = Math.round(h13*10.0)/10.0;
		h14 = Math.sqrt(Math.pow(endx-C5.getCenterX(), 2) + Math.pow(endy-C5.getCenterY(), 2));	h14 = Math.round(h14*10.0)/10.0;
		h15 = Math.sqrt(Math.pow(endx-E.getCenterX(), 2) + Math.pow(endy-E.getCenterY(), 2));	h15 = Math.round(h15*10.0)/10.0;
		hS.setText(String.valueOf(h1));
		hA1.setText(String.valueOf(h2));
		hA2.setText(String.valueOf(h3));
		hA3.setText(String.valueOf(h4));
		hB1.setText(String.valueOf(h5));
		hB2.setText(String.valueOf(h6));
		hB3.setText(String.valueOf(h7));
		hB4.setText(String.valueOf(h8));
		hB5.setText(String.valueOf(h9));
		hC1.setText(String.valueOf(h10));
		hC2.setText(String.valueOf(h11));
		hC3.setText(String.valueOf(h12));
		hC4.setText(String.valueOf(h13));
		hC5.setText(String.valueOf(h14));
		hE.setText(String.valueOf(h15));
	}
	
	private double getEdge(Circle c1, Circle c2) {
		double g = Math.sqrt(Math.pow(c1.getCenterX()-c2.getCenterX(), 2) + Math.pow(c1.getCenterY()-c2.getCenterY(), 2));
		return g;
	}
	
	private void pathColor(List<String> path) {
		String label = "Path: ";
		for(String str : path) {
			if(str.equals(S.getId())) {
				S.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(A1.getId())) {
				A1.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(A2.getId())) {
				A2.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(A3.getId())) {
				A3.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(B1.getId())) {
				B1.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(B2.getId())) {
				B2.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(B3.getId())) {
				B3.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(B4.getId())) {
				B4.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(B5.getId())) {
				B5.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(C1.getId())) {
				C1.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(C2.getId())) {
				C2.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(C3.getId())) {
				C3.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(C4.getId())) {
				C4.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(C5.getId())) {
				C5.setFill(Paint.valueOf("#1fff23"));
			}
			else if(str.equals(E.getId())) {
				E.setFill(Paint.valueOf("#1fff23"));
			}
			
			label += str + " -> "; 
			
		}
		int n = label.length() - 4;
		printPath.setText(label.substring(0, n));
	}
	
	private void refreshColor() {
		S.setFill(Paint.valueOf("DODGERBLUE"));
		A1.setFill(Paint.valueOf("DODGERBLUE"));
		A2.setFill(Paint.valueOf("DODGERBLUE"));
		A3.setFill(Paint.valueOf("DODGERBLUE"));
		B1.setFill(Paint.valueOf("DODGERBLUE"));
		B2.setFill(Paint.valueOf("DODGERBLUE"));
		B3.setFill(Paint.valueOf("DODGERBLUE"));
		B4.setFill(Paint.valueOf("DODGERBLUE"));
		B5.setFill(Paint.valueOf("DODGERBLUE"));
		C1.setFill(Paint.valueOf("DODGERBLUE"));
		C2.setFill(Paint.valueOf("DODGERBLUE"));
		C3.setFill(Paint.valueOf("DODGERBLUE"));
		C4.setFill(Paint.valueOf("DODGERBLUE"));
		C5.setFill(Paint.valueOf("DODGERBLUE"));
		E.setFill(Paint.valueOf("DODGERBLUE"));
	}
	
	private void refreshStatus() {
		n1.setBlankStatus();
		n2.setBlankStatus();
		n3.setBlankStatus();
		n4.setBlankStatus();
		n5.setBlankStatus();
		n6.setBlankStatus();
		n7.setBlankStatus();
		n8.setBlankStatus();
		n9.setBlankStatus();
		n10.setBlankStatus();
		n11.setBlankStatus();
		n12.setBlankStatus();
		n13.setBlankStatus();
		n14.setBlankStatus();
		n15.setBlankStatus();
		
	}
	
	@FXML
	private void findPath() {
		refreshColor();
		refreshStatus();
		if(getChoiceBox()) {
			getHeuristicValue();

			n1.setEndDistance(h1);
			n2.setEndDistance(h2);
			n3.setEndDistance(h3);
			n4.setEndDistance(h4);
			n5.setEndDistance(h5);
			n6.setEndDistance(h6);
			n7.setEndDistance(h7);
			n8.setEndDistance(h8);
			n9.setEndDistance(h9);
			n10.setEndDistance(h10);
			n11.setEndDistance(h11);
			n12.setEndDistance(h12);
			n13.setEndDistance(h13);
			n14.setEndDistance(h14);
			n15.setEndDistance(h15);
			
			graphNode.clear();
			graphNode.add(n1);
			graphNode.add(n2);
			graphNode.add(n3);
			graphNode.add(n4);
			graphNode.add(n5);
			graphNode.add(n6);
			graphNode.add(n7);
			graphNode.add(n8);
			graphNode.add(n9);
			graphNode.add(n10);
			graphNode.add(n11);
			graphNode.add(n12);
			graphNode.add(n13);
			graphNode.add(n14);
			graphNode.add(n15);
			for(ANode i : graphNode) {
				if(i.getTitle().equals(s)) {
					i.setStart();
				}
				if(i.getTitle().equals(e)) {
					i.setEnd();
				}
			}
			
			
			for(ANode i : graphNode) {
				if(i.getTitle().equals(s)) {
					if(AStarAlgorithm.AStarSearch(i, path)) {
						pathColor(path);
						break;
					}
					else {
						JOptionPane.showMessageDialog(null, "No possible path", "Can not find path", JOptionPane.ERROR_MESSAGE);
			            return;
					}
				}
				
			}
			
			
		}
	}

}
