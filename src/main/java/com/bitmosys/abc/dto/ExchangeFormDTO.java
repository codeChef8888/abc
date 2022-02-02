package com.bitmosys.abc.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeFormDTO {
	

	private Long fromCoin;
	private Long toCoin;
	private BigDecimal availableCoins;
	private BigDecimal coinAmount;

}
