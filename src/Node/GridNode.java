package Node;

import Grid.Cell;

public class GridNode extends Node {
	
	static final int value = 1;

	public GridNode(double x, double y) {
		super(x, y);
	}
	
	Cell cell;
    String text;
    
    public GridNode(String text, double x, double y){
        super(x,y);
        this.text = text;
        cell = new Cell(text, x, y);
    }

    public Cell getCell(){
        return cell;
    }

    public void setTraversable(){
        if(!isStart() && !isEnd()) {
        	setBlank();
        }
    }
    
    public void setBlock(){
    	if(!isStart() && !isEnd()) {
    		setStatus(Status.BLOCKED);
        }
        
        cell.setBlock();
    }
    
    @Override
    public void setStart() {
    	super.setStart();
    	cell.setStart();
    }
    
    @Override
    public void setEnd() {
    	super.setEnd();
    	cell.setEnd();
    }
    
    @Override
    public void setBlank() {
		super.setBlank();
		cell.setTraversable();
	}
    
}
