package com.acmebank.accountManager.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import com.acmebank.accountManager.model.Account;


public interface AccountRepository extends JpaRepository<Account, Long>{
	List<Account> findByAccountNumberIn(Set<String> accountBumSet);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	Optional<Account> findByAccountNumber(String accountNum);
}
