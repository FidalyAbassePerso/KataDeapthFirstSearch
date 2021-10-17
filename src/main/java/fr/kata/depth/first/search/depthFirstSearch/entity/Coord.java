package fr.kata.depth.first.search.depthFirstSearch.entity;

/**
 * 
 * @author fidal
 * Représente une positions dans la map
 *
 */
public class Coord {
	
	private int x;
    private int y;


    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Coord[] getVoisinsAdjacent(){
        Coord[] coords = {new Coord(x,y+1),new Coord(x,y-1),new Coord(x+1,y),new Coord(x-1,y)};
        return coords;
    }
    
    

	
	public boolean equals(Object obj) {
		
		Coord c = (Coord) obj;
		
		return this.getX() == c.getX() && this.getY() == c.getY();
	}

	public String toString() {
    	return "("+this.getX()+","+this.getY()+")";
    }

}
