package com.bitmosys.abc.service;

import java.math.BigDecimal;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.model.Response;

public interface CoinAmountService {

	public Object getAllUserCoins(Long userId);
	
	public int getTotalCoinTypes();
	
	public Response exchangeCoins(Long userId,Long fromCoin, Long toCoin, BigDecimal coinAmount);
	
	public Response exchangeCoin(ExchangeFormDTO exchangeFormDTO);
}
