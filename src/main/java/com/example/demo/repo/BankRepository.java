package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface BankRepository extends JpaRepository<User,Integer> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update User set balance = ?1 where user_id=?2")
	public void saveBalanceByAcctID(int user_id);
    
	
}
