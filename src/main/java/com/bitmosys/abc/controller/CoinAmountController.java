package com.bitmosys.abc.controller;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.bitmosys.abc.serviceImpl.CoinAmountServiceImpl;

@CrossOrigin
@Controller
public class CoinAmountController {
	
	@Autowired
	CoinAmountServiceImpl coinAmountServiceImpl;
	
	@GetMapping("/usercoins/{userId}")
	public String getAll(@PathVariable(value = "userId") Long userId,Model model) {
		model.addAttribute("totalCoins", coinAmountServiceImpl.getTotalCoinTypes());
		model.addAttribute("listUserCoins",coinAmountServiceImpl.getAllUserCoins(userId));
		return "home";
	}
	
//	
//	@Transactional
//	@RequestMapping(method = { RequestMethod.POST}, value = "/usercoins/{userId}/exchange")
//	public String exchange(@PathVariable(value = "userId") Long userId,@RequestParam("fromCoin") Long fromCoin, @RequestParam("toCoin") Long toCoin,
//			@RequestParam("coinAmount") BigDecimal coinAmount) {
//		
//		System.out.println("Hellow howdy im great" + fromCoin + toCoin + coinAmount);
//		coinAmountServiceImpl.exchangeCoins(userId, fromCoin, toCoin, coinAmount);
//		return "redirect:/usercoins/1";
//		
//	}
//	
	
	

}
