import java.util.ArrayList;

public class Board {

	public Cell[][] board;
	private Piece tempRemovedPiece;
	private int turn;
	private boolean removedPieceFlag;
	
	public Board() {
		board = new Cell[8][8];
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				board[x][y] = new Cell();
			}
		}
		turn = 0;
		tempRemovedPiece = null;
		removedPieceFlag = false;
	}
	
	/*
	 * Overload for creating a new board with same Cell pieces
//	 */
//	public Board(Cell[][] brd) {
//		board = brd.clone();
//		for(int y=0; y<8; y++) {
//			for(int x=0; x<8; x++) {
//				board[x][y] = new Cell(x,y,board[x][y].piece);
//			}
//		}
//	}
	
	public Cell[][] makeCopy(Cell[][] brd){
//TODO;		
		return null;
	}
	
	/*
	 * Move the piece in the starting Cell to the ending Cell
	 * TODO: Implement check for killing
	 */
	public void movePiece(int startX, int startY, int endX, int endY) {
		Piece temp = board[startX][startY].piece; //Take piece from starting point
			
		if(turn == temp.Color && temp != null && temp.canMove(board,endX,endY)) { //If that piece is allowed to move to that cell
			board[startX][startY].clearCell(); //Remove it from starting cell
			board[endX][endY].setPiece(temp); //Add it to ending cell
			temp.hasMoved = true;
			temp.x = endX; temp.y = endY;
			
			turn = (temp.Color + 1) % 2;
			
		}
		else {
			if(turn != temp.Color) System.out.println("Wrong turn");
			if(temp == null) System.out.println("No piece in the selected cell");
			if(!temp.canMove(board, endX, endY)) System.out.println("That piece cannot move to the selected space");
			System.out.println("Piece not moved. Try again"); //TODO: Raise flag that piece was not moved
		}
	}
	
	
	/*
	 * Simple move method that doesnt check if move is legal
	 * useful for just moving pieces around the board
	 */
	public void simpleMove(int startX, int startY, int endX, int endY) {
		Piece temp = board[startX][startY].piece; //Take piece from starting point
		board[startX][startY].clearCell(); //Remove it from starting cell
		
		if(!board[endX][endY].isEmpty()) {
			tempRemovedPiece = board[endX][endY].piece;
			removedPieceFlag = true;
		}
		board[endX][endY].setPiece(temp); //Add it to ending cell
		temp.x = endX; temp.y = endY;
	}
	
	public void undoSimpleMove(int startX, int startY, int endX, int endY) {
		Piece temp = board[startX][startY].piece; //Take piece from starting point
		board[startX][startY].clearCell(); //Remove it from starting cell
		if(removedPieceFlag)
			board[startX][startY].piece = tempRemovedPiece;
		board[endX][endY].setPiece(temp); //Add it to ending cell
		temp.x = endX; temp.y = endY;
		removedPieceFlag = false;
	}
	
	/*
	 * Remove a piece from the board
	 * and return it
	 * TODO: Add it to list of killed pieces
	 */
	public Piece removePiece(int x, int y) {
		Piece temp = board[x][y].piece;
		board[x][y].clearCell();
		return temp;
	}
	
	public Cell getCell(int x, int y) {
		return board[x][y];
	}
	
	public void setCell(Piece piece) {
		int x, y;
		x = piece.x; y = piece.y;
		board[x][y].setPiece(piece);
	}
	
	public String toString() {
		String result = "";
		result += "    A     B     C     D     E     F     G     H\n";
		
		for(int y=0; y<8; y++) {
			result += 8 - y + " ";
			for(int x=0; x< 8; x++) {
				result += board[x][y];
			}
			result += "\n";
		}
		
		return result;
	}
//	
//	public String toString() {
//		String result = "";
//		for(int y=0; y<8; y++) {
//			for(int x=0; x<8; x++) {
//				result += board[x][y];
//			}
//			result += "\n";
//		}
//		return result;
//	}
	
	/*
	 * Get the King of a particular color
	 * TODO: Test the weird casting
	 * Maybe put some checks
	 */
	public King getKing(int Color) {
		Piece temp = null;
		for(int y=0; y<8; y++) {
			for(int x=0; x< 8; x++) {
				temp = board[x][y].piece;
				if(temp.getType().equals("King") && temp.Color == Color) break;
			}
		}
		return (King)temp;
	}
	
	/*
	 * Test if the King of that color is in check
	 */
	public boolean kingInCheck(int Color) {
		
		King tempKing = getKing(Color);
		int kingX = tempKing.x;
		int kingY = tempKing.y;
		Cell tempCell = board[kingX][kingY];
		Piece temp = null;
		
		for(int y=0; y<8; y++) {
			for(int x=0; x< 8; x++) {
				temp = board[x][y].piece;
				if(temp.getPossibleMoves(board).contains(tempCell)) {
					return true;
				}
			}
		}
		return false;
	}

	
}
