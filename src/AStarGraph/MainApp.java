package AStarGraph;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainApp extends Application  {
	
	private Stage primaryStage;
	private StackPane root;
	private MediaPlayer player;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AStarSearch");
		
		String path = "C:\\Users\\quan.dh176850\\eclipse-workspace\\AStarPathFinding\\music\\BoJack-Horseman-Theme-Song.mp3";
		Media media = new Media(new File(path).toURI().toString());
		
		player = new MediaPlayer(media);
		player.play();
		
		
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
