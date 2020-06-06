package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        showMainView();
    }

    /**
     * Show main view
     */
    public void showMainView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResource("View/MainView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("css/grid.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


}
