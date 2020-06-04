package Graph;

import java.io.IOException;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	private static Stage mainStage;
	private static Parent root;
	
	public DisplayGraph() {
		
	}
	
	@Override
	public void start(Stage primaryStage){
		try {
			mainStage = primaryStage;
			root = FXMLLoader.load(getClass().getResource("GraphDraw.fxml"));
			
			mainStage.setTitle("AStarGraph");
			mainStage.setScene(new Scene(root));
			
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Stage getPrimaryStage() {
		return mainStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}