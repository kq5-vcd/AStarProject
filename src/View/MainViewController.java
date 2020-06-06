package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Grid.Cell;
import main.Grid.CellType;
import main.Grid.Grid;
import main.Grid.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private Grid grid;
    Cell startCell;
    Cell endCell;

    ObservableList<Cell> gridList = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<Integer> rowChoiceBox;

    @FXML
    private ChoiceBox<Integer> columnChoiceBox;

    @FXML
    private  StackPane gridPane;

    @FXML
    private Button exitBtn;
    @Override
    public void initialize(URL url, ResourceBundle rb){
//        choiceList.removeAll(choiceList);
//        choiceList.addAll(3,4,5,6,7,8);
        rowChoiceBox.getItems().addAll(3,4,5,6,7,8);
        columnChoiceBox.getItems().addAll(6,7,8);
        rowChoiceBox.setValue(6);
        columnChoiceBox.setValue(8);
    }

    public void resetGridPane(){
        gridPane.getChildren().removeAll(grid);
    }
    private void createGridPane(int columns, int rows){

        resetGridPane();
        double n = (double) gridPane.getHeight()/rows;
        if (n*columns > gridPane.getWidth()){
            n = (double) gridPane.getWidth()/columns;
            grid = new Grid(columns, rows, gridPane.getWidth(), n*rows) ;
            //gridPane.setAlignment(Pos.CENTER);
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    String text = i + " " + j;
                    Cell cell = new Cell(text, j, i, CellType.TRAVERSABLE);

                    grid.add(cell, j, i);
                }
            }

            startCell = grid.getCell(0,0);
            //startCell.setText("Start");
            startCell.setStart();

            endCell = grid.getCell(rows - 1, columns -1);
            //endCell.setText("End");
            endCell.setEnd();

            gridPane.getChildren().add(grid);
            gridPane.setAlignment(Pos.CENTER);
        } else {
            grid = new Grid(columns, rows, columns*n, gridPane.getHeight()) ;
            //gridPane.setAlignment(Pos.CENTER);
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    String text = i + " " + j;
                    Cell cell = new Cell(text, j, i, CellType.TRAVERSABLE);

                    grid.add(cell, j, i);
                }
            }

            startCell = grid.getCell(0,0);
            //startCell.setText("Start");
            startCell.setStart();

            endCell = grid.getCell(rows - 1, columns -1);
            //endCell.setText("End");
            endCell.setEnd();

            gridPane.getChildren().add(grid);
            gridPane.setAlignment(Pos.CENTER);


        }

    }

    @FXML
    private void handleOK(){
        createGridPane(columnChoiceBox.getValue(), rowChoiceBox.getValue());
    }

    @FXML
    private void fillObstacles(){
        grid.setType(CellType.OBSTACLE);
    }

    @FXML
    private void removeAllObstacles(){
        grid.setType(CellType.TRAVERSABLE);
    }
    @FXML
    private void handleExit(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleStartBtn(){
        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                Cell cell = grid.getCell(i,j);
                cell.setOnMouseReleased( e-> {
                    cell.setOnMouseReleased(null);
                });

                cell.setOnMouseReleased(e -> {
                    removeStart();
                    cell.setStart();
                    startCell = cell;
                    System.out.println(startCell.getRow() + " - " + startCell.getColumn());
                });
            }
        }
    }

    @FXML
    private void handleEndBtn(){

        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                Cell cell = grid.getCell(i,j);

                cell.setOnMouseReleased( e -> {
                    cell.setOnMouseReleased(null);
                });

                cell.setOnMouseReleased( e -> {
                    removeEnd();
                    cell.setEnd();
                    endCell = cell;
                    System.out.println(endCell.getRow() + " - " + endCell.getColumn());
                });
            }
        }
    }

    public void removeStart(){
        getStartCell().getStyleClass().removeAll("start");
        getStartCell().setType(CellType.TRAVERSABLE);
    }

    public void removeEnd(){
        getEndCell().getStyleClass().removeAll("end");
        getEndCell().setType(CellType.TRAVERSABLE);
    }

    public Cell getStartCell(){
        return startCell;
    }

    public Cell getEndCell(){
        return endCell;
    }



}
