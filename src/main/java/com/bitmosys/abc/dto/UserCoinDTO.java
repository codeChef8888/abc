package com.bitmosys.abc.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoinDTO {

	private Long fromCoin;
	private BigDecimal coinAmount;
}
