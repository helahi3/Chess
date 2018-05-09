
import java.util.ArrayList;

public class Pawn extends Piece {
	String type;
	
	public Pawn(int iX, int iY, int iColor) {
		super(iX, iY, iColor);
		this.type = "Pawn";
		
	}

	
	@Override
	public boolean validPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cell> getPossibleMoves(Cell[][] board) {

		/*
		 * Pawn can move forward 1 space
		 * Can move up to 2 spaces forward if it hasn't moved yet
		 * Can only take pieces diagonally
		 * TODO: Upgrading
		 * TODO: En Passant 
		 */
		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();

		King whiteKing = super.getKing(board, 0);
		King blackKing = super.getKing(board, 1);

		Board newBord = new Board();
		newBord.board = board;
		
		if(this.Color == 1) { //Black starts at top of board
			//Remove the possible move from listOfMoves if friendly king will be in check
			
			//If space below pawn is empty, add to possible moves
			if(board[x][y+1].isEmpty()) {
				listOfMoves.add(board[x][y+1]); 
				newBord.simpleMove(x, y, x, y+1);
				if(super.kingInCheck(newBord.board, blackKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x, y-1);
			}
			
			//If pawn is in starting position, and next two spaces are empty, add to possible moves
			if(this.y == 1 && board[x][y+1].isEmpty() && board[x][y+2].isEmpty()) {
				listOfMoves.add(board[x][y+2]);
				newBord.simpleMove(x, y, x, y+2);
				if(super.kingInCheck(board, blackKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x, y-2);
			}
			
			
			//If diagonals contain enemy pieces, can attack so add to possible moves
			if(x <= 6 && !board[x+1][y+1].isEmpty() && board[x+1][y+1].piece.Color == 0) {
				listOfMoves.add(board[x+1][y+1]);
				newBord.simpleMove(x, y, x+1, y+1);
				if(super.kingInCheck(board, blackKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x-1, y-1);
			}
			
			
			if(x >= 1 && !board[x-1][y+1].isEmpty() && board[x-1][y+1].piece.Color == 0) {
				listOfMoves.add(board[x-1][y+1]);
				newBord.simpleMove(x, y, x-1, y+1);
				if(super.kingInCheck(board, blackKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x+1, y-1);
			}
			
		}
		else { //White starts at bottom of board
			
			//If space above pawn is empty, add to possible moves
			if(board[x][y-1].isEmpty()) {
				listOfMoves.add(board[x][y-1]);
				newBord.simpleMove(x, y, x, y-1);
				if(super.kingInCheck(board, whiteKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x, y+1);
			}
			
			//If pawn is in starting position, and next two spaces are empty, add to possible moves
			if(this.y == 6 && board[x][y-1].isEmpty() && board[x][y-2].isEmpty()) {
				listOfMoves.add(board[x][y-2]);
			
				newBord.simpleMove(x, y, x, y-2);
				if(super.kingInCheck(board, whiteKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x, y+2);
			}
			
			//If diagonals contain enemy pieces, can attack so add to possible moves
			if(x <= 6 && !board[x+1][y-1].isEmpty() && board[x+1][y-1].piece.Color == 1) {
				listOfMoves.add(board[x+1][y-1]);
				newBord.simpleMove(x, y, x+1, y-1);
				if(super.kingInCheck(board, whiteKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x-1, y+1);
			}
			
			if(x >= 1 && !board[x-1][y-1].isEmpty() && board[x-1][y-1].piece.Color == 1) {
				listOfMoves.add(board[x-1][y-1]);
				newBord.simpleMove(x, y, x-1, y-1);
				if(super.kingInCheck(board, whiteKing)) listOfMoves.remove(listOfMoves.size() - 1); 
				newBord.undoSimpleMove(x, y, x+1, y+1);
			}	
		}
		
		return listOfMoves;
	}
	
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
		return "P" + Color;
	}


	@Override
	public String getType() {
		return this.type;
	}


	@Override
	public ArrayList<Cell> pseudoLegalMoves(Cell[][] inputBoard) {

		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();
		Board newBoard = new Board();
		Cell[][] board = newBoard.board;

		
		if(this.Color == 1) { //Black starts at top of board
			//Remove the possible move from listOfMoves if friendly king will be in check
			
			//If space below pawn is empty, add to possible moves
			if(board[x][y+1].isEmpty()) {
				listOfMoves.add(board[x][y+1]); 
			}
			
			//If pawn is in starting position, and next two spaces are empty, add to possible moves
			if(this.y == 1 && board[x][y+1].isEmpty() && board[x][y+2].isEmpty()) {
				listOfMoves.add(board[x][y+2]);
			}
			
			
			//If diagonals contain enemy pieces, can attack so add to possible moves
			if(x <= 6 && !board[x+1][y+1].isEmpty() && board[x+1][y+1].piece.Color == 0) {
				listOfMoves.add(board[x+1][y+1]);
			}
			
			
			if(x >= 1 && !board[x-1][y+1].isEmpty() && board[x-1][y+1].piece.Color == 0) {
				listOfMoves.add(board[x-1][y+1]);
			}
			
		}
		else { //White starts at bottom of board
			
			//If space above pawn is empty, add to possible moves
			if(board[x][y-1].isEmpty()) {
				listOfMoves.add(board[x][y-1]);
			}
			
			//If pawn is in starting position, and next two spaces are empty, add to possible moves
			if(this.y == 6 && board[x][y-1].isEmpty() && board[x][y-2].isEmpty()) {
				listOfMoves.add(board[x][y-2]);
			}
			
			//If diagonals contain enemy pieces, can attack so add to possible moves
			if(x <= 6 && !board[x+1][y-1].isEmpty() && board[x+1][y-1].piece.Color == 1) {
				listOfMoves.add(board[x+1][y-1]);
			}
			
			if(x >= 1 && !board[x-1][y-1].isEmpty() && board[x-1][y-1].piece.Color == 1) {
				listOfMoves.add(board[x-1][y-1]);
			}	
		}
		
		return listOfMoves;	}

}
