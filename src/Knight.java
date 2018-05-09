

import java.util.ArrayList;

public class Knight extends Piece {

	String type;
	
	public Knight(int iX, int iY, int iColor) {
		super(iX, iY, iColor);
		type = "Knight";
	}

	
	@Override
	public boolean validPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cell> getPossibleMoves(Cell[][] board) {
		/*
		 * Movement of Knight is as follows
		 * X changes by 1, Y changes by 2
		 * X changes by 2, Y changes by 1
		 * Can take pieces everywhere
		 * Can jump over pieces
		 */
		
		ArrayList<Cell> listOfMoves = new ArrayList<Cell>();
		int offsetX, offsetY; 
		
		King enemyKing = null;
		if(this.Color == 1) enemyKing = super.getKing(board, 0);
		if(this.Color == 0) enemyKing = super.getKing(board, 1);		
		Board newBord = new Board();
		newBord.board = board;

		int[][] arr = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,-1}}; //All potential ways to move a knight 
		
		for(int i=0; i<arr.length; i++) {
			offsetX = arr[i][0]; offsetY = arr[i][1]; //First number in inner array is the offset for X, second is offset for Y
			
			//Making sure the move does not exceed the board limits
			if(this.x + offsetX <= 7 && this.x + offsetX >= 0 && this.y + offsetY <=7 && this.y + offsetY >=0) {
				
				//Make sure the destination is either empty, or an enemy color
				if(board[x+offsetX][y+offsetY].isEmpty() || board[x+offsetX][y+offsetY].piece.Color != this.Color) {
					listOfMoves.add(board[x+offsetX][y+offsetY]);
				
					newBord.simpleMove(x, y, x+offsetX, y+offsetY);
					if(super.kingInCheck(newBord.board, enemyKing)) listOfMoves.remove(listOfMoves.size() - 1); 
					newBord.undoSimpleMove(x, y, x-offsetX, y-offsetY);

				}
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
		return "N" + Color;
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


		int[][] arr = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,-1}}; //All potential ways to move a knight 
		
		for(int i=0; i<arr.length; i++) {
			offsetX = arr[i][0]; offsetY = arr[i][1]; //First number in inner array is the offset for X, second is offset for Y
			
			//Making sure the move does not exceed the board limits
			if(this.x + offsetX <= 7 && this.x + offsetX >= 0 && this.y + offsetY <=7 && this.y + offsetY >=0) {
				
				//Make sure the destination is either empty, or an enemy color
				if(board[x+offsetX][y+offsetY].isEmpty() || board[x+offsetX][y+offsetY].piece.Color != this.Color) {
					listOfMoves.add(board[x+offsetX][y+offsetY]);

				}
			}
		}
		return listOfMoves;
	}
	

}
