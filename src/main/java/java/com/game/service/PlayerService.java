package java.com.game.service;

import org.springframework.stereotype.Service;

import java.com.game.model.Player;

@Service
public class PlayerService {

	
	public Player createPlayer(String playerName) {
		return new Player(playerName);
	}
}
