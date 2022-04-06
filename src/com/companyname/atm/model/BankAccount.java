package com.companyname.atm.model;

import java.math.BigDecimal;

import com.companyname.atm.datastore.BankAccountDataStore;

/**
 * Represents a Bank Account
 * @author Michael McGill
 * 
 */
public class BankAccount {
	private String accountNumber;
	private int pinCode;
	// BigDecimal is better for financial values in real world use.
	private BigDecimal balance;
	private BigDecimal overDraft;
	private BigDecimal withdrawableAmount;

	public BankAccount(String accountNumber, int pinCode, BigDecimal balance, BigDecimal overDraft) {
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
		this.balance = balance;
		this.overDraft = overDraft;
		this.withdrawableAmount = balance.add(overDraft);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
		// Withdrawable amount should be the overdraft limit plus balance
		setWithdrawableAmount();
	}

	public BigDecimal getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(BigDecimal overDraft) {
		this.overDraft = overDraft;
	}

	public BigDecimal getWithdrawableAmount() {
		return withdrawableAmount;
	}

	public void setWithdrawableAmount(BigDecimal withdrawableAmount) {
		this.withdrawableAmount = withdrawableAmount;
	}

	/**
	 * Overloaded method to update the Withdrawable Amount. This should be invoked
	 * when there is balance Change.
	 * 
	 */
	private void setWithdrawableAmount() {
		this.withdrawableAmount = balance.add(overDraft);
	}

}