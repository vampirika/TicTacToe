package foo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerOnePositions = new ArrayList<Integer>();
	static ArrayList<Integer> playerTwoPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
			startGame();
	}

	public static void startGame() {
		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' }, };
		printGameBoard(gameBoard);

		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter placement (1-9)");
			int playerOnePosition = input.nextInt();
			System.out.println("You chose: " + playerOnePosition);
			while (playerOnePositions.contains(playerOnePosition) || playerTwoPositions.contains(playerOnePosition)) {
				System.out.println("Position taken, retry.");
				playerOnePosition = input.nextInt();
			}
			placePiece(gameBoard, playerOnePosition, "player one");
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				printGameBoard(gameBoard);
				break;
			}
			System.out.println(result);
			Random randomPos = new Random();
			int playerTwoPosition = randomPos.nextInt(9) + 1;
			while (playerOnePositions.contains(playerTwoPosition) || playerTwoPositions.contains(playerTwoPosition)) {
				playerTwoPosition = randomPos.nextInt(9) + 1;
			}
			placePiece(gameBoard, playerTwoPosition, "player two");
			System.out.println("Opponent chose: " + playerTwoPosition);
			printGameBoard(gameBoard);
			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				printGameBoard(gameBoard);
				break;
			}
		}
	}

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePiece(char[][] gameBoard, int position, String player) {

		char symbol = 'X';
		if (player.equals("player one")) {
			symbol = 'X';
			playerOnePositions.add(position);
		} else if (player.equals("player two")) {
			symbol = 'O';
			playerTwoPositions.add(position);
		}

		switch (position) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		}

	}

	public static String checkWinner() {

		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(3, 5, 7);

		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(diagonal1);
		winningConditions.add(diagonal2);

		for (List l : winningConditions) {
			if (playerOnePositions.containsAll(l)) {
				return "Player One wins!";
			} else if (playerTwoPositions.containsAll(l)) {
				return "Player Two wins!";
			} else if (playerOnePositions.size() + playerTwoPositions.size() == 9) {
				return "Game ends in draw.";
			}
		}

		return "";
	}
}
