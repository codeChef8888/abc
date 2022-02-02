package com.bitmosys.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bitmosys.abc.dto.UserCoinDTO;
import com.bitmosys.abc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    @Query(value = "select * from users u inner join coin_amount c on u.id=c.user_id", nativeQuery= true)
	public List<User> getAllUserCoins(); 
}
