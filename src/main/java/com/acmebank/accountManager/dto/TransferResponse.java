package com.acmebank.accountManager.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferResponse {
	private String sendingAccount;
	private String receivingAccount;
	private BigDecimal amount;
	private String status;
	
	@JsonProperty("sending-account")
	public String getSendingAccount() {
		return sendingAccount;
	}
	public void setSendingAccount(String sendingAccount) {
		this.sendingAccount = sendingAccount;
	}
	
	@JsonProperty("receiving-account")
	public String getReceivingAccount() {
		return receivingAccount;
	}
	public void setReceivingAccount(String receivingAccount) {
		this.receivingAccount = receivingAccount;
	}
	
	@JsonProperty("transferred-amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@JsonProperty("transaction-status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
