package com.bitmosys.abc.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoinDTO {

	private Long userId;
	private String firstName;
	private String lastName;
	private Long coinId;
	private BigDecimal amount;
}
