package AStarGraph;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class DisplayGraph extends Application  {
	
	private Stage primaryStage;
	private AnchorPane root;
	
	private ArrayList<Node> nodeData = new ArrayList<Node>();
	
	public DisplayGraph() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AStarGraph");
		showGraphOverview();
	}
	
	public void showGraphOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DisplayGraph.class.getResource("GraphCanvas.fxml"));
			root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GraphController controller = loader.getController();
			controller.setGraph(this,nodeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
