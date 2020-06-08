package Grid;

import javafx.scene.layout.StackPane;

public class Cell extends StackPane {

    double column;
    double row;
    String text;
    boolean traversable;

    /**
     * The constructor
     **/
    public Cell(String text, double column, double row){
    	super();
    	
        this.column = column;
        this.row = row;
        this.text = text;
        this.traversable = true;
        
        updateStatus();
    }

    public void updateStatus(){
        getStyleClass().removeAll("traversable", "obstacle");

        if(traversable){
            getStyleClass().add("traversable");
        } else {
        	getStyleClass().add("obstacle");
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

    public void setTraversable(){
        traversable = true;
        updateStatus();
    }

    public void setBlock() {
    	traversable = false;
        updateStatus();
    }

}

