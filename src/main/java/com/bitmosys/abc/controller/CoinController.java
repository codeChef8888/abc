package com.bitmosys.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bitmosys.abc.serviceImpl.CoinServiceImpl;

@Controller
public class CoinController {

	@Autowired
	CoinServiceImpl coinServiceImpl;
	
	@GetMapping(value ="/usercoins/{userId}/coins")
	public String getAllCoins(@PathVariable("userId") Long userId,Model model) {
		System.out.println("ya her buy coin ko userId" + userId);
		model.addAttribute("currentUserId", userId);
		model.addAttribute("coinsList", coinServiceImpl.getAllCoins());
		return "coins";
	}
}
