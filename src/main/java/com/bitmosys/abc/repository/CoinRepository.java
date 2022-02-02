package com.bitmosys.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitmosys.abc.model.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

}
