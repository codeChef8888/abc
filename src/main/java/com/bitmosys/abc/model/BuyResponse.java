package com.bitmosys.abc.model;

import com.bitmosys.abc.dto.UserCoinDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyResponse {

	private String status;
	private String message;
	private UserCoinDTO coinDTO;
	
}
