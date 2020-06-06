package main.Grid;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Cell extends StackPane {

    int column;
    int row;
    String text;
    Cell startCell;
    Cell endCell;
    CellType type;

    public Cell(String text, int column, int row, CellType type){
        this.column = column;
        this.row = row;
        this.type = type;
        this.text = text;

        updateCellType();
        AnchorPane anchorPane = new AnchorPane();
        Label lbl = new Label(text);
        getChildren().addAll(anchorPane, lbl);
    }

    public void updateCellType(){
        getStyleClass().removeAll("traversable", "obstacle");

        switch (type){
            case TRAVERSABLE:
                getStyleClass().add("traversable");
                break;
            case OBSTACLE:
                getStyleClass().add("obstacle");
                break;
        }
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public boolean isTraversable(){
        return type == CellType.TRAVERSABLE;
    }

    public void setStart(){

        getStyleClass().removeAll("traversable", "obstacle");
        getStyleClass().add("start");
    }

    public void setEnd(){
        getStyleClass().removeAll("traversable", "obstacle");
        getStyleClass().add("end");
    }

    public void setType(CellType type){
        this.type = type;
        updateCellType();
    }

    public String getText(){
        return text;
    }

    public void setText(String text){

        this.text = text;
    }




}
