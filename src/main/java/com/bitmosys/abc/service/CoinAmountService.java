package com.bitmosys.abc.service;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.dto.UserCoinDTO;
import com.bitmosys.abc.model.BuyResponse;
import com.bitmosys.abc.model.Response;

public interface CoinAmountService {

	public Object getAllUserCoins(Long userId);
	
	public int getTotalCoinTypes(Long userId);
	
//	public Response exchangeCoins(Long userId,Long fromCoin, Long toCoin, BigDecimal coinAmount);
	
	public BuyResponse buyCoin(Long userId,UserCoinDTO coinDTO);
	
	public Response exchangeCoin(Long userId,ExchangeFormDTO exchangeFormDTO);
}
