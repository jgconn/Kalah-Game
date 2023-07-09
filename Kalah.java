package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure. Remove this comment (or rather, replace it
 * with something more appropriate)
 */
public class Kalah {
	private String playerInput = "";
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		SeedList seedList = new SeedList();
		Rules rules = new Rules();
		Board board = new Board(io, rules, seedList);
		PlayerTurn playerTurn = new PlayerTurn(io, rules);
		Movement movement = new Movement(playerTurn, seedList);

		while (!playerInput.equals("q")) {
			int checkWinner = board.compareWinner(seedList, playerTurn.getCurrentTurn());

			if (checkWinner != -1) {
				int totalScore = board.addUpScore(seedList, checkWinner);

				System.out.println("END");
				io.println("Game over");
				board.printBoard(rules, seedList);
				board.getScores(seedList, checkWinner, totalScore);
				break;
			}
			playerInput = playerTurn.getPrompt();
			try {
				if (playerInput.equals("q")) {
					throw new IllegalArgumentException("Game over");
				}

				int playerInputInt = Integer.parseInt(playerInput);

				if (playerInputInt < 1 || playerInputInt > 6) {
					throw new ArrayIndexOutOfBoundsException("Out of bounds, please try again!");
				}

				if (playerTurn.getCurrentTurn() == 0) {
					if (seedList.getMapP1Seeds(playerInputInt) == 0) {
						throw new NullPointerException("House is empty. Move again.");
					}
				} else {
					if (seedList.getMapP2Seeds(playerInputInt) == 0) {
						throw new NullPointerException("House is empty. Move again.");
					}
				}
				movement.moveSeed(playerTurn, seedList);
				board.printBoard(rules, seedList);



				// add movement class
				//io.println(String.valueOf(playerTurn.getCurrentTurn()));
				//movement.moveSeed(playerTurn, seedList);
				//board.printBoard(rules, seedList);


			} catch (NumberFormatException e) {
				io.println("Invalid input, please try again!");
			} catch (IllegalArgumentException e) {
				io.println(e.getMessage());
				board.printBoard(rules, seedList);
			} catch (ArrayIndexOutOfBoundsException e) {
				io.println(e.getMessage());
			} catch (NullPointerException e) {
				io.println(e.getMessage());
				board.printBoard(rules, seedList);
			}
		}
	}
}
