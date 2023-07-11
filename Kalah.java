package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.NoSuchElementException;

public class Kalah {
	private SeedList seedList;
	private Rules rules;
	private Board board;
	private PlayerTurn playerTurn;
	private Movement movement;
	private String playerInput = "";
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		initialiseGame(io);

		while (!playerInput.equals("q")) {
			int winnerIndex = board.compareWinner(playerTurn.getCurrentTurn());

			if (winnerIndex != -1) {
				int totalScore = board.addUpScore(winnerIndex);

				io.println("Game over");
				board.printBoard();
				board.getScores(winnerIndex, totalScore);
				break;
			}

			playerInput = playerTurn.getPrompt();

			try {
				validatePlayerInput(playerInput);
				int playerInputInt = Integer.parseInt(playerInput);
				validatePlayerInputBounds(playerInputInt);

				if (playerTurn.getCurrentTurn() == 0) {
					validateNonEmptyHouse(seedList.getMapP1Seeds(playerInputInt));
				} else {
					validateNonEmptyHouse(seedList.getMapP2Seeds(playerInputInt));
				}

				movement.moveSeed(playerTurn, seedList);
				board.printBoard();

			} catch (NumberFormatException e) {
				io.println("Invalid input, please try again!");
			} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
				io.println(e.getMessage());
				board.printBoard();
			} catch (NoSuchElementException e) {
				io.println(e.getMessage());
				board.printBoard();
			} catch (Exception e) {
				io.println("An unexpected error occurred.");
				board.printBoard();
			}
		}
	}

	private void validatePlayerInput(String playerInput) {
		if (playerInput.equals("q")) {
			throw new IllegalArgumentException("Game over");
		}
	}

	private void validatePlayerInputBounds(int playerInputInt) {
		if (playerInputInt < 1 || playerInputInt > 6) {
			throw new ArrayIndexOutOfBoundsException("Out of bounds, please try again!");
		}
	}

	private void validateNonEmptyHouse(int seedCount) {
		if (seedCount == 0) {
			throw new NoSuchElementException("House is empty. Move again.");
		}
	}

	public void initialiseGame(IO io) {
		this.seedList = new SeedList();
		this.rules = new Rules();
		this.board = new Board(io, rules, seedList);
		this.playerTurn = new PlayerTurn(io, rules);
		this.movement = new Movement(playerTurn, seedList);
	}
}
