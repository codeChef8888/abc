package com.bitmosys.abc.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coin_amount")
public class CoinAmount {
	
	@EmbeddedId
	CoinAmountKey id;
	
	@ManyToOne
	@MapsId("coinId")
	@JoinColumn(name = "coin_id")
	Coin coin;
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	
	BigDecimal amount;
	

}
