package com.bitmosys.abc.model;

import com.bitmosys.abc.dto.ExchangeFormDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	
	private String status;
	private String message;
    private ExchangeFormDTO exchangeFormDto;
}
