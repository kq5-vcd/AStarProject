package main.Grid;

import javafx.scene.layout.Pane;

public class Grid extends Pane {

    int rows;
    int columns;

    double width;
    double height;

    Cell[][] cells;

    public Grid(int columns, int rows, double width, double height){
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;

        cells = new Cell[rows][columns];
    }

    /**
     * Get the cell in grid
     **/
    public Cell getCell(int row, int column){
        return cells[row][column];
    }

    /**
     * Add a new cell to the grid
     **/
    public void add(Cell cell, int column, int row){

        cells[row][column] = cell;

        double w = width/columns;
        double h = height/rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefSize(w,h);

        getChildren().add(cell);
    }

    /**
     * Set the type of the cell
     **/
    public void setType(CellType type){

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                cells[i][j].setType(type);
            }
        }
    }
}
