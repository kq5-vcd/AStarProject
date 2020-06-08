package Grid;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Node.Node;
public class Cell extends StackPane {

    double column;
    double row;
    String text;
    Node.Status status;
    Label labelF;

    /**
     * The constructor
     **/
    public Cell(String text, double column, double row, Node.Status status){
        this.column = column;
        this.row = row;
        this.text = text;
        this.status = status;

        updateStatus();
        labelF = new Label(text);
        labelF.getStyleClass().add("label-f");
        getChildren().addAll(labelF);

    }

    public void updateStatus(){
        getStyleClass().removeAll("traversable", "obstacle");

        switch (status){
            case BLANK:
                getStyleClass().add("traversable");
                break;
            case BLOCKED:
                getStyleClass().add("obstacle");
                break;
        }
    }

    /**
     * Return the column of the cell
     **/
    public double getColumn(){
        return column;
    }

    /**
     * Return the row of the cell
     **/
    public double getRow(){
        return row;
    }

    /**
     * Set start
     **/
    public void setStart(){
        getStyleClass().removeAll("traversable", "obstacle");
        getStyleClass().add("start");
    }

    //
    public void setEnd(){
        getStyleClass().removeAll("traversable", "obstacle");
        getStyleClass().add("end");
    }

    public void setStatus(Node.Status status){
        this.status = status;
        updateStatus();
    }
    
    public void setTextF( String text) {
		labelF.setText(text);
	}



//    double column;
//    double row;
//    String text;
//    Cell startCell;
//    Cell endCell;
//    CellType type;
//
//    public Cell(String text, double column, double row, CellType type){
//        this.column = column;
//        this.row = row;
//        this.type = type;
//        this.text = text;
//
//        updateCellType();
//        AnchorPane anchorPane = new AnchorPane();
//        Label lbl = new Label(text);
//        getChildren().addAll(anchorPane, lbl);
//    }
//
//    public void updateCellType(){
//        getStyleClass().removeAll("traversable", "obstacle");
//
//        switch (type){
//            case TRAVERSABLE:
//                getStyleClass().add("traversable");
//                break;
//            case OBSTACLE:
//                getStyleClass().add("obstacle");
//                break;
//        }
//    }
//
//    public double getRow(){
//        return row;
//    }
//
//    public double getColumn(){
//        return column;
//    }
//
//    public boolean isTraversable(){
//        return type == CellType.TRAVERSABLE;
//    }
//

//
//    public void setEnd(){
//        getStyleClass().removeAll("traversable", "obstacle");
//        getStyleClass().add("end");
//    }
//
//    public void setType(CellType type){
//        this.type = type;
//        updateCellType();
//    }
//
//    public String getText(){
//        return text;
//    }
//
//    public void setText(String text){
//        this.text = text;
//    }
//
//


}
