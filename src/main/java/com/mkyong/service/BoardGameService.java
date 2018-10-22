package com.mkyong.service;

import com.mkyong.model.BoardGame;
import com.mkyong.model.Pit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class BoardGameService.
 */
@Service
public class BoardGameService {

	/** The game. */
	private BoardGame game;

	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(BoardGameService.class);
	
	

	/**
	 * New game.
	 *
	 * @param playerName1 the player name 1
	 * @param playerName2 the player name 2
	 */
	// creates a new instance of the game
	public void newGame(String playerName1, String playerName2) {
		System.out.println("attempting to start new game");
		// if game isn't already running
		if (game == null) {
			this.game = new BoardGame(playerName1, playerName2);
			// Instantiate pits for both players
			this.game.createPits();
		}
		return;	
	}

	/**
	 * Execute move.
	 *
	 * @param pitId the pit id
	 * @return the list
	 */
	public List<Pit> executeMove(String pitId) {
			return game.makeMove(pitId);
	}
	
	/**
	 * Gets the active player name.
	 *
	 * @return the active player name
	 */
	public String getActivePlayerName() {
		return game.getCurrentActivePlayer().getName();
	}

}
