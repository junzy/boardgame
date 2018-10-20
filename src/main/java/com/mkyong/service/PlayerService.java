package com.mkyong.service;

import com.mkyong.model.Player;

import org.springframework.stereotype.Service;


@Service
public class PlayerService {

	
	public Player createPlayer(String playerName) {
		return new Player(playerName);
	}
}
