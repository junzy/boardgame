package com.mkyong.controller;

import java.util.HashMap;
import java.util.Map;

import com.mkyong.service.BoardGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class WelcomeController {
	
	@Autowired
	private BoardGameService boardGameService;
	

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/newGame")
	public String newGame(@RequestParam("player1") String player1,@RequestParam("player2") String player2) {
		Map<String, Object> model =  new HashMap<>();
		model.put("message", "new game created");
		boardGameService.newGame(player1, player2);
		System.out.println("creating new game with: " +  player1 + " : " + player2);
		return "game";
	}
	
	@RequestMapping("/executeMove")
	public String executeMove(@RequestParam Map<String,String> params, Map<String, Object> model) {
		model.put("message", "new game created");
		boardGameService.executeMove(params.get("id"));
		return "game";
	}

}