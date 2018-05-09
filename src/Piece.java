
import java.util.ArrayList;


public abstract class Piece {
	
	public int x, y;  //Coordinates on board
	public int Color; //0 for white, 1 for black
	public boolean hasMoved;
	public boolean threateningEnemyKing;
	
	public Piece(int iX, int iY, int iColor) {
		this.x = iX;
		this.y = iY;
		this.Color = iColor;
		hasMoved = false;
		threateningEnemyKing = false;
	}
		

	
	/*
	 * Test to see if final coordinates are in a valid path
	 */
	public abstract boolean validPath(int finalX, int finalY);
	
	/*
	 * List of all possible moves. canMove tests to see if move is in those options
	 * Tests to see if friendly king will be under check, not including 
	 */
	public abstract ArrayList<Cell> getPossibleMoves(Cell[][] board);
	
	/*
	 * Same as getPossibleMoves, except ignoring the possibility of friendly king being under check
	 */
	public abstract ArrayList<Cell> pseudoLegalMoves(Cell[][] board);


	/*
	 * Check if the input move is in the list of moves given by getPossibleMoves
	 */
	public abstract boolean canMove(Cell[][] board, int xx, int yy);
	
	/*
	 * 
	 */
	public abstract boolean isAlive();
	
	/*
	 * 
	 */
	public abstract boolean hasMoved();	
	
	public abstract String toString();
	
	public abstract String getType();
	
	/*
	 * Return a king of the input Color
	 */
	public final King getKing(Cell[][] board, int Color) {
		Piece temp = null;
		for(int y=0; y<8; y++) {
			for(int x=0; x< 8; x++) {
				try {
					temp = board[x][y].piece;
					if(temp.getType().equals("King") && temp.Color == Color) {
						return (King)temp;
					}

				} catch (Exception e) { continue; }
			}
		}
		return null;
	}
	
	/*
	 * Check if the king input is in check by enemy pieces
	 */
	public final boolean kingInCheck(Cell[][] board, King king) {
		
				
		Cell kingCell = board[king.x][king.y];
		Piece temp = null;
		
		for(int y=0; y<8; y++) {
			for(int x=0; x< 8; x++) {
				try {
					temp = board[x][y].piece;
					if(temp == null) continue;
				} catch (Exception e) {
					continue;				
				}
				String tempType = temp.getType();
				if((tempType.equals("Queen") || tempType.equals("Rook") || tempType.equals("Bishop")) && (temp.pseudoLegalMoves(board).contains(kingCell))) {
					System.out.println("King in check");
					return true;
				}
			}
		}
		return false;
	}
	
//	public final Board newBoard(Cell[][] board) {
//		
//		return new Board();
//	}


}


