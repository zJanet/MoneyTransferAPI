package com.acmebank.accountManager.service;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmebank.accountManager.constant.ErrorCode;
import com.acmebank.accountManager.dto.TransferRequest;
import com.acmebank.accountManager.exception.AccountNotExistException;
import com.acmebank.accountManager.exception.OverDraftException;
import com.acmebank.accountManager.model.Account;
import com.acmebank.accountManager.repository.AccountRepository;


@Service
public class AccountsService {
	private static final Logger log = LoggerFactory.getLogger(AccountsService.class);
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Transactional
	public void transferBalances(TransferRequest request) {
		Account fromAccount = accountRepo.findByAccountNumber(request.getFromAccountNumber())
				.orElseThrow(() -> new AccountNotExistException("Account with id:" + request.getFromAccountNumber() + " does not exist.", ErrorCode.ACCOUNT_ERROR));
		
		Account toAccount = accountRepo.findByAccountNumber(request.getToAccountNumber())
				.orElseThrow(() -> new AccountNotExistException("Account with id:" + request.getToAccountNumber() + " does not exist.", ErrorCode.ACCOUNT_ERROR));
		
		if(fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
			throw new OverDraftException("Account with id:" + request.getFromAccountNumber() + " does not have enough balance to transfer.", ErrorCode.ACCOUNT_ERROR);
		}
		
		fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
//		int result = 1/0;
		toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
	}
	
	
}
