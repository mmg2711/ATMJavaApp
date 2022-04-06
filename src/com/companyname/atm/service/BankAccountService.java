package com.companyname.atm.service;

import java.math.BigDecimal;

import com.companyname.atm.model.BankAccount;

/**
 * This is a service interface for interacting with the
 * Bank Account Data Source pertaining to Account Credentials
 * @author Michael McGill
 *
 */

public interface BankAccountService {
	public boolean isAccountValid(String accountNumber) throws BankAccountServiceException;
	public boolean isPINValid(String accountNumber, int pinCode) throws BankAccountServiceException;
	public BankAccount getValidBankAccount(String accountNumber, int pinCode) throws BankAccountServiceException;
	public void updateAfterWithdrawal(BankAccount bankAccount, BigDecimal withdrawalAmount) throws BankAccountServiceException;

}
