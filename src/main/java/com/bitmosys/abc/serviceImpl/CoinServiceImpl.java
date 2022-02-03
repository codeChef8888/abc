package com.bitmosys.abc.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitmosys.abc.dto.CoinDTO;
import com.bitmosys.abc.model.Coin;
import com.bitmosys.abc.repository.CoinRepository;
import com.bitmosys.abc.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	CoinRepository coinRepository;
	
	@Autowired
	ModelMapper coinMapper;
	
	@Override
	public List<CoinDTO> getAllCoins() {
		return coinRepository.findAll().stream().map(this::convertEntityToDto).
				collect(Collectors.toList());
	}
	
	private CoinDTO convertEntityToDto(Coin coin) {
		
		coinMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CoinDTO coinDto = new CoinDTO();
		coinDto = coinMapper.map(coin, CoinDTO.class);
		return coinDto;
	}

}
