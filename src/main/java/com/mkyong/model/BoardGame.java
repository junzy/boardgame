package com.mkyong.model;

import java.util.List;

/**
 * The Class BoardGame that handles
 */
public class BoardGame {

	/** Constants */
	public static final int TOTAL_PITS = 14;
	public static final int P1_LOWER = 0;
	public static final int P1_UPPER = 5;
	public static final int P2_LOWER = 7;
	public static final int P2_UPPER = 12;
	public static final int P1_LARGE_POSITION = 6;
	public static final int P2_LARGE_POSITION = 13;
	public static final int NORMAL_PIT_STARTING_COUNT = 6;
	public static final int LARGE_PIT_STARTING_COUNT = 0;

	/** The first player */
	private Player player1;

	/** The second player */
	private Player player2;

	/** The board. */
	private Board board;

	/** The current active player. */
	private Player currentActivePlayer;

	/**
	 * Instantiates a new board game.
	 *
	 * @param playerName1
	 *            the player name 1
	 * @param playerName2
	 *            the player name 2
	 */
	public BoardGame(String playerName1, String playerName2) {
		this.player1 = new Player(playerName1);
		this.player2 = new Player(playerName2);
		this.board = new Board();

		// set the current active player
		setCurrentActivePlayer(player1);
	}
	

	/**
	 * Gets the current active player.
	 *
	 * @return the current active player
	 */
	public Player getCurrentActivePlayer() {
		return this.currentActivePlayer;
	}

	/**
	 * Gets the pits. Also doubles as the game state.
	 *
	 * @return the pits
	 */
	public List getPits() {
		return board.getPits();
	}

	/**
	 * Sets the current active player.
	 *
	 * @param currentPlayer
	 *            the new current active player
	 */
	public void setCurrentActivePlayer(Player currentPlayer) {
		this.currentActivePlayer = currentPlayer;
	}

	/**
	 * Creates the pits and instantiates the initial count
	 */
	// instantiates the list of pits for both players
	public void createPits() {
		for (int i = P1_LOWER; i <= P1_UPPER; i++) {
			Pit pit = new Pit(PitType.NORMAL, NORMAL_PIT_STARTING_COUNT, i);
			board.addPit(pit);
		}
		// Creates the large Pit for Player1
		Pit largePit = new Pit(PitType.BIG, LARGE_PIT_STARTING_COUNT, P1_LARGE_POSITION);
		board.addPit(largePit);

		for (int i = P2_LOWER; i <= P2_UPPER; i++) {
			Pit pit = new Pit(PitType.NORMAL, NORMAL_PIT_STARTING_COUNT, i);
			board.addPit(pit);
		}
		// Creates the large Pit for Player2
		largePit = new Pit(PitType.BIG, LARGE_PIT_STARTING_COUNT, P2_LARGE_POSITION);
		board.addPit(largePit);

	}

	/**
	 * Toggle active player.
	 */
	public void toggleActivePlayer() {
		if (currentActivePlayer == player1) {
			this.currentActivePlayer = player2;
		} else {
			this.currentActivePlayer = player1;
		}
	}

	/**
	 * Make move for both players
	 *
	 * @param pitPosition
	 *            the pit position
	 */
	public List<Pit> makeMove(String pitPositionStr) {

		if (!pitPositionStr.equals("null")) {
			int pitPosition = Integer.parseInt(pitPositionStr);
			// Check count of stones in the given pit
			int stoneCount = board.getNumberOfStones(pitPosition);

			// check which players turn is it currently
			if (currentActivePlayer == player1 && pitPosition >= P1_LOWER && pitPosition <= P1_UPPER
					&& stoneCount > 0) {
				// check if valid player1 move

				int index = placeStones(pitPosition);

				// Check if the last rock placed by player1 was into an empty cell.
				// if so, takeover the rocks from the opposite row.
				checkCapture(index, P1_LOWER, P1_UPPER, P1_LARGE_POSITION);

				// Check whos turn it is
				if (index != P1_LARGE_POSITION) {
					// toggle active player
					toggleActivePlayer();
				}
			} else if (pitPosition >= P2_LOWER && pitPosition <= P2_UPPER && stoneCount > 0) {

				int index = placeStones(pitPosition);

				// Check if the last rock placed by player1 was into an empty cell.
				// if so, takeover the rocks from the opposite row.
				checkCapture(index, P2_LOWER, P2_UPPER, P2_LARGE_POSITION);

				// Check whos turn it is
				if (index != P2_LARGE_POSITION) {
					// toggle active player
					toggleActivePlayer();
				}
			}
		}

		// Check game finished
		boolean isFinished = checkFinished(board);

		if (isFinished) {
			emptyAllPits();
			return null;
		}

		return board.getPits();
	}

	/**
	 * Empty all pits.
	 */
	public void emptyAllPits() {
		// Empty P1 pits
		for (int i = P1_LOWER; i <= P1_UPPER; i++) {
			int stonesToAdd = board.getNumberOfStones(i);
			if (stonesToAdd > 0) {
				board.setNumberOfStones(i, 0);
				board.addToStoneCount(P1_LARGE_POSITION, stonesToAdd);
			}
		}

		// Empty P2 pits
		for (int i = P2_LOWER; i <= P2_UPPER; i++) {
			int stonesToAdd = board.getNumberOfStones(i);
			if (stonesToAdd > 0) {
				board.setNumberOfStones(i, 0);
				board.addToStoneCount(P2_LARGE_POSITION, stonesToAdd);
			}
		}
	}

	/**
	 * Check if the game is finished. If either players pits are empty, trigger the
	 * end of the game.
	 *
	 * @param board
	 *            the board
	 * @return true, if successful
	 */
	private boolean checkFinished(Board board) {

		boolean isFinished = true;

		int lower;
		int upper;

		if (currentActivePlayer == player1) {
			lower = P1_LOWER;
			upper = P1_UPPER;
		} else {
			lower = P2_LOWER;
			upper = P2_UPPER;
		}

		// Check Player houses for stones
		for (int i = lower; i <= upper; i++) {
			if (board.getNumberOfStones(i) > 0) {
				isFinished = false;
				break;
			}
		}

		return isFinished;
	}

	/**
	 * Check if the last rock placed by player1 was into an empty cell. if so,
	 * takeover the rocks from the opposite row.
	 * 
	 * @param index
	 *            the index
	 * @param lower
	 *            the lower
	 * @param upper
	 *            the upper
	 * @param store
	 *            the store
	 */
	public void checkCapture(int index, int lower, int upper, int store) {
		// if last rock was placed in an empty pit, then capture the rocks
		// in the opposite players pit and claim them
		if (index >= lower && index <= upper && board.getNumberOfStones(index) == 1) {
			int indexAcross = (index + P2_LOWER) % TOTAL_PITS;
			int amountAcross = board.getNumberOfStones(indexAcross);

			if (amountAcross > 0) {
				// Empty own pits and opponents pit on the opposite side
				board.setNumberOfStones(indexAcross, 0);
				board.setNumberOfStones(index, 0);

				// Add to current players large pit
				board.addToStoneCount(store, (amountAcross + 1));
			}
		}
	}

	/**
	 * Function to place the stones one by one until empty
	 *
	 * @param pitPosition
	 *            the pit position to start from
	 * @return the integer
	 */
	// returns last position of last placed stone
	private Integer placeStones(int pitPosition) {
		int stoneCount = board.getNumberOfStones(pitPosition);
		board.setNumberOfStones(pitPosition, 0);
		pitPosition++;
		// skip player2's large pit
		if (currentActivePlayer == player1) {
			while (stoneCount != 0) {
				if (pitPosition > P2_UPPER) {
					pitPosition = P1_LOWER;
				}

				// Add stone for every pit
				board.addToStoneCount(pitPosition, 1);

				// Next index, lower amount
				pitPosition++;
				stoneCount--;
			}

			pitPosition--; // Save last position checked
			return pitPosition;
		} else {
			while (stoneCount != 0) {
				if (pitPosition > P2_LARGE_POSITION) {
					pitPosition = P1_LOWER;
				} else if (pitPosition == P1_LARGE_POSITION) {
					// Skip P1 store
					pitPosition = P2_LOWER;
				}

				// Add stone for every pit
				board.addToStoneCount(pitPosition, 1);

				// Next index, lower amount
				pitPosition++;
				stoneCount--;
			}

			pitPosition--; // Save last position checked
			return pitPosition;
		}
	}

}
