package com.bitmosys.abc.controller;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.model.Response;
import com.bitmosys.abc.serviceImpl.CoinAmountServiceImpl;

@CrossOrigin
@RestController
public class ExchangeModalController {
	
	@Autowired
	CoinAmountServiceImpl coinAmountServiceImpl;
	
	@Transactional
	@RequestMapping( method = {RequestMethod.POST, RequestMethod.PUT },value = "/usercoins/{userId}/exchange",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> exchange(@RequestBody ExchangeFormDTO exchangeFormDTO,@PathVariable(value = "userId") Long userId){
		
		System.out.println("ma controller ma xu"+userId);
		
		

		return new ResponseEntity<>(coinAmountServiceImpl.exchangeCoin(exchangeFormDTO),HttpStatus.OK);
		
	}

}
