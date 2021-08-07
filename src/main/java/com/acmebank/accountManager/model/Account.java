package com.acmebank.accountManager.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String accountNumber;
	
	@NotNull
	@Min(value=0, message="account balance must be positive")
	private  BigDecimal balance;
	
//	constructor methods
	public Account() {
		
	}
	public Account(String accountNumber, @NotNull @Min(value = 0, message = "account balance must be positive") BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
//	setter, getter
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	
//	toString
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	
	
}
