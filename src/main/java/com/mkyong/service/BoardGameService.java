package com.mkyong.service;

import com.mkyong.model.BoardGame;
import com.mkyong.model.Player;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BoardGameService {

	private BoardGame game;

	public static final int TOTAL_PITS = 14;
	public static final int P1_LOWER = 0;
	public static final int P1_UPPER = 5;
	public static final int P2_LOWER = 7;
	public static final int P2_UPPER = 12;
	public static final int P1_LARGE_POSITION = 6;
	public static final int P2_LARGE_POSITION = 13;
	public static final int NORMAL_PIT_STARTING_COUNT = 6;
	public static final int LARGE_PIT_STARTING_COUNT = 0;

	static final Logger logger = Logger.getLogger(BoardGameService.class);
	
	

	// creates a new instance of the game
	public void newGame(String playerName1, String playerName2) {
		System.out.println("attempting to start new game");
		// if game isn't already running
		if (game == null) {
			this.game = new BoardGame(playerName1, playerName2);
		}
		// Instantiate pits for both players
		game.createPits();
		
		return;	
	}

	public void executeMove(String pitId) {
		if (pitId != null) {
			game.makeMove(Integer.parseInt(pitId));
		}
		
	}

}
