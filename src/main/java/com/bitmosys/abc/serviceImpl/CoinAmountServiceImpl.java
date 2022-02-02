package com.bitmosys.abc.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.dto.UserCoinDTO;
import com.bitmosys.abc.model.BuyResponse;
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

//	@Override
//	public Response exchangeCoins(Long userId, Long fromCoin, Long toCoin, BigDecimal coinAmount) {
//		BigDecimal bint = coinAmountRepository.getAmount(fromCoin);
//		System.out.println("Check this amount" + bint);
//		Response response = new Response();
//
//		if (coinAmount.compareTo(bint) == 1) {
//			response.setStatus("failure");
//			response.setMessage("the exchange amount exceeded the available coin amount!!!");
//			System.out.println("cannot exchange");
//		} else {
//			response.setStatus("success");
//			response.setMessage("exchange successfull!!!");
//			coinAmountRepository.deductAmount(userId,coinAmount, fromCoin);
//			coinAmountRepository.addAmount(userId,coinAmount, toCoin);
//
//		}
//		return response;
//
//	}

	@Override
	public Response exchangeCoin(Long userId, ExchangeFormDTO exchangeFormDTO) {

		Long fromCoin = exchangeFormDTO.getFromCoin();
		Long toCoin = exchangeFormDTO.getToCoin();
		BigDecimal availableCoinAmount = exchangeFormDTO.getAvailableCoins();
		BigDecimal exchangeCoinAmount = exchangeFormDTO.getCoinAmount();

//		BigDecimal bint = coinAmountRepository.getAmount(fromCoin);
//		System.out.println("Check this amount" + bint);

		Response response = new Response();

		if (availableCoinAmount.compareTo(BigDecimal.valueOf(0)) != 0) {

			if (fromCoin != toCoin) {
				if (exchangeCoinAmount.compareTo(availableCoinAmount) == 1) {

					response.setStatus("failure");
					response.setMessage("Transaction Failed. Coin Amount for Exchange must be less or equal to : [ " + availableCoinAmount + " ]" );
					response.setExchangeFormDto(exchangeFormDTO);

				} else {

					response.setStatus("success");
					response.setMessage("Coins Exchanged successfully!!!");
					response.setExchangeFormDto(exchangeFormDTO);
					coinAmountRepository.deductAmount(userId, exchangeCoinAmount, fromCoin);
					coinAmountRepository.addAmount(userId, exchangeCoinAmount, toCoin);

				}
			} else {
				response.setStatus("failure");
				response.setMessage("You are exchanging the same coin types!!! Please Check!!!");
				response.setExchangeFormDto(exchangeFormDTO);
			}

		} else {
			response.setStatus("failure");
			response.setMessage("You don't have any coins for exchanging");
			response.setExchangeFormDto(exchangeFormDTO);
		}

		return response;

	}

	@Override
	public int getTotalCoinTypes(Long userId) {
		return coinAmountRepository.getTotalCoinTypes(userId);
	}

	@Override
	public BuyResponse buyCoin(Long userId, UserCoinDTO coinDTO) {
		Long fromCoin= coinDTO.getFromCoin();
		BigDecimal amount = coinDTO.getCoinAmount();
		coinAmountRepository.addAmount(userId, amount, fromCoin);
		return new BuyResponse("success", "Coin is bought", coinDTO);
	}

}
