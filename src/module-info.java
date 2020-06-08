module AStarProject {
	exports application;
	exports Algorithm;
	exports Exception;
	exports Graph;
	exports Grid;

	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.fxml;
	opens Graph to javafx.fxml;
	opens Grid to javafx.fxml;
}