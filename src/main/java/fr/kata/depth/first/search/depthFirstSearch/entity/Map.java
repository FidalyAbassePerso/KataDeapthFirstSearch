package fr.kata.depth.first.search.depthFirstSearch.entity;

/**
 * 
 * @author fidal
 * 
 * Représente une map correspondant au labyrinthe
 *
 */
public class Map {
	
	private int maxRows; //nombre max de ligne
	private int maxCols;// nombre max de colonnes

	private int[][] map; // matrice représentant le labyrinthe
	
	private Coord positionDepart; // position de départ dans le labyrinthe
	
	public Map(int maxRows,int maxCols,Coord positionDepart) {
		this.maxCols = maxCols;
		this.maxRows = maxRows;
		this.map = new int[maxRows][maxCols];
		this.setPositionDepart(positionDepart);
	}
	
	public Map(int maxRows,int maxCols) {
		this.maxCols = maxCols;
		this.maxRows = maxRows;
		this.map = new int[maxRows][maxCols];
	}
	

	public boolean faitPartieDeLaMap(Coord c) {
		
		if (maxRows > 0) {
			return ((c.getX() >= 0 && c.getX() < maxRows) && (c.getY() >= 0 && c.getY() < maxCols));
		}
		return false;
	}


	public int getMaxRows() {
		return maxRows;
	}


	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}


	public int getMaxCols() {
		return maxCols;
	}


	public void setMaxCols(int maxCols) {
		this.maxCols = maxCols;
	}


	public int[][] getMap() {
		return map;
	}


	public void setMap(int[][] map) {
		this.map = map;
	}


	@Override
	public String toString() {

		for (int i = 0; i < maxRows ; i ++) {
			
			String row = "";
			
			for (int j = 0; j < maxCols ; j ++) {
				row += map[i][j]+" ";
			}
			row += "\n";
			System.out.println(row);
			
		} 
		return "";
	}

	public Coord getPositionDepart() {
		return positionDepart;
	}

	public void setPositionDepart(Coord positionDepart) {
		this.positionDepart = positionDepart;
	}
	
	

}
