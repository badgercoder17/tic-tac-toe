import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;

public class TicTacToe {

	public static void main(String[] args) {
		// to be used if checkWinner returns an int
		boolean userWon = false;
		boolean compWon = false;
		int hasWon = 0;
		String userName = getUsername();
		char[][] board = new char[5][5];
		// create the empty board
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j < 5; j=j+2) {
				board[i][j] = '|';
			}
		}
		for (int i = 1; i < 5; i=i+2) {
			for (int j = 0; j < 5; j=j+2) {
				board[i][j] = '_';
			}
		}
		boolean userFirst = playerFirst();
		// Start the game
		for (int turn = 1; turn < 10; turn++) {
			if (userFirst) {
				System.out.println( userName + " goes first!");
				playerTurn(board);
				hasWon = checkWinner(board);
				if (hasWon == 1) {
					userWon = true;
					break;
				}
				//check is user won
				compTurn(board);
				hasWon = checkWinner(board);
				if (hasWon == 2) {
					compWon = true;
					break;
				}
				printBoard(board);
			} else {
				System.out.println( "Computer goes first!");
				compTurn(board);
				//check if comp won
				hasWon = checkWinner(board);
				if (hasWon == 2) {
					compWon = true;
					break;
				}
				playerTurn(board);
				hasWon = checkWinner(board);
				if (hasWon == 2) {
					userWon = true;
					break;
				}
				printBoard(board);
			}
		}
		if (userWon) {
			System.out.println("Congratulations " + userName + ", you won!");
			// TODO decide if I want to exit anything other than 0
			System.exit(0);
		} else if (compWon) {
			System.out.println("Sorry " + userName + ", the computer beat you!");
			System.exit(0);
		}
	}
	
	public static String getUsername () {
		System.out.print("Please enter your username: ");
		Scanner scnr = new Scanner(System.in);
		String userName = null;
		boolean validName = false;
		userName = scnr.nextLine();
		// TODO fix this regex (max 10 char name)
		// I think it's "^[a-zA-Z][0-9]{4,10}$"
		if (userName.matches("CalvinC96")) {
			validName = true;
		}
		while (!validName) {
			System.out.println("Name must be under 10 characters. Try again");
			userName = scnr.nextLine();
			if (userName.matches("CalvinC96")) {
				validName = true;
			}
		}
		scnr.close();
		return userName;
	}
	
	public static boolean playerFirst() {
		Random rng = new Random();
		int check = rng.nextInt(2);
		if (check == 1) {
			return true;
		}
		return false;
	}
	
	/*
	 * Method for the players turn. Player will always be 'X' 
	 */
	public static char[][] playerTurn(char[][] board) {
		System.out.println("Enter a row number, comma then a column number (ex. top left is 1,1. Bottom right is 3,3)");
		// Regex for correct formatting
		Pattern p = Pattern.compile("^[1-3](\\d),[1-3](\\d)$");
		Scanner scnr = new Scanner(System.in);
		String input;
		input = scnr.nextLine();
		// TODO fix this input issue
		// make sure input is in correct format
		boolean validInput = p.matcher(input).matches();
		while (!validInput) {
			System.out.println("Invalid input, try again. (Ex. 1,1)");
			input = scnr.nextLine();
			if (p.matcher(input).matches()) {
				validInput = true;
			}
		}
		//not parse the input to get the matrix values
		String delim = "[,]";
		String[] values = input.split(delim);
		int userRow = Integer.parseInt(values[0]);
		int userCol = Integer.parseInt(values[2]);
		if (userRow == 1) {
			userRow--;
		} else if (userRow == 3) {
			userRow++;
		}
		if (userCol == 1) {
			userCol--;
		} else if (userCol == 3) {
			userCol++;
		}
		// Check to see if that spot's already taken
		boolean spotTaken = false;
		if (board[userRow][userCol] != 0) {
			spotTaken = true;
		}
		if (spotTaken) {
			
		}
		board[userRow][userCol] = 'X';
		scnr.close();
		printBoard(board);
		return board;
	}
	
	/*
	 * Method for the comps turn. Comp will always be 'O'.
	 * Right now it plays randomly, but I plan to make multiple difficulties.
	 */
	public static char[][] compTurn(char[][] board) {
		Random rng = new Random();
		int row = rng.nextInt(3);
		int col = rng.nextInt(3);
		if (row == 1) {
			row--;
		} else if (row == 3) {
			row++;
		}
		if (col == 1) {
			col--;
		} else if (col == 3) {
			col++;
		}
		board[row][col] = 'O';
		printBoard(board);
		return board;
	}
	
	/*
	 * Returns 1 if user won, 2 if comp won, 0 if no one did.
	 */
	public static int checkWinner (char[][] board) {
		// ints to keep track of winners
		int userVal = 0;
		int compVal = 0;
		// check the cols
		for (int i = 0; i < 2; i++) {
			userVal = 0;
			compVal = 0;
			for (int j = 0; j < 5; j=j+2) {
				if (board[i][j] == 'X') {
					userVal++;
				} else if (board[i][j] == 'O') {
					compVal++;
				}
			}
			if (userVal == 3) {
				// user won
				return 1;
			} else if (compVal == 3) {
				// comp won
				return 2;
			}
		}
		
		//check the rows
		for (int i = 0; i < 2; i++) {
			userVal = 0;
			compVal = 0;
			for (int j = 0; j < 5; j=j+2) {
				if (board[j][i] == 'X') {
					userVal++;
				} else if (board[j][i] == 'O') {
					compVal++;
				}
			}
			if (userVal == 3) {
				// user won
				return 1;
			} else if (compVal == 3) {
				// comp won
				return 2;
			}
		}
		
		// check a diagonal
		for (int i = 0; i < 5; i=i+2) {
			userVal = 0;
			compVal = 0;
				if (board[i][i] == 'X') {
					userVal++;
				} else if (board[i][i] == 'O') {
					compVal++;
				}
			if (userVal == 3) {
				// user won
				return 1;
			} else if (compVal == 3) {
				// comp won
				return 2;
			}
		}
		
		// check other diagonal
		for (int i = 0; i < 5; i=i+2) {
			int j = 4;
			userVal = 0;
			compVal = 0;
				if (board[i][j] == 'X') {
					userVal++;
				} else if (board[i][j] == 'O') {
					compVal++;
				}
			if (userVal == 3) {
				// user won
				return 1;
			} else if (compVal == 3) {
				// comp won
				return 2;
			}
			j = j -2;
		}
		
		return 0;
	}
	
	public static void printBoard (char[][] board) {
		// print it
		// So the board looks terrible because there's no way to make it look good, but it's correct.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
	}
	
}