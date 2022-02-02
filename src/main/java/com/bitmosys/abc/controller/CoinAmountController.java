package com.bitmosys.abc.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.bitmosys.abc.serviceImpl.CoinAmountServiceImpl;

@CrossOrigin
@Controller
public class CoinAmountController {
	
	@Autowired
	CoinAmountServiceImpl coinAmountServiceImpl;
	
	@GetMapping("/usercoins/{userId}")
	public String getAll(@PathVariable(value = "userId") Long currentUserId,Model model) {
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("totalCoins", coinAmountServiceImpl.getTotalCoinTypes(currentUserId));
		model.addAttribute("listUserCoins",coinAmountServiceImpl.getAllUserCoins(currentUserId));
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
