package com.mkyong.model;

import com.mkyong.service.BoardGameService;

public class BoardGame {
	private Player player1;
	private Player player2;
	private Board board;
	private Player currentActivePlayer;

	public BoardGame(String playerName1, String playerName2) {
		this.player1 = new Player(playerName1);
		this.player2 = new Player(playerName2);
		this.board = new Board();
	}

	public Player getCurrentActivePlayer() {
		return this.currentActivePlayer;
	}

	public void setCurrentActivePlayer(Player currentPlayer) {
		this.currentActivePlayer = currentPlayer;
	}

	// instantiates the list of pits for both players
	public void createPits() {
		for (int i = BoardGameService.P1_LOWER; i < BoardGameService.P1_UPPER; i++) {
			Pit pit = new Pit(PitType.NORMAL, BoardGameService.NORMAL_PIT_STARTING_COUNT, i);
			board.pits.add(pit);
		}
		// Creates the large Pit for Player1
		Pit largePit = new Pit(PitType.BIG, BoardGameService.LARGE_PIT_STARTING_COUNT,
				BoardGameService.P1_LARGE_POSITION);
		board.pits.add(largePit);

		for (int i = BoardGameService.P2_LOWER; i < BoardGameService.P2_UPPER; i++) {
			Pit pit = new Pit(PitType.NORMAL, BoardGameService.NORMAL_PIT_STARTING_COUNT, i);
			board.pits.add(pit);
		}
		// Creates the large Pit for Player2
		largePit = new Pit(PitType.BIG, BoardGameService.LARGE_PIT_STARTING_COUNT, BoardGameService.P2_LARGE_POSITION);
		board.pits.add(largePit);

	}

	public void toggleActivePlayer() {
		if (currentActivePlayer == player1) {
			this.currentActivePlayer = player2;
		} else {
			this.currentActivePlayer = player1;
		}
	}

	public void makeMove(int pitPosition) {

		// Check count of stones in the given pit
		int stoneCount = board.getNumberOfStones(pitPosition);

		// check which players turn is it currently
		if (currentActivePlayer == player1) {
			// Validate pit position is >= 1 and <= 6
			if (pitPosition >= BoardGameService.P1_LOWER && pitPosition <= BoardGameService.P1_UPPER
					&& stoneCount > 0) {
				// Do Move
				int index = placeStones(pitPosition);
//
//				// Check capture
//				checkCapture(board, index, BoardGameService.P1_LOWER, BoardGameService.P1_UPPER,
//						BoardGameService.P1_LARGE_POSITION);
//
//				// Check turn
//				if (index != P1_STORE) {
//					// Switch turn
//					gameService.switchTurn(game.getSecondPlayer(), game.getId());
//				}
//
//				// Check game finished
//				boolean isFinished = checkFinished(game, board);
//
//				if (isFinished) {
//					emptyAllPits(board);
//					gameService.updateGameState(game, GameState.FINISHED);
//				}
			}
		}
//		return;
	}

	//returns last position of last placed stone
	private Integer placeStones(int pitPosition) {
		int stoneCount = board.getNumberOfStones(pitPosition);
		board.setNumberOfStones(pitPosition, 0);

		// skip player2's large pit
		if (currentActivePlayer == player1) {
			while (stoneCount != 0) {
	            if(pitPosition > BoardGameService.P2_UPPER) {
	                pitPosition = BoardGameService.P1_LOWER;
	            }

	            // Add stone for every pit
	            board.incrementStoneCount(pitPosition);

	            // Next index, lower amount
	            pitPosition++;
	            stoneCount--;
	        }

	        pitPosition--; // Save last position checked
	        return pitPosition;
		} else {
			while (stoneCount != 0) {
	            if(pitPosition > BoardGameService.P2_LARGE_POSITION) {
	                pitPosition = BoardGameService.P1_LOWER;
	            }
	            else if (pitPosition == BoardGameService.P1_LARGE_POSITION) {
	                // Skip P1 store
	                pitPosition = BoardGameService.P2_LOWER;
	            }

	            // Add stone for every pit
	            board.incrementStoneCount(pitPosition);

	            // Next index, lower amount
	            pitPosition++;
	            stoneCount--;
	        }

	        pitPosition--; // Save last position checked
	        return pitPosition;	
		}
	}

}
