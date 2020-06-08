package Grid;


import Node.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class GridNode extends Node {

    Cell cell;
    String text;
    Status status;
    public GridNode(String text, double x, double y, Status status){
        super(x,y);
        this.text = text;
        this.status = status;
        //updateStatus();
        cell = new Cell(text, x, y, status);
        //this.cell = cell;
    }

    public Cell getCell(){
        return cell;
    }



    public void setBlocked(){
        setStatus(Status.BLOCKED);
    }

//    public void setStatus(Status status){
//        updateStatus();
//    }

//    public void setStart(){
//
//    }






}

