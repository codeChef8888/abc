package com.bitmosys.abc.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitmosys.abc.dto.ExchangeFormDTO;
import com.bitmosys.abc.dto.UserCoinDTO;
import com.bitmosys.abc.dto.UsersCoinListDTO;
import com.bitmosys.abc.model.BuyResponse;
import com.bitmosys.abc.model.CoinAmount;
import com.bitmosys.abc.model.Response;
import com.bitmosys.abc.repository.CoinAmountRepository;
import com.bitmosys.abc.service.CoinAmountService;

@Service
public class CoinAmountServiceImpl implements CoinAmountService {

	@Autowired
	CoinAmountRepository coinAmountRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UsersCoinListDTO> getAllUserCoins(Long userId) {

		return coinAmountRepository.getById(userId).stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
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

		Optional<CoinAmount> coin = coinAmountRepository.findIfCoinExits(userId, toCoin);

		if (coin.isPresent()) {

			if (availableCoinAmount.compareTo(BigDecimal.valueOf(0)) != 0) {

				if (fromCoin != toCoin) {
					if (exchangeCoinAmount.compareTo(availableCoinAmount) == 1) {

						response = exchangeFailureNotEnoughCoins(exchangeFormDTO);
						return response;

					} else {
						response = exchangeSuccess(userId, exchangeFormDTO);
						return response;

					}
				} else {
					response = exchangeFailureSameCoin(exchangeFormDTO);
					return response;

				}

			} else {
				response = exchangeFailureNoCoin(exchangeFormDTO);
				return response;
			}

		} else {

			if (availableCoinAmount.compareTo(BigDecimal.valueOf(0)) != 0) {

				if (fromCoin != toCoin) {
					if (exchangeCoinAmount.compareTo(availableCoinAmount) == 1) {

						response = exchangeFailureNotEnoughCoins(exchangeFormDTO);
						return response;

					} else {
						response = exchangeSuccessEntry(userId, exchangeFormDTO);
						return response;

					}
				} else {
					response = exchangeFailureSameCoin(exchangeFormDTO);
					return response;

				}

			} else {
				response = exchangeFailureNoCoin(exchangeFormDTO);
				return response;
			}
		}

	}

	@Override
	public int getTotalCoinTypes(Long userId) {
		return coinAmountRepository.getTotalCoinTypes(userId);
	}

	@Override
	public BuyResponse buyCoin(Long userId, UserCoinDTO coinDTO) {

		Long fromCoin = coinDTO.getFromCoin();
		BigDecimal amount = coinDTO.getCoinAmount();
		Optional<CoinAmount> coin = coinAmountRepository.findIfCoinExits(userId, fromCoin);

		if (coin.isPresent()) {
			coinAmountRepository.addAmount(userId, amount, fromCoin);
			return new BuyResponse("success", "Coin is bought", coinDTO);
		} else {
			coinAmountRepository.addNewCoinAmount(userId, fromCoin, amount);
			return new BuyResponse("success", "Coin is bought", coinDTO);
		}

	}

	public Response exchangeFailureNotEnoughCoins(ExchangeFormDTO exchangeFormDTO) {
		Response response = new Response();
		BigDecimal availableCoinAmount = exchangeFormDTO.getAvailableCoins();
		response.setStatus("failure");
		response.setMessage("Transaction Failed. Coin Amount for Exchange must be less or equal to : [ "
				+ availableCoinAmount + " ]");
		response.setExchangeFormDto(exchangeFormDTO);
		return response;

	}

	public Response exchangeSuccess(Long userId, ExchangeFormDTO exchangeFormDTO) {
		Long fromCoin = exchangeFormDTO.getFromCoin();
		Long toCoin = exchangeFormDTO.getToCoin();
		BigDecimal exchangeCoinAmount = exchangeFormDTO.getCoinAmount();
		Response response = new Response();
		response.setStatus("success");
		response.setMessage("Coins Exchanged successfully!!!");
		response.setExchangeFormDto(exchangeFormDTO);
		coinAmountRepository.deductAmount(userId, exchangeCoinAmount, fromCoin);
		coinAmountRepository.addAmount(userId, exchangeCoinAmount, toCoin);
		return response;
	}

	public Response exchangeFailureSameCoin(ExchangeFormDTO exchangeFormDTO) {
		Response response = new Response();
		response.setStatus("failure");
		response.setMessage("You are exchanging the same coin types!!! Please Check!!!");
		response.setExchangeFormDto(exchangeFormDTO);
		return response;
	}

	public Response exchangeFailureNoCoin(ExchangeFormDTO exchangeFormDTO) {
		Response response = new Response();
		response.setStatus("failure");
		response.setMessage("You don't have any coins for exchanging");
		response.setExchangeFormDto(exchangeFormDTO);
		return response;
	}

	public Response exchangeSuccessEntry(Long userId, ExchangeFormDTO exchangeFormDTO) {
		Response response = new Response();
		response.setStatus("success");
		response.setMessage("Coins Exchanged successfully!!!");
		response.setExchangeFormDto(exchangeFormDTO);
		Long fromCoin = exchangeFormDTO.getFromCoin();
		Long toCoin = exchangeFormDTO.getToCoin();
		BigDecimal coinAmount = exchangeFormDTO.getCoinAmount();
		coinAmountRepository.deductAmount(userId, coinAmount, fromCoin);
		coinAmountRepository.addNewCoinAmount(userId, toCoin, coinAmount);
		return response;
	}

	private UsersCoinListDTO convertEntityToDto(CoinAmount userCoins) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UsersCoinListDTO coinListDTO = new UsersCoinListDTO();
		coinListDTO = modelMapper.map(userCoins, UsersCoinListDTO.class);
		return coinListDTO;
	}

//	private CoinAmount convertEntityToDto(UsersCoinListDTO coinListDTO) {
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//		
//		CoinAmount userCoins= new CoinAmount();
//		userCoins = modelMapper.map(coinListDTO, CoinAmount.class);
//		return userCoins;
//	}

}
