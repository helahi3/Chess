//Each individual cell of the board

public class Cell {
		
	boolean possibleDestination; //TODO: Possibly unnecessary
	public Piece piece;
	int x,y;
	
	//Constructor
	public Cell() {
		possibleDestination = false;
		piece = null;
		x= 0; y = 0;
	}

	/*
	 * Place input Piece in this Cell
	 */
	public void setPiece(Piece input) { 
		this.piece = input; 
		this.x = piece.x; this.y = piece.y;
	}
	
	/*
	 * Remove Piece from this Cell
	 */
	public void clearCell() { 
		piece = null;
	}	
	
	/*
	 * Return whether cell is empty
	 */
	public boolean isEmpty() { 
		return piece == null;
	}
	
	/*
	 * toString method to print board to console
	 */
	public String toString() {
		String res = "{ " + this.piece + " }";
		String res2 = "{    }";
		if(this.isEmpty()) return res2;
		else return res;
		
	}
	
	//Only used for testing
	public String toString2() {
		String res = "{" + x + "," + y + "}";
		return res;
	}
	
}
