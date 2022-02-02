package com.bitmosys.abc.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.bitmosys.abc.dto.ExchangeFormDTO;

import com.bitmosys.abc.serviceImpl.CoinAmountServiceImpl;

@CrossOrigin
@RestController
public class ExchangeModalController {

	@Autowired
	CoinAmountServiceImpl coinAmountServiceImpl;

	@Transactional
	@RequestMapping(method = { RequestMethod.POST,
			RequestMethod.PUT }, value = "/usercoins/{userId}/exchange", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> exchange(@RequestBody ExchangeFormDTO exchangeFormDTO,
			@PathVariable(value = "userId") Long userId) {

		return new ResponseEntity<>(coinAmountServiceImpl.exchangeCoin(userId, exchangeFormDTO), HttpStatus.OK);

	}

}
