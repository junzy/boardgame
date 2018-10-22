package com.mkyong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.parser.JSONParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.Pit;
import com.mkyong.service.BoardGameService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

	@Autowired
	private BoardGameService boardGameService;
	
	static final Logger logger = Logger.getLogger(GameController.class);

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello and welcome to bol.com's boardgame";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/newGame")
	public String newGame(@RequestParam("player1") String player1, @RequestParam("player2") String player2) {
		Map<String, Object> model = new HashMap<>();
		model.put("message", "new game created");
		boardGameService.newGame(player1, player2);
		logger.info("creating new game with: " + player1 + " : " + player2);
		return "game";
	}

	@RequestMapping(value = "/executeMove", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity executeMove(@RequestParam Map<String, String> params, Map<String, Object> model)
			throws JsonProcessingException {
		List<Pit> gameState = boardGameService.executeMove(params.get("id"));
		// gameState is null if the game is over
		if (gameState == null) {
			model.put("message", "Game Over!");
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			String arrayToJson = objectMapper.writeValueAsString(gameState);
			model.put("gameState", arrayToJson);
			model.put("playerTurn", boardGameService.getActivePlayerName());
		}
		return new ResponseEntity<Object>(model, HttpStatus.OK);
	}

}