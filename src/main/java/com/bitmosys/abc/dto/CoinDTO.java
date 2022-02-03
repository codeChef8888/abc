package com.bitmosys.abc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinDTO {
	
	private Long coinId;
	private String coinName;
	private String photo;

}
