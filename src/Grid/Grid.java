package grid;

import javafx.scene.layout.Pane;
import node.*;
public class Grid extends Pane {

    int rows;
    int columns;

    double width;
    double height;

    GridNode[][] cells;

    public Grid(int columns, int rows, double width, double height){
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;

        cells = new GridNode[rows][columns];
    }

    /**
     * Get the cell in grid
     **/
    public GridNode getCell(int row, int column){
        return cells[row][column];
    }

    /**
     * Add a new cell to the grid
     **/
    public void add(GridNode cell, int column, int row){

        cells[row][column] = cell;

        double w = width/columns;
        double h = height/rows;
        double x = w * column;
        double y = h * row;

        cell.getCell().setLayoutX(x);
        cell.getCell().setLayoutY(y);
        cell.getCell().setPrefSize(w,h);

        getChildren().add(cell.getCell());
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }
    /**
     * Set the type of the cell
     **/
    public void setStatus(ANode.Status status){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                cells[i][j].getCell().setStatus(status);
            }
        }
    }

}
