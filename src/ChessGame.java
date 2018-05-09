import java.util.Scanner;

public class ChessGame {

	public static void main(String[] args) {
		Board gameBoard = new Board();
		gameBoard = initializePieces();

		Scanner sc = new Scanner(System.in);
		int startX, startY, endX, endY;
		String temp; int[] arr;
		
		
		while(true) {
			System.out.println(gameBoard);
			System.out.println("Enter starting cell, followed by ending cell (In the form D2, D4). Enter 999 to quit");
		//	try {
				temp = sc.nextLine();
				arr = convertNotation(temp);
				startX = arr[0]; startY = arr[1]; 
				
				temp = sc.nextLine();
				arr = convertNotation(temp);
				endX = arr[0]; endY = arr[1];
				if(startX == 999) break;
				gameBoard.movePiece(startX, startY, endX, endY);

//			} catch (NullPointerException e) {
//				System.out.println("Input in the wrong format");
//			} 
		}
	
		sc.close();
		System.out.println("Goodbye!");
	}
	
	
	public static Board initializePieces() {
		Piece[] pieces = new Piece[32];
		Board gameBoard = new Board();
		
		//Pawns
		for(int i=0; i<8; i++) { 
			pieces[i] = new Pawn(i,6,0); //White
			pieces[i+16] = new Pawn(i,1,1); //Black
		}
		
		pieces[8] = new Knight(1,7,0); //White Knights
		pieces[9] = new Knight(6,7,0);
		pieces[10] = new Bishop(2,7,0); //White Bishops
		pieces[11] = new Bishop(5,7,0);
		pieces[12] = new Rook(0,7,0); //White Rooks
		pieces[13] = new Rook(7,7,0);
		pieces[14] = new Queen(3,7,0); //White Queen
		pieces[15] = new King(4,7,0); //White King
 		
		pieces[24] = new Knight(1,0,1); //Black Knights
		pieces[25] = new Knight(6,0,1);
		pieces[26] = new Bishop(2,0,1); //Black Bishops
		pieces[27] = new Bishop(5,0,1);
		pieces[28] = new Rook(0,0,1); //Black Rooks
		pieces[29] = new Rook(7,0,1);
		pieces[30] = new Queen(3,0,1); //Black Queen
		pieces[31] = new King(4,0,1); //Black King

		
		for(int i=0; i<32; i++) {
				gameBoard.setCell(pieces[i]);
		}
		return gameBoard;
	}
	
	/*
	 * This method converts the standard chess notation into numbers for our board
	 */
	public static int[] convertNotation(String notation) {
		
		char charX = notation.charAt(0); //Gets ABC..
		char charY = notation.charAt(1);
		if(charX > 'H' || charX < 'A' || charY > '8' || charY < '1') return null;
		
		int x = charX - 'A';
		int y = -charY + '1' + 7;

		int[] arr = {x,y};
		return arr;
	}
	
}
