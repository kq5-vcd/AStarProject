module AStarPathFinding {
	exports AStarGraph;
	//exports AStarGrid;
	
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;
	
	opens AStarGraph to javafx.fxml;
}