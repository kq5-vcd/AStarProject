package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	private AnchorPane homePane;
	@FXML
	private AnchorPane graphPane;
	@FXML
	private AnchorPane gridPane;
	@FXML
	private Button graphBtn;
	@FXML
	private Button gridBtn;
	
	public void openGraph() {
		resetVisibility();
		graphPane.setVisible(true);
	}
	
	public void openGrid() {
		resetVisibility();
		gridPane.setVisible(true);
	}
	
	public void home() {
		resetVisibility();
		homePane.setVisible(true);
	}
	
	public void resetVisibility() {
		graphPane.setVisible(false);
		gridPane.setVisible(false);
		homePane.setVisible(false);
	}
	
}
