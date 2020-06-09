module AStarProject {
	exports graph;
	exports application;
	exports grid;
	exports node;
	exports algorithm;
	exports exception;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.fxml;
	opens graph to javafx.fxml;
	opens grid to javafx.fxml;
	opens exception to javafx.fxml;
}