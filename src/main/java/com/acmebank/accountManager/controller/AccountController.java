package com.acmebank.accountManager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acmebank.accountManager.dto.TransferRequest;
import com.acmebank.accountManager.dto.TransferResponse;
import com.acmebank.accountManager.exception.AccountNotExistException;
import com.acmebank.accountManager.exception.OverDraftException;
import com.acmebank.accountManager.model.Account;
import com.acmebank.accountManager.repository.AccountRepository;
import com.acmebank.accountManager.service.AccountsService;



@RestController
@RequestMapping("/account-manager")
public class AccountController {
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) String accountNum){
		try {
			List<Account> accountInfo = new ArrayList<Account>();
			
			//  request without accountNumber
			if (accountNum == null)
				accountRepo.findAll().forEach(accountInfo::add);
			
			//  request with one or more accountNumber
			else {
				//  change String to List
				String trimedAccountNum = accountNum.trim();
				Set<String> accountNumSet = new HashSet<String>(Arrays.asList(trimedAccountNum.split(",",0)));
				accountRepo.findByAccountNumberIn(accountNumSet).forEach(accountInfo::add);
				
			}
			
			if (accountInfo.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(accountInfo, HttpStatus.OK);
			
		}  catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/transfer")
	public ResponseEntity<TransferResponse> transferMoney(@RequestBody @Valid TransferRequest request) throws Exception{
		
		try {
			accountService.transferBalances(request);
			TransferResponse response = new TransferResponse();
			response.setSendingAccount(request.getFromAccountNumber());
			response.setReceivingAccount(request.getToAccountNumber());
			response.setAmount(request.getAmount());

			response.setStatus("SUCCESS");
			
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}catch (AccountNotExistException | OverDraftException e) {
			log.error("Fail to transfer balances, please check with system administrator.");
			throw e;
		}
		
	}
	
	
}
