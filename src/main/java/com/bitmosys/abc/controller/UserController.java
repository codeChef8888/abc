package com.bitmosys.abc.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



import com.bitmosys.abc.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public Object getUser() {
		return userRepository.getAllUserCoins();
	}
	
	@GetMapping("/demo")
	public String demo() {
		return "hello";
	}
}
