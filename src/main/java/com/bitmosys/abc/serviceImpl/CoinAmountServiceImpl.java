package com.bitmosys.abc.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.model.Response;
import com.bitmosys.abc.repository.CoinAmountRepository;
import com.bitmosys.abc.service.CoinAmountService;

@Service
public class CoinAmountServiceImpl implements CoinAmountService {

	@Autowired
	CoinAmountRepository coinAmountRepository;

	@Override
	public Object getAllUserCoins(Long userId) {

		return coinAmountRepository.getById(userId);
	}

	@Override
	public Response exchangeCoins(Long userId, Long fromCoin, Long toCoin, BigDecimal coinAmount) {
		BigDecimal bint = coinAmountRepository.getAmount(fromCoin);
		System.out.println("Check this amount" + bint);
		Response response = new Response();

		if (coinAmount.compareTo(bint) == 1) {
			response.setStatus("failure");
			response.setMessage("the exchange amount exceeded the available coin amount!!!");
			System.out.println("cannot exchange");
		} else {
			response.setStatus("success");
			response.setMessage("exchange successfull!!!");
			coinAmountRepository.deductAmount(coinAmount, fromCoin);
			coinAmountRepository.addAmount(coinAmount, toCoin);

		}
		return response;

	}

	@Override
	public Response exchangeCoin(ExchangeFormDTO exchangeFormDTO) {
		Long fromCoin = exchangeFormDTO.getFromCoin();
		Long toCoin = exchangeFormDTO.getToCoin();
		BigDecimal coinAmount = exchangeFormDTO.getCoinAmount();

		BigDecimal bint = coinAmountRepository.getAmount(fromCoin);
		System.out.println("Check this amount" + bint);
		Response response = new Response();

		if (fromCoin != toCoin) {
			if (coinAmount.compareTo(bint) == 1) {
				response.setStatus("failure");
				response.setMessage("the exchange amount exceeded the available coin amount!!!");
				response.setExchangeFormDto(exchangeFormDTO);
				System.out.println("cannot exchange");
			} else {
				response.setStatus("success");
				response.setMessage("exchange successfull!!!");
				response.setExchangeFormDto(exchangeFormDTO);
				coinAmountRepository.deductAmount(coinAmount, fromCoin);
				coinAmountRepository.addAmount(coinAmount, toCoin);

			}
		} else {
			response.setStatus("failure");
			response.setMessage("You are exchanging the same coin types!!! Please Check!!!");
			response.setExchangeFormDto(exchangeFormDTO);
		}

		return response;

	}

	@Override
	public int getTotalCoinTypes() {
		return coinAmountRepository.getTotalCoinTypes();
	}

}
