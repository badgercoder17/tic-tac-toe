import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;

public class TicTacToe {

	public static void main(String[] args) {
		boolean playerWon = false;
		boolean compWon = false;
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
		boolean checkFirst = playerFirst();
		
		for (int turn = 1; turn < 10; turn++) {
			if (checkFirst) {
				playerTurn(board, playerWon);
				//check is user won
				compTurn(board, compWon);
			} else {
				compTurn(board, compWon);
				//check if comp won
				playerTurn(board, playerWon);
			}
		}
		printBoard(board);
	}
	
	public static String getUsername () {
		System.out.print("Please enter your username: ");
		Scanner scnr = new Scanner(System.in);
		String userName = null;
		boolean validName = false;
		userName = scnr.nextLine();
		// TODO fix this regex (max 10 char name)
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
		int check = rng.nextInt(1);
		if (check == 1) {
			return true;
		}
		return false;
	}
	
	public static char[][] playerTurn(char[][] board, boolean playerWon) {
		System.out.println("Enter a row number, comma then a column number (ex. top left is 1,1. Bottom right is 3,3)");
		// Regex for correct formatting
		Pattern p = Pattern.compile("^(\\d),(\\d)");
		Scanner scnr = new Scanner(System.in);
		String input = scnr.nextLine();
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
		
		scnr.close();
		return null;
	}
	
	public static char[][] compTurn(char[][] board, boolean compWon) {
		return null;
	}
	
	public boolean checkWinner (String userName) {
		return false;
	}
	public static void printBoard (char[][] board) {
		// print it
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println('\n');
		}
		
	}
	
}