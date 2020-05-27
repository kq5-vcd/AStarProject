package AStarGraph;
import AStarGraph.Neighbor;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Node {
	
	private final DoubleProperty x;
	private final DoubleProperty y;
	private final DoubleProperty textx;
	private final DoubleProperty texty;
	private final StringProperty text;
	private final DoubleProperty endx;
	private final DoubleProperty endy;
	private final DoubleProperty heuristic;
	private final ObjectProperty<Paint> color;
	private ArrayList<Neighbor> neighbor = new ArrayList<Neighbor>();
	
	
	public Double getX() {
		return x.get();
	}
	
	public void setX(Double x) {
		this.x.set(x);
	}
	
	public DoubleProperty xProperty() {
		return x;
	}

	public Double getY() {
		return y.get();
	}
	
	public void setY(Double y) {
		this.y.set(y);
	}
	
	public DoubleProperty yProperty() {
		return y;
	}
	
	public Double getTextx() {
		return textx.get();
	}
	
	public void setTextx(Double textx) {
		this.textx.set(textx);
	}
	
	public DoubleProperty textxProperty() {
		return textx;
	}
	
	public Double getTexty() {
		return texty.get();
	}
	
	public void setTexty(Double texty) {
		this.texty.set(texty);
	}
	
	public DoubleProperty textyProperty() {
		return texty;
	}
	
	public String getText() {
		return text.get();
	}
	
	public void setText(String text) {
		this.text.set(text);
	}
	
	public StringProperty textProperty() {
		return text;
	}
	
	public Double getEndx() {
		return endx.get();
	}
	
	public void setEndx(Double endx) {
		this.endx.set(endx);
	}
	
	public DoubleProperty endxProperty() {
		return endx;
	}
	
	public Double getEndy() {
		return endy.get();
	}
	
	public void setEndy(Double endy) {
		this.endy.set(endy);
	}
	
	public DoubleProperty endyProperty() {
		return endy;
	}

	public Double getHeuristic() {
		return heuristic.get();
	}
	
	public void setHeuristic(Double h) {
		this.heuristic.set(h);
	}
	
	public DoubleProperty heuristicProperty() {
		return heuristic;
	}
	
	public Paint getColor() {
		return color.get();
	}
	
	public void setColor(Paint color) {
		this.color.set(color);
	}

	public ObjectProperty<Paint> colorProperty() {
		return color;
	}
	
	public ArrayList<Neighbor> getNeighbor() {
		return this.neighbor;
	}
	
	public void setNeighbor(ArrayList<Neighbor> neighbor) {
		this.neighbor.clear();
		this.neighbor.addAll(neighbor);
	}
	
	
	public Node() {
		this(null,null,null,null,null);
	}

	public Node(Double x, Double y, ArrayList<Neighbor> neighbor, Double endx, Double endy) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.textx = new SimpleDoubleProperty(x-10.0);
		this.texty = new SimpleDoubleProperty(y+5.0);
		this.endx = new SimpleDoubleProperty(endx);
		this.endy = new SimpleDoubleProperty(endy);
		Double h = Math.sqrt(Math.pow(getEndx()-getX(), 2) + Math.pow(getEndy()-getY(), 2));
		this.heuristic = new SimpleDoubleProperty(h);
		this.text = new SimpleStringProperty(String.valueOf(h));
		this.color = new SimpleObjectProperty<Paint>(Paint.valueOf("DODGERBLUE"));
		setNeighbor(neighbor);
	}

}
