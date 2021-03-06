package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {

	private String topLeftPlacement;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	static JButton[] buArr;

	static ArrayList<Integer> playerOnePositions = new ArrayList<Integer>();
	static ArrayList<Integer> playerTwoPositions = new ArrayList<Integer>();

	public GUI() {

		frame = new JFrame();
		buArr = new JButton[9];
		JButton[] buArr = new JButton[9];
		for (int i = 0; i < 9; i++) {
			buArr[i] = new JButton("" + (i + 1));
			buArr[i].addActionListener(this);
			System.out.println("button added");
		}

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(4, 3));
		for (JButton b : buArr) {
			panel.add(b);
			System.out.println("Button Added to GUI");
		}

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe Game");
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//placePiece(0, "player one");
		System.out.println("Button pressed");
	}
// placePiece does not happen.
// Need to work out how to have button press relevant to the button.
	public static void placePiece(int position, String player) {

		String symbol = "X";
		if (player.equals("player one")) {
			symbol = "X";
			playerOnePositions.add(position);
		} else if (player.equals("player two")) {
			symbol = "O";
			playerTwoPositions.add(position);
		}

		switch (position) {
		case 1:
			buArr[0].setText(symbol);
			
			break;
		case 2:
			buArr[1].setText(symbol);
			break;
		case 3:
			buArr[2].setText(symbol);
			break;
		case 4:
			buArr[3].setText(symbol);
			break;
		case 5:
			buArr[4].setText(symbol);
			break;
		case 6:
			buArr[5].setText(symbol);
			break;
		case 7:
			buArr[6].setText(symbol);
			break;
		case 8:
			buArr[7].setText(symbol);
			break;
		case 9:
			buArr[8].setText(symbol);
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

	public static void main(String[] args) {
		new GUI();
	}

}
