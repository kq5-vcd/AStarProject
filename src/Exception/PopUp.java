package Exception;

import java.io.IOException;

import application.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PopUp {
	
	public void popUp() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			
			Stage primaryStage = new Stage();
   		    primaryStage.setTitle("AStarSearch");
   		    primaryStage.setScene(scene);
   		    primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
