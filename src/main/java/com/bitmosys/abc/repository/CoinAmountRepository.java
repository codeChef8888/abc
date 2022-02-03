package com.bitmosys.abc.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitmosys.abc.model.CoinAmount;
import com.bitmosys.abc.model.CoinAmountKey;

@Repository
public interface CoinAmountRepository extends JpaRepository<CoinAmount, CoinAmountKey> {

	
	@Query(value = "select * from coin_amount c where c.user_id = :userId ", nativeQuery = true)
	public List<CoinAmount> getById(@Param("userId") Long userId); 
	
	@Query(value = "select * from coin_amount c where c.user_id = :userId and c.coin_id = :coinId ", nativeQuery = true)
	public Optional<CoinAmount> findIfCoinExits(@Param("userId") Long userId, @Param("coinId") Long coinId); 
	
	@Query(value = "select amount from coin_amount c where c.coin_id = :coinId ", nativeQuery = true)
	public BigDecimal getAmount(@Param("coinId") Long coinId);
	
	@Modifying
	@Query(value = "update coin_amount c set c.amount = c.amount - (:amt)  where c.user_id = :userId and c.coin_id = :coinId ", nativeQuery = true)
	public void deductAmount(@Param("userId") Long userId, @Param("amt") BigDecimal amount, @Param("coinId") Long coinId);
	
	@Modifying
	@Query(value = "update coin_amount c set c.amount = c.amount + (:amt)  where c.user_id = :userId and c.coin_id = :coinId ", nativeQuery = true)
	public void addAmount(@Param("userId") Long userId, @Param("amt") BigDecimal amount, @Param("coinId") Long coinId);
	
	@Query(value =" select count(*) from coin_amount c where c.user_id = :userId ", nativeQuery = true)
	public int getTotalCoinTypes(@Param("userId") Long userId);
	
	@Modifying
    @Query(value = "insert into coin_amount (coin_id, user_id, amount) value (:coinId, :userId, :amt)", nativeQuery = true)
    @Transactional
	public void addNewCoinAmount(@Param("userId") Long userId, @Param("coinId") Long coinId,@Param("amt") BigDecimal amount);
	

}
