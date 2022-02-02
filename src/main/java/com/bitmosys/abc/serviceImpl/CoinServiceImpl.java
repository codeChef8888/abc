package com.bitmosys.abc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitmosys.abc.repository.CoinRepository;
import com.bitmosys.abc.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	CoinRepository coinRepository;
	
	@Override
	public Object getAllCoins() {
		return coinRepository.findAll();
	}

}
