package com.myBank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

	@RequestMapping("/movie1")
	public String select() {
		return "imse/movie1";
	}
	
	@RequestMapping("/movie2")
	public String selectEL() {
		return "imse/movie2";
	}
}
