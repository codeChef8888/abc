package com.bitmosys.abc.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersCoinListDTO {
	private Long userId;
	private BigDecimal userCoinAmount;
	private Long coinId;
	private String coinPhoto;
	private String coinName;

}
