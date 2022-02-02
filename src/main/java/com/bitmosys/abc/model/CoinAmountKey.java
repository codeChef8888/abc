package com.bitmosys.abc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinAmountKey implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "coin_id")
	Long coinId;
	
	@Column(name = "user_id")
	Long userId;

}
