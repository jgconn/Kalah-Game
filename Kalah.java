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
			playerInput = playerTurn.getPrompt();
			try {
				if (playerInput.equals("q")) {
					throw new IllegalArgumentException("Game over");
				}

				int playerInputInt = Integer.parseInt(playerInput);

				if (playerInputInt < 1 || playerInputInt > 6) {
					throw new ArrayIndexOutOfBoundsException("Out of bounds, please try again!");
				}

				// add movement class
				//io.println(String.valueOf(playerTurn.getCurrentTurn()));
				movement.moveSeed(playerInputInt, playerTurn, seedList);
				board.printBoard(rules, seedList);


			} catch (NumberFormatException e) {
				io.println("Invalid input, please try again!");
			} catch (IllegalArgumentException e) {
				io.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				io.println(e.getMessage());
			}
		}
	}
}
