package com.acmebank.accountManager.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TransferRequest {
	@NotNull
	private String fromAccountNumber;
	
	@NotNull
	private String toAccountNumber;
	
	@NotNull
	@Min(value=0, message="Transfer amount can not be less than zero")
	private BigDecimal amount;

	@JsonCreator
	public TransferRequest(@NotNull String fromAccountNumber, @NotNull String toAccountNumber,
			@NotNull @Min(value = 0, message = "Transfer amount can not be less than zero") BigDecimal amount) {
		super();
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
	}
	
	@JsonCreator
	public TransferRequest() {
		super();
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
	


}
