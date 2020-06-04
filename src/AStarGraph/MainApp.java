package AStarGraph;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

public class MainApp extends Application  {
	
	private Stage primaryStage;
	private StackPane root;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AStarSearch");
		showApp();
	}
	
	public void showApp() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DisplayGraph.class.getResource("AStarFrame.fxml"));
			root = (StackPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
