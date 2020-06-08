package Grid;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

import Algorithm.AStar;
import Node.GridNode;
import application.MainApp;

public class GridController implements Initializable {

    private Grid grid;
    private GridNode startCell;
    private GridNode endCell;

    @FXML
    private ChoiceBox<Integer> rowChoiceBox;

    @FXML
    private ChoiceBox<Integer> columnChoiceBox;

    @FXML
    private  StackPane gridPane;

    @FXML
    private Button createGridBtn;

    @FXML
    private Button findPathBtn;

    @FXML
    private Button showStepsBtn;

    @FXML
    private Button setStartBtn;

    @FXML
    private Button setEndBtn;

    @FXML
    private Button setObstacleBtn;

    @FXML
    private Button fillAllObstaclesBtn;

    @FXML
    private Button removeObstacleBtn;

    @FXML
    private Button removeAllObstaclesBtn;

    @FXML
    private Button homeBtn;

    int col, row;
    double width, height;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    	for(int i = 3; i < 16; i++) {
    		rowChoiceBox.getItems().add(i);
    		columnChoiceBox.getItems().add(i);
    	}

        rowChoiceBox.setValue(6);
        columnChoiceBox.setValue(10);
        
        width = gridPane.getWidth();
        height = gridPane.getHeight();
        
    }

    /**
     * Reset the grid pane
     **/
    public void resetGridPane(){
        gridPane.getChildren().removeAll(grid);
    }


    /**
     * Create the grid pane
     **/
    private void createGridPane(){
        resetGridPane();
        
        double n =  height/row;
        
        if(n * col > width){
            n =  width/col;
            grid = new Grid(col, row, width, n * row) ;
            
            for(int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    String text = i + " " + j;
                    GridNode gridNode = new GridNode(text, j, i);
                    grid.add(gridNode, j, i);
                }
            }

        } else {
            grid = new Grid(col, row, col * n, height) ;
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    String text = i + " " + j;
                    GridNode gridNode = new GridNode(text, j, i);
                    grid.add(gridNode, j, i);
                }
            }

        }
        
        startCell = grid.getCell(0,0);
        startCell.getCell().setStart();
        endCell = grid.getCell(row - 1, col -1);
        endCell.getCell().setEnd();
        
        gridPane.getChildren().add(grid);
    }
    
    public void createConnections() {
    	for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
            	GridNode currentNode = grid.getCell(i, j);
            	
            	for(int x = -1; x < 2; x++) {
            		for(int y = -1; y < 2; y++) {
            			if(!(x == 0 && y== 0)) {
            				int indX = i + x;
                			int indY = j + y;
                			
                			if(indX >= 0 && indX < row && indY >= 0 && indY < col) {
                				currentNode.setTo(grid.getCell(indX, indY));
                				grid.getCell(indX, indY).setTo(currentNode);
                			}
            			}
            		}
            	}
            }
        }
    }

    /**
     * Create new grid
     **/
    public void createGrid(){
    	row = rowChoiceBox.getValue();
    	col = columnChoiceBox.getValue();
        createGridPane();

        findPathBtn.setDisable(false);
        setStartBtn.setDisable(false);
        setEndBtn.setDisable(false);
        setObstacleBtn.setDisable(false);
        fillAllObstaclesBtn.setDisable(false);
        removeObstacleBtn.setDisable(false);
        removeAllObstaclesBtn.setDisable(false);
    }

    /**
     * Fill all obstacles
     **/
    public void fillObstacles(){
        grid.setBlock();
    }

    /**
     * Remove all obstacles in the grid
     **/
    public void removeAllObstacles(){
        grid.setBlank();
    }
    /**
     * Back to the main menu
     **/
    public void home(){
        resetGridPane();
		MainApp.getHomeController().home();
    }

    /**
     * Clear the used and checked gridnode
     **/
    public void clear(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (!grid.getCell(i,j).isStart() && !grid.getCell(i,j).isEnd() && !grid.getCell(i,j).isBlocked()){
                    System.out.println(i + " : " + j + " : " + grid.getCell(i,j).getStatus());
                    
                    grid.getCell(i,j).setBlank();
                }
            }
        }

    }

    /**
     * Set Start node
     **/
    public void setStart(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased( e -> gridNode.getCell().setOnMouseReleased(null));

                gridNode.getCell().setOnMouseReleased( e -> {
                    gridNode.getCell().getStyleClass().removeAll("checked", "used");
                    removeStart();
                    
                    gridNode.setStart();
                    startCell = gridNode;
                    
                    System.out.println("Start : " + startCell.getCell().getRow() + " - " + startCell.getCell().getColumn());
                });
            }
        }
    }

    /**
     * Set End node
     **/
    public void setEnd(){

        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased( e -> gridNode.getCell().setOnMouseReleased(null));

                gridNode.getCell().setOnMouseReleased( e -> {
                    gridNode.getCell().getStyleClass().removeAll("checked", "used");
                    removeEnd();
                    
                    gridNode.setEnd();
                    endCell = gridNode;
                    System.out.println(endCell.getCell().getRow() + " - " + endCell.getCell().getColumn());
                });
            }
        }
    }

    /**
     * Set new Obstacle
     **/
    public void setObstacle(){

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased(null);
                gridNode.getCell().setOnMouseReleased( e -> {
                    gridNode.setBlank();
                    gridNode.setBlock();
                });
            }
        }

    }

    /**
     * Remove an obstacle
     **/
    public void removeObstacle(){
        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){

                GridNode gridNode = grid.getCell(i,j);
                gridNode.getCell().setOnMouseReleased(null);
                if (gridNode.isBlocked()){

                    gridNode.getCell().setOnMouseReleased( e -> {
                        gridNode.setBlank();
                    });

                }
            }
        }
    }

    public void removeStart(){
        startCell.setBlank();
    }

    public void removeEnd(){
        endCell.setBlank();
    }


    public void findPath(){
//        clear();
//        showPath();
//        for (int i = 0; i < rowChoiceBox.getValue(); i++){
//            for (int j = 0; j < columnChoiceBox.getValue(); j++){
//                GridNode gridNode = grid.getCell(i,j);
//
//                gridNode.getCell().setOnMouseReleased( e -> gridNode.getCell().setOnMouseReleased(null));
//
//                System.out.println(i + " " + j + " " + gridNode.getStatus());
//
//            }
//        }
    	AStar.AStarSearch(startCell, endCell);
        showStepsBtn.setDisable(false);

    }

    public void showPath(){

//    	for (int i = 0; i < row; i++){
//            for (int j = 0; j < col; j++){
//                if (!grid.getCell(i, j).isEnd() && !grid.getCell(i, j).isStart() && !grid.getCell(i, j).isBlocked()){
//                    grid.getCell(i,j).resetNode();
//                    //System.out.println("Check 2 : " + i + " - " + j + " " + grid.getCell(i,j).getStatus());
//                }
//            }
//        }
//
//
////                for (int i = 0; i < rows; i++){
////            for (int j = 0; j < columns; j++){
////                System.out.println("Check : " + i + " - " + j + " " + grid.getCell(i,j).getStatus());
////            }
////        }
//
//        AStar.AStarSearch(getStartCell(), getEndCell());
//
//        for (int i = 0; i < rows; i++){
//            for (int j = 0; j < columns; j++){
//
//                if (grid.getCell(i,j) != getStartCell() && grid.getCell(i,j) != getEndCell() && !grid.getCell(i,j).isBlocked()) {
////                    if (grid.getCell(i,j).isChecked()){
////                        grid.getCell(i,j).getCell().getStyleClass().add("checked");
////                    }
//                    if (grid.getCell(i,j).isUsed()){
//                        grid.getCell(i,j).getCell().getStyleClass().add("used");
//                    }
//
//                }
//
//            }
//        }


    }

    public void showSteps() {
//        for (int i = 0; i < rowChoiceBox.getValue(); i++){
//            for (int j = 0; j < columnChoiceBox.getValue(); j++){
//
//                if (grid.getCell(i,j) != getStartCell() && grid.getCell(i,j) != getEndCell() && !grid.getCell(i,j).isBlocked()) {
//                    if (grid.getCell(i,j).isChecked()){
//                       grid.getCell(i,j).getCell().getStyleClass().add("checked");
//                    }
//
//                }
//
//            }
//        }
    }



}

