package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application  {
	
	private static Parent root;
	private static Stage mainStage;
	private static MainController homeController;
	
	public static Stage getMainStage() {
		return mainStage;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static MainController getHomeController() {
		return homeController;
	}
	
	@Override
	public void start(Stage primaryStage){
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AStarFrame.fxml"));
			System.out.println(loader);
			
			mainStage = primaryStage;
			root = loader.load();
			
			homeController = (MainController) loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("css/grid.css").toExternalForm());
   		    primaryStage.setTitle("A Star Search");
   		    primaryStage.setScene(scene);
   		    primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Run");
		launch(args);
	}
}
