package com.mkyong.controller;

import java.util.Map;

import com.mkyong.service.BoardGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String hello(Map<String, Object> model) {
		model.put("message", "new game created");
		boardGameService.newGame((String) model.get("player1"), (String) model.get("player2"));
		System.out.println("creating new game");
		return "game";
	}

}