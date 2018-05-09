import java.util.ArrayList;

public class King extends Piece {

	boolean inCheck;
	String type;
	
	
	public King(int iX, int iY, int iColor) {
		super(iX, iY, iColor);
		inCheck = false;
		type = "King";
	}

	@Override
	public boolean validPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cell> getPossibleMoves(Cell[][] board) {
		/*
		 * X or Y or both can change 
		 * Can take pieces anywhere
		 * Have to stop before friendly pieces
		 * Cannot jump over pieces
		 * Ugly hack, copied bishop and rook part
		 * Limited movement length to 1
		 */
		
		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();
		int offsetX, offsetY;
		
		for(int j =0; j < 4; j++) {
			if(j==0) {offsetX = 1; offsetY = 0;}
			else if(j == 1) {offsetX = -1; offsetY = 0;}
			else if(j == 2) {offsetX = 0; offsetY = 1;}
			else {offsetX = 0; offsetY = -1;}
			
			int i = 1;
				if(i == 0) continue;
				offsetX *= i; offsetY *= i;
				
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) 						//Check if next piece is empty
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) 	//If next space has friendly piece, break
						break;
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
						break;
					}
				}
				
				offsetX /= i; offsetY /= i;
			
		}
		
		for(int j=0 ; j<4 ; j++) { //4 cases for 4 possible diagonal paths bishop can take. TODO: Use case/switch instead??
			if(j==0) {offsetX = 1; offsetY = 1;}
			else if(j == 1) {offsetX = 1; offsetY = -1;}
			else if(j == 2) {offsetX = -1; offsetY = 1;}
			else {offsetX = -1; offsetY = -1;}

			
			int i = 1;
			offsetX *= i; offsetY *= i;
	
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) 						//Check if next piecec is empty
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) 	//If next space has friendly piece, break
						break;
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
						break;
					}
				}
				offsetX /= i; offsetY /= i; //Set offset values back to original
			
		
			}
		
			return listOfMoves;
		}

	@Override
	public boolean canMove(Cell[][] board, int xx, int yy){

		ArrayList<Cell> listOfMoves = getPossibleMoves(board);
		if(listOfMoves.contains(board[xx][yy])) return true;

		return false;
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasMoved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "K" + Color;
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public boolean inCheck(Cell[][] board) {
		Cell tempCell = board[x][y];
		Piece temp = null;
		
		for(int y=0; y<8; y++) {
			for(int x=0; x< 8; x++) {
				try {
					temp = board[x][y].piece;
				} catch (Exception e) {
					continue;				
				}
				if(temp.getPossibleMoves(board).contains(tempCell)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<Cell> pseudoLegalMoves(Cell[][] board) {
		return getPossibleMoves(board);
	}

}
