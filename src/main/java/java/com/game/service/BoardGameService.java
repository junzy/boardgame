package java.com.game.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.com.game.model.BoardGame;
import java.com.game.model.Player;

@Service
public class BoardGameService {
	
	private BoardGame game;
	
//	@Autowired
//	public BoardGameService(BoardGame game) {
//		this.game = game;
//	}
//	final static Logger logger = Logger.getLogger(BoardGameService.class);


	// creates a new instance of the game
	public void newGame(String playerName1, String playerName2) {
		System.out.println("attempting to start new game");
		// if game isn't already running
		if (game == null) {
			Player player1 = new Player(playerName1);
			Player player2 = new Player(playerName2);
			this.game = new BoardGame(player1, player2);
		}
	}
	
	public void executeMove(Integer pitNumber) {
		
	}

}
