package node;


import grid.Cell;
import node.ANode;

public class GridNode extends ANode {

    Cell cell;
    String text;
    Status status;

    public GridNode(String text, double x, double y, Status status){
        super(x,y);
        this.text = text;
        this.status = status;
        cell = new Cell(text, x, y, status);
    }

    public Cell getCell(){
        return cell;
    }

    public void setBlocked(){
        setStatus(Status.BLOCKED);
    }
}

