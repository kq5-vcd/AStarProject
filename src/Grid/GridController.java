package Grid;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import Algorithm.AStar;
import Node.Node;
import application.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {

    private Grid grid;
    GridNode startCell;
    GridNode endCell;

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



    @Override
    public void initialize(URL url, ResourceBundle rb){
        rowChoiceBox.getItems().addAll(3,4,5,6,7,8,9,10,11,12,13,14,15);
        columnChoiceBox.getItems().addAll(3,4,5,6,7,8,9,10,11,12,13,14,15);
        rowChoiceBox.setValue(6);
        columnChoiceBox.setValue(10);
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
    private void createGridPane(int columns, int rows){
        resetGridPane();
        double n =  gridPane.getHeight()/rows;
        if (n*columns > gridPane.getWidth()){
            n =  gridPane.getWidth()/columns;
            grid = new Grid(columns, rows, gridPane.getWidth(), n*rows) ;
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    String text = "";
                    GridNode gridNode = new GridNode(text, j , i, Node.Status.BLANK);
                    gridNode.setBlank();
                    grid.add(gridNode, j, i);
                }
            }

        } else {
            grid = new Grid(columns, rows, columns*n, gridPane.getHeight()) ;
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    String text = "";
                    GridNode gridNode = new GridNode(text, j, i, Node.Status.BLANK);
                    gridNode.setBlank();
                    grid.add(gridNode, j, i);
                }
            }

        }
        startCell = grid.getCell(0,0);
        startCell.getCell().setStart();
        endCell = grid.getCell(rows - 1, columns -1);
        endCell.getCell().setEnd();
        gridPane.getChildren().add(grid);
        //showPath();


    }

    /**
     * Create new grid
     **/
    public void createGrid(){
        createGridPane(columnChoiceBox.getValue(), rowChoiceBox.getValue());

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
        grid.setStatus(Node.Status.BLOCKED);
    }

    /**
     * Remove all obstacles in the grid
     **/
    public void removeAllObstacles(){
        grid.setStatus(Node.Status.BLANK);
    }
    /**
     * Back to the main menu
     **/
    public void home(){
        resetGridPane();
        findPathBtn.setDisable(true);
        setStartBtn.setDisable(true);
        setEndBtn.setDisable(true);
        setObstacleBtn.setDisable(true);
        fillAllObstaclesBtn.setDisable(true);
        removeObstacleBtn.setDisable(true);
        removeAllObstaclesBtn.setDisable(true);
        showStepsBtn.setDisable(true);
        MainApp.getHomeController().home();
    }

    /**
     * Clear the used and checked gridnode
     **/
    public void clear(){
        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                if (!grid.getCell(i,j).isStart() && !grid.getCell(i,j).isEnd() && !grid.getCell(i,j).isBlocked()){
                    System.out.println(i + " : " + j + " : " + grid.getCell(i,j).getStatus());
                    grid.getCell(i,j).getCell().getStyleClass().removeAll("checked", "used");
                    grid.getCell(i,j).getCell().getStyleClass().add("traversable");
                    grid.getCell(i,j).setBlank();
                }
            }
        }

    }

    /**
     * Set Start node
     **/
    public void setStart(){

        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){

                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased( e -> gridNode.getCell().setOnMouseReleased(null));

                gridNode.getCell().setOnMouseReleased( e -> {
                    gridNode.getCell().getStyleClass().removeAll("checked", "used");
                    removeStart();
                    gridNode.getCell().setStart();
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
                    gridNode.getCell().setEnd();
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

        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased(null);
                gridNode.getCell().setOnMouseReleased( e -> {
                    gridNode.getCell().getStyleClass().removeAll("checked", "used");
                    gridNode.setBlank();
                    //System.out.println("Before : " + gridNode.getCell().getRow() + " - " + gridNode.getCell().getColumn() + " " + gridNode.getStatus());
                    gridNode.setBlocked();
                    gridNode.getCell().setStatus(Node.Status.BLOCKED);
                });
            }
        }
        //System.out.println("-----");

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
                        gridNode.getCell().getStyleClass().removeAll("obstacle");
                        gridNode.setBlank();
                        gridNode.getCell().getStyleClass().add("traversable");
                    });

                }
            }
        }
    }

    public void removeStart(){
        getStartCell().setBlank();
        getStartCell().getCell().getStyleClass().removeAll("start", "checked", "used");
        getStartCell().getCell().setStatus(Node.Status.BLANK);
    }

    public void removeEnd(){
        getStartCell().setBlank();
        getEndCell().getCell().getStyleClass().removeAll("end", "checked", "used");
        getEndCell().getCell().setStatus(Node.Status.BLANK);
    }

    public GridNode getStartCell(){
        return startCell;
    }

    public GridNode getEndCell(){
        return endCell;
    }


    public void findPath(){
        clear();
        showPath();
        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){
                GridNode gridNode = grid.getCell(i,j);

                gridNode.getCell().setOnMouseReleased( e -> gridNode.getCell().setOnMouseReleased(null));

                System.out.println(i + " " + j + " " + gridNode.getStatus());

            }
        }

        showStepsBtn.setDisable(false);

    }

    public void showPath(){

        int rows = rowChoiceBox.getValue();
        int columns = columnChoiceBox.getValue();
//        for (int i = 0; i < rows; i++){
//            for (int j = 0; j < columns; j++){
//                System.out.println("Check : " + i + " - " + j + " " + grid.getCell(i,j).getStatus());
//            }
//        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!grid.getCell(i, j).isEnd() && !grid.getCell(i, j).isStart() && !grid.getCell(i, j).isBlocked()){
                    grid.getCell(i,j).resetNode();
                    //System.out.println("Check 2 : " + i + " - " + j + " " + grid.getCell(i,j).getStatus());
                }
            }
        }



        // node (0, 0)
        grid.getCell(0,0).setTo(grid.getCell(0,1));
        grid.getCell(0,0).setTo(grid.getCell(1,0));
        grid.getCell(0,0).setTo(grid.getCell(1,1));

        // node (r -1, c - 1)
        grid.getCell(grid.getRows() -1, columns -1).setTo(grid.getCell(rows-1, columns-2));
        grid.getCell(grid.getRows() -1, columns -1).setTo(grid.getCell(rows-2, columns-1));
        grid.getCell(grid.getRows() -1, columns -1).setTo(grid.getCell(rows-2, columns-2));


        // node (0, c-1)
        grid.getCell(0, columns-1).setTo(grid.getCell(0, columns-2));
        grid.getCell(0, columns-1).setTo(grid.getCell(1, columns-2));
        grid.getCell(0, columns-1).setTo(grid.getCell(1, columns-1));

        // node (r-1, 0)
        grid.getCell(rows -1, 0).setTo(grid.getCell(rows - 1, 1));
        grid.getCell(rows -1, 0).setTo(grid.getCell(rows - 2, 0));
        grid.getCell(rows -1, 0).setTo(grid.getCell(rows - 2, 1));

        for (int i = 1; i < rows-1; i++){
            grid.getCell(i, 0).setTo(grid.getCell(i-1, 0));
            grid.getCell(i, 0).setTo(grid.getCell(i-1, 1));
            grid.getCell(i, 0).setTo(grid.getCell(i, 1));
            grid.getCell(i, 0).setTo(grid.getCell(i+1, 0));
            grid.getCell(i, 0).setTo(grid.getCell(i+1, 1));

            grid.getCell(i, columns-1).setTo(grid.getCell(i-1, columns-1));
            grid.getCell(i, columns-1).setTo(grid.getCell(i-1, columns-2));
            grid.getCell(i, columns-1).setTo(grid.getCell(i, columns-2));
            grid.getCell(i, columns-1).setTo(grid.getCell(i+1, columns-1));
            grid.getCell(i, columns-1).setTo(grid.getCell(i+1, columns-2));

        }

        for (int j = 1; j < columnChoiceBox.getValue()-1; j++){
            grid.getCell(0, j).setTo(grid.getCell(0, j-1));
            grid.getCell(0, j).setTo(grid.getCell(1, j-1));
            grid.getCell(0, j).setTo(grid.getCell(1, j));
            grid.getCell(0, j).setTo(grid.getCell(1, j+1));
            grid.getCell(0, j).setTo(grid.getCell(0, j+1));

            grid.getCell(rows-1, j).setTo(grid.getCell(rows-1, j-1));
            grid.getCell(rows-1, j).setTo(grid.getCell(rows-2, j-1));
            grid.getCell(rows-1, j).setTo(grid.getCell(rows-2, j));
            grid.getCell(rows-1, j).setTo(grid.getCell(rows-2, j+1));
            grid.getCell(rows-1, j).setTo(grid.getCell(rows-1, j+1));
        }



        for (int i = 1; i < rowChoiceBox.getValue() -1; i++){
            for (int j = 1; j < columnChoiceBox.getValue() -1; j++){
                grid.getCell(i,j).setTo(grid.getCell(i-1, j-1));
                grid.getCell(i,j).setTo(grid.getCell(i-1, j));
                grid.getCell(i,j).setTo(grid.getCell(i-1, j+1));
                grid.getCell(i,j).setTo(grid.getCell(i, j-1),1);
                grid.getCell(i,j).setTo(grid.getCell(i, j +1),1);
                grid.getCell(i,j).setTo(grid.getCell(i+1, j-1));
                grid.getCell(i,j).setTo(grid.getCell(i+1, j));
                grid.getCell(i,j).setTo(grid.getCell(i+1, j+1));
            }
        }


//                for (int i = 0; i < rows; i++){
//            for (int j = 0; j < columns; j++){
//                System.out.println("Check : " + i + " - " + j + " " + grid.getCell(i,j).getStatus());
//            }
//        }

        AStar.AStarSearch(getStartCell(), getEndCell());

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){

                if (grid.getCell(i,j) != getStartCell() && grid.getCell(i,j) != getEndCell() && !grid.getCell(i,j).isBlocked()) {
//                    if (grid.getCell(i,j).isChecked()){
//                        grid.getCell(i,j).getCell().getStyleClass().add("checked");
//                    }
                    if (grid.getCell(i,j).isUsed()){
                        grid.getCell(i,j).getCell().getStyleClass().add("used");
                        grid.getCell(i,j).getCell().setTextF(""+Math.round(grid.getCell(i,j).getHeuristic()*10.0)/10.0);
                    }

                }

            }
        }


    }

    public void showSteps() {
        for (int i = 0; i < rowChoiceBox.getValue(); i++){
            for (int j = 0; j < columnChoiceBox.getValue(); j++){

                if (grid.getCell(i,j) != getStartCell() && grid.getCell(i,j) != getEndCell() && !grid.getCell(i,j).isBlocked()) {
                    if (grid.getCell(i,j).isCheck()){
                        grid.getCell(i,j).getCell().getStyleClass().add("checked");
                        grid.getCell(i,j).getCell().setTextF(""+Math.round(grid.getCell(i,j).getHeuristic()*10.0)/10.0);
                    }

                }

            }
        }
    }



}
