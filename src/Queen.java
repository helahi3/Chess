import java.util.ArrayList;

public class Queen extends Piece {
	String type;
	
	public Queen(int iX, int iY, int iColor) {
		super(iX, iY, iColor);
		type = "Queen";
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
		 * Ugly hack below
		 * Copied method from Rook and Bishop both
		 * TODO: Find an efficient way of doing that
		 */
		
		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();
		int offsetX, offsetY;
		King enemyKing = null;
		
		if(this.Color == 1) enemyKing = super.getKing(board, 0);
		if(this.Color == 0) enemyKing = super.getKing(board, 1);
		
		Board newBord = new Board();
		newBord.board = board;

		
		for(int j =0; j < 4; j++) {
			if(j==0) {offsetX = 1; offsetY = 0;}
			else if(j == 1) {offsetX = -1; offsetY = 0;}
			else if(j == 2) {offsetX = 0; offsetY = 1;}
			else {offsetX = 0; offsetY = -1;}
			
			for(int i= 1; i< 8; i++) {
				if(i == 0) continue;
				offsetX *= i; offsetY *= i;
				
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) {						//Check if next piece is empty
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					
						newBord.simpleMove(x, y, x+offsetX, y+offsetY);
						if(super.kingInCheck(newBord.board, enemyKing)) listOfMoves.remove(listOfMoves.size() - 1); 
						newBord.undoSimpleMove(x, y, x-offsetX, y-offsetY);
					}
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) {	//If next space has friendly piece, break
						break;	
					}
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
						newBord.simpleMove(x, y, x+offsetX, y+offsetY);
						if(super.kingInCheck(newBord.board, enemyKing)) listOfMoves.remove(listOfMoves.size() - 1); 
						newBord.undoSimpleMove(x, y, x-offsetX, y-offsetY);

						break;
					}
				}				
				offsetX /= i; offsetY /= i;
			}
		}
		
		for(int j=0 ; j<4 ; j++) { //4 cases for 4 possible diagonal paths bishop can take. TODO: Use case/switch instead??
			if(j==0) {offsetX = 1; offsetY = 1;}
			else if(j == 1) {offsetX = 1; offsetY = -1;}
			else if(j == 2) {offsetX = -1; offsetY = 1;}
			else {offsetX = -1; offsetY = -1;}

			
			for(int i=1; i <= 7; i++) { //Loop through maximum possible diagonal
				offsetX *= i; offsetY *= i;
	
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) {						//Check if next piecec is empty						
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					
						newBord.simpleMove(x, y, x+offsetX, y+offsetY);
						if(super.kingInCheck(newBord.board, enemyKing)) listOfMoves.remove(listOfMoves.size() - 1); 
						newBord.undoSimpleMove(x, y, x-offsetX, y-offsetY);
					}
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) {	//If next space has friendly piece, break
						break;
					}
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					
						newBord.simpleMove(x, y, x+offsetX, y+offsetY);
						if(super.kingInCheck(newBord.board, enemyKing)) listOfMoves.remove(listOfMoves.size() - 1); 
						newBord.undoSimpleMove(x, y, x-offsetX, y-offsetY);

						break;
					}
				}				offsetX /= i; offsetY /= i; //Set offset values back to original
			}
		
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
		return "Q" + Color;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public ArrayList<Cell> pseudoLegalMoves(Cell[][] inputBoard) {
		
		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();
		int offsetX, offsetY;
		Board newBoard = new Board();
		Cell[][] board = newBoard.board;
		
		for(int j =0; j < 4; j++) {
			if(j==0) {offsetX = 1; offsetY = 0;}
			else if(j == 1) {offsetX = -1; offsetY = 0;}
			else if(j == 2) {offsetX = 0; offsetY = 1;}
			else {offsetX = 0; offsetY = -1;}
			
			for(int i= 1; i< 8; i++) {
				if(i == 0) continue;
				offsetX *= i; offsetY *= i;
				
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) {						//Check if next piece is empty
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					}
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) {	//If next space has friendly piece, break
						break;	
					}
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
						break;
					}
				}				
				offsetX /= i; offsetY /= i;
			}
		}
		
		for(int j=0 ; j<4 ; j++) { //4 cases for 4 possible diagonal paths bishop can take. TODO: Use case/switch instead??
			if(j==0) {offsetX = 1; offsetY = 1;}
			else if(j == 1) {offsetX = 1; offsetY = -1;}
			else if(j == 2) {offsetX = -1; offsetY = 1;}
			else {offsetX = -1; offsetY = -1;}

			
			for(int i=1; i <= 7; i++) { //Loop through maximum possible diagonal
				offsetX *= i; offsetY *= i;
	
				if(x + offsetX <0 || x + offsetX >7 || y + offsetY <0 || y + offsetY >7 ) continue; //Dont include cases where move exceeds board
				else {
					if(board[x+offsetX][y+offsetY].isEmpty()) {						//Check if next piecec is empty						
						listOfMoves.add(board[x+offsetX][y+offsetY]);
					}
					else if(board[x+offsetX][y+offsetY].piece.Color == this.Color) {	//If next space has friendly piece, break
						break;
					}
					else if(board[x+offsetX][y+offsetY].piece.Color != this.Color) {	//If next space has enemy, kill and then break
						listOfMoves.add(board[x+offsetX][y+offsetY]);
						break;
					}
				}				offsetX /= i; offsetY /= i; //Set offset values back to original
			}
		
		}
		
		
		return listOfMoves;	}

}
